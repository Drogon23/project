<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/main.js"></script>

</head>
<body>
	<header>
		<h1>나의 해야할 일들</h1>
		<div class="add">
			<button onclick="window.location='TodoFormServlet'">새로운 todo 등록</button>
		</div>
	</header>
	<div class="wrap">
		<table>
			<th><span>TODO</span></th>
			<tr id="todogroup">
				<c:forEach var="todoList" items="${todoList}">
					<td>
						<div class="content">
							<span class="title">${todoList.title}</span><br>
							${todoList.regdate}, ${todoList.name}, 우선순위 ${todoList.sequence}
							<button class="mvright"
								onclick="changeType(${todoList.id},'${todoList.type}')">➜</button>
						</div>
					</td>
				</c:forEach>

			</tr>
		</table>
		<table>
			<th><span>DOING</span></th>
			<tr id="doinggroup">
				<c:forEach var="doingList" items="${doingList}">
					<td>
						<div class="content">
							<span class="title">${doingList.title}</span><br>
							${doingList.regdate}, ${doingList.name}, 우선순위
							${doingList.sequence}
							<button class="mvright"
								onclick="changeType(${doingList.id},'${doingList.type}')">➜</button>
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
		<table>
			<th><span>DONE</span></th>
			<tr id="donegroup">
				<c:forEach var="doneList" items="${doneList}">
					<td>
						<div class="content">
							<span class="title">${doneList.title}</span><br>
							${doneList.regdate}, ${doneList.name}, 우선순위 ${doneList.sequence}
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>