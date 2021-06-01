package com.xys.scw.user.exp;

import com.xys.scw.enums.UserExceptionEnum;

public class UserException extends RuntimeException {

	public UserException() {}
	
	public UserException(UserExceptionEnum enums) {
		super(enums.getMsg());
	}
}
