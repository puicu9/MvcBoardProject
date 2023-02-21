package yeonghun.repository;

import java.util.List;
import java.util.Map;

import yeonghun.dto.BoardInsertDto;
import yeonghun.dto.BoardUpdateDto;
import yeonghun.entity.BoardEntity;
import yeonghun.util.Criteria;

public interface BoardRepository {
	
	// 게시물 등록
	void insertBoard(BoardInsertDto boardInsertDto);

	// 등록된 게시물 개수 조회
	int selectBoardListCount();

	// 파일 업로드
	void insertFile(Map<String, Object> map);

	// 게시판 List 조회
	List<BoardEntity> selectBoardListOrderByNumberDesc(Criteria cri);

	// 게시판 상세 조회
	BoardEntity selectBoardDetailByNumber(int board_number);

	// 게시물 상세보기, 파일 업로드 리스트
	List<Map<String, Object>> selectFileByNumber(int board_number);

	// 게시물 상세보기, 파일 다운로드
	Map<String, Object> selectFileByNumberDownload(Map<String, Object> map);

	// board update, 글 제목, 글 내용 수정
	void updateBoardByDto(BoardUpdateDto dto);

	// file update, 사용 Y -> N 으로
	void updateFileByNumber(Map<String, Object> map);

	// file_order 구하기(파일 업로드 및 파일 수정 업로드에 활용
	int getFileOrderByNumber(int board_number);

	// delete Board Y -> N
	void updateBoardByNumber(int board_number);

	// delete File Y -> N
	void updateFileByNumber(int board_number);
	
	// paging list 전체 열 개수
	int listCount();

	



}
