/**
 * form에 빈 칸이 있는지 체크
 * 
 * @returns 빈칸이 있다면 false, 모두 입력했다면 true
 */
function check() {
	if (todoform.title.value == "") {
		alert("할일을 입력해 주세요.");
		todoform.title.focus();
		return false;
	} else if (todoform.name.value == "") {
		alert("이름을 입력해 주세요");
		todoform.name.focus();
		return false;
	} else if (!todoform.seq1.checked && !todoform.seq2.checked
			&& !todoform.seq3.checked) {
		alert("우선순위를 선택해 주세요");
		return false;
	} else {
		return true;
	}

}
