<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="text-align: center;">

	<% 
	String msgErro = (String) request.getAttribute("msgErro");
	String cor = "";
	if(msgErro == null){
		msgErro ="Insira as coordenadas";
		cor = "color: black;";
	}else{
		cor = "color: red;";
	}
	%>
	<h1 style="<%= cor %>"><%= msgErro %></h1>
	<form action="ServletPista" method="post">
		<fieldset style="border:none;">
			<label>Latitude:</label>
			<input type="text" name="latitude" style="text-align:center;border:2px solid black;border-radius:10px;background-color:#AAA;color:#ffffff;">
		</fieldset>
		<fieldset style="border:none;">
			<label>Longitude:</label>
			<input type="text" name="longitude" style="text-align:center;border:2px solid black;border-radius:10px;background-color:#AAA;color:#ffffff;">
		</fieldset>
		<input type="submit" value="Enviar">
	</form>
</body>
</html>