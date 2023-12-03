<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@page import="entidad.Prestamo" %>
		<%@page import="entidad.Cuenta" %>
			<%@page import="java.util.ArrayList" %>

				<!DOCTYPE html>
				<html>

				<head>
					<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
					<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
						integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
						crossorigin="anonymous">
					<style type="text/css">
						<jsp:include page="css\StyleSheet.css"></jsp:include>
					</style>
					<title>Detalle de solicitud</title>
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
				</li>
				<li><a id="usuario">
						<%= session.getAttribute("Session_us") %>
					</a></li>
			</ul>
		</nav>
					</div>
					<div align="center">
						<%if(request.getAttribute("prestamo") !=null) { Prestamo
							prestamo=(Prestamo)request.getAttribute("prestamo");
							Cuenta cuentaSolicitud = (Cuenta) request.getAttribute("cuentaSolicitud");
							%>
							<h2>Detalle de Solicitud de Prestamo </h2>
							<form action="ServletPrestamos" method="post">
								<table>
									<td>
										<input type="hidden" id="idPrestamo" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
										<p> Nro. de solicitud <%= prestamo.getIdPrestamo() %>
										<p> Monto de solicitud: $<%=prestamo.getMontoTotal() %></p>
										<p> Valor de cuota: $<%=prestamo.getMontoCuota() %></p>
										<p> Cantidad de cuotas: <%= prestamo.getPlazoPago() %></p>
										<p> Fecha de solicitud: <%= prestamo.getFechaSolicitud() %></p>
										<p> Saldo del usuario: $<%= cuentaSolicitud.getSaldo() %></p>
										<p><input type="submit" name="btnAprobarPrestamo" value="Aprobar"></input></p>
										<p><input type="submit" name="btnRechazarPrestamo" value="Rechazar"></input></p>
									</td>
								</table>
							</form>
						<%} else {%>
							<h2>No se seleccionó ninguna solicitud de préstamo</h2>
						<%} %>
					</div>
				</body>

				</html>