<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ContextPath}/style/stylesheet.css" rel="stylesheet"
	type="text/css" />
<title>File Upload</title>
</head>
<body>

	<h3>
		<a href="${ContextPath}/index.html">[ JWebLite Example ]</a>
	</h3>
	<fieldset>
		<legend>File Upload</legend>
		<div>

			<p>Hello ${Jwl.test}!</p>
			<p>File Uploaded: ${Jwl.file.name}</p>
			<br />
			<form action="" method="post" enctype="multipart/form-data">
				<input type="text" name="test" value="${Jwl.test}" /><br /> <input
					type="file" name="file" /><br /> <input type="submit"
					value="Submit" />
			</form>

		</div>
	</fieldset>

</body>
</html>