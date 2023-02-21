<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- 관련 스크립트 -->
<script src="<c:url value="/resources/js/board/boardUpdate.js?ver=2"/>"></script>

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
		<form class="form" action="/board/update/${boardEntity.board_number}" method="post" enctype="multipart/form-data"
			name="submitForm" id="submitForm">
			<div class="insert-around">
				<div class="insertDiv-group">
					<h1>게시글 수정</h1>
				</div>

				<div class="insertDiv-group">
					<span class="content">글번호</span>
					<br /> 
					<input type="text" name="board_number" id="board_number" class="number center" disabled
					value="${boardEntity.board_number}">
				</div>
				
				<div class="insertDiv-group">
					<span class="content">작성일</span>
					<br /> 
					<input type="text" name="board_inputdate" id="board_inputdate" class="number center"
					value="<fmt:formatDate value="${boardEntity.board_inputdate}" pattern="yyyy-MM-dd HH:mm:ss" />" disabled>
				</div>
				
				<!-- 회원 고유번호 -->
				<input type="hidden" name="user_number" id="user_number" value="${boardEntity.user_number}">
				
				<div class="insertDiv-group">
					<span class="content">작성자</span>
					<br /> 
					<input type="text" name="user_name" id="user_name" class="writer center" disabled
					value="${boardEntity.user_name}">
				</div>

				<div class="insertDiv-group">
					<span class="content">글제목</span>
					<br /> 
					<input type="text" name="board_title" id="board_title" class="title center"
					value="${boardEntity.board_title}">
				</div>

				<div class="insertDiv-group">
					<span class="content">글내용</span>
					<br/>
					<textarea id="board_content" name="board_content" rows="10" cols="40">
						${boardEntity.board_content}
					</textarea> <br />
				</div>
				
				<div class="file-group">
					
				
						<span class="content">기존 파일 목록</span>
						<br/>
						
						<c:if test="not empty ${fileList}">
							<c:forEach var="file" items="${fileList}" varStatus="status">
								<div class="file-old-group">
									<input type="hidden" name="file">
										<a href="#this" name="file" onclick="return false;">
											${file.FILE_REALNAME}
										</a>(${file.FILE_SIZE} kb) 
										
										<!-- 삭제 버튼 클릭하면 전달할 파라미터의 저장공간 -->
										<input type="hidden" id="FILE_NUMBER" name="FILE_NUMBER" value="${file.FILE_NUMBER}">
										<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NUMBER_${status.index}">
										
										<button type="button" class="file_delete" id="file_delete" value="${file.FILE_NUMBER}">삭제</button>
								</div>
							</c:forEach>
						</c:if>
						
						<br/>
						<span>새로운 파일추가하기</span>
							<button id="plusFile">파일추가</button>
							<div class="file-new-group" id="file-new-group">
								<input type="file" name="file">
								<button name='deleteFile'>삭제</button>
						</div>
				</div>
				
				
				<br/>


				<c:if test="${userEntity.user_number eq boardEntity.user_number}">
					<button type="button" id="submitButton">수정하기</button>
					<button type="button" id="deleteButton">현재 글 삭제하기</button>
					<button type="button" id="listButton">글 목록보기</button>
				</c:if>
				

			</div>
			
			<!-- 삭제 버튼 클릭하면 전달할 파라미터의 저장공간 -->
			<input type="hidden" id="file_number" name="file_number" value="">
			<input type="hidden" id="file_delete_number" name="file_delete_number[]" value="">
			<input type="hidden" id="file_delete_name" name="file_delete_name[]" value="">
			
		</form>

	</div>


</body>
</html>