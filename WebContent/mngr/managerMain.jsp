<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
	src="http://code.jquery.com/jquery.min.js"></script>
<script src="/shoppingmall/mngr/managermain.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.id }">
		<div id="mList">
			<p>로그인 하세요.</p>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.id }">
		<div id="mList">
			<ul>
				<li>상품관련작업
				<li><button id="registProduct">상품등록</button>
				<li><button id="updateProduct">상품수정/삭제</button>
			</ul>
			<ul>
				<li>상품 QnA 작업
				<li><button id="qna">상품 QnA 답변</button>
			</ul>
		</div>
	</c:if>
</body>
</html>