# Introduction #

Getting Started

# Details #

**Depends:**
  * JRE 1.5+
  * Tomcat 5.5+
  * jstl.jar (JSTL 1.1+)
  * standard.jar (JSTL 1.1+)
  * commons-logging-1.1.1.jar
  * commons-lang-2.6.jar
  * commons-fileupload-1.2.2.jar
  * commons-io-2.2.jar
  * commons-codec-1.5.jar
  * json-20080701.jar



&lt;hr /&gt;



**web.xml:**
```
<filter>
	<display-name>JWebLiteFilter</display-name>
	<filter-name>JWebLiteFilter</filter-name>
	<filter-class>jweblite.web.JWebLiteFilter</filter-class>
	<init-param>
		<param-name>AttrPrefix</param-name>
		<param-value>Jwl</param-value>
	</init-param>
	<init-param>
		<param-name>Encoding</param-name>
		<param-value>UTF-8</param-value>
	</init-param>
	<init-param>
		<param-name>GZipEnabled</param-name>
		<param-value>true</param-value>
	</init-param>
	<init-param>
		<param-name>InitClassName</param-name>
		<param-value></param-value>
	</init-param>
	<init-param>
		<param-name>ErrorPage</param-name>
		<param-value></param-value>
	</init-param>
	<init-param>
	<param-name>FileUploadSizeMax</param-name>
		<param-value>10485760</param-value>
	</init-param>
</filter>
<filter-mapping>
	<filter-name>JWebLiteFilter</filter-name>
	<url-pattern>*.jsp</url-pattern>
	<dispatcher>FORWARD</dispatcher>
	<dispatcher>INCLUDE</dispatcher>
	<dispatcher>REQUEST</dispatcher>
	<dispatcher>ERROR</dispatcher>
</filter-mapping>
```

| **Param** | **Type** | **Required** | **Default** | **Comments** |
|:----------|:---------|:-------------|:------------|:-------------|
| AttrPrefix | String   | N            | Jwl         | namespace for EL |
| Encoding  | String   | N            | UTF-8       | charset encoding for request and response|
| GZipEnabled | boolean  | N            | true        | Is GZip enabled or not |
| InitClassName | String   | N            |             | load class on server startup |
| ErrorPage | String   | N            |             | error page name, "debug": debug mode, "": nothing |



&lt;hr /&gt;



**Implicit variables (JSP):**
| **Variable** | **Type** | **Comments** |
|:-------------|:---------|:-------------|
| JwlCP        | String   | context path |
| Jwl          | JWebLitePage | page instance |
| JwlFM        | FormModel | request form model |
| JwlSessionManager | JWebLiteSessionManager | session manager |