<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<title>:: CTE Tool ::</title>

<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/highcharts.js" />"></script>
<script src="<c:url value="/resources/js/exporting.js" />"></script>

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
                <div align="left"><b>Lista de Resultados dos Testes</b> </div>
            </h3>
        </div>
        <div class="panel-body">
            <c:if test="${empty listResult}">
                Não existem resultados<br /><br />
            </c:if>
            <c:if test="${not empty listResult}">   
            
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <th>Data</th>
                        <th>Benchmark</th>
                        <th>Workload</th>
                        <th>Nº Request</th>
                        <th>Nº Request OK</th>
                        <th>Nº Request Erro</th>
                        <th>Menor Tempo Resposta</th>
                        <th>Máximo Tempo Resposta</th>
                        <th>Média Tempo Resposta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listResult}" var="result">
                        <tr>
                        	<th><c:out value="${result.dateTestFormat}"/></th>
                        	<th><c:out value="${result.benchmark.name}"/></th>
                        	<th><c:out value="${result.workload}"/></th>
                        	<th><c:out value="${result.numberRequest}"/></th>
                        	<th><c:out value="${result.numberRequestOK}"/></th>
                        	<th><c:out value="${result.numberRequestKO}"/></th>
                        	<th><c:out value="${result.minResponseTime}"/></th>
                        	<th><c:out value="${result.maxResponseTime}"/></th>
                        	<th><c:out value="${result.meanResponseTime}"/></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <a href="listBenchmark">Voltar</a>
        </div>
    </div>
</div>
    
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
   
<script type="text/javascript">

    var	options = {
       	chart: {
           	renderTo: 'container',
           	type: 'spline'
       	},
        title: {
            text: 'Gráfico Desempenho',
            x: -20
        },
        subtitle: {
            text: 'Tempo de Resposta X Workload',
            x: -20
        },
        credits: {
			enabled: false
        },
        xAxis: {
        	title: {
                text: 'Workload'
            },
            categories: []
        },
        exporting: {
        	enabled: false
        },
        yAxis: {
            title: {
                text: 'Tempo de Resposra (ms)'
            }
        },
        tooltip: {
            valueSuffix: ' ms',
            crosshairs: true,
            shared: true
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            borderWidth: 0
        },
        series: []
    };
    
	$(document).ready(function() {
		 var chart = new Highcharts.Chart(options);
		 chart.showLoading('Carregando Gráfico...');
		 
	     $.ajax({
	        url: 'graphic',
	        type: 'GET',
	        async: true,
			data: 'id=<%= request.getParameter("id") %>',
	        dataType: "json",
	        success: function(data) {
	      		
	        	for (var i=0; i < data.series.length; i++){
	                options.series[i] = data.series[i];
                }
	        	
	           	options.xAxis.categories = data.categories;
	        	chart = new Highcharts.Chart(options);
	        },
	        cache: false
	    });
	}); 
	
</script>
    
</body>
</html>
