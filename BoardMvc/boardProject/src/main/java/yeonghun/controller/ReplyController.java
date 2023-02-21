package yeonghun.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yeonghun.dto.ReplyInsertDto;
import yeonghun.dto.ReplySecondInsertDto;
import yeonghun.dto.ReplyUpdateDto;
import yeonghun.entity.ReplyEntity;
import yeonghun.service.ReplyService;
import yeonghun.util.AES256Util;

@Controller
@RequestMapping(value="/reply", produces = "application/text; charset=UTF-8")
public class ReplyController {

	@Autowired
	ReplyService replyService;
	
	@Autowired
	AES256Util aes; // 양방향 암호화
	
	// 댓글 list 불러오기
	@ResponseBody
	@GetMapping(value = "/list/{board_number}")
	public String replyList(@PathVariable int board_number, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		
		List<ReplyEntity> replyList = replyService.getReplyList(board_number); 
//		System.out.println(replyList.size());
		
		JSONArray jsonArray = new JSONArray();
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		for(ReplyEntity item : replyList) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("reply_number", item.getReply_number());
			jsonObject.put("user_name", aes.decrypt(item.getUser_name()));
			jsonObject.put("reply_content", item.getReply_content());
			jsonObject.put("reply_inputdate", sdf.format(item.getReply_inputdate()));
			jsonObject.put("reply_depth", item.getReply_depth());
			
			jsonArray.add(jsonObject);
		}

		return  jsonArray.toJSONString();
	}

	// 부모 댓글 등록 
//	@ResponseBody // 이걸 사용해서 return될 때 @ResponseBody JSON 형태의 데이터를 반환 혹은 파일이 다운로드 되었음
	@PostMapping(value = "/insert")
	public String replyInsertPost(ReplyInsertDto dto) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
//		System.out.println("dto check");
//		System.out.println(dto.toString());

		// 부모 댓글 등록
		int cnt = -9999;
		cnt = replyService.replyInsertParent(dto) ;
		
//		JSONObject jsonObject = new JSONObject() ;
//		jsonObject.put("cnt", cnt) ; 
		
//		return 	jsonObject.toJSONString() ;
//		return "board/boardDetail";
//		return "reply/replyList";
		return "redirect:/board/list/"+dto.getBoard_number() ;
	}
	
	// 답글 등록
	@PostMapping(value="/secondInsert")
	public String replySecondInsert(ReplySecondInsertDto dto) {
		System.out.println("dto check");
		System.out.println(dto.toString());
		
		int cnt = -9999 ;
		cnt = replyService.replySecondInsert(dto);
		
		
		return "redirect:/board/list/"+dto.getReply_second_board_number() ;
	}
	
	// 답글 수정
	@PostMapping(value="/secondUpdate")
	public String replySecondUpdate(ReplyUpdateDto dto) {
//		System.out.println("ReplyUpdateDto check");
//		System.out.println(dto.toString());
		
		int reply_number = dto.getReply_second_update_reply_number() ;
		
		// 대댓글 수정
		replyService.replySecondUpdate(dto);
		
		return "redirect:/board/list/"+dto.getReply_second_update_board_number() ; 
	}
	
	// 댓글 삭제
	@PostMapping(value="/secondDelete")
	public String replyDelete(@RequestParam("reply_second_delete_reply_number") int reply_second_delete_reply_number,
			@RequestParam("reply_second_delete_board_number") int reply_second_delete_board_number) 
	{
//		System.out.println("replyDelete check");
//		System.out.println("reply_second_delete_reply_number : " + reply_second_delete_reply_number);
//		System.out.println("reply_second_delete_board_number : " + reply_second_delete_board_number);
		int reply_number = reply_second_delete_reply_number ;
		int board_number = reply_second_delete_board_number ;
		
		replyService.replyDelete(reply_number);

		return "redirect:/board/list/"+board_number ; 
	}
	
	
}
