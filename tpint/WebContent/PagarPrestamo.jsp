<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.Prestamo" %>
    <%@page import="entidad.Cuenta" %>
    <%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Pagar Prestamo</title>
</head>
<body>
<div class="encabezado">
<nav class="dropdownmenu">
  <ul>
    <li><a href="HomeCliente.jsp">Home</a></li>
      <li><a href="MisCuentas.jsp">Mis Cuentas</a></li>
    <li><a href="ServletTransferencias?Transfer=1">Transferir</a></li>
    <li><a href="#">Prestamos</a>
	   	<ul id="submenu">
	   		<li><a href="ServletPrestamos?op=mp">Mis prestamos</a></li>
	   		<li><a href="ServletPrestamos?op=s">Solicitar prestamo</a></li>
	   	</ul>
    </li>
	<li><a  href="InfoCliente.jsp" id="usuario"><%= session.getAttribute("Session_us") %></a>
		     <ul id="submenu">
 			<li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
	</ul>
	<li>
  </ul>
</nav>
</div>
<div align="center">
	<%if(request.getAttribute("prestamo") != null) {
		Prestamo prestamo = (Prestamo)request.getAttribute("prestamo");
	%>
	<%ArrayList<Integer> cuotasAPagar = (ArrayList<Integer>) request.getAttribute("cuotasAPagar");
		if (cuotasAPagar != null && cuotasAPagar.size() > 0 ) {
	%>
	<h2>Pagar Prestamo </h2>
	<% if (request.getAttribute("success")!=null) { %>
	<font color="green">
		<%=request.getAttribute("success")%>
	</font> <br>
	<%} else if (request.getAttribute("error")!=null) {%>
		<font color="red">
			<%=request.getAttribute("error")%>
		</font> <br>
	<%} %>
	<form action="ServletPrestamos" method="post">
		<table>
			<td>
			<input type="hidden" id="idPrestamo" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
			<p> Nro. de prestamo <%= prestamo.getIdPrestamo() %>
		      <p>  Cuota:
		      	<select name="Cuotas">
		      		<% for (int cuota : cuotasAPagar) { %>
		      			<option value="<%=cuota%>"> <%=cuota%> </option>
		      		<% } %>
				</select>
				<input type="hidden" name="montoTotal" value=<%=request.getAttribute("montoTotal")%>>
				<p>Monto total: $<%=request.getAttribute("montoTotal") %></p>
				<input type="hidden" name="montoRestante" value=<%=request.getAttribute("montoRestante")%>>
				<p>Monto restante: $<%=request.getAttribute("montoRestante") %></p>
				<input type="hidden" name="montoCuota" value=<%=request.getAttribute("montoCuota")%>>
				<p>Valor de cuota: $<%=request.getAttribute("montoCuota") %></p>
 				<p> Cuenta:
							<select name="Cuenta" required>
						<% 
							ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentasCte");
							if (cuentas != null) {%>
							<%for (Cuenta cuenta : cuentas) {%>
								<option value="<%= cuenta.getCbu()%>"> <%=cuenta.getTipoCuenta()%> - $ <%= cuenta.getSaldo()%> </option>
						<%}} else {%>
								<option value="0">No existen cuentas</option>
						<%} %>
				</select>
	     	 <br><br><p><input type="submit" name="btnPagarPrestamo" value="Pagar"></input></br></p>
	     
	     </td>   
		</table>
	</form>
	<%} else {%>
		<h2>El prestamo no posee cuotas a pagar o ya ha sido abonado en su totalidad</h2>
	<%} %>
	<%} else {%>
		<h2>No se selecciono ningun prestamo</h2>
	<%} %>
	</div>
</body>
</html>