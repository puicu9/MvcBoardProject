package yeonghun.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yeonghun.dto.ReplyUpdateDto;
import yeonghun.entity.ReplyEntity;


public interface ReplyRepository {

	//현재까지 등록된 댓글 갯수 확인
	int getReplyCount();
	
	// 댓글 등록
	int insertReplyByDto(Map<String, Object> map);

	// 댓글 리스트
	List<ReplyEntity> selectReplyByNumber(int board_number);

	// 답글 댓글
	int insertReplySecondByDto(Map<String, Object> map);

	// 대댓글 group Number 구하기
	int selectGroupByNumber(int reply_second_reply_number);
	
	// 대댓글 order Number 구하기
	int selectOrderByNumber(int reply_second_group);

	// 대댓글 depth Number 구하기
	int selectDepthByNumber(int reply_second_reply_number);

	// 대댓글 수정
	void updateReplyByNumber(ReplyUpdateDto dto);

	// 댓글 삭제
	void updateReplyByMap(Map<String, Object> map);

	// 댓글 삭제 전, 댓글에 관한 정보
	Map<String, Object> selectReplyInfoByNumber(int reply_number);



	// order, depth, group 구하기
//	HashMap<String, Object> selectReplyByDto(int reply_second_reply_number);

}
