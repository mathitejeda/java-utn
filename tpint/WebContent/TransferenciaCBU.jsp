<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferencia CBU</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		<style type="text/css">
			<jsp:include page="css\StyleSheet.css"></jsp:include>
		</style>
<style>
	input[type=number]::-webkit-inner-spin-button, 
	input[type=number]::-webkit-outer-spin-button { 
	    -webkit-appearance: none;
	    -moz-appearance: none;
	    appearance: none;
	    margin: 0; 
	}
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
				     
				 <li><a  href="ServletCliente?InfoCliente=1" id="usuario"><%= session.getAttribute("Session_us") %></a>
				            <ul id="submenu">
				        <li><a href="servlet?CerrarSession=1">Cerrar sesión</a></li>
				      </ul>
				      </li>
		</nav>
	</div>
	
	<br>
	
	<%
		///Solo creo una instancia de Cuenta si se recibe la del request
		Cuenta destino = null; 
		if(request.getAttribute("cuentaDestino")!=null)
			destino = (Cuenta)request.getAttribute("cuentaDestino");
		ArrayList<Cuenta> cuentasUsuario = null;
		if(session.getAttribute("cuentasCliente")!=null)
			cuentasUsuario = (ArrayList<Cuenta>)session.getAttribute("cuentasCliente"); 
	%>
	
	<div  align="center">
	<h2>Transferir</h2>
			<form method="post" action="ServletTransferencias">
			CBU: <input type="text" name="CBU"> <input type="submit" name="buscarCbu" value="Buscar">
		</form> <br>
	<!-- Si nunca se instancia cuenta no muestro la tabla con los controles para la transferencia -->
	<%if(destino != null && cuentasUsuario != null){ %>
		<table>
			<h3> CUENTA DESTINO </h3>
			<tr>  
				<td>  
					<div>
						<p> CBU: <%=destino.getCbu() %>
						<p> Cliente: <%=destino.getCliente().getNombre() + " " + destino.getCliente().getApellido() %>
						<p> Tipo de Cuenta: <%=destino.getTipoCuenta() %>
					</div>
				</td>
			    
			</tr>
		</table>
		<br>
		<form action="ServletTransferencias" method="post">
		<h3> CUENTA ORIGEN </h3>
			<table>
				<tr>
					<%for(Cuenta cuenta : cuentasUsuario){ %>
						<%if( !(destino.getCbu().matches(cuenta.getCbu()) ) ){ %>
							<td>
								<p><input type="radio" name="cuenta" value="<%=cuenta.getCbu() %>">Cuenta: 		<%=cuenta.getCbu() %></input>
								<p>Tipo de cuenta: <%=cuenta.getTipoCuenta() %>
								<p>Saldo cuenta: 	$<%=cuenta.getSaldo() %>
							</td>
						<%} %>
					<%} %>
				</tr>
			</table>
			<input type="hidden" name="CBUDestino" value="<%=destino.getCbu()%>">
			<p>Monto Transferencia: $<input type="number" name="Monto" min="0" value="0.00" step=".01"> <!-- cambio -->
			<input type="submit" name="montoTransfer" value="Transferir">
		</form>

	<%}else{ %>
		<table>
			<tr>
				<%if(request.getAttribute("success")!=null){ %>
					<td><h2><%=request.getAttribute("success") %></h2></td>
				<%}else{ %>
					<td><h2>Ingrese un CBU de cuenta</h2></td>	
				<%} %>
			</tr>
		</table>
	<%} %>
	</div>
</body>
</html>