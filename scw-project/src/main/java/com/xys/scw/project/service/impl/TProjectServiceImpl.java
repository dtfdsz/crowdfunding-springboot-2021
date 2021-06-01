package com.xys.scw.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import com.xys.scw.project.bean.TProject;
import com.xys.scw.project.bean.TProjectImages;
import com.xys.scw.project.bean.TProjectTag;
import com.xys.scw.project.bean.TProjectType;
import com.xys.scw.project.bean.TReturn;
import com.xys.scw.project.constant.ProjectConstant;
import com.xys.scw.project.mapper.TProjectImagesMapper;
import com.xys.scw.project.mapper.TProjectMapper;
import com.xys.scw.project.mapper.TProjectTagMapper;
import com.xys.scw.project.mapper.TProjectTypeMapper;
import com.xys.scw.project.mapper.TReturnMapper;
import com.xys.scw.project.service.TProjectService;
import com.xys.scw.project.util.AppDateUtils;
import com.xys.scw.project.vo.req.ProjectRedisStorageVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TProjectServiceImpl implements TProjectService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	TProjectMapper projectMapper;
	
	@Autowired
	TProjectImagesMapper projectImagesMapper;
	
	@Autowired
	TReturnMapper returnMapper;
	@Autowired
	TProjectTypeMapper projectTypeMapper;
	@Autowired
	TProjectTagMapper projectTagMapper;
	
	@Transactional
	@Override
	public void saveProject(String accessToken, String projectToken, byte code) {
		
		String memberId=stringRedisTemplate.opsForValue().get(accessToken);
		
		//1.从redis中获取bigVo数量
		String bigStr=stringRedisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken);
		ProjectRedisStorageVo bigVo=JSON.parseObject(bigStr,ProjectRedisStorageVo.class);
		
		//2.保存项目
		TProject project=new TProject();
		project.setName(bigVo.getName());
		project.setRemark(bigVo.getRemark());
		project.setMoney(bigVo.getMoney());
		project.setDay(bigVo.getDay());
		project.setStatus(code+"");
		project.setMemberid(Integer.parseInt(memberId));
		project.setCreatedate(AppDateUtils.getFormatTime());
		
		//主键回填
		projectMapper.insertSelective(project);
		
		Integer projectId=project.getId();
		log.debug("保存项目id={}",projectId);
		
		//3.保存图片
		TProjectImages projectImage=new TProjectImages();
		projectImage.setProjectid(projectId);
		projectImage.setImgurl(bigVo.getHeaderImage());
		projectImage.setImgtype((byte)0);
		projectImagesMapper.insertSelective(projectImage);
		
		List<String> detailsImage=bigVo.getDetailsImage();
		
		for(String imgPath:detailsImage) {
			TProjectImages pi=new TProjectImages();
			pi.setProjectid(projectId);
			pi.setImgurl(imgPath);
			pi.setImgtype((byte)1);
			projectImagesMapper.insertSelective(pi);
		}
		//保存回报
		List<TReturn> projectReturn=bigVo.getProjectReturns();
		for(TReturn retObj:projectReturn) {
			retObj.setProjectid(projectId);
			returnMapper.insertSelective(retObj);
			
		}
		
		//5.保存项目和分类关系
		List<Integer> typeids=bigVo.getTypeids();
		for(Integer typeId:typeids) {
			TProjectType pt=new TProjectType();
			pt.setProjectid(projectId);
			pt.setTypeid(typeId);
			projectTypeMapper.insertSelective(pt);
			
		}
		
		//6.保存项目和标签关系
		List<Integer> tagids=bigVo.getTagids();
		for(Integer tagId:tagids) {
			
			TProjectTag pt=new TProjectTag();
			pt.setProjectid(projectId);
			pt.setTagid(tagId);
			projectTagMapper.insertSelective(pt);
			
		}
		
		//9.清理redis
		stringRedisTemplate.delete(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken);
	}

	
}
