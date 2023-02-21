<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- userInsert 관련 스크립트 -->
<script src="<c:url value="/resources/js/user/userLogin.js?ver=2"/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

<body>
	<div class="container">
		<form class="form" action="/user/login" method="post"
			name="submitForm" id="submitForm">
			<div class="login-around">
				<div class="loginDiv-group">
					<h1>로그인</h1>
				</div>

				<div class="loginDiv-group">
					<label for="user_id">아이디 <input type="text" class="content"
						name="user_id" id="user_id" placeholder="아이디">
					</label>
				</div>

				<div class="loginDiv-group">
					<label for="user_password">비밀번호</label> <input type="password"
						class="content" name="user_password" id="user_password"
						placeholder="비밀번호">
				</div>

				<c:if test="${not empty errorMessage}">
					<div class="loginDiv-group">
						<!-- <label for="login-failed" id="login-failed"> -->
						<span class="error">${errorMessage}</span>
						<!-- </label> -->
					</div>
				</c:if>



				<button type="button" id="submitButton" class="login-btn">로그인</button>
				<button type="button" id="goToButton" class="login-btn">회원가입</button>

			</div>
		</form>


	</div>



</body>
</html>