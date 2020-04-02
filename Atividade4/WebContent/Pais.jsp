<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Modelo.Pais" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pais</title>
</head>
<body>
<%Pais pais = (Pais)request.getAttribute("Pais");%>

<h1>País cadastrado!</h1><br>
<p>
<b>ID:</b> <%= Pais.getId() %><br>
<b>Nome:</b> <%= Pais.getNome() %><br>
<b>População:</b> <%= Pais.getPopulacao() %><br>
<b>Área:</b> <%= Pais.getArea() %>
</p>
</body>
</html>