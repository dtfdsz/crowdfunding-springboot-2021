package com.xys.scw.webui.service.exp.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xys.scw.vo.resp.AppResponse;
import com.xys.scw.webui.service.TMemberServiceFeign;
import com.xys.scw.webui.vo.resp.UserAddressVo;
import com.xys.scw.webui.vo.resp.UserRespVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TMemberServiceFeignExceptionHandler implements TMemberServiceFeign {

	@Override
	public AppResponse<UserRespVo> login(String loginacct, String password) {
		AppResponse<UserRespVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【登录】失败");
		
		log.error("调用远程服务【登录】失败");
		
		return resp;
	}

	@Override
	public AppResponse<List<UserAddressVo>> address(String accessToken) {
		AppResponse<List<UserAddressVo>> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【根据用户查询地址】失败");
		
		log.error("调用远程服务【根据用户查询地址】失败");
		
		return resp;
	}

}
