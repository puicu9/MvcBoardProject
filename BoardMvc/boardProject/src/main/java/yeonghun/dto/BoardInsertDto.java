package yeonghun.dto;


public class BoardInsertDto { // 게시판 등록 테이블

	private	int user_number; // 회원 고유번호
	private String board_title ;// 게시판 제목
	private String board_content ;// 게시판 내용
	
	
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
	@Override
	public String toString() {
		return "BoardInsertDto [user_number=" + user_number + ", board_title=" + board_title + ", board_content="
				+ board_content + "]";
	}
	
	
	
	
	

	
	


	


}
