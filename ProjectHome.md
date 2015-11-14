## Lightweight Java Web Framework ##

  * Lightweight
  * Request (HelloWorld) -> Init controller automatically (HelloWorld.java) -> View (HelloWorld.jsp)
  * Change charset encoding automatically
  * Fast and concise

輕量的Java Web框架

**Usage:**

**HelloWorld.jsp**
```
EL:
Hello ${Jwl.test}!
```
**HelloWorld.java**
```
public class HelloWorld implements JWebLitePage {

	private String test = null;

	public HelloWorld() {
		super();
	}

	public void doRequest(WebContext context, FormModel fm) throws SkipException {
		this.test = fm.getString("test", "World");
	}

	public String getTest() {
		return test;
	}

}
```
**Result**
```
Hello World!
```



&lt;hr /&gt;



**See also:**

jQuery Multiple Select Box Plugin (http://code.google.com/p/jquerymultipleselectbox/)

jQuery Lazy Scroll Loading Plugin (http://code.google.com/p/jquerylazyscrollloading/)

JDBMapLite (http://code.google.com/p/jdbmaplite/)