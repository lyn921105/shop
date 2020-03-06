<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/shoppingmall/css/style.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script src="/shoppingmall/mngr/qnaProcess/qnalist.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.id }">
		<meta http-equiv="Refresh"
			content="0;url=/shoppingmall/mg/managerMain.do">
	</c:if>
	<div id="qnaHeader">
		<button id="bookMain">관리자 메인으로</button>
	</div>
	<c:if test="${count==0 }">
		<p>등록된 QnA가 없습니다.
	</c:if>
	<c:if test="${count>0 }">
		<div id="qnaList">
			<c:forEach var="qna" items="${qnaLists}">
				<ul>
					<c:if test="${qna.getQora()==1 }">
						<li><p>[${qna.getBook_title() }]상품에 대한 QnA></p>
							<p>${qna.getQna_writer() }<small class="date">${qna.getReg_date()}</small>
							</p>
							<p>${qna.getQna_content() }</p>
					</c:if>
					<c:if test="${qna.getReply()==0 }">
						<p>
							<button id="reply" name="${qna.getQna_id() }"
								oneclick="reply(this)">답변하기</button>
						</p>
					</c:if>
					<c:if test="${qna.getQora()==2 }">
						<li class="re">
							<p>${qna.getQna_content() }</p>
							<p>
								<c:if test="${qna.getQna_wtiter()=='manager' }">관리자</c:if>
								<small class="date">(${qna.getReg_date() })</small>
							</p>
							<p>
								<button id="editReply" name="${qna.getQna_id()}"
									onclick="edit(this)">수정</button>
							</p>
					</c:if>
				</ul>
				<hr>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>