<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
</head>
<body>

	<h3 style="text-decoration: underline;">Hello World</h3>

	Hello ${JWL.test}!

	<form action="" method="get">
		<input type="text" name="test" value="${JWL.test}" /><input
			type="submit" />
	</form>

</body>
</html>