package yeonghun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yeonghun.dto.ReplyInsertDto;
import yeonghun.dto.ReplySecondInsertDto;
import yeonghun.dto.ReplyUpdateDto;
import yeonghun.entity.ReplyEntity;
import yeonghun.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyRepository replyRepository;

	// 부모 댓글 등록
	@Override
	public int replyInsertParent(ReplyInsertDto dto) {
		int board_number = dto.getBoard_number();
		int user_number = dto.getUser_number();
		String reply_content = dto.getReply_content();
		
		//현재까지 등록된 댓글 갯수 확인
		int reply_group = replyRepository.getReplyCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("board_number", board_number);
		map.put("user_number", user_number);
		map.put("reply_content", reply_content);
		map.put("reply_group", reply_group);
		
		// 댓글 등록
		int cnt = replyRepository.insertReplyByDto(map);
		
		return cnt; 
		
	}

	// 댓글 list 불러오기
	@Override
	public List<ReplyEntity> getReplyList(int board_number) {
		List<ReplyEntity> replyList = replyRepository.selectReplyByNumber(board_number);
		
		return replyList ;
	}

	// 답글 댓글
	@Override
	public int replySecondInsert(ReplySecondInsertDto dto) {
		// 대댓글 group, order, Depth 구하기
//		HashMap<String, Object> hm = replyRepository.selectReplyByDto(dto.getReply_second_reply_number());
//		System.out.println("hm check");
//		System.out.println(hm.toString());
		
//		int reply_second_group = (int) hm.get("REPLY_GROUP");
//		int reply_second_order = (int) hm.get("REPLY_ORDER");
//		int reply_second_depth = (int) hm.get("REPLY_DEPTH");
//		
//		System.out.println("hm check");
//		System.out.println(reply_second_group);
//		System.out.println(reply_second_order);
//		System.out.println(reply_second_depth);
		
		int reply_second_group = replyRepository.selectGroupByNumber(dto.getReply_second_reply_number());
		int reply_second_order = replyRepository.selectOrderByNumber(reply_second_group);
		int reply_second_depth = replyRepository.selectDepthByNumber(dto.getReply_second_reply_number());
		
//		System.out.println("order, depth check");
//		System.out.println(reply_second_order);
//		System.out.println(reply_second_depth);
		
		Map<String, Object> map = new HashMap<>();
		map.put("board_number", dto.getReply_second_board_number());
		map.put("user_number", dto.getReply_second_user_number());
		map.put("reply_content", dto.getReply_second_content());
		map.put("reply_group", reply_second_group);
		// order, depth 번호에 +1 값 넣기 
		map.put("reply_order", reply_second_order);
		map.put("reply_depth", reply_second_depth +1);
		
		int cnt = replyRepository.insertReplySecondByDto(map);
		
		return cnt;
	}

	// 대댓글 수정
	@Override
	public void replySecondUpdate(ReplyUpdateDto dto) {
		replyRepository.updateReplyByNumber(dto);
	}

	// 댓글 삭제
	@Override
	public void replyDelete(int reply_number) {

		// 댓글 삭제 전, 댓글에 관한 정보
		Map<String, Object> replyInfo = replyRepository.selectReplyInfoByNumber(reply_number);
//		System.out.println("service replyInfo");
//		System.out.println(replyInfo.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reply_number", replyInfo.get("REPLY_NUMBER"));
		map.put("reply_content", replyInfo.get("REPLY_CONTENT"));
		
		// 댓글 삭제(수정)
		replyRepository.updateReplyByMap(map);
	}


	

}
