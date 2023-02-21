package yeonghun.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import yeonghun.dto.BoardInsertDto;
import yeonghun.dto.BoardUpdateDto;
import yeonghun.entity.BoardEntity;
import yeonghun.repository.BoardRepository;
import yeonghun.util.AES256Util;
import yeonghun.util.Criteria;
import yeonghun.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;

	@Resource(name = "uploadPath")
	String uploadPath ;
	
	@Resource(name="fileUtil")
	FileUtil fileUtil;
	
	@Autowired
	AES256Util aes; // 양방향 암호화
	
	// 게시글 등록(파일 업로드 포함)
	@Override
	public void writeBoard(BoardInsertDto boardInsertDto, MultipartFile[] file) throws Exception{
		
		// 게시글 등록
		boardRepository.insertBoard(boardInsertDto); //여기서 게시판 작성자 number, 게시판 글번호 정보 담기
		
		// repository 통해서 등록한 게시물 갯수 정보 가져오기
		int board_number = boardRepository.selectBoardListCount();// 최근 작성한 게시물 번호
		int user_number = boardInsertDto.getUser_number(); // 작성자 number
		
		int file_order = boardRepository.getFileOrderByNumber(board_number);// 가장 마지막 file_order 구하기
				
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("board_number", board_number);
		map.put("user_number", user_number);
		map.put("file_order", file_order);
				
		// 파일 업로드
		List<Map<String, Object>> fileList = fileUtil.insertFileInfo(map, file);
		
		for (int i=0; i<fileList.size(); i++) {
			boardRepository.insertFile(fileList.get(i));
		}
		
	}

	// 게시글 List 조회
	@Override
	public List<BoardEntity> getBoardList(Criteria cri) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		List<BoardEntity> boardList = boardRepository.selectBoardListOrderByNumberDesc(cri);
		
		// 암호화된것 다시 복호화
		for(int i=0; i<boardList.size();i++) {
			String user_name = boardList.get(i).getUser_name();
			boardList.get(i).setUser_name(aes.decrypt(user_name));
		}
		
		
		return boardList;
	}

	// 게시글 상세조회
	@Override
	public BoardEntity getBoardDetailList(int board_number) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
			BoardEntity boardEntity = boardRepository.selectBoardDetailByNumber(board_number);

			// 이름 복호화
			String user_name = boardEntity.getUser_name();
			boardEntity.setUser_name(aes.decrypt(user_name));
			
		return boardEntity;
	}

	// 게시물 상세보기, 파일 업로드 리스트
	@Override
	public List<Map<String, Object>> getFileList(int board_number) {
			
		return boardRepository.selectFileByNumber(board_number);
	}

	// 게시물 상세보기, 파일 다운로드
	@Override
	public Map<String, Object> getFileInfo(Map<String, Object> map) {
		
		return boardRepository.selectFileByNumberDownload(map);
	}

	// 게시물 수정하기, Post
	@Override
	public void updateBoard(BoardUpdateDto dto, String[] file_numbers, String[] file_names, MultipartFile[] mFile) throws Exception {

		// board 글제목, 글내용 수정
		boardRepository.updateBoardByDto(dto);
		
		try {
			// 파일 삭제
			List<Map<String, Object>> deleteFileList = fileUtil.updateFileInfo(dto, file_numbers, file_names);
			
			Map<String, Object> map = new HashMap<String, Object>() ;
			for(int i = 0; i < deleteFileList.size(); i++) {
				map = deleteFileList.get(i);
				
				if(map.get("file_state").equals("N")) {
					// 기존 파일을 사용 안 하겠다
					boardRepository.updateFileByNumber(map);
				}
			}
			
			// 파일 추가
			int board_number = dto.getBoard_number();
			int user_number = dto.getUser_number();
			int file_order = boardRepository.getFileOrderByNumber(board_number);// 가장 마지막 file_order 구하기
			
			Map<String, Integer> numberMap = new HashMap<String, Integer>();
			numberMap.put("board_number", board_number);
			numberMap.put("user_number", user_number);
			numberMap.put("file_order", file_order);
			
			List<Map<String, Object>> fileInsertList = fileUtil.insertFileInfo(numberMap, mFile);
			
			for (int i=0; i < fileInsertList.size(); i++) {
				boardRepository.insertFile(fileInsertList.get(i));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 게시물 삭제
	@Override
	public void deleteBoardAndFile(int board_number) {
		// BOARD Y -> N
		boardRepository.updateBoardByNumber(board_number);
		// FILE Y -> N
		boardRepository.updateFileByNumber(board_number);
		
	}

	// pageing list 전체 열 개수
	@Override
	public int listCount() {
		return boardRepository.listCount();
	}

	

}
