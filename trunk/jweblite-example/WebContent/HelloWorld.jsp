<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/stylesheet.css" rel="stylesheet" type="text/css" />
<title>Hello World</title>
</head>
<body>

	<h3 style="text-decoration: underline;">
		<a href="index.html">JWebLite Example</a>
	</h3>
	<fieldset>
		<legend>Hello World</legend>
		<div>

			Hello ${Jwl.test}!

			<form action="" method="get">
				<input type="text" name="test" value="${Jwl.test}" /><input
					type="submit" />
			</form>

		</div>
	</fieldset>

</body>
</html>