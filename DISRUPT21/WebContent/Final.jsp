<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String msgChave = (String) request.getAttribute("msgChave");
	String cor = "";
	int chaveBot = 0;
	chaveBot = (Integer) request.getAttribute("chaveBot");
		if (msgChave == null) {
			msgChave = "Descubra a chave";
			cor = "color: black;";
		} else {
			cor = "color: red;";
		}
		String[] pista = (String[]) request.getAttribute("pista");
		for (int x = 0; x < pista.length; x++) {
	%><h4>
		Pista
		<%=x + 1%>:
		<%=pista[x]%></h4>
	<%
		}
	%>

	<h1 style="<%=cor%>"><%=msgChave%></h1>
	<form action="ServletFim" method="post">
		<fieldset style="border: none;">
			<label>Digite a chave:</label> <input type="text" name="chave"
				value="<%=chaveBot%>"
				style="text-align: center; border: 2px solid black; border-radius: 10px; background-color: #AAA; color: #ffffff;">
		</fieldset>
		<input type="submit" value="Enviar">
	</form>
</body>
</html>