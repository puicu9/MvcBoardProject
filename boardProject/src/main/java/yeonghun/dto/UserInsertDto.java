package yeonghun.dto;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;

import yeonghun.util.AES256Util;

public class UserInsertDto { // 회원 테이블

	private	String user_id; // 회원 아이디
	private String user_password ;// 회원 비밀번호
	private String user_name ;// 회원 이름
	private String user_email ; // 회원 이메일
	private String user_contact ; // 회원 연락처
	private String user_zipcode; // 회원 우편번호
	private String user_roadname ; // 회원 도로명 주소
	private String user_address; // 회원 상세주소
	
	
	//getter, setter, toString
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_contact() {
		return user_contact;
	}
	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}
	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}
	public String getUser_roadname() {
		return user_roadname;
	}
	public void setUser_roadname(String user_roadname) {
		this.user_roadname = user_roadname;
	}
	
	
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	@Override
	public String toString() {
		return "UserInsertDto [user_id=" + user_id + ", user_password=" + user_password + ", user_name=" + user_name
				+ ", user_email=" + user_email + ", user_contact=" + user_contact + ", user_zipcode=" + user_zipcode
				+ ", user_roadname=" + user_roadname + ", user_address=" + user_address + "]";
	}

}
