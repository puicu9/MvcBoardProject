package yeonghun.service;


import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


import yeonghun.dto.BoardInsertDto;
import yeonghun.dto.BoardUpdateDto;
import yeonghun.entity.BoardEntity;
import yeonghun.util.Criteria;

public interface BoardService {

	
	// 게시글 등록(파일 업로드 포함)
	void writeBoard(BoardInsertDto dto, MultipartFile[] file) throws Exception;

	// 게시글 List 조회
	List<BoardEntity> getBoardList(Criteria cri) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException ;

	// 게시글 상세 조회
	BoardEntity getBoardDetailList(int board_number) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException;

	// 게시물 상세보기, 파일 업로드 리스트
	List<Map<String, Object>> getFileList(int board_number);

	// 게시물 상세보기, 파일 다운로드
	Map<String, Object> getFileInfo(Map<String, Object> map);

	// 게시물 수정하기
	void updateBoard(BoardUpdateDto dto, String[] file_numbers, String[] file_names, MultipartFile[] mFile) throws Exception;

	// 게시물 삭제
	void deleteBoardAndFile(int board_number);
	
	// Board 전체 열 개수 count()
	int listCount();

	


	
	
}
