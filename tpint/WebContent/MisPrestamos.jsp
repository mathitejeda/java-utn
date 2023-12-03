<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mis prestamos</title>
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		<style type="text/css">
			<jsp:include page="css\StyleSheet.css"></jsp:include>
		</style>
	</head>
	<body>
		<div class="encabezado">
			<nav class="dropdownmenu">
			  <ul>
			    <li><a href="HomeCliente.jsp">Home</a></li>
     			<li><a href="ServletCuenta?MisCuentas=1">Mis Cuentas</a></li>
			    <li><a href="ServletTransferencias?Transfer=1">Transferir</a></li>
			    <li><a href="#">Prestamos</a>
				   	<ul id="submenu">
				   		<li><a href="ServletPrestamos?op=mp">Mis prestamos</a></li>
						<li><a href="ServletPrestamos?op=s">Solicitar prestamo</a></li>
				   	</ul>
			    </li>

		 <li><a  href="ServletCliente?InfoCliente=1" id="usuario"><%= session.getAttribute("Session_us") %></a>
		            <ul id="submenu">
		        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
		      </ul>
		      <li>
			  </ul>
			</nav>
		</div>
	    <div align="center">
			<%
				ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
				if (request.getAttribute("prestamos")!=null) {
					prestamos = (ArrayList<Prestamo>) request.getAttribute("prestamos");
				}
				if (prestamos != null && prestamos.size() > 0) {
			%>
		        <table class="table">
			            <caption><h2>Mis prestamos</h2></caption>
			            <tr>
			                <th >Nro. de prestamo</th>
			                <th >Cuenta Destino</th>
			                <th >Capital</th>  <!-- monto total -->
			                <th >Plazo</th>
			                <th >Fecha solicitud</th>  <!-- fecha de solicitud -->
			                <th >Saldo a pagar</th>
			                <th >Estado</th>
			            </tr>
				<% for (Prestamo prestamo : prestamos) {%>
			            <tr>
			                <td><%= prestamo.getIdPrestamo() %></td>
			                <td><%= prestamo.getCbu() %></td>
			                <td>$<%= prestamo.getMontoTotal() %></td>
			                <td><%= prestamo.getPlazoPago() %></td>
			                <td><%= prestamo.getFechaSolicitud() %></td>
			                <td>$<%= prestamo.getMontoTotal() %></td>
			                <td >
			                <div >
			                <% if (prestamo.isAprobado()) {%>
		                		<a href="ServletPrestamos?op=p&idPrestamo=<%=prestamo.getIdPrestamo()%>" align="center">Pagar</a>
		                		<%} else if(prestamo.isPendiente()) {%>
		                			Pendiente de Aprobacion
		                			<% } else if (prestamo.isAprobado()==false) {%>
		                				Rechazado
		                			<% } %>
		                		
		                	</div>
			                </td>
			           	</tr>
				<%} %>
					</table>
			<% } else {%>
				<h2 colspan="7">No hay prestamos para mostrar</h2>
			<% } %>
	     </div>
	</body>
</html>