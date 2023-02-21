package yeonghun.dto;

public class BoardUpdateDto {
	
	private int board_number ; // 글 번호
	private int user_number ; // 회원 고유번호
	private String board_title ; //글 제목
	private String board_content ; //글 내용
	
	// getter, setter, toString
	
	@Override
	public String toString() {
		return "BoardUpdateDto [board_number=" + board_number + ", user_number=" + user_number + ", board_title="
				+ board_title + ", board_content=" + board_content + "]";
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
	
}
