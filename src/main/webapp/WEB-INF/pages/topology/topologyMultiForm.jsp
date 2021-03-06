<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>:: CTE Tool ::</title>

<script src="<c:url value="/resources/js/interact.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">

<style type="text/css">
	.myrow-container {
      margin: 20px;
    }
       
	#load-balance-dropzone {
	  height: 120px;
	}
	
	#aplication-layer-dropzone {
	  height: 120px;
	}
	
	#database-dropzone {
	  height: 120px;
	}
	
	.dropzone {
	  background-color: #ccc;
	  border: dashed 4px transparent;
	  border-radius: 4px;
	  margin: 10px auto 30px;
	  padding: 10px;
	  width: 450px;
	  transition: background-color 0.3s;
	}
	
	.drop-active {
	  border-color: #aaa;
	}
	
	.drop-target {
	  background-color: #29e;
	  border-color: #fff;
	  border-style: solid;
	}
	
	.drag-drop {
	  display: inline-block;
	  min-width: 40px;
	  padding: 2em 0.5em;
	
	  color: #fff;
	  background-color: #29e;
	  border: solid 2px #fff;
	
	  -webkit-transform: translate(0px, 0px);
	          transform: translate(0px, 0px);
	
	  transition: background-color 0.3s;
	}
	
	.drag-drop.can-drop {
	  color: #000;
	  background-color: #4e4;
	}

</style>

</head>
<body class=".container-fluid">
	
	<div class="container myrow-container">
	    <div class="panel panel-success">
	        <div class="panel-heading">
	            <h3 class="panel-title">
					<b>Arraster e solte os componentes para construir a Topologia</b>
	            </h3>
	        </div>
	        <div class="panel-body">
	            
				<div id="div-drag-nginx" onMouseOut="moverInicioSePosicaoInvalida('drag-nginx')" class="draggable drag-drop drag-nginx">Load Balancer (NGinx)</div>
				
				<div id="div-drag-wordpress1" onMouseOut="moverInicioSePosicaoInvalida('drag-wordpress1')" class="draggable drag-drop drag-wordpress1">WordPress</div>
				<div id="div-drag-wordpress2" onMouseOut="moverInicioSePosicaoInvalida('drag-wordpress2')" class="draggable drag-drop drag-wordpress2">WordPress</div>
				<div id="div-drag-wordpress3" onMouseOut="moverInicioSePosicaoInvalida('drag-wordpress3')" class="draggable drag-drop drag-wordpress3">WordPress</div>
				<div id="div-drag-wordpress4" onMouseOut="moverInicioSePosicaoInvalida('drag-wordpress4')" class="draggable drag-drop drag-wordpress4">WordPress</div>
				<div id="div-drag-wordpress5" onMouseOut="moverInicioSePosicaoInvalida('drag-wordpress5')" class="draggable drag-drop drag-wordpress5">WordPress</div>
				
				<div id="div-drag-mysql" onMouseOut="moverInicioSePosicaoInvalida('drag-mysql')" class="draggable drag-drop drag-mysql">MySQL</div>
	            
	            <br/>
	            <br/>
	            <br/>
	            
                <form:form cssClass="form-horizontal" modelAttribute="benchmark" method="post" action="saveBenchmark">
                
				 	<div class="help-block with-errors" style="margin: 0 auto; width: 40%;">
		            	<form:errors path="numberInstanceLb" />
		            </div>
		            
		            <div class="help-block with-errors" style="margin: 0 auto; width: 40%;">
		            	<form:errors path="numberInstanceWp" />
		            </div>
		            
		            <div class="help-block with-errors" style="margin: 0 auto; width: 40%;">
		            	<form:errors path="numberInstanceDb" />
		            </div>
		            
					<div id="load-balance-dropzone" class="dropzone">
						Load Balancer
				 	</div>
				 	
				 	<div id="aplication-layer-dropzone" class="dropzone">
						Aplication Layer
				 	</div>
				 	
				 	 <div id="database-dropzone" class="dropzone">
						Database
				 	</div>
			 	
                    <div class="form-group">
                        <div class="col-xs-6">
                            <form:hidden path="type" value="1"/>
                            <form:hidden id="numberInstanceLb" path="numberInstanceLb" value="0" />
                            <form:hidden id="numberInstanceWp" path="numberInstanceWp" value="0" />
                            <form:hidden id="numberInstanceDb" path="numberInstanceDb" value="0" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="name" >Nome</form:label> </div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="name" value="${benchmarkObject.name}"/>
                        </div>
                        <div class="help-block with-errors">
                        	<form:errors path="name" />
                        </div>
                   	</div>
                    
                    <input type="submit" id="saveBenchmark" onclick="beforeSubmit()" class="btn btn-primary" value="Salvar" />
                	<a href="index">Voltar</a>     
                </form:form>
	        </div>
	    </div>
	</div>
	
 	<input id="ondrop-drag-nginx" type="text" style="display: none;" />
 	<input id="ondrop-drag-wordpress1" type="text" style="display: none;" />
 	<input id="ondrop-drag-wordpress2" type="text" style="display: none;" />
 	<input id="ondrop-drag-wordpress3" type="text" style="display: none;" />
 	<input id="ondrop-drag-wordpress4" type="text" style="display: none;" />
 	<input id="ondrop-drag-wordpress5" type="text" style="display: none;" />
 	<input id="ondrop-drag-mysql" type="text" style="display: none;" />

	<script type="text/javascript">
	
		interact('.draggable')
		  .draggable({
		    inertia: true,
		    
		    restrict: {
		      restriction: "parent",
		      endOnly: true,
		      elementRect: { top: 0, left: 0, bottom: 1, right: 1 }
		    },
		    
		    autoScroll: true,
		    
		    onmove: dragMoveListener
		  });
	
		function dragMoveListener (event) {
		  var target = event.target,
		      x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx,
		      y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
	
		  target.style.webkitTransform =
		  target.style.transform = 'translate(' + x + 'px, ' + y + 'px)';
	
		  target.setAttribute('data-x', x);
		  target.setAttribute('data-y', y);
		}
	
		window.dragMoveListener = dragMoveListener;
	
		//load-balance-dropzone
		interact('#load-balance-dropzone').dropzone({
		  accept: '.drag-nginx',

		  overlap: 1.00,
	
		  ondropactivate: function (event) {
		    event.target.classList.add('drop-active');
		  },
		  
		  ondragenter: function (event) {
			ondragenter(event);
		  },
		  
		  ondragleave: function (event) {
			ondragleave(event, 'drag-nginx');
		  },
		  
		  ondrop: function (event) {
			document.getElementById('ondrop-drag-nginx').value = 'true';
		  },
		  
		  ondropdeactivate: function (event) {
			ondropdeactivate(event);
		  }
		});
		
		//aplication-layer-dropzone
		interact('#aplication-layer-dropzone').dropzone({
		  accept: '.drag-wordpress1, .drag-wordpress2, .drag-wordpress3, .drag-wordpress4, .drag-wordpress5',

		  overlap: 1.00,
	
		  ondropactivate: function (event) {
		    event.target.classList.add('drop-active');
		  },
		  
		  ondragenter: function (event) {
			ondragenter(event);
		  },
		  
		  ondragleave: function (event) {
			ondragleave(event, 'drag-' + getValorWordpress(event));
		  },
		  
		  ondrop: function (event) {
			 document.getElementById('ondrop-drag-' + getValorWordpress(event)).value = 'true';
		  },
		  
		  ondropdeactivate: function (event) {
			ondropdeactivate(event);
		  }
		});
		
		//database-dropzone
		interact('#database-dropzone').dropzone({
		  accept: '.drag-mysql',

		  overlap: 1.00,
	
		  ondropactivate: function (event) {
		    event.target.classList.add('drop-active');
		  },
		  
		  ondragenter: function (event) {
			ondragenter(event);
		  },
		  
		  ondragleave: function (event) {
			ondragleave(event, 'drag-mysql');  
		  },
		  
		  ondrop: function (event) {
			document.getElementById('ondrop-drag-mysql').value = 'true';
		  },
		  
		  ondropdeactivate: function (event) {
			ondropdeactivate(event);
		  }
		});
		
		function beforeSubmit(){
			var input_nginx = document.getElementById('ondrop-drag-nginx').value;
			var input_mysql = document.getElementById('ondrop-drag-mysql').value;
			
			for(i = 1; i < 6; i++){
				var input = document.getElementById('ondrop-drag-wordpress' + i).value;
				if(input == 'true'){
					addNumberInstanceWp();
				}
			}
			
			if(input_nginx == 'true'){
				addNumberInstanceLb();
			}
			
			if(input_mysql == 'true'){
				addNumberInstanceDb();
			}
		}
		
		function addNumberInstanceLb(){
			var numberInstanceLb = parseInt(document.getElementById('numberInstanceLb').value);
			document.getElementById('numberInstanceLb').value = numberInstanceLb + 1;
		}
		
		function addNumberInstanceWp(){
			var numberInstanceWp = parseInt(document.getElementById('numberInstanceWp').value);
			document.getElementById('numberInstanceWp').value = numberInstanceWp + 1;
		}
		
		function addNumberInstanceDb(){
			var numberInstanceDb = parseInt(document.getElementById('numberInstanceDb').value);
			document.getElementById('numberInstanceDb').value = numberInstanceDb + 1;
		}
		
		function moverInicioSePosicaoInvalida(valor){
			if(document.getElementById('ondrop-' + valor).value != 'true'){
				document.getElementById('div-' + valor).removeAttribute('style');
				document.getElementById('div-' + valor).removeAttribute('data-x');
				document.getElementById('div-' + valor).removeAttribute('data-y');
			}
		}
		
		function ondragleave(event, valor){
			document.getElementById('ondrop-' + valor).value = 'false';
		    event.target.classList.remove('drop-target');
		    event.relatedTarget.classList.remove('can-drop');
		}
		
		function ondropdeactivate(event){
			 event.target.classList.remove('drop-active');
			 event.target.classList.remove('drop-target');
		}
		
		function ondragenter(event){
			event.target.classList.add('drop-target');
		    event.relatedTarget.classList.add('can-drop');
		}
		
		function getValorWordpress(event){
			var classList = '' + event.relatedTarget.classList;
			
		 	if(classList.indexOf('1') > -1){
				return 'wordpress1';				  
			}else if(classList.indexOf('2') > -1){
				return 'wordpress2'; 
			}else if(classList.indexOf('3') > -1){
				return 'wordpress3';
			}else if(classList.indexOf('4') > -1){
				return 'wordpress4';
			}else if(classList.indexOf('5') > -1){
				return 'wordpress5';
			}
		}
		
	</script>
	
</body>
</html>