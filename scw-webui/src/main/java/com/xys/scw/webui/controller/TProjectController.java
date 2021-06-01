package com.xys.scw.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xys.scw.vo.resp.AppResponse;
import com.xys.scw.webui.service.TMemberServiceFeign;
import com.xys.scw.webui.service.TProjectServiceFeign;
import com.xys.scw.webui.vo.resp.ProjectDetailVo;
import com.xys.scw.webui.vo.resp.ReturnPayConfirmVo;
import com.xys.scw.webui.vo.resp.TMemberAddress;
import com.xys.scw.webui.vo.resp.UserAddressVo;
import com.xys.scw.webui.vo.resp.UserRespVo;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class TProjectController {

	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@Autowired
	TMemberServiceFeign memberServiceFeign;
	
	@RequestMapping("/project/projectInfo")
	public String index(Integer id,Model model) {
		
		AppResponse<ProjectDetailVo> resp = projectServiceFeign.detailsInfo(id);
		
		ProjectDetailVo vo = resp.getData();
		
		model.addAttribute("projectDetailVo",vo);
		
		return "project/index";
	}
	
	@RequestMapping("/project/support/{projectId}/{returnId}")
	public String support(			
						@PathVariable("projectId") Integer projectId,
						@PathVariable("returnId") Integer returnId,
						Model model,
						HttpSession session)
	{
		AppResponse<ReturnPayConfirmVo> resp = projectServiceFeign.returnInfo(projectId, returnId);
		ReturnPayConfirmVo data=resp.getData();
		
		model.addAttribute("returnPayConfirmVo",data);
		session.setAttribute("returnPayConfirmVoSession", data);
		
			
		return "project/pay-step-1";
	}
	
	@RequestMapping("/project/confirm/order/{num}")
	public String confirmOrder(@PathVariable("num")Integer num,Model model,HttpSession session) {
		
		UserRespVo userRespVo=(UserRespVo)session.getAttribute("loginMember");
		if(userRespVo==null) {
			return "redirect:/login";
		}
		
		String accessToken=userRespVo.getAccessToken();
		
		
		AppResponse<List<UserAddressVo>> resp = memberServiceFeign.address(accessToken);
		
		List<UserAddressVo> data = resp.getData();
		
		
		model.addAttribute("memberAddressList",data);
		
		ReturnPayConfirmVo prcvo= (ReturnPayConfirmVo)session.getAttribute("returnPayConfirmVoSession");
		
		prcvo.setNum(num);
		prcvo.setTotalPrice(new BigDecimal(num*prcvo.getPrice()+prcvo.getFreight()));
		
		session.setAttribute("returnPayConfirmVoSession", prcvo);
		return "project/pay-step-2";
	}
	
	
	
	
}
