<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/common.jsp"%>

<!-- boardInsert 관련 스크립트 -->
<script src="<c:url value="/resources/js/board/boardList.js?ver=2"/>"></script>

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
		
		<table class="boardList">
			
			<thead>
				<tr class="boardList">
					<th class="th number">글번호</th>
					<th class="th title">글제목</th>
					<th class="th name">작성자</th>
					<th class="th inputdate">작성일자</th>
				</tr>
			
			</thead>
			
			<tbody>
				<c:forEach var="item" items="${boardList}">
					<tr>
						<td class="number"><c:out value="${item.board_number}" /></td>
						
							<td class="title">
								<a href="/board/list/${item.board_number}"><c:out value="${item.board_title}" /></a>
							</td>
						
						<td class="name"><c:out value="${item.user_name}" /></td>
						<td class="inputdate"><fmt:formatDate value="${item.board_inputdate}" pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			
			
			</tbody>
		
		
		</table>
		<button type="button" id="writeButton" class="writeButton">글쓰기</button>
		
			<div>
			  <ul class="paging">
			    <c:if test="${pageMaker.prev}">
			    	<li class="paging"><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
			    </c:if> 
			
			    <c:forEach var="idx" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			    	<li class="paging"><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
			    </c:forEach>
			
			    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			    	<li class="paging"><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
			    </c:if>
			  </ul>





		</div>
		





</body>
</html>