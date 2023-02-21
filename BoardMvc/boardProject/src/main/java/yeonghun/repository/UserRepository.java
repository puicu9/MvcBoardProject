package yeonghun.repository;

import yeonghun.dto.UserInsertDto;
import yeonghun.dto.UserLoginDto;
import yeonghun.entity.UserEntity;

public interface UserRepository {

	// 회원가입 
	void insertUser(UserInsertDto userInsertDto);

	// 회원 가입, 아이디 중복 검사
	int selectUserById(String user_id);

	// 로그인
	UserEntity selectUserByIdPw(UserLoginDto userLoginDto);

	// 회원정보 수정
	void updateUserByNumber(UserEntity userEntity);

	// 회원정보 수정 후 보여줄 회원정보
	UserEntity selectUserByNumber(int user_number);

	// 회원 탈퇴 -> Y to N
	void deleteUpdateUserByNumber(int user_number);

}
