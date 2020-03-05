<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script src="/login.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.id}">
		<div id="lStatus">
			<ul>
				<li><label for="cid">아이디</label> <input id="cid" name="cid"
					type="email" size="20" placeholder="example@kings.com" autofocus
					maxlength="50">
					<button id="checkId">ID중복확인</button></li>
				<li><label for="cppasswd">비밀번호</label> <input id="cppasswd"
					name="cppasswd" type="password" size="20" placeholder="6~16자 문자"
					maxlength="16"></li>
					<button id="uLogin">로그인</button>
					<button id="uRes">회원가입</button>
			</ul>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.id}">
		<div id="lstatus">
			<ul>
				<li>
					${sessionScope.id}님이 로그인 하셨습니다.
					<div id="info">
						<table>
							<tr height="10">
								<td><button id="uLogout">로그아웃</button></td>
								<td><button id="uUpdate">회원 정보 변경</button></td>
								<td><form id="cartForm" method="post" action="/shoppingmall/cartList.do">
									<input type="hidden" name="buyer" value="${sessionScope.id}">
									<input type="submit" name="cart" value="장바구니">
								</form></td>
								<td><form id="buyForm" method="post" action="/shoppingmall/buyList.do">
									<input type="hidden" name="buyer" value="${sessionScope.id}">
									<input type="submit" name="buy" value="구매내역">
								</form></td>
							</tr>
						</table>
					</div>
				
			</ul>
		</div>
	</c:if>
</body>
</html>