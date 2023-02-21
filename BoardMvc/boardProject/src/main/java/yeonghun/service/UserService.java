package yeonghun.service;


import yeonghun.dto.UserInsertDto;
import yeonghun.dto.UserLoginDto;
import yeonghun.entity.UserEntity;

public interface UserService {

	// 회원가입 
	void insertUser(UserInsertDto userInsertDto);

	// 회원 가입, 아이디 중복 검사
	int validCheck(String user_id);

	// 로그인
	UserEntity loginUser(UserLoginDto userLoginDto);

	// 회원정보 수정
	UserEntity updateUser(UserInsertDto userInsertDto, UserEntity userEntity);

	// 회원 탈퇴
	void deleteUser(int user_number);
	
}
