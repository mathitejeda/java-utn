<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
			integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		<style type="text/css">
			<jsp:include page="css\StyleSheet.css"></jsp:include>
		</style>
		<title> Autorizar prestamos </title>
	</head>

	<body>
	<div class="encabezado">
		<nav class="dropdownmenu">
  <ul>
    <li><a href="HomeAdmin.jsp">Home</a></li>
    <li><a href="#">Clientes</a>
      <ul id="submenu">
        <li><a href="ServletCliente?AgregarClientes=1">Dar de alta</a></li>
        <li><a href="servlet?ListarClientes=1">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Cuentas</a>
       <ul id="submenu">
        <li><a href="ServletCuenta?AgregarCuentas=1">Dar de alta</a></li>
       <li><a href="ServletCuenta?ListarCuentas=1">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Prestamos</a>
      <ul id="submenu">
        <li><a href="ServletPrestamos?op=ap">Autorizar</a></li>
        <li><a href="ServletPrestamos?op=l">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Informes</a>
      <ul id="submenu">
        <li><a href="ServletInformes?ListarMovimientosPorMes=1">Movimientos</a></li>
         <li><a href="ServletInformes?ListarPrestamosPorMes=1">Prestamos</a></li>
      </ul>
    </li>
    <li><a href="#">Usuarios</a>
       <ul id="submenu">
        <li><a href="servlet?AgregarUsuario=1">Dar de alta</a></li>
      </ul>
    </li>
     <li><a href="ServletCliente?InfoCliente=1" id="usuario"><%= session.getAttribute("Session_us") %></a>
            <ul id="submenu">
        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
      </ul>
      <li>
  </ul>
</nav>
		</div>
		<div align="center" style="padding: 15px">
			<h2>Prestamos pendientes de autorizacion</h2>
		<% if (request.getAttribute("success")!=null) { %>
			<font color="green">
				<%=request.getAttribute("success")%>
			</font> <br>
			<%} else if (request.getAttribute("error")!=null) {%>
				<font color="red">
					<%=request.getAttribute("error")%>
				</font> <br>
		<%} %>
		<% ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
			if (request.getAttribute("prestamosPend")!=null) {
				prestamos = (ArrayList<Prestamo>) request.getAttribute("prestamosPend");
			}
			if (prestamos != null && prestamos.size() > 0) {
		%>
			<table class="table">
				<tr>
					<th align="center">Nro. de prestamo</th>
					<th align="center">Cuenta Destino</th>
					<th align="center">Cliente</th>
	                <th align="center">Capital</th>  <!-- monto total -->
	                <th align="center">Plazo</th>
	                <th align="center">Fecha solicitud</th>  <!-- fecha de solicitud -->
	                <th align="center">Accion</th>
				</tr>
				<% for (Prestamo prestamo : prestamos) {%>
				<tr>
					<td><%= prestamo.getIdPrestamo() %></td>
					<td><%= prestamo.getCbu() %></td>
					<td><%= prestamo.getCliente().getUsuario().getIdUsuario() %></td>
					<td>$<%= prestamo.getMontoTotal() %></td>
					<td><%= prestamo.getPlazoPago() %></td>
					<td><%= prestamo.getFechaSolicitud() %></td>
					<td align="center"><a href="ServletPrestamos?op=dsol&idPrestamo=<%=prestamo.getIdPrestamo()%>">Autorizar</a>
				</tr>
				<%} %>
			</table>
		<%} else {%>
			<h2>No hay prestamos para mostrar</h2>
		<% } %>
		</div>
	</body>

	</html>