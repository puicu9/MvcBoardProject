package yeonghun.entity;

import java.sql.Timestamp;

public class FileEntity {
	
	private int file_number;
	private int user_number;
	private int board_number;
	private int file_order;
	private Timestamp file_inputdate;
	private String file_state;
	private String file_path;
	private String file_realname;
	private String file_savename;
	private int file_size ;
	
	// getter, setter, toString
	public int getFile_number() {
		return file_number;
	}
	public void setFile_number(int file_number) {
		this.file_number = file_number;
	}
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public int getBoard_number() {
		return board_number;
	}
	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}
	public int getFile_order() {
		return file_order;
	}
	public void setFile_order(int file_order) {
		this.file_order = file_order;
	}
	public Timestamp getFile_inputdate() {
		return file_inputdate;
	}
	public void setFile_inputdate(Timestamp file_inputdate) {
		this.file_inputdate = file_inputdate;
	}
	public String getFile_state() {
		return file_state;
	}
	public void setFile_state(String file_state) {
		this.file_state = file_state;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_realname() {
		return file_realname;
	}
	public void setFile_realname(String file_realname) {
		this.file_realname = file_realname;
	}
	public String getFile_savename() {
		return file_savename;
	}
	public void setFile_savename(String file_savename) {
		this.file_savename = file_savename;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	@Override
	public String toString() {
		return "FileEntity [file_number=" + file_number + ", user_number=" + user_number + ", board_number="
				+ board_number + ", file_order=" + file_order + ", file_inputdate=" + file_inputdate + ", file_state="
				+ file_state + ", file_path=" + file_path + ", file_realname=" + file_realname + ", file_savename="
				+ file_savename + ", file_size=" + file_size + "]";
	}
	
	

}
