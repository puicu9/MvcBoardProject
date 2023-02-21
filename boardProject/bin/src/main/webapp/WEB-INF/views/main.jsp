<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yeonghun</title>
</head>
<body>
<script type="text/javascript">
	<c:if test="${not empty message}">
		var message = "${message}" ; 	
		alert(message);
	</c:if>

</script>

	<h1>main</h1>
</body>
</html>