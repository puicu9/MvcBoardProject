package yeonghun.service;

import java.util.List;

import yeonghun.dto.ReplyInsertDto;
import yeonghun.dto.ReplySecondInsertDto;
import yeonghun.dto.ReplyUpdateDto;
import yeonghun.entity.ReplyEntity;

public interface ReplyService {

	// 부모 댓글 등록
	int replyInsertParent(ReplyInsertDto dto) ;

	// 댓글 list 불러오기
	List<ReplyEntity> getReplyList(int board_number);

	// 답글 댓글
	int replySecondInsert(ReplySecondInsertDto dto);

	// 대댓글 수정
	void replySecondUpdate(ReplyUpdateDto dto);

	// 댓글 삭제
	void replyDelete(int reply_number);

	

}
