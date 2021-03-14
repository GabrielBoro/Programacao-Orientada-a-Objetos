<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tipo de Usuário</title>
</head>
<body style="text-align: center; font-family: Arial;">
	<%
		String msgBanco = (String) request.getAttribute("msgBanco");
	String cor = "";
	if (msgBanco == null) {
		msgBanco = "Escolha seu tipo de usuário";
		cor = "color: black;";
	} else {
		cor = "color: red;";
	}
	%>
	<h1 style="<%=cor%>"><%=msgBanco%></h1>
	<form action="ServletUsuario" method="post">
		<fieldset style="border: none;">
			<select name="usuario">
				<option disabled>Tipo de Usuário</option>
				<option value="1">Usuário Administrador</option>
				<option value="2">Usuário Comum</option>
			</select> <br>
			<input type="submit" value="Enviar" style="margin-top: 10px">
			<input type="submit" value="Usar o bot" name="bot" style="margin-top: 10px">
		</fieldset>
	</form>

</body>
</html>