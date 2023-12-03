<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import= "entidad.Movimiento" %>
<%@ page import= "java.util.ArrayList" %>
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
		<title> Movimientos</title>
	</head>

<% 
	ArrayList<Movimiento> movimientos = null;
	if(request.getAttribute("movimientos")!=null)
		movimientos = (ArrayList<Movimiento>)request.getAttribute("movimientos");
%>

	<body>
		<div class="encabezado">
		<nav class="dropdownmenu">
		  <ul>
		    <li><a href="HomeCliente.jsp">Home</a></li>
		      <li><a href="MisCuentas.jsp">Mis Cuentas</a></li>
		    <li><a href="TransferenciaCBU.jsp">Transferir</a></li>
		    <li><a href="#">Prestamos</a>
			   	<ul id="submenu">
			   		<li><a href="ServletPrestamos?op=mp">Mis prestamos</a></li>
			   		<li><a href="ServletPrestamos?op=s">Solicitar prestamo</a></li>			   		
			   	</ul>
		  
		     <li><a  href="InfoCliente.jsp" id="usuario"><%= session.getAttribute("Session_us") %></a>
		            <ul id="submenu">
		        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
		      </ul>
		      <li>
		   </ul>
		</nav>
		</div>
		<div  align="center" style="padding: 15px">
			<%if(request.getAttribute("movimientos")!=null){ %>
				<h2>Movimientos de Cuenta <%=movimientos.get(0).getCuenta().getCbu() %></h2>
				<br>
				<table id="table_id" class="table">
					<thead>	
						<tr>
							<th>Fecha operacion</th>
							<th>Tipo</th>
							<th>Importe</th>
							<th>Concepto</th>					
						</tr>
						</thead>
					<tbody>	
						<%for(Movimiento movimiento : movimientos){ %>
							<tr>
								<td><%=movimiento.getFechaOperacion().toString() %></td>
								<td><%=movimiento.getTipoMovimiento().getDescripcion() %></td>
								<td><%=movimiento.getImporte() %></td>
								<td><%=movimiento.getConcepto() %></td>
							</tr>
						<%} %>
						</tbody>
					</table>
			<%} %>
		</div>
	</body>

	</html>