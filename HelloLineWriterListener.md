# Introduction #

Hello LineWriterListener

# Details #

**Usage:**

HelloLineWriterListener.jsp
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hello LineWriterListener</title>
</head>
<body>

	<p>Hello LineWriterListener!</p>

</body>
</html>
```

HelloLineWriterListener.java
```
public class HelloLineWriterListener implements JWebLitePage, LineWriterListener {

	public HelloLineWriterListener() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm) throws SkipException {
	}

	public void onAfterLine(Writer writer, int index, String line) throws IOException {
		if (line.contains("Hello LineWriterListener!")) {
			writer.write("<br />LineWriterListener: Hello Sir!");
		}
	}

	public void onBeforeLine(Writer writer, int index, String line) throws IOException {
	}

	public void onFirstLine(Writer writer) throws IOException {
	}

	public void onLastLine(Writer writer) throws IOException {
	}

}
```

**Result:**
```
Hello LineWriterListener!

LineWriterListener: Hello Sir!
```