package com.xys.scw.webui.service.exp.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xys.scw.vo.resp.AppResponse;
import com.xys.scw.webui.service.TProjectServiceFeign;
import com.xys.scw.webui.vo.resp.ProjectDetailVo;
import com.xys.scw.webui.vo.resp.ProjectVo;
import com.xys.scw.webui.vo.resp.ReturnPayConfirmVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<List<ProjectVo>> all() {
		AppResponse<List<ProjectVo>> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询首页热点项目】失败");
		log.error("调用远程服务【查询首页热点项目】失败");
		return resp;
	}

	@Override
	public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
		AppResponse<ProjectDetailVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询项目详情】失败");
		log.error("调用远程服务【查询项目详情】失败");
		return resp;
	}

	@Override
	public AppResponse<ReturnPayConfirmVo> returnInfo(Integer projectId, Integer returnId) {
		AppResponse<ReturnPayConfirmVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【确认回报信息】失败");
		log.error("调用远程服务【确认回报信息】失败");
		return resp;
	}

}
