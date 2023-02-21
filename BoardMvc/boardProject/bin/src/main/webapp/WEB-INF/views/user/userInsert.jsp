<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- userInsert 관련 스크립트 -->
<script src="<c:url value="/resources/js/user/userInsert.js?ver=2"/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

<body>

	<div class="container">
		<form class="form" action="/user/insert" method="post"
			name="submitForm" id="submitForm">
			<div class="insert-around">
				<div class="insertDiv-group">
					<h1>회원가입</h1>
				</div>

				<div class="insertDiv-group">
					<label for="user_id">아이디</label> <input type="text" name="user_id"
						id="user_id" placeholder="아이디를 입력해주세요(필수)">
					<button type="button" id="idValidCheck">아이디 중복 검사</button>
					<br /> <label for="id_check" id="id_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_password">비밀번호</label> <input type="password"
						name="user_password" id="user_password" placeholder="비밀번호를 입력해주세요(필수)">
					<br /> <label for="password_check" id="password_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="password_doubleCheck">비밀번호 확인</label> <input
						type="password" name="password_doubleCheck" id="user_doubleCheck"
						placeholder="비밀번호를 입력해주세요(필수)"> <br /> <label
						class="password_doubleCheck" id="password_doubleCheck"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_name">이름</label> <input type="text"
						class="content" name="user_name" id="user_name"
						placeholder="이름을 입력해주세요(필수)"> <br /> <label for="name_check"
						id="name_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_email">이메일</label> <input type="email"
						class="content" name="user_email" id="user_email"
						placeholder="이메일을 입력해주세요(필수)"> <br /> 
						<label for="email_check" id="email_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_contact">연락처</label> <input type="text"
						class="content" name="user_contact" id="user_contact"
						placeholder="연락처를 입력해주세요(필수)"> <br /> 
						<label for="user_check" id="contact_check"></label>
				</div>

				<button type="button" id="DaumPostcode">주소검색</button>
				<br/>
				<label for="zipcode_check" id="zipcode_check"></label>
				<br/>
				<div class="insertDiv-group">
					<label for="user_zipcode">우편번호</label>
						<input type="text"
						name="user_zipcode" id="user_zipcode" placeholder="우편번호(필수)" readonly
						class="content">
						
				</div>

				<div class="insertDiv-group">
					<label for="user_roadname">도로명 주소</label> 
					<input type="text"
						class="content" name="user_roadname" id="user_roadname" readonly
						placeholder="도로명 주소(필수)"> 
						<br />
				</div>

				<div class="insertDiv-group">
					<label for="user_address">상세주소</label> 
					<input type="text"
						class="content" name="user_address" id="user_address"
						placeholder="상세주소(선택)"> 
						<br />
				</div>



				<!-- <input type="submit" id="submitButton" value="회원가입"> -->
				<button type="button" id="submitButton" class="btn">회원가입</button>
				<button type="reset" class="btn">취소</button>

			</div>

		</form>

	</div>


	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</body>
</html>