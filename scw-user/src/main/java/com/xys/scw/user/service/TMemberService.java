package com.xys.scw.user.service;

import java.util.List;

import com.xys.scw.user.bean.TMember;
import com.xys.scw.user.bean.TMemberAddress;
import com.xys.scw.user.vo.req.UserRegistVo;
import com.xys.scw.user.vo.resp.UserRespVo;

public interface TMemberService {
	int saveTMember(UserRegistVo vo);

	UserRespVo getUserByLogin(String loginacct,String password);

	List<TMemberAddress> listAddress(Integer memberId);

	TMember getMebmerById(Integer id);
}
