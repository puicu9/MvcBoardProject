package yeonghun.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import yeonghun.dto.BoardUpdateDto;

@Component("fileUtil")
public class FileUtil {
	
	@Resource(name = "uploadPath")
	String uploadPath ;

	public List<Map<String, Object>> insertFileInfo(Map<String, Integer> map, MultipartFile[] file) throws Exception{
		
		int board_number = (int) map.get("board_number");
		int user_number = (int) map.get("user_number");
		int file_order = (int) map.get("file_order");
		
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		
		File target = new File(uploadPath); 
		
		// File.mkdir() : 프로그래밍 로직이 진행 중에 만들고자 하는 Folder(디렉토리)의 상위 Folder가 존재하지 않을 경우에는 생성이 불가능한 함수
		// File.mkdirs() : 프로그래밍 로직이 진행 중에 만들고자 하는 Foolder의 상위 Folder가 존재하지 않을 경우에는 상위 Folder까지 모두 생성하는 함수
		if(!target.exists()) {
			target.mkdirs();
		}
		
		
		for(int i = 0; i < file.length; i++) {
			// 랜덤 식별자, 랜덤으로 이름 만들어 줌
			UUID uuid = UUID.randomUUID();
			
			if(file_order <= 0) {
				file_order = 1 ;
			} else {
				file_order += 1 ; // count(*) 된 수를 1씩 증가
			}
			
			
			String file_realname = file[i].getOriginalFilename();
			String file_extension = file_realname.substring(file_realname.lastIndexOf(".") + 1); // 파일 확장자
			String file_savename = uuid.toString() + "."+ file_extension ;
				
			
			Long file_size = file[i].getSize(); // Long 정수 타입에서 가장 큰 타입
			
//				System.out.println("파일 실제 이름 : " + file_realname);
//				System.out.println("파일 저장 이름 : " + file_savename);
//				System.out.println("파일 크기 : " + file_size);
//				System.out.println("content type : " + file[i].getContentType());
			
			target = new File(uploadPath, file_savename);
			
			file[i].transferTo(target);
			
			Map<String, Object> fileInfo = new HashMap<String, Object>();
			
			fileInfo.put("board_number", board_number); // 게시판 고유번호
			fileInfo.put("user_number", user_number); // 유저 고유번호
			fileInfo.put("file_order", file_order); // 파일 업로드 순서
			fileInfo.put("file_realname", file_realname); // 파일 실제이름
			fileInfo.put("file_savename", file_savename); // 파일 저장이름
			fileInfo.put("file_path", uploadPath); // 파일 저장경로
			fileInfo.put("file_size", file_size); // 파일 저장 크기
			fileList.add(fileInfo);
		}
		
		
		
		
		return fileList ;
		
	}

	public List<Map<String, Object>> updateFileInfo(BoardUpdateDto dto, 
													String[] file_numbers, 
													String[] file_names) throws Exception {
		// 여기서부터 소스코드 작성

		// 삭제 목록 담을 list
		List<Map<String, Object>> deleteFileList = new ArrayList<Map<String,Object>>();
		
		// 삭제 목록
		Map<String, Object> fileInfo = new HashMap<String, Object>(); 
		if(file_numbers != null && file_names!= null) { // 삭제할 객체
			
			for(int i = 0; i < file_numbers.length; i++) {
				fileInfo.put("file_state", "N");
				fileInfo.put("file_number", file_numbers[i]);
				deleteFileList.add(fileInfo);
			}
		}
		
		return deleteFileList;
	}

}
