<%@page import="dominio.TipoSeguro" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="Inicio.jsp">Inicio</a> <a href="servletSeguro?op=a">Agregar seguro</a> <a href="servletSeguro?op=l">Listar seguros</a>
<br><br>
<h1>Agregar Seguro</h1>

<form method="post" action="servletSeguro">
	<% if (request.getAttribute("success")!=null) { %>
		<font color="green"><%=request.getAttribute("success")%></font> <br>
	<%} else if (request.getAttribute("error")!=null) {%>
		<font color="red"><%=request.getAttribute("error")%></font> <br>
	<%} %>
	<%
		ArrayList<TipoSeguro> listTipoSeguro = null;
		if (request.getAttribute("listaTipoSeguros")!=null) {
			listTipoSeguro = (ArrayList<TipoSeguro>) request.getAttribute("listaTipoSeguros");
		}
	%>
	IdSeguros: 
	<%=
		request.getAttribute("proxID")
	%> <br>
	Descripcion			<input type="text" name="txtDescripcion" required><br><br>
	Tipo de Seguro		<select name="tipoSeguro"> 
	<%
		if(listTipoSeguro!=null) {
			for(TipoSeguro tipo : listTipoSeguro){ %>
		<option value="<%=tipo.getIdTipo()%>"><%=tipo.getDescripcion()%></option>
	<%}} else {%>
		<option value="0"> ----- </option>
	<%} %>
	</select> <br><br>
	Costo contratacion	<input type="number" name="txtCostoContratacion" required><br><br>
	Costo Maximo Asegurado	<input type="number" name="txtCostoMax" required><br><br>
	<input type="submit" name="btnAceptar" value="Aceptar">
	
</form>

</body>
</html>