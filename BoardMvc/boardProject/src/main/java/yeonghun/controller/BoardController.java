package yeonghun.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import yeonghun.dto.BoardInsertDto;
import yeonghun.dto.BoardUpdateDto;
import yeonghun.entity.BoardEntity;
import yeonghun.service.BoardService;
import yeonghun.util.AES256Util;
import yeonghun.util.Criteria;
import yeonghun.util.PageMaker;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	AES256Util aes; // 양방향 암호화
	
	@Resource(name = "uploadPath")
	String uploadPath ;
	

	// 게시판 리스트 페이지 Get
	@GetMapping(value = "/list")
	public String boardListGet(Model model, Criteria cri) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		List<BoardEntity> boardList = boardService.getBoardList(cri) ;
		model.addAttribute("boardList", boardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.listCount());
		model.addAttribute("pageMaker", pageMaker) ;
		
		return "board/boardList";
	}
	
	
	// 게시판 등록 Get
	@GetMapping(value = "/insert")
	public String boardInsertGet() {
		return "board/boardInsert";
	}
	
	// 게시물 등록 및 파일 업로드 Post
	@PostMapping(value = "/insert")
	public String boardInsertPost(BoardInsertDto dto, MultipartFile[] file, Model model, Criteria cri) throws Exception {
		//System.out.println("boardInsertPost check");
		
		// 게시물 등록 및 파일 업로드 진행하는 service method
		boardService.writeBoard(dto, file);
		
		// 게시판 등록 get으로 가기
		boardListGet(model, cri);
		
		return "redirect:/board/list";
	}
	
	// 게시판 상세보기 Get
	@GetMapping(value="/list/{board_number}")
	public String boardDetailGet(@PathVariable int board_number, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		// System.out.println(board_number);// check
		
		// 게시판 글, 제목, 내용 + user_number 담김
		BoardEntity boardEntity = boardService.getBoardDetailList(board_number);
		model.addAttribute("boardEntity", boardEntity);
		
		// 게시물 상세보기, 파일 업로드 리스트
		List<Map<String, Object>> fileList = boardService.getFileList(board_number);
		model.addAttribute("fileList", fileList) ;
		
//		System.out.println("fileList check");
//		System.out.println(fileList.toString());
		
		return "board/boardDetail";
	}
	
	// 게시물 상세보기, 파일 다운로드 Post
	@PostMapping(value = "/download")
	public void fileDownloadPost(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		Map<String, Object> result = boardService.getFileInfo(map);
		
//		System.out.println("result check"); //확인
//		System.out.println(result.toString()); // 확인
		
		String file_savename = (String) result.get("FILE_SAVENAME"); //FILE_SAVENAME
		String file_realname = (String) result.get("FILE_REALNAME"); // FILE_REALNAME
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[] 형식으로 변환함
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(uploadPath + file_savename));
		
		// 파일 유형 설정
		response.setContentType("application/octet-stream");
		
		// 파일 길이 설정
		response.setContentLength(fileByte.length);
		
		// 데이터 형식/성향 설정(attachment : 첨부파일)
		response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(file_realname, "UTF-8")+"\";");
		
		// 버퍼의 출력스트림을 출력
		response.getOutputStream().write(fileByte); 
		
		// 버퍼에 남아있는 출력 스트림을 출력
		response.getOutputStream().flush();
		
		// 출력 스트림 닫기
		response.getOutputStream().close(); 
		
	}
	
	// 게시글 수정 Get
	@GetMapping(value="/update/{board_number}")
	public String updateBoardGet(@PathVariable int board_number, BoardInsertDto dto, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {

		// 게시판 글, 제목, 내용 + user_number 담김
		BoardEntity boardEntity = boardService.getBoardDetailList(board_number);
		model.addAttribute("boardEntity", boardEntity);
		
		// 게시물 상세보기, 파일 업로드 리스트
		List<Map<String, Object>> fileList = boardService.getFileList(board_number);
		model.addAttribute("fileList", fileList) ;
		
		return "board/boardUpdate";
	}
	
	// 게시글 수정 Post
	@PostMapping(value="/update/{board_number}")
	public String updateBoardPost(BoardUpdateDto dto,
			@RequestParam(value="file_delete_number[]") String[] file_numbers,
			@RequestParam(value="file_delete_name[]") String[] file_names,
			@RequestParam(value="file")MultipartFile[] mFile,
			Model model,
			Criteria cri
			) throws Exception {
		
		boardService.updateBoard(dto, file_numbers, file_names, mFile);
		
		// 게시판 detail 이동
		int board_number = dto.getBoard_number() ;
		boardDetailGet(board_number, model);
		
		return "redirect:/board/list/"+board_number;
	}
	
	// 게시글 삭제 Get Y to N
	// 게시글뿐만이 아니라 파일도 N으로
	@GetMapping(value="/delete/{board_number}")
	public String boardDeleteGet(@PathVariable int board_number, Model model, Criteria cri) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		// System.out.println(board_number); // 넘어옴
		
		boardService.deleteBoardAndFile(board_number);
		
		// 게시판 List Get으로 이동
		boardListGet(model, cri);
		
		return "redirect:/board/list";
	}
	
	
	
	
}
