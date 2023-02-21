package yeonghun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import yeonghun.dto.UserInsertDto;
import yeonghun.dto.UserLoginDto;
import yeonghun.entity.UserEntity;
import yeonghun.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	// 회원가입
	@Override
	public void insertUser(UserInsertDto userInsertDto) {
		userRepository.insertUser(userInsertDto);
	}

	// 회원 가입, 아이디 중복 검사
	@Override
	public int validCheck(String user_id) {
		int searchedId = userRepository.selectUserById(user_id);
		
		return searchedId;
	}

	// 로그인
	@Override
	public UserEntity loginUser(UserLoginDto userLoginDto) {
		UserEntity userEntity = userRepository.selectUserByIdPw(userLoginDto);
		return userEntity;
	}

	// 회원정보 수정
	@Override
	public UserEntity updateUser(UserInsertDto userInsertDto, UserEntity userEntity) {
		userEntity.setUser_name(userInsertDto.getUser_name());
		userEntity.setUser_email(userInsertDto.getUser_email());
		userEntity.setUser_contact(userInsertDto.getUser_contact());
		userEntity.setUser_zipcode(userInsertDto.getUser_zipcode());
		userEntity.setUser_roadname(userInsertDto.getUser_roadname());
		userEntity.setUser_address(userInsertDto.getUser_address());
		
		userRepository.updateUserByNumber(userEntity);
		
		// 변경된 값 조회하기
		userEntity = userRepository.selectUserByNumber(userEntity.getUser_number());
		
		return userEntity;
	}

	// 회원탈퇴
	@Override
	public void deleteUser(int user_number) {
		userRepository.deleteUpdateUserByNumber(user_number);
		
	}

}
