package yeonghun.dto;

public class ReplyInsertDto {
	private int reply_number ; // 댓글 고유번호
	private int board_number ; // 게시판 고유번호
	private int user_number ; // 회원 고유번호
	private String reply_content ; // 댓글 내용
	private int group ; // 댓글 그룹번호
	private int order ; // 댓글 순서번호
	private int depth ; // 댓글 깊이
	
	//getter, setter, toString

	
	@Override
	public String toString() {
		return "ReplyInsertDto [reply_number=" + reply_number + ", board_number=" + board_number + ", user_number="
				+ user_number + ", reply_content=" + reply_content + ", group=" + group + ", order=" + order
				+ ", depth=" + depth + "]";
	}
	
	public int getReply_number() {
		return reply_number;
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
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	

}
