<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="JwlPageUtils"
	uri="https://code.google.com/p/jweblite/JwlPageUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Hello Corner</title>
</head>
<body>

	<h3>
		<a href="index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>Hello Corner</legend>
		<div>

			<JwlPageUtils:corner header="Hello Corner" width="300px"
				title="Corner">
				Hello Corner
			</JwlPageUtils:corner>

			<br /> <br /> <br />

			<JwlPageUtils:corner width="300px">
				Hello Corner
			</JwlPageUtils:corner>

		</div>
	</fieldset>

</body>
</html>