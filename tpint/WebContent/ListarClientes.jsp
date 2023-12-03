<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entidad.Cliente" %>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="dao.ClienteDao"%>
<%@page import="entidad.Cliente"%>
<%@page import="daoImpl.Conexion"%>
<%@page import="java.util.ArrayList"%>
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
		<title> Clientes </title>
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
		<form method="get" action="ServletCliente"> 
			<h2>Listado de clientes</h2>
			<div>
				<label>Usuario:</label>
				<input type="text" name="usuarioFill">
				<label>DNI:</label>
				<input type="number" name="dniFil">
				<label>Nombre:</label>
				<input type="text" name="nombreFil">
				<label>Sexo:</label>
				<select name="sexoFil">
					<option value="0">-</option>
					<option value="1">Femenino</option>
					<option value="2">Masculino</option>
				</select>
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
				var respuesta = confirm("Eliminar Cliente?");
				
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
	<form action="ServletCliente" method="post">		
	
				<% if (request.getAttribute("success")!=null) { %>
					<font color="green"><%=request.getAttribute("success")%></font> <br>
				<%} else if (request.getAttribute("error")!=null) {%>
					<font color="red"><%=request.getAttribute("error")%></font> <br>
				<%} %>
		<table id="table_id" class="table">
			<thead>	
				<tr>
					<th>Usuario</th>
					<th>DNI</th>
					<th>CUIL</th>
					<th>Nombre</th>
					<th>Sexo</th>
					<th>Nacionalidad</th>
					<th>Fecha de Nacimiento</th>
					<th>Direccion</th>
					<th>Localidad</th>
					<th>Provincia</th>
					<th>Correo ElectrÃ³nico</th>
					<th>Telefono</th>
					<th>Estado</th>	
					<th>Accion</th>		
				</tr>
			</thead>
			<tbody>	
				<% ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("clientes");
				
				if(clientes !=null){ 				
					for(Cliente cliente : clientes){
						//if(cliente.getUsuario().getAdmin() == false){
						%>
						<tr>
						 <form action="ServletCliente" method="post">
							<td><%=cliente.getUsuario().getIdUsuario() %>  <input type="hidden" name="idUsuario" value="<%=cliente.getUsuario().getIdUsuario() %>"></input></td>
							<td><%=cliente.getDni() %><input type="hidden" name="dni" value="<%=cliente.getDni() %>"></input></td>
							<td><%=cliente.getCuil() %><input type="hidden" name="cuil" value="<%=cliente.getCuil() %>"></input></td>
							<td><%=cliente.getNombre() +", " + cliente.getApellido() %><input type="hidden" name="nombre" value="<%=cliente.getNombre() %>"></input><input type="hidden" name="apellido" value="<%=cliente.getApellido() %>"></input></td>
							
							<%if(cliente.getSexo().equals("Masculino")){ %>
								<td>M</td>
							<% } else if(cliente.getSexo().equals("Femenino")) { %>
								<td>F</td>	
							<% } %>
														
							<td><%=cliente.getNacionalidad() %><input type="hidden" name="nacionalidad" value="<%=cliente.getNacionalidad() %>"></input></td>
							<td><%=cliente.getFechaNacimiento() %><input type="hidden" name="fecha" value="<%=cliente.getFechaNacimiento() %>"></input></td>
							<td><%=cliente.getDireccion() %><input type="hidden" name="direccion" value="<%=cliente.getDireccion() %>"></input></td>
							<td><%=cliente.getLocalidad() %><input type="hidden" name="localidad" value="<%=cliente.getLocalidad() %>"></input></td>
							<td><%=cliente.getProvincia() %><input type="hidden" name="provincia" value="<%=cliente.getProvincia() %>"></input></td>
							<td><%=cliente.getEmail() %><input type="hidden" name="email" value="<%=cliente.getEmail() %>"></input></td>
							<td><%=cliente.getTelefono() %><input type="hidden" name="telefono" value="<%=cliente.getTelefono() %>"></input></td>
							<td><%if(cliente.getEstado()==true){ %>
									Activo
								<%}else{ %>
									Inactivo
								<%} %>
							<input type="hidden" name="estado" value="<%if(cliente.getEstado()==true){ %>
									Activo
								<%}else{ %>
									Inactivo
								<%} %>"></input></td>
							
							<td style="width: 127px; ">
		                        <input type = "submit" name="btnModificar" value="Modificar Datos">
		                        <input type = "submit" name="btnIrModificarPass" value="Modificar Clave">
		                        <input type = "submit" name="btnEliminarCliente" value="Eliminar" onclick="return ConfirmDelete()">           
		                      </td>
		                      </form>
							</tr>
						<%}
					//}
				}%>
				</tbody>
			</table>	
		</form> 
		</div>
	</body>
	</html>