<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="Modelo.Pais, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Paises</title>
</head>
<body>
<h2>LISTA DE PAISES</h2>
<%
	
	ArrayList<Pais> Pais = (ArrayList<Pais>)request.getAttribute("Pais");
	for(Pais pais:Pais){
%>

<p>
<b>ID:</b><%= Pais.getId() %><br>	
<b>NOME:</b><%= Pais.getNome() %>	<br>
<b>POPULA��O:</b><%=Pais.getPopulacao() %>	<br>
<b>AREA:</b><%=Pais.getArea() %><br>
</p>
<% } %>
</body>
</html>