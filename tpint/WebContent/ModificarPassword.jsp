<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
			integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		<style type="text/css">
			<jsp:include page="css\StyleSheet.css"></jsp:include>
		</style>
		<title>Modificar Clave </title>
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
			        <li><a href="ServletPrestamos?op=lp">Listar</a></li>
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
			     <li><a href="#"><%= session.getAttribute("Session_us") %></a>
			            <ul id="submenu">
			        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
			      </ul>
			      <li>
			  </ul>
			</nav>
		</div>
		<%
			String id = "";
			if(request.getAttribute("ClienteRedirect")!=null)
				id = request.getAttribute("ClienteRedirect").toString();
		%>	
	
		<div align="center" style="padding: 15px">
			<h2>Modificar Clave</h2>
			<% String idUsuario = (String)request.getAttribute("idUsuario");%>
			<form method="post" action="ServletCliente">	
				<table>
					<tr>  
						<td>
							<p align="right">Usuario</p> 
							<p align="right">Clave</p> 
							<p align="right">Repetir Clave</p> 
						</td>
						<td>
							<p><%=idUsuario %> <input type="hidden" name="usuario" value="<%=idUsuario %>">
					      	<p><input type="password" pattern=".{6,}" name="txtPass2"  placeholder="Al menos 6 caracteres"></input></p>
					      	<p><input type="password" pattern=".{6,}" name="txtPass" type ="hidden" placeholder="Al menos 6 caracteres"></input></p>
					    </td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="btnModificarPass" value="Guardar" align="center"/></td>
					</tr>
				</table>
				<% if (request.getAttribute("success")!=null) { %>
				<font color="green"><%=request.getAttribute("success")%></font> <br>
				<%} else if (request.getAttribute("error")!=null) {%>
				<font color="red"><%=request.getAttribute("error")%></font> <br>
				<%} %>
			</form>
		</div>
	</body>
</html>