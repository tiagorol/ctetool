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
                        <div class="control-label col-xs-3"> <form:label path="image" >Image</form:label> </div>
                        <div class="col-xs-6">
                            <form:hidden path="id" value="${configurationObject.id}"/>
                            <form:input cssClass="form-control" path="image" value="${configurationObject.image}"/>
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

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function submitConfigurationForm() {				
			
		    // getting the configuration form values
		    var name = $('#name').val().trim();
		    var age = $('#age').val();
		    var salary = $('#salary').val();
		    if(name.length ==0) {
		        alert('Please enter name');
		        $('#name').focus();
		        return false;
		    }
	
		    if(age <= 0) {
		        alert('Please enter proper age');
		        $('#age').focus();
		        return false;
		    }
	
		    if(salary <= 0) {
		        alert('Please enter proper salary');
		        $('#salary').focus();
		        return false;
		    }
		    return true;
		};	
	</script>

</body>
</html>