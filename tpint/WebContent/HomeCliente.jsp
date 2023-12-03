<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Home</title>
</head>
<body>
<div class="encabezado">
<title>BANCO</title>
</head>
<body>
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
		     <li><a href="servlet?CerrarSession=1">Cerrar sesion</a></li>
		   </ul>
		  <li>
  </ul>
</nav>
</div>
</body>
</html>