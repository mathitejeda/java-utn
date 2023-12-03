<%@page import="entidad.Mes"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Prestamos por Periodo</title>
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
			<h2>Prestamos por Periodo</h2>
			<p>Préstamos solicitados a lo largo de un mes. Consulte esta información sobre el usuario y/o el estado de préstamo deseado.</p>
			<form action="ServletInformes" method="get">
				<label for="filtroEstadoPrestamo">Estado:</label>
				<select name="estado">
					<option value="0">Todos</option>
					<option value="1">Aprobado</option>
					<option value="2">Sin aprobar</option>
				</select>
				<label>Usuario:</label>
				<input type="text" name="usuarioFiltro">	
				<input type="submit" name="btnFiltrarPrest" value="Filtrar">
			</form>
			<br>
			<form method="get" action="servletInformes">	
				<table id="table_id" class="table">
					<thead>	
					<tr>
						<th>Anio</th>
						<th>Mes</th>
						<th>Cliente</th>
						<th>Monto Total</th>
					</tr>	
					</thead>
					<tbody>			
					<% ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>)request.getAttribute("prestamosPorMes");
						BigDecimal montoTotal = (BigDecimal)request.getAttribute("montoTotal");	
											
						if(prestamos != null){ 						
							for(Prestamo prestamo : prestamos){ %>			
								<tr>
									<td><%= prestamo.getFechaSolicitud().getYear() %></td>
									<% for(Mes mes : Mes.values()){ 
										if(mes.getidMes() == prestamo.getFechaSolicitud().getMonth()){ %>
										<td><%= mes.getNombre() %></td>
										<%}
									}%>									
									<% if(prestamo.getCliente() == null ){ %>
										<td><p> Todos </p> </td>
									<%} else { %>
										<td><%= prestamo.getCliente().getUsuario().getIdUsuario() %></td>
									<%} %>
									<td>$<%= prestamo.getMontoTotal() %></td>
								</tr>
						<% } %>
					<% } %>
					</tbody>
				</table>	
			</form>
			<h3><label>Monto total del Periodo: $<%=montoTotal%></label></h3>
		</div>
	</body>
</html>