package yeonghun.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.eclipse.jdt.internal.compiler.ast.SuperReference;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yeonghun.dto.UserInsertDto;
import yeonghun.dto.UserLoginDto;
import yeonghun.entity.UserEntity;
import yeonghun.service.UserService;
import yeonghun.util.AES256Util;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@Inject
	BCryptPasswordEncoder passwordEncoder; //단방향 암호화
	
	@Autowired
	AES256Util aes; // 양방향 암호화
	
	UserEntity userEntity = null;
	UserEntity getSessionInfo(HttpSession session) {
		return userEntity = (UserEntity) session.getAttribute("userEntity"); // session 정보
	}

	// 회원가입 get method
	@GetMapping(value = "/insert")
	public String userInsertGet() {
		return "user/userInsert";
	}

	// 회원가입 post method
	@PostMapping(value = "/insert")
	public String userInsertPost(UserInsertDto dto) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String password = passwordEncoder.encode(dto.getUser_password());
		dto.setUser_password(password); // 단방향 암호화
		
		// 양방향 암호화
		dto.setUser_name(aes.encrypt(dto.getUser_name()));
		dto.setUser_email(aes.encrypt(dto.getUser_email()));
		dto.setUser_contact(aes.encrypt(dto.getUser_contact()));
		dto.setUser_zipcode(aes.encrypt(dto.getUser_zipcode()));
		dto.setUser_roadname(aes.encrypt(dto.getUser_roadname()));
		dto.setUser_address(aes.encrypt(dto.getUser_address()));
		
		
		userService.insertUser(dto);
		
		return "user/userLogin"; // 회원가입 성공 후 로그인 페이지로
	}

	// 회원가입 시 유효성 검사
	@ResponseBody
	@PostMapping(value = "/idValidCheck")
	public String idValidPost(@RequestParam("user_id") String user_id) {
		// 수정 후, 변수 명 : searchedId -> idCheck
		// 가입한 아이디가 있다면 count(*) 계산으로 1 반환, 그렇지 않다면 0 반환
		int idCheck = userService.validCheck(user_id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", idCheck); // 1 : 가입한 아이디 있음, 0: 가입한 아이디 없음
		
		/* 
		 * 수정 전, 변수 명 : searchedId
		 * if (idCheck == "1") { // != null 의미: 가입 불가 jsonObject.put("state", idCheck);
		 * // 1 : 가입한 계정이 있음 } else if (idCheck == "0") { jsonObject.put("state",
		 * idCheck); // 0 : 가입한 계정이 없음 }
		 */

		return jsonObject.toJSONString();
	}

	// 로그인 Get method
	@GetMapping(value = "/login")
	public String userLoginGet() {
		return "user/userLogin";
	}

	// 로그인 Post method
	@PostMapping(value = "/login")
	public String userLoginPost(UserLoginDto userLoginDto, HttpSession session, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		UserEntity userEntity = userService.loginUser(userLoginDto);
		
		String errorMessage = "등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못입력하셨습니다.";
		
		if(userEntity == null) {	
			model.addAttribute("errorMessage", errorMessage);
			return "user/userLogin";
		}
		
		// 양방향 복호화
		userEntity.setUser_name(aes.decrypt(userEntity.getUser_name()));
		userEntity.setUser_email(aes.decrypt(userEntity.getUser_email()));
		userEntity.setUser_contact(aes.decrypt(userEntity.getUser_contact()));
		userEntity.setUser_zipcode(aes.decrypt(userEntity.getUser_zipcode()));
		userEntity.setUser_roadname(aes.decrypt(userEntity.getUser_roadname()));
		userEntity.setUser_address(aes.decrypt(userEntity.getUser_address()));
		
		
		boolean passwordMatch = passwordEncoder.matches(userLoginDto.getUser_password(), userEntity.getUser_password());
		
		if (userEntity != null && passwordMatch) { // 로그인 성공한 경우
			session.setAttribute("userEntity", userEntity);
			
			return "main" ;
		} else { // 로그인 실패한 경우
			session.setAttribute("userEntity", null);
			model.addAttribute("errorMessage", errorMessage);
			return "user/userLogin";
		}
	}


	// 로그아웃
	@GetMapping(value = "/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
	// 회원정보수정 Get
	@GetMapping(value = "/update")
	public String userUpdateGet(HttpSession session, Model model) {
		UserEntity userEntity = getSessionInfo(session); // 수정 이전의 session 정보
		
		model.addAttribute("userEntity", userEntity); 
		
		//int user_number = userEntity.getUser_number();
		//System.out.println("user_number check");
		//System.out.println(user_number); 값 들어가는거 확인됨
		//System.out.println(userEntity.toString());
		
		return "user/userUpdate";
	}
	
	// 회원정보수정 Post
	@PostMapping(value = "/update")
	public String userUpdatePost(HttpSession session, UserInsertDto userInsertDto, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		UserEntity userEntity = getSessionInfo(session);
		
		// System.out.println(userEntity.toString()); 값 들어옴 확인
		String message = "";
		boolean passwordMatch = passwordEncoder.matches(userInsertDto.getUser_password(), userEntity.getUser_password());
		
		if(userInsertDto.getUser_password() != null && passwordMatch) {//비밀번호 옳게 들어오면
			// System.out.println(userInsertDto);
			
			// 양방향 암호화
			userInsertDto.setUser_name(aes.encrypt(userInsertDto.getUser_name()));
			userInsertDto.setUser_email(aes.encrypt(userInsertDto.getUser_email()));
			userInsertDto.setUser_contact(aes.encrypt(userInsertDto.getUser_contact()));
			userInsertDto.setUser_zipcode(aes.encrypt(userInsertDto.getUser_zipcode()));
			userInsertDto.setUser_roadname(aes.encrypt(userInsertDto.getUser_roadname()));
			userInsertDto.setUser_address(aes.encrypt(userInsertDto.getUser_address()));
			
			userEntity = userService.updateUser(userInsertDto, userEntity);
			
			// 양방향 복호화
			userEntity.setUser_name(aes.decrypt(userEntity.getUser_name()));
			userEntity.setUser_email(aes.decrypt(userEntity.getUser_email()));
			userEntity.setUser_contact(aes.decrypt(userEntity.getUser_contact()));
			userEntity.setUser_zipcode(aes.decrypt(userEntity.getUser_zipcode()));
			userEntity.setUser_roadname(aes.decrypt(userEntity.getUser_roadname()));
			userEntity.setUser_address(aes.decrypt(userEntity.getUser_address()));
			
			message = "성공적으로 회원정보가 수정되었습니다.";
			model.addAttribute("userEntity", userEntity); // update 이후 수정된 사항 select
			model.addAttribute("message", message);
			
			
		} else { // 비밀번호 잘못 들어오면
			message = "비밀번호를 잘못입력하셨습니다.";
			model.addAttribute("userEntity", userEntity); // 수정 실패, 수정 이전의 사용자 정보
			model.addAttribute("message", message);
		}

		
		return "user/userUpdate";
	}
	
	// 회원 탈퇴 Y to N
	@GetMapping(value="/delete")
	public String userDeleteGet(HttpSession session, Model model) {
		UserEntity userEntity = getSessionInfo(session);
		int user_number = userEntity.getUser_number();
		
		userService.deleteUser(user_number);
		
		session.invalidate();
		model.addAttribute("message", "성공적으로 탈퇴가 되었습니다.");
		
		return "main";
	}
	

}
