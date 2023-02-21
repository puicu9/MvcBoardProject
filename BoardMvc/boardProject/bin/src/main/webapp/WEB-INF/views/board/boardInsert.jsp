<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- 관련 스크립트 -->
<script src="<c:url value="/resources/js/board/boardInsert.js?ver=1"/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

<body>
<script type="text/javascript">
	<c:if test="${empty userEntity}">
		var message = "로그인을 하셔야 접근 가능합니다." ; 	
		alert(message);
		location.href='/user/login'
	</c:if>
</script>

	<div class="container">
		<form class="form" action="/board/insert" method="post" enctype="multipart/form-data"
			name="submitForm" id="submitForm">
			<div class="insert-around">
				<div class="insertDiv-group">
					<h1>게시판 등록</h1>
				</div>

				<input type="hidden" id="user_number" name="user_number" 
					value="${userEntity.user_number}">

				<div class="insertDiv-group">
					<span class="board_title">글제목</span>
					<br /> 
					<input type="text" name="board_title" id="board_title" class="title"
						placeholder="제목을 입력해주세요(필수)">
					<br /> <label for="title_check" id="title_check"></label>
				</div>

				<div class="insertDiv-group">
					<span class="board_content">글내용</span>
					<br/>
					<textarea id="board_content" name="board_content" rows="10" cols="40"
					placeholder="내용을 입력해주세요(필수)"></textarea> <br />
						<label for="content_check" id="content_check"></label>
				</div>
					<span>파일추가하기</span>
					<button id="plusFile">파일추가</button>
					<div class="file-new-group" id="file-new-group">
						<input type="file" name="file">
						<button name='deleteFile'>삭제</button>
					</div>
				
				<br/>


				<button type="button" id="submitButton" class="btn">등록하기</button>
				<button type="reset" class="btn">취소</button>

			</div>

		</form>

	</div>



</body>
</html>