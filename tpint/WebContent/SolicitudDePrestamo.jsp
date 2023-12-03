<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Solicitar prestamo</title>
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
				      </li>
				</ul>
			</nav>
		</div>
		<br>
		<div align="center">
			<h2>Formulario de Prestamo </h2>
			<% if (request.getAttribute("success")!=null) { %>
				<font color="green">
					<%=request.getAttribute("success")%>
				</font> <br>
				<%} else if (request.getAttribute("error")!=null) {%>
					<font color="red">
						<%=request.getAttribute("error")%>
					</font> <br>
			<%} %>
			<form action="ServletPrestamos" method="post">
				<table>
					<td>
						<p> Prestamo Nro. <%= request.getAttribute("id") %></p>
						<p> Monto: $ <input type="number" name="MontoPrestamo" required></input>
						<p> Cuotas: 
							<select id="cuotas" name="Cuotas" onchange="updateInteres()">
								<option value="0" selected>Seleccione una opcion</option>
								<option value="6">6</option>
								<option value="12">12</option>
								<option value="18">18</option>
								<option value="24">24</option>
								<option value="30">30</option>
								<option value="36">36</option>
							</select>
						</p>
						<input type="hidden" id="interesInput" name="interes">
						<p> Interes: <span id="interes"></span>%</p>

						<script type="text/javascript">
							function updateInteres() {
								var cuotas = document.getElementById('cuotas').value;
								var interes;

								switch(cuotas) {
									case '6':
										interes = '5';
										break;
									case '12':
										interes = '10';
										break;
									case '18':
										interes = '15';
										break;
									case '24':
										interes = '20';
										break;
									case '30':
										interes = '25';
										break;
									case '36':
										interes = '30';
										break;
									default:
										interes = '0';
								}

								document.getElementById('interes').textContent = interes;
								document.getElementById('interesInput').value = interes;
							}
						</script>
						<p> Cuenta:
							<select name="Cuenta" required>
						<% 
							ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentasCte");
							if (cuentas != null) {%>
							<%for (Cuenta cuenta : cuentas) {%>
								<option value="<%= cuenta.getCbu()%>"> <%= cuenta.getTipoCuenta() %> </option>
						<%}} else {%>
								<option value="0">No existen cuentas</option>
						<%} %>
							</select>
							<br><br>
						<p><input type="submit" name="btnSolicitarPrestamo" value="Solicitar Prestamo"></input></br></p>
					</td>
				</table>
			</form>
		</div>
	</body>
</html>