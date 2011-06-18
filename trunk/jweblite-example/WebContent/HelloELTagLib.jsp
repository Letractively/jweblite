<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="RequestELUtils"
	uri="https://code.google.com/p/jweblite/RequestELUtils"%>
<%@ taglib prefix="StringUtils"
	uri="https://code.google.com/p/jweblite/StringUtils"%>
<%@ taglib prefix="StringELUtils"
	uri="https://code.google.com/p/jweblite/StringELUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Hello EL Tag Lib</title>
</head>
<body>

	<h3>
		<a href="index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Hello EL Tag Lib</legend>
		<div>

			Hello ${RequestELUtils:getParameter(JwlReq, "test", "")}!

			<div style="font-size: small; font-weight: bold;">String:
				HelloELTagLib</div>
			<div style="font-size: small; font-weight: bold;">Array:
				${Jwl.numberList}</div>
			<br />

			<ul>
				<li>String substring:
					${StringELUtils:substring("HelloELTagLib", 7, -1)}</li>
				<li>Array join: ${StringUtils:join(Jwl.numberList, ", ")}</li>
				<li>String concat:
					${StringELUtils:concat(StringELUtils:substring("HelloELTagLib", 7,
					-1), StringUtils:join(Jwl.numberList, ", "))}</li>
			</ul>

		</div>
	</fieldset>

</body>
</html>