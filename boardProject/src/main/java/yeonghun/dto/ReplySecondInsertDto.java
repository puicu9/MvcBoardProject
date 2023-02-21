package yeonghun.dto;

public class ReplySecondInsertDto {
	private int reply_second_reply_number;
	private String reply_second_content;
	private int reply_second_user_number;
	private int reply_second_board_number;
	
	// getter setter toString
	public int getReply_second_reply_number() {
		return reply_second_reply_number;
	}
	public void setReply_second_reply_number(int reply_second_reply_number) {
		this.reply_second_reply_number = reply_second_reply_number;
	}
	public String getReply_second_content() {
		return reply_second_content;
	}
	public void setReply_second_content(String reply_second_content) {
		this.reply_second_content = reply_second_content;
	}
	public int getReply_second_user_number() {
		return reply_second_user_number;
	}
	public void setReply_second_user_number(int reply_second_user_number) {
		this.reply_second_user_number = reply_second_user_number;
	}
	public int getReply_second_board_number() {
		return reply_second_board_number;
	}
	public void setReply_second_board_number(int reply_second_board_number) {
		this.reply_second_board_number = reply_second_board_number;
	}
	@Override
	public String toString() {
		return "ReplySecondInsertDto [reply_second_reply_number=" + reply_second_reply_number
				+ ", reply_second_content=" + reply_second_content + ", reply_second_user_number="
				+ reply_second_user_number + ", reply_second_board_number=" + reply_second_board_number + "]";
	}
	

	
	
}
