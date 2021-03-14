<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<title>Insert title here</title>

<script type='text/javascript'>
	function addPistas() {
		var quantidade = document.getElementById("pista").value;
		var container = document.getElementById("container");
		while (container.hasChildNodes()) {
			container.removeChild(container.lastChild);
		}
		for (i = 0; i < quantidade; i++) {
			container.appendChild(document.createTextNode("Pista " + (i + 1)))
			var input = document.createElement("input");
			input.type = "text";
			input.name = "pista" + i;
			container.appendChild(input);
			container.appendChild(document.createElement("br"));
		}
		var enviar = document.createElement("input");
		enviar.type = "submit";
		enviar.value = "Salvar pistas";
		var form = document.getElementById("form");
		form.appendChild(enviar);
	}
</script>
</head>
<body>

	<% 
	String msgSuc = (String) request.getAttribute("msgSuc");
	if(msgSuc == null){
		msgSuc ="";
	}
	%>
	<h1 style="color:blue;"><%= msgSuc %></h1>

	

	<form action="ServletAdmin" id="form">
		<h4>Chave das pistas</h4>
		<input type="number" name="chave">
		<h4>Quantidade de pistas</h4>
		<input type="text" id="pista" name="pista" value=""> <a
			href="#" id="filldetails" onclick="addPistas()">Enviar</a>
		<div id="container"></div>
	</form>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
</body>
</html>