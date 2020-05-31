<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Paises</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">


</head>

<body>
    <!-- Barra superior com os menus de navegacao£o -->
	<c:import url="Menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header" style="text-align: center;">SITE PARA Cadastramento  de País</h3>
        <hr>
        <div class="row">
        	<a class="btn btn-primary" href="CriarPaises.jsp" style="margin-right:20px; margin-left:330px;">CRIAR UM PAÍS</a>
        	<a class="btn btn-primary" href="ListarPaises.jsp" style="margin-right:20px;">BUSCAR UM PAÍS</a>
        	<a class="btn btn-primary" href="ListarPaises.jsp?acao=reiniciar">LISTAR PAISES</a>
        	
        </div>	
        
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>