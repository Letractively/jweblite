<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Alternating</title>
</head>
<body>

	<h3>
		<a href="index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Alternating</legend>
		<div>

			<ul>
				<c:forEach begin="1" end="5">
					<c:set var="color" value="${Jwl.colorIterator.next}" />
					<li style="color: ${color};">${color}</li>
				</c:forEach>
			</ul>

		</div>
	</fieldset>

</body>
</html>