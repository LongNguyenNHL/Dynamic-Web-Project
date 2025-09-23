<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!doctype html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<title>Student</title>
</head>
<body>
	<h1>Student List</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>class_name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var = "student" items = "${studentList}">
				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.className}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>