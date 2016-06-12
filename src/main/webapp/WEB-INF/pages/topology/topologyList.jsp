<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<title>:: CTE Tool ::</title>

<script src="<c:url value="/resources/js/interact.min.js" />"></script>
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
                <div align="left"><b>Lista de Benchmark</b> </div>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty listBenchmark}">
                Não existem Benchmarks criados<br /><br />
            </c:if>
            <c:if test="${not empty listBenchmark}">   
            
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Nome</th>
                        <th>Tipo Topologia</th>
                        <th>Nº Instâncias LB</th>
                        <th>Nº Instâncias WP</th>
                        <th>Nº Instâncias BD</th>
                        <th>Tipo Instância BD</th>
                        <th>Tipo Instância WP</th>
                        <th>Nº de execuções</th>
                        <th>Workload</th>
                        <th>Apagar</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listBenchmark}" var="benchmark">
                        <tr>
                        	<th><c:out value="${benchmark.name}"/></th>
                        	<th><c:out value="${benchmark.typeTopology}"/></th>
                        	<th><c:out value="${benchmark.numberInstanceLb}"/></th>
                        	<th><c:out value="${benchmark.numberInstanceWp}"/></th>
                        	<th><c:out value="${benchmark.numberInstanceDb}"/></th>
                        	<th><c:out value="${benchmark.size}"/></th>
                        	<th><c:out value="${benchmark.sizeWordpress}"/></th>
                        	<th><c:out value="${benchmark.rounds}"/></th>
                        	<th><c:out value="${benchmark.workloads}"/></th>
                        	<th><a href="deleteBenchmark?id=<c:out value='${benchmark.id}'/>">Apagar</a></th>                         	
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <a href="index">Voltar</a>
        </div>
    </div>
</div>
    
</body>
</html>