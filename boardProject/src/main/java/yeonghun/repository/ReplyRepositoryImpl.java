package yeonghun.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yeonghun.dto.ReplyInsertDto;
import yeonghun.dto.ReplyUpdateDto;
import yeonghun.entity.ReplyEntity;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository{

	private final String replyPathFix = "yeonghun.repository.ReplyRepository" ;
	
	@Autowired
	SqlSession sqlSession;
	
	//현재까지 등록된 댓글 갯수 확인
	@Override
	public int getReplyCount() {
		return sqlSession.selectOne(replyPathFix + ".getReplyCount");
	}

	// 부모 댓글 등록
	@Override
	public int insertReplyByDto(Map<String, Object> map) {
		
		return sqlSession.insert(replyPathFix+".insertReplyByDto", map);
	}

	// 댓글 리스트
	@Override
	public List<ReplyEntity> selectReplyByNumber(int board_number) {
		return sqlSession.selectList(replyPathFix + ".selectReplyByNumber", board_number);
	}

	// 답글 댓글
	@Override
	public int insertReplySecondByDto(Map<String, Object> map) {
		return sqlSession.insert(replyPathFix + ".insertReplySecondByDto", map);
	}

	// 대댓글 group Number 구하기
	@Override
	public int selectGroupByNumber(int reply_second_reply_number) {
		return sqlSession.selectOne(replyPathFix + ".selectGroupByNumber", reply_second_reply_number);
	}
	
	// 대댓글의 order Number 구하기
	@Override
	public int selectOrderByNumber(int reply_second_group) {
		return sqlSession.selectOne(replyPathFix + ".selectOrderByNumber", reply_second_group);
	}

	// 대댓글 depth Number 구하기
	@Override
	public int selectDepthByNumber(int reply_second_reply_number) {
		return sqlSession.selectOne(replyPathFix + ".selectDepthByNumber", reply_second_reply_number);
	}

	// 대댓글 수정
	@Override
	public void updateReplyByNumber(ReplyUpdateDto dto) {
		sqlSession.update(replyPathFix + ".updateReplyByNumber", dto) ;
		
	}

	// 댓글 삭제
	@Override
	public void updateReplyByMap(Map<String, Object> map) {
		sqlSession.update(replyPathFix + ".updateReplyByMap", map);
	}

	// 댓글 삭제 전, 댓글에 관한 정보
	@Override
	public Map<String, Object> selectReplyInfoByNumber(int reply_number) {
		return sqlSession.selectOne(replyPathFix + ".selectReplyInfoByNumber", reply_number);
	}


}
