<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/todoform.js"></script>

</head>
<body>
	<header>
		<p>할일 등록</p>
	</header>
	<form name="todoform" method="post" action="TodoAddServlet"
		onsubmit="return check()">
		<p class="pcontent">어떤일인가요?</p>
		<p>
			<input type="box" name="title" class="title" maxlength="24">
		</p>
		<p class="pcontent">누가 할일인가요?</p>
		<p>
			<input type="box" name="name" class="name">
		</p>
		<p class="pcontent">우선순위를 선택하세요</p>
		<p class="sequence">
			<input type="radio" id="seq1" name="seq" value="1" >1순위&emsp;
			<input type="radio" id="seq2" name="seq" value="2" >2순위&emsp;
			<input type="radio" id="seq3" name="seq" value="3" >3순위
		</p>
		<input type="button" onclick="window.location.href='MainServlet'" class="back" value="<이전">
		<input type="submit" class="btn">
		<input type="reset" class="btn">
	</form>

</body>
</html>