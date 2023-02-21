package yeonghun.entity;

import java.sql.Timestamp;

public class UserEntity {

	private int user_number;// 회원 고유번호
	private String user_id;// 회원 아이디
	private String user_password;// 회원 비밀번호
	private String user_name;// 회원 이름
	private String user_email ; // 회원 이메일
	private String user_contact ; // 회원 연락처
	private String user_zipcode; // 회원 우편번호
	private String user_roadname ; // 회원 도로명 주소
	private Timestamp user_inputdate;// 회원 가입일자
	private String user_state;// 회원 계정 사용상태 'Y' or 'N'
	private String user_address; // 회원 상세주소
	private String user_remark;// 회원 비고
	
	//Getter, Setter, toString
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
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
	public Timestamp getUser_inputdate() {
		return user_inputdate;
	}
	public void setUser_inputdate(Timestamp user_inputdate) {
		this.user_inputdate = user_inputdate;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_remark() {
		return user_remark;
	}
	public void setUser_remark(String user_remark) {
		this.user_remark = user_remark;
	}
	
	@Override
	public String toString() {
		return "UserEntity [user_number=" + user_number + ", user_id=" + user_id + ", user_password=" + user_password
				+ ", user_name=" + user_name + ", user_email=" + user_email + ", user_contact=" + user_contact
				+ ", user_zipcode=" + user_zipcode + ", user_roadname=" + user_roadname + ", user_inputdate="
				+ user_inputdate + ", user_state=" + user_state + ", user_address=" + user_address
				+ ", user_remark=" + user_remark + "]";
	}
	
	
}
