package yeonghun.entity;

import java.sql.Timestamp;

public class ReplyEntity {
	private int reply_number ; // 댓글 고유번호
	private int board_number ; // 게시판 고유번호
	private int user_number ; // 회원 고유번호
	private String user_name ;// 회원 id
	private String reply_content ; // 댓글 내용
	private Timestamp reply_inputdate ; // 댓글 작성일자
	private int reply_group ; // 댓글 그룹번호
	private int reply_order ; // 댓글 순서번호
	private int reply_depth ; // 댓글 깊이
	private String reply_state ; // 댓글 사용상태
	private String reply_remark ; // 댓글 비고
	
	//getter, setter, toString
	
	
	public int getReply_number() {
		return reply_number;
	}
	@Override
	public String toString() {
		return "ReplyEntity [reply_number=" + reply_number + ", board_number=" + board_number + ", user_number="
				+ user_number + ", user_name=" + user_name + ", reply_content=" + reply_content + ", reply_inputdate="
				+ reply_inputdate + ", reply_group=" + reply_group + ", reply_order=" + reply_order + ", reply_depth="
				+ reply_depth + ", reply_state=" + reply_state + ", reply_remark=" + reply_remark + "]";
	}
	public void setReply_number(int reply_number) {
		this.reply_number = reply_number;
	}
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Timestamp getReply_inputdate() {
		return reply_inputdate;
	}
	public void setReply_inputdate(Timestamp reply_inputdate) {
		this.reply_inputdate = reply_inputdate;
	}
	public int getReply_group() {
		return reply_group;
	}
	public void setReply_group(int reply_group) {
		this.reply_group = reply_group;
	}
	public int getReply_order() {
		return reply_order;
	}
	public void setReply_order(int reply_order) {
		this.reply_order = reply_order;
	}
	public int getReply_depth() {
		return reply_depth;
	}
	public void setReply_depth(int reply_depth) {
		this.reply_depth = reply_depth;
	}
	public String getReply_state() {
		return reply_state;
	}
	public void setReply_state(String reply_state) {
		this.reply_state = reply_state;
	}
	public String getReply_remark() {
		return reply_remark;
	}
	public void setReply_remark(String reply_remark) {
		this.reply_remark = reply_remark;
	}
	
	
	
}
