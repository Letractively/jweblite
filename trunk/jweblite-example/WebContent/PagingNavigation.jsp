<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="PagingTag"
	uri="https://code.google.com/p/jweblite/PagingTag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Paging Navigation</title>
</head>
<body>

	<h3>
		<a href="index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Paging Navigation</legend>
		<div>

			<c:set var="dataProvider" value="${Jwl.dataProvider}" />
			<table border="1" cellpadding="5" cellspacing="0">
				<c:forEach var="viewItem" items="${dataProvider.viewList}">
					<tr>
						<td>${viewItem}</td>
					</tr>
				</c:forEach>
			</table>
			<br />

			<!-- Paging Navigation -->
			<PagingTag:paging index="index" provider="${dataProvider}">
				<PagingTag:prev>
					<a href="?page=${index}">&#60;&#60;</a>
				</PagingTag:prev>
				<PagingTag:pages selected="selected">
					<c:set var="viewIndex" value="${index + 1}" />
					<c:if test="${selected == true}">${viewIndex}</c:if>
					<c:if test="${selected == false}">
						<a href="?page=${index}">${viewIndex}</a>
					</c:if>
				</PagingTag:pages>
				<PagingTag:next>
					<a href="?page=${index}">&#62;&#62;</a>
				</PagingTag:next>
			</PagingTag:paging>

		</div>
	</fieldset>

</body>
</html>