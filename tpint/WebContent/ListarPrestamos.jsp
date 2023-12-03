<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css"
			href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
				<style type="text/css">
					<jsp:include page="css\StyleSheet.css"></jsp:include>
				</style>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<script type="text/javascript" charset="utf8"
			src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$('#table_id').DataTable();
			});
		</script>
		<title> Prestamos </title>
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
		        <li><a href="AgregarUsuario.jsp">Dar de alta</a></li>
		      </ul>
		     <li><a href="InfoCliente.jsp" id="usuario"><%= session.getAttribute("Session_us") %></a>
		            <ul id="submenu">
		        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
		      </ul>
		      <li>
		  </ul>
		</nav>
	</div>
	<div align="center" style="padding: 15px">
		<h2>Listado de prestamos</h2>
		<form method="post" action="ServletPrestamos"> 
		<div>
			<label>CBU:</label>
			<input type="text" name="cbuFil">
			<label>Fecha Solicitud:</label>	
			<input type="date" name="FechaSolicitud"></input></p>
			<!--  		
			<label>Cliente:</label>
			<input type="text" name="usuarioFil">	-->					
			<label>Importe:</label>
				<select name="opFil">
					<option value="1">igual a:</option>
					<option value="2">mayor a:</option>
					<option value="3">menor a:</option>
				</select>
				<input type="number" name="saldoFil">
			<label>Estado:</label>
				<select name="estadofiltro">
					<option value="1">Aprobado</option>
					<option value="2">Rechazado</option>
				</select>
		    <input type = "submit" name="btnfiltrar" value="Filtrar">
		    <input type = "submit" name="btnElimfiltro" value="Eliminar Filtro">
		</div>
		</form>
		<br>
		<% ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
			if (request.getAttribute("prestamos")!=null) {
				prestamos = (ArrayList<Prestamo>) request.getAttribute("prestamos");
			}
			if (prestamos != null && prestamos.size() > 0) {
		%>
	<table id="table_id" class="table">
		<thead>	
			<tr>
				<th>Nro. de prestamo</th>
				<th>Cuenta Destino</th>
				<th>Cliente</th>
				<th>Capital</th> <!-- monto total -->
				<th>Plazo</th>
				<th>Fecha solicitud</th> <!-- fecha de solicitud -->
				<th>Aprobado</th>
			</tr>
		</thead>
		<tbody>	
			<% for (Prestamo prestamo : prestamos) {%>
				<tr>
					<td><%= prestamo.getIdPrestamo() %></td>
					<td><%= prestamo.getCbu() %></td>
					<td><%= prestamo.getCliente().getUsuario().getIdUsuario() %></td>
					<td>$<%= prestamo.getMontoTotal() %></td>
					<td><%= prestamo.getPlazoPago() %></td>
					<td><%= prestamo.getFechaSolicitud() %></td>					
					<td align="center"><%= prestamo.isAprobado() ? "Si" : "No" %></td>
				</tr>
				<%} %>
		</tbody>
		</table>
		<%} else {%>
			<h2 colspan="7">No hay prestamos para mostrar</h2>
		<% } %>
	</div>
	</body>

	</html>