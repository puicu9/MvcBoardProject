package yeonghun.dto;

public class ReplyUpdateDto {
	private int reply_second_update_reply_number; // reply_number
	private int reply_second_update_board_number; // board_number
	private String reply_second_update_content; // reply_content
	
	// getter, setter, toString
	
	
	public int getReply_second_update_reply_number() {
		return reply_second_update_reply_number;
	}
	@Override
	public String toString() {
		return "ReplyUpdateDto [reply_second_update_reply_number=" + reply_second_update_reply_number
				+ ", reply_second_update_board_number=" + reply_second_update_board_number
				+ ", reply_second_update_content=" + reply_second_update_content + "]";
	}
	public void setReply_second_update_reply_number(int reply_second_update_reply_number) {
		this.reply_second_update_reply_number = reply_second_update_reply_number;
	}
	public int getReply_second_update_board_number() {
		return reply_second_update_board_number;
	}
	public void setReply_second_update_board_number(int reply_second_update_board_number) {
		this.reply_second_update_board_number = reply_second_update_board_number;
	}
	public String getReply_second_update_content() {
		return reply_second_update_content;
	}
	public void setReply_second_update_content(String reply_second_update_content) {
		this.reply_second_update_content = reply_second_update_content;
	}
	
	
	
}
