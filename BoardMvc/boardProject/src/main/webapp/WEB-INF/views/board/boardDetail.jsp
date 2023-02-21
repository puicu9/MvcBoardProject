<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- 관련 스크립트 -->
<script src="<c:url value="/resources/js/reply/replyDetail.js?ver=2"/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>

<body>
<script type="text/javascript">
	<c:if test="${empty userEntity}">
		var message = "로그인을 하셔야 접근 가능합니다." ; 	
		alert(message) ;
		location.href='/user/login' ;
	</c:if>
		
	// 파일 저장하기
	function downloadFile(file_number){
		// console.log('clicked');
		var saveFileForm = $("form[name='submitForm']");
		$('#file_number').attr('value', file_number);
		saveFileForm.attr('action', '/board/download');
		saveFileForm.submit();
	}
	
	
	$(document).ready(function() {

	/* 클릭 이벤트 */
		// 게시물 수정하러 가기	
		$('#updateButton').click(function(){
			event.preventDefault();
			location.href='/board/update/'+${boardEntity.board_number} ;
		});
			
		// 게시물 삭제하러 가기
		$('#deleteButton').click(function(){
			event.preventDefault();
			boardDelete();
		});	
		
		function boardDelete() {
			var message = '게시글을 삭제하시면 복구하기 어렵습니다. 그래도 삭제하시겠습니까?'
			message += '\n\n삭제를 원하신다면 확인을 눌러주세요.';
			if (confirm(message)) {
				location.href = '/board/delete/'+${boardEntity.board_number} ;
			} else {
				alert('삭제하지 않아주셔서 감사합니다');
			}
		}
		
		
		
		
		

	});/* end document.ready() */
		
		
</script>

	<div class="container">
			<div class="insert-around">
				<div class="insertDiv-group">
					<h1>게시판 상세보기</h1>
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
					<input type="text" name="board_inputdate" id="board_inputdate" class="number center" disabled
					value="<fmt:formatDate value="${boardEntity.board_inputdate}" pattern="yyyy-MM-dd HH:mm:ss" />" >
				</div>
				
				<div class="insertDiv-group">
					<span class="content">작성자</span>
					<br /> 
					<input type="text" name="user_name" id="user_name" class="writer center" disabled
					value="${boardEntity.user_name}">
				</div>

				<div class="insertDiv-group">
					<span class="content">글제목</span>
					<br /> 
					<input type="text" name="board_title" id="board_title" class="title center" readonly
					value="${boardEntity.board_title}">
				</div>

				<div class="insertDiv-group">
					<span class="content">글내용</span>
					<br/>
					<textarea id="board_content" name="board_content" rows="10" cols="40" readonly>
						${boardEntity.board_content}
					</textarea> <br />
				</div>
				
				<form class="form" action="/board/download" method="post" 
					name="submitForm" id="submitForm">
					
					<c:if test="${not empty fileList}">	
						<div class="file-group">
							<span class="content">파일 목록</span>
							<br/>
							
								<c:forEach var="file" items="${fileList}">
									
									<input type="hidden" id="file_number" name="file_number" value="">
									
										<a href="#this" name="file" onclick="downloadFile('${file.FILE_NUMBER}')">
											${file.FILE_REALNAME}
										</a>(${file.FILE_SIZE} kb)
								
									<br/>
								</c:forEach>
							
						</div>
					</c:if>
				</form>
				
				<br/>

				<button type="button" id="listButton">게시판 목록으로 이동</button>
				
				<c:if test="${userEntity.user_number eq boardEntity.user_number}">
					<button type="button" id="updateButton">글 수정</button>
					<button type="button" id="deleteButton">글 삭제</button>
				</c:if>

			</div>

			<!-- 댓글 영역 -->
			<div class="insert-around top">
			
				<!-- 댓글 작성 영역 -->
				<c:if test="${whoLogIn == 1}">
					<form class="form" id="replyForm" action="/reply/insert" method="post" role="form">
					 
					 <%-- action="/reply/insert/${boardEntity.board_number}" method="post" 
						name="replyForm" --%> 
						
	
						<!-- board_number -->
							<input type="hidden" name="board_number" value="${boardEntity.board_number}">
						<!-- 게시글(댓글) 작성하는 user_number -->
							<input type="hidden" id="user_number" name="user_number"  value="${boardEntity.user_number}">
												
						<div class="insertDiv-group">	
							<table class="table-reply">
								<tr class="tr-reply">
									<th class="th th-reply-writer">작성자</td>
									<th class="th th-reply-content">댓글 내용</td>
								</tr>
								
								<tr class="tr-reply">
									<td class="td-reply-writer">${boardEntity.user_id}</td>
									<td class="td-reply-content">
										<input type="text" id="reply_content" name="reply_content" placeholder="댓글 입력 ...">
									</td>
								</tr>
							</table>
							<br>
							<button type="reset">취소</button>
							<button type="submit" id="replySubmitButton">댓글</button>
						</div>
					</form>
						<hr/>
				</c:if>
				<!-- 댓글 달리는 부분 -->
					<ul class="replied-group" id="reply-attach-object">
						
						<%-- <li class="each-reply"> <!-- 댓글 1개 -->
								<div class="div-replied">
									<span class="span-replied">아이디</span>
									<span class="span-replied">작성일자</span>
									
									<c:if test="${userEntity.user_number eq replyEntity.user_number}">
										<button>수정</button>
										<button>삭제</button>
									</c:if>
									
									<p class="p-replied">내용</p>
									<button class="button-replied" id="doReply">답글</button>
								</div>
						</li> --%>
						
					</ul>
					
					<form id="replySecondForm" action="/reply/secondInsert" method="post">
						<input type="hidden" name="reply_second_reply_number" value="">
						<input type="hidden" name="reply_second_content" value="">
						<input type="hidden" name="reply_second_user_number" value="">
						<input type="hidden" name="reply_second_board_number" value="">
					</form>
					
					<form id="replySecondUpdateForm" action="/reply/secondUpdate" method="post">
						<input type="hidden" name="reply_second_update_reply_number" value="">
						<input type="hidden" name="reply_second_update_content" value="">
						<input type="hidden" name="reply_second_update_board_number" value="">
					</form>
					
					<form id="replySecondDeleteForm" action="/reply/secondDelete" method="post">
						<input type="hidden" name="reply_second_delete_reply_number" value="">
						<input type="hidden" name="reply_second_delete_content" value="">
						<input type="hidden" name="reply_second_delete_board_number" value="">
					</form>
				
				
			</div>
		
		

	</div>


</body>
</html>