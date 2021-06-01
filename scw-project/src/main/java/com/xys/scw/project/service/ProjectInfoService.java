package com.xys.scw.project.service;

import java.util.List;

import com.xys.scw.project.bean.TProject;
import com.xys.scw.project.bean.TProjectImages;
import com.xys.scw.project.bean.TReturn;
import com.xys.scw.project.bean.TTag;
import com.xys.scw.project.bean.TType;

public interface ProjectInfoService {

	List<TType> getProjectTypes();

	List<TTag> getAllProjectTags(); 

	TProject getProjectInfo(Integer projectId);

	List<TReturn> getProjectReturns(Integer projectId);

	List<TProject> getAllProjects();

	List<TProjectImages> getProjectImages(Integer id);

	TReturn getProjectReturnById(Integer retId);

}
