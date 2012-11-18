<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ContextPath}/style/stylesheet.css" rel="stylesheet"
	type="text/css" />
<title>Hello Application</title>
</head>
<body>

	<h3>
		<a href="${ContextPath}/index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Hello Application</legend>
		<div>

			Hello ${Jwl.initClassName}!

			<p>Server started at: ${Jwl.test}</p>

		</div>
	</fieldset>

</body>
</html>