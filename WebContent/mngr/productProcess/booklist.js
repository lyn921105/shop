/**
 * 
 */

$(document).ready(function(){
	// [책등록] 버튼 클릭
	$("#regist").click(function(){
		window.location.href("/shoppingmall/mg/bookregisterForm.do");
	});
	
	// [관리자 메인으로] 버튼 클릭
	$("#bookMain").click(function(){
		window.location.href("/shoppingmall/mg/managerMain.do");
	});
	
	// [수정] 버튼 클릭
	function edit(editBtn) {
		var rStr = editBtn.name;
		var arr = rStr.split(",");
		var query = "/shoppingmall/mg/bookUpdateForm.do?book_id="+arr[0];
		query += "&book_kind=" + arr[1];
		window.location.href(query);
	}
	
	// [삭제] 버튼 클릭
	function del(delBtn) {
		var rStr = delBtn.name;
		var arr = rStr.split(",");
		var query = "/shoppingmall/mg/bookDeletePro.do?book_id="+arr[0];
		query += "&book_kind=" + arr[1];
		window.location.href(query);
	}
});