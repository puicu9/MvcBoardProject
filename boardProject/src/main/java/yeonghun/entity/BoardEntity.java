package yeonghun.entity;

import java.sql.Timestamp;

public class BoardEntity {
	private int board_number ;
	private int user_number ;
	private String user_id ;
	private String user_name ;
	private String board_title ;
	private String board_content ;
	private Timestamp board_inputdate ;
	private String board_state ;
	private String board_remark ;
	
	
	// getter, setter, toString
	public int getBoard_number() {
		return board_number;
	}
	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}
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
	public String getUser_name(){
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name ;
	}
	
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Timestamp getBoard_inputdate() {
		return board_inputdate;
	}
	public void setBoard_inputdate(Timestamp board_inputdate) {
		this.board_inputdate = board_inputdate;
	}
	public String getBoard_state() {
		return board_state;
	}
	public void setBoard_state(String board_state) {
		this.board_state = board_state;
	}
	public String getBoard_remark() {
		return board_remark;
	}
	public void setBoard_remark(String board_remark) {
		this.board_remark = board_remark;
	}
	@Override
	public String toString() {
		return "BoardEntity [board_number=" + board_number + ", user_number=" + user_number + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_inputdate=" + board_inputdate + ", board_state=" + board_state + ", board_remark="
				+ board_remark + "]";
	}
	
	
	

}
