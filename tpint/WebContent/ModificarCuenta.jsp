<%@page import="java.math.BigDecimal"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
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
		<title> Cuentas </title>
	</head>

	<body>
	<div class="encabezado">
		<nav class="dropdownmenu">
		  <ul>
		    <li><a href="HomeAdmin.jsp">Home</a></li>
		    <li><a href="#">Clientes</a>
		      <ul id="submenu">
		        <li><a href="AgregarClientes.jsp">Dar de alta</a></li>
		        <li><a href="servlet?ListarClientes=1">Listar</a></li>
		      </ul>
		    </li>
		    <li><a href="#">Cuentas</a>
		       <ul id="submenu">
		        <li><a href="AgregarCuenta.jsp">Dar de alta</a></li>
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
		<div align="center" style="padding: 15px">
			<h2>Modificar cuenta</h2>
			<form method="post" action="ServletCuenta">
			<% Cuenta cuenta = (Cuenta)request.getAttribute("cuenta"); %>
			<h4>Se modifica la cuenta del usuario <%=cuenta.getCliente().getUsuario().getIdUsuario() %></h4>			
				<table>
					<tr>  
				 		<td>  
							<p> CBU:  <%=cuenta.getCbu() %></p>
								
								<input type="hidden" name="cbu" value="<%=cuenta.getCbu() %>">
							<p> Tipo de cuenta:
								<select type="hidden" name="IdTipoCuenta">
									<% if(cuenta.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO)){ %>
										<option value="0">Caja Ahorro</option>
										<option value="1">Caja ahorro</option>
										<option value="2">Cuenta Corriente</option>
									<% } else if(cuenta.getTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE)){ %>
										<option value="0">Cuenta Corriente</option>
										<option value="1">Caja ahorro</option>
										<option value="2">Cuenta Corriente</option>
									<% } %>
								</select>
							</p>
							<p> Saldo: <input required type="number" type="hidden" name="Saldo" value=<%=BigDecimal.ZERO %>></input> </p>
							<p> Estado: 
								<select name="Estado" type="hidden">
									<option value="1">Activo</option>
									<option value="2">Inactivo</option>
								</select>
							</p>							
							<input required type="submit" name="btnModificarCuenta" value="Modificar cuenta"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>