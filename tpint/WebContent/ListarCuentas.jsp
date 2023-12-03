<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
		<title> Cuentas </title>
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
        <li><a href="servlet?CerrarSession=1">Cerrar sesion</a></li>
      </ul>
      <li>
  </ul>
</nav>
		</div>
		<div align="center" style="padding: 15px">
		<form method="get" action="ServletCuenta"> 
			<h2>Listado de cuentas</h2>
			<div>
				<label>Nro cuenta:</label>
				<input type="number" name="nrocuentaFil">
				<label>CBU:</label>
				<input type="text" name="cbuFil">
				<label>Tipo:</label>
				<select name="TipoCuentaFil">
					<option value="0">-</option>
					<option value="1">Cuenta corriente</option>
					<option value="2">Caja de ahorro</option>
				</select>
				<label>Saldo:</label>
				<select name="opFil">
					<option value="1">igual a:</option>
					<option value="2">mayor a:</option>
					<option value="3">menor a:</option>
				</select>
				<input type="number" name="saldoFil">
				<label>Estado:</label>
				<select name="estadofiltro">
					<option value="1">Activo</option>
					<option value="2">Inactivo</option>
				</select>
		        <input type = "submit" name="btnfiltrar" value="Filtrar">
		        <input type = "submit" name="btnElimfiltro" value="Eliminar Filtro">
			</div>
			<br>
			<!--  CONFIRMACION DE ELIMINACIÓN -->
			<script type="text/javascript">
			function ConfirmDelete()
			{
				var respuesta = confirm("Eliminar Cuenta?");
				
				if(respuesta==true)
					{
						return true;
					}
				else 
					{
					return false;
					}
			}
			</script>
	</form>
	<form method="post" action="ServletCuenta">		
		<table id="table_id" class="table">
			<thead>	
				<tr>
					<th align="center">Numero de cuenta</th>
					<th align="center">CBU</th>
					<th align="center">Cliente</th>
					<th align="center">Tipo de cuenta</th>
					<th align="center">Fecha de alta</th>
					<th align="center">Saldo</th>
					<th align="center">Estado</th>
					<th align="center">Acciones</th>
				</tr>
			</thead>
			<tbody>	
				<% 
					ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>)request.getAttribute("cuentas");
							
					if(cuentas != null){ 						
						for(Cuenta cuenta : cuentas){
							//if(cuenta.getCliente().getUsuario().getAdmin() == true){
							%>
							<tr>
						 		<form action="ServletCuenta" method="post">
								<td><%=cuenta.getNroCuenta() %></td>
								<td><%=cuenta.getCbu() %> <input type="hidden" name="cbuvalor" value="<%=cuenta.getCbu() %>"></input></td> 
								<td><%=cuenta.getCliente().getUsuario().getIdUsuario() %> </td>
							
								<%if(cuenta.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO)){ %>
									<td>CA</td>
								<% } else if(cuenta.getTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE)) { %>
									<td>CC</td>	
								<% } %>
								
								<td><%=cuenta.getFechaAlta() %></td>
								<td>$<%=cuenta.getSaldo() %></td>
								
								<%if(cuenta.getEstado() == true){ %>
									<td>Activa</td>
								<% } else if(cuenta.getEstado() == false) { %>
									<td>Inactiva</td>	
								<% } %>
								<td style="width: 134px; " align="center">
								<input type="hidden" name="cbu" value="<%=cuenta.getCbu() %>">
								<input type="submit" name="btnIrModificarCuenta" value="Modificar"></input>					
								&nbsp;
								<input type="submit" name="btnEliminarCuenta" value="Eliminar" onclick="return ConfirmDelete()"></input></td>
						 	</form>
						 	</tr>
					<% } %>
				<% } %>
				</tbody>
				<% if (request.getAttribute("success")!=null) { %>
				<font color="green"><%=request.getAttribute("success")%></font> <br>
				<%} else if (request.getAttribute("error")!=null) {%>
				<font color="red"><%=request.getAttribute("error")%></font> <br>
				<%} %>
				</table>
			</form>		
		</div>
	</body>
</html>