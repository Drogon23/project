/**
 * TodoTypeServlet에 요청해 할일 type을 todo->doing, doing->done으로 변경 
 * 
 * 서버에서 변경 성공시 'success' 실패시 'fail' 출력
 */
function changeType(id, type) {

	var oReq = new XMLHttpRequest();

	oReq.addEventListener("load", function() {
		
		if (this.responseText == "success") {
			
			var clicked = document.activeElement;

			var clickedcontent = clicked.parentNode.parentNode;

			if (clickedcontent.parentNode.id == "todogroup") {
				clicked.outerHTML = clicked.outerHTML.replace("TODO", "DOING");
				document.getElementById("doinggroup").appendChild(
						clickedcontent);
			} else if (clickedcontent.parentNode.id == "doinggroup") {
				document.getElementById("donegroup")
						.appendChild(clickedcontent);
				clicked.remove();
			}
		} else {
			alert("변경실패");
		}
	});

	oReq.open("POST", "TodoTypeServlet");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oReq.send("id="+id+"&type="+type);
}