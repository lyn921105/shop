<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
	src="http://code.jquery.com/jquery.min.js"></script>
<script src="/shoppingmall/mngr/qnaProcess/qnawrite.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sexxionScope.id }">
		<meta http-equiv="Refresh"
			content="0;url=/shoppingmall/mg/mansgerMain.do">
	</c:if>

	<input type="hidden" id="qna_writer" value="manager">
	<input type="hidden" id="qna_id" value="${qna_id }">
	<input type="hidden" id="book_id" value="${book_id }">
	<input type="hidden" id="book_title" value="${book_title}">
	<input type="hidden" id="qora" value="${qora}">

	<div id="writeForm" class="box">
		<ul>
			<li><p>[${book_title }] 의 QnA</p>
				<P>QnA내용:${qna_content }</P>
			<li><label for="rContent">답변</label> <textarea id="rContent"
					rows="13" cols="50"></textarea>
			<li class="label2">
				<button id="replyPro">답변하기</button>
				<button id="cancle">취소</button>
		</ul>
	</div>
</body>
</html>