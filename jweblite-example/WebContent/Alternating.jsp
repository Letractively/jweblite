<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="JwlPageUtils"
	uri="https://code.google.com/p/jweblite/JwlPageUtils"%>
<%@ taglib prefix="RequestUtils"
	uri="https://code.google.com/p/jweblite/RequestUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ContextPath}/style/stylesheet.css" rel="stylesheet"
	type="text/css" />
<title>Alternating</title>
</head>
<body>

	<h3>
		<a href="${ContextPath}/index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Alternating</legend>
		<div>

			<ul>
				<JwlPageUtils:alternating var="colorIterator" data="red,green,blue" />
				<c:forEach begin="1" end="5">
					<c:set var="color" value="${colorIterator.next}" />
					<li style="color: ${color};">${color}</li>
				</c:forEach>
			</ul>

		</div>
	</fieldset>

</body>
</html>