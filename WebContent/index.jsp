<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="FrontController" method='post'>
<input type="submit" name="command" value="DOM"/>
</form>
<br/>
<form action="FrontController" method='post'>
<input type="submit" name="command" value="SAX"/>
</form>
<br/>
<form action="FrontController" method='post'>
<input type="submit" name="command" value="STAX"/>
</form>
<br/>
</body>
</html>