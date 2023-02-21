package yeonghun.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yeonghun.dto.BoardInsertDto;
import yeonghun.dto.BoardUpdateDto;
import yeonghun.entity.BoardEntity;
import yeonghun.util.Criteria;


@Repository
public class BoardRepositoryImpl implements BoardRepository{
	
	private final String FixPath = "yeonghun.repository.BoardRepository";
	
	@Autowired
	SqlSession sqlSession;

	// 게시물 등록
	@Override
	public void insertBoard(BoardInsertDto boardInsertDto) {
		sqlSession.insert(FixPath + ".insertBoard", boardInsertDto);
	}

	// 등록된 게시물 개수 조회
	@Override
	public int selectBoardListCount() {
		int count = sqlSession.selectOne(FixPath + ".selectBoardListCount") ;
		return count;
	}

	// 파일 업로드
	@Override
	public void insertFile(Map<String, Object> map) {
	 sqlSession.insert(FixPath + ".insertFile", map);
		
	}

	// 게시판 List 조회
	@Override
	public List<BoardEntity> selectBoardListOrderByNumberDesc(Criteria cri) {
		List<BoardEntity> boardList = sqlSession.selectList(FixPath + ".selectBoardListOrderByNumberDesc", cri);
		
		return boardList;
	}

	// 게시판 상세 조회
	@Override
	public BoardEntity selectBoardDetailByNumber(int board_number) {
		BoardEntity boardEntity = sqlSession.selectOne(FixPath + ".selectBoardDetailByNumber", board_number);
		
		return boardEntity;
	}

	// 게시물 상세보기, 파일 업로드 리스트
	@Override
	public List<Map<String, Object>> selectFileByNumber(int board_number) {
		return sqlSession.selectList(FixPath + ".selectFileByNumber", board_number);
	}

	// 게시물 상세보기, 파일 다운로드
	@Override
	public Map<String, Object> selectFileByNumberDownload(Map<String, Object> map) {

		return sqlSession.selectOne(FixPath + ".selectFileByNumberDownload", map) ;
	}

	// board update, 글 제목, 글 내용 수정
	@Override
	public void updateBoardByDto(BoardUpdateDto dto) {
		sqlSession.update(FixPath + ".updateBoardByDto", dto);
	}

	// file update, file_state Y -> N 으로
	@Override
	public void updateFileByNumber(Map<String, Object> map) {
		sqlSession.update(FixPath + ".updateFileByNumber", map);
		
	}
	
	// file_order 구하기(파일 업로드 및 파일 수정 업로드에 활용
	@Override
	public int getFileOrderByNumber(int board_number) {
		return sqlSession.selectOne(FixPath + ".getFileOrderByNumber", board_number);
	}

	// delete Board Y -> N
	@Override
	public void updateBoardByNumber(int board_number) {
		sqlSession.update(FixPath + ".deleteBoardByNumber", board_number);
	}

	// delete File Y -> N
	@Override
	public void updateFileByNumber(int board_number) {
		sqlSession.update(FixPath + ".deleteFileByNumber", board_number);
	}

	// list 전체 열 개수
	@Override
	public int listCount() {
		return sqlSession.selectOne(FixPath + ".listCount");
	}


}
