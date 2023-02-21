package yeonghun.dto;

public class UserLoginDto {
	private String user_id ; // 회원 아이디
	private String user_password ; // 회원 비밀번호
	
	//Getter, Setter, toString
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
	
	@Override
	public String toString() {
		return "UserLoginDto [user_id=" + user_id + ", user_password=" + user_password + "]";
	}
	


	
	

	
}
