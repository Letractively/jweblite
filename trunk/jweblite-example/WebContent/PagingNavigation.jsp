<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Paging Navigation</title>
</head>
<body>

	<h3 style="text-decoration: underline;">
		<a href="index.html">JWebLite Example</a>
	</h3>
	<fieldset>
		<legend>Paging Navigation</legend>
		<div>

			<table border="1" cellpadding="5" cellspacing="1">
				<c:forEach var="userName" items="${JWL.dataProvider.viewList}">
					<tr>
						<td>${userName}</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<!-- Paging Navigation -->
			<c:if test="${JWL.dataProvider.hasPrevious}">
				<a href="?page=${JWL.dataProvider.currentIndex - 1}">&#60;&#60;</a>
			</c:if>
			<c:forEach var="pageIndex" items="${JWL.dataProvider.viewIndexList}">
				<c:choose>
					<c:when test="${pageIndex != JWL.dataProvider.currentIndex}">
						<a href="?page=${pageIndex}">${pageIndex + 1}</a>
					</c:when>
					<c:otherwise>${pageIndex + 1}</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${JWL.dataProvider.hasNext}">
				<a href="?page=${JWL.dataProvider.currentIndex + 1}">&#62;&#62;</a>
			</c:if>

		</div>
	</fieldset>

</body>
</html>