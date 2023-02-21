package yeonghun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import yeonghun.entity.UserEntity;

public class SuperController { // test 중
	
	public HttpSession session = null ;
	public UserEntity userEntity = null ;
	

	public void loginInformation(HttpServletRequest request) {
		this.session = request.getSession();
		this.userEntity = (UserEntity) this.session.getAttribute("userEntity");
	}
	
	
	

}
