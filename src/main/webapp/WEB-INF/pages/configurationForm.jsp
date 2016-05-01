<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>:: CTE Tool ::</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
    <div class="container myrow-container">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">
                    Configuração
                </h3>
            </div>
            <div class="panel-body">
                <form:form id="configurationRegisterForm" cssClass="form-horizontal" modelAttribute="configuration" method="post" action="saveConfiguration">
    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="image" >Imagem</form:label> </div>
                        <div class="col-xs-6">
                            <form:hidden path="id" value="${configurationObject.id}"/>
                            <form:input cssClass="form-control" path="image" value="${configurationObject.image}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="image" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="size" >Tipo Instância</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="size" value="${configurationObject.size}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="size" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="sizeWordpress" >Tipo Instância WordPress</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="sizeWordpress" value="${configurationObject.sizeWordpress}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="sizeWordpress" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="agentUser" >Usuário (SSH)</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="agentUser" value="${configurationObject.agentUser}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="agentUser" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="hostCrawler" >Host Crawler</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="hostCrawler" value="${configurationObject.hostCrawler}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="hostCrawler" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="rounds" >Número de Execuções</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="rounds" value="${configurationObject.rounds}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="rounds" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="workloads" >Workload</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="workloads" value="${configurationObject.workloads}"/>
                            <div class="help-block">
                            	Ex.: 100,300,500,700
                            </div>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="workloads" />
                        </div>
                    </div>
    
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-4">
                            </div>
                            <div class="col-xs-4">
                                <input type="submit" id="saveConfiguration" class="btn btn-primary" value="Salvar" />
                            </div>
                            <div class="col-xs-4">
                            </div>
                        </div>
                    </div>
    
                </form:form>
                
                <a href="index">Voltar</a>
            </div>
        </div>
    </div>

</body>
</html>