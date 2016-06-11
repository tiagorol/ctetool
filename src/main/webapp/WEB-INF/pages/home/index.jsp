<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>:: CTE Tool ::</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
	<div class="container myrow-container">
	    <div class="panel panel-success">
	        <div class="panel-heading">
	            <h3 class="panel-title">
					<b>Escolha a opção que você deseja</b>
	            </h3>
	        </div>
	        <div class="panel-body">
	            <a href="createConfiguration">Configuração</a><br/>
	            <a href="createTopologySingle">Cria Topologia Single</a><br/>
			    <a href="createTopologyMulti">Criar Topologia Multi</a>
	        </div>
	    </div>
	</div>
</body>
</html>