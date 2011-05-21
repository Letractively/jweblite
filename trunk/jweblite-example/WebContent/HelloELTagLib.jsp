<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="StringUtils"
	uri="https://code.google.com/p/jweblite/StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Hello EL Tag Lib</title>
</head>
<body>

	<h3 style="text-decoration: underline;">
		<a href="index.html">JWebLite Example</a>
	</h3>
	<fieldset>
		<legend>Hello EL Tag Lib</legend>
		<div>

			<p>${StringUtils:join(Jwl.numberList, ",")}</p>

		</div>
	</fieldset>

</body>
</html>