<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
<!-- <meta charset="UTF-8"> -->

<!-- JQuery 스크립트 -->
<!-- <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="http://code.jquery.com/jquery-3.6.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    

<!-- css 파일 불러오기 -->
<link href="<c:url value="/resources/css/common.css?ver=3"/>" rel='stylesheet' />
<link href="<c:url value="/resources/css/board.css?ver=1"/>" rel='stylesheet' />

<!-- <link href="/resources/css/common.css?after" rel="stylesheet"> -->

<!-- js 파일 불러오기 예 -->
<%-- <script src='<c:url value="/resources/core/main.js"/>'></script> --%>




<title>Yeonghun</title>

</head>
<body>



<!-- 로그인 안 했으면 0으로 값 부여, 로그인 했으면 1로 부여 // 관리자 기능 없어서 관리자에 대한 번호 지정은 안 함 -->
	<c:set var="whoLogIn" value="0" />
	<c:if test="${not empty userEntity}">
		<c:set var="whoLogIn" value="1" />
		
		<!-- 접속한 사람 number -->
		<input type="hidden" id="loginfo" value="${userEntity.user_number}">
	</c:if>
	

	




	<!-- 네비게이션 -->
	<div class="container">
		<nav class="nav">
			<ul class="nav">
				<a href="/">
					<li class="nav">Yeonghun</li>
				</a>
				
				<c:if test="${whoLogIn == 0}">
					<a href="/user/login">
						<li class="nav">로그인</li>
					</a>
				</c:if>

				<c:if test="${whoLogIn == 0}">
					<a href="/user/insert">
						<li class="nav">회원가입</li>
					</a>
				</c:if>

				<c:if test="${whoLogIn != 0}">
					<a href="/board/list">
						<li class="nav">게시판</li>
					</a>
				</c:if>

				<c:if test="${whoLogIn != 0}">
					<a href="/user/update">
						<li class="nav">회원정보수정</li>
					</a>
				</c:if>

				<c:if test="${whoLogIn != 0}">
					<a href="/user/logout">
						<li class="nav">로그아웃</li>
					</a>
				</c:if>
			</ul>
		</nav>
	</div>



</body>
</html>