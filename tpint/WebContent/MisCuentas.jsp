<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title> Clientes </title>
</head>
<% 
	ArrayList<Cuenta> cuentas = null;
	if(session.getAttribute("cuentasCliente")!= null)
		cuentas = (ArrayList<Cuenta>)session.getAttribute("cuentasCliente");
%>

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
<br> 
<div align="center">
<h2>Mis Cuentas</h2>
<h4> Cuentas asociadas</h4>
<form method="post" action="ServletHTML">
<%if(session.getAttribute("cuentasCliente")!=null){ %>
	<table>
		<tr>
			<%for(Cuenta cuenta : cuentas){ %>  
				 <td>  
				 	
				 		 Nro Cuenta: 		<%=cuenta.getNroCuenta() %>
				 		 <p>Tipo: 				<%=cuenta.getTipoCuenta() %>
				 		 <p>CBU: 			    <%=cuenta.getCbu() %>
				 		 <p>Saldo: 			    <%=cuenta.getSaldo() %>
				 		 <br><br><a href= "ServletMovimientos?MovimientoCuenta=<%=cuenta.getCbu()%>">Movimientos</a>
				 </td>  
			<%} %>
		</tr>
	</table>
<%} %>

</form>
</div>
</body>
</html>