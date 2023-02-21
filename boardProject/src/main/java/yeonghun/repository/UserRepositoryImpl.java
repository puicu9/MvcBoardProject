package yeonghun.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yeonghun.dto.UserInsertDto;
import yeonghun.dto.UserLoginDto;
import yeonghun.entity.UserEntity;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private final String userPassFix = "yeonghun.repository.UserRepository";
	
	@Autowired
	SqlSession sqlSession;

	// 회원가입 
	@Override
	public void insertUser(UserInsertDto userInsertDto) {
		sqlSession.insert(userPassFix+".insertUser", userInsertDto);
		
	}

	// 회원 가입, 아이디 중복 검사
	@Override
	public int selectUserById(String user_id) {
		int searchedId = sqlSession.selectOne(userPassFix+".selectUserById", user_id) ;
		return searchedId;
	}

	// 로그인
	@Override
	public UserEntity selectUserByIdPw(UserLoginDto userLoginDto) {
		UserEntity userEntity = sqlSession.selectOne(userPassFix + ".selectUserByIdPw", userLoginDto);
		return userEntity;
	}

	//회원정보 수정
	@Override
	public void updateUserByNumber(UserEntity userEntity) {
		int cnt = -9999;
		cnt = sqlSession.update(userPassFix + ".updateUserByNumber", userEntity);
	}

	// 회원정보 수정 후 보여줄 회원정보
	@Override
	public UserEntity selectUserByNumber(int user_number) {
		UserEntity userEntity = sqlSession.selectOne(userPassFix + ".selectUserByNumber", user_number); 
		return userEntity ;
	}

	// 회원 탈퇴 Y to N
	@Override
	public void deleteUpdateUserByNumber(int user_number) {
		sqlSession.update(userPassFix + ".deleteUpdateUserByNumber", user_number);
		
	}

}
