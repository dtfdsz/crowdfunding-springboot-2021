package com.xys.scw.user.vo.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserRegistVo implements Serializable{
		@ApiModelProperty("手机号")
		private String loginacct;
		@ApiModelProperty("密码")
		private String userpswd;
		@ApiModelProperty("邮箱")
		private String email;
		@ApiModelProperty("验证码")
		private String code;
		@ApiModelProperty("用户类型：0个人，1企业")
		private String usertype;
}
