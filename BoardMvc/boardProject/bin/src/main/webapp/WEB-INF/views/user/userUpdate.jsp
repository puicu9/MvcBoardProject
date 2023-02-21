<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- userUpdate 관련 스크립트 -->
<script src="<c:url value="/resources/js/user/userUpdate.js?ver=2"/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

<body>
<script type="text/javascript">
	<c:if test="${not empty message}">
		var message = "${message}" ; 	
		alert(message);
	</c:if>

</script>


	<div class="container">
		<form class="form" action="/user/update" method="post"
			name="submitForm" id="submitForm">
			<div class="insert-around">
				<div class="insertDiv-group">
					<h1>회원정보수정</h1>
				</div>

				<div class="insertDiv-group">
					<label for="user_id">아이디</label> <input type="text" name="user_id"
						id="user_id" value="${userEntity.user_id}" disabled> <br /> <label
						for=id_check" id="id_check" class="error"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_name">이름</label> <input type="text"
						class="content" name="user_name" id="user_name"
						value="${userEntity.user_name}"> <br /> <label
						for="name_check" id="name_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_email">이메일</label> <input type="email"
						class="content" name="user_email" id="user_email"
						value="${userEntity.user_email}"> <br /> <label
						for="email_check" id="email_check"></label>
				</div>

				<div class="insertDiv-group">
					<label for="user_contact">연락처</label> <input type="text"
						class="content" name="user_contact" id="user_contact"
						value="${userEntity.user_contact}"> <br /> <label
						for="user_check" id="contact_check"></label>
				</div>

				<button type="button" id="DaumPostcode">주소 검색(변경 필요 시 클릭)</button>
				<br /> <label for="zipcode_check" id="zipcode_check"></label> <br />

				<div class="insertDiv-group">
					<label for="user_zipcode">우편번호</label> <input type="text"
						value="${userEntity.user_zipcode}" name="user_zipcode"
						id="user_zipcode" class="content">

				</div>

				<div class="insertDiv-group">
					<label for="user_roadname">도로명 주소</label>
					 <input type="text" value="${userEntity.user_roadname}"
						 class="content" name="user_roadname" id="user_roadname">
				</div>

				<div class="insertDiv-group">
					<label for="user_adress">상세주소</label>
					<input type="text" value="${userEntity.user_address}"
						 class="content" name="user_address" id="user_address">
				</div>

				<div class="insertDiv-group">
					<label for="user_password">비밀번호 확인</label> 
					<input type="password" name="user_password" id="user_password" placeholder="비밀번호를 입력해주세요">
					<br /> <label for="password_check" id="password_check"></label>

				</div>

				<button type="button" id="submitButton">회원정보 수정</button>
				<button type="reset" class="btn">취소</button>
				<button type="button" id="userDelete" class="btn">회원탈퇴</button>

			</div>

		</form>

	</div>



	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</body>
</html>