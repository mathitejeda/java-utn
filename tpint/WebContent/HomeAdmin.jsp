<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Home</title>
</head>
<div class="encabezado">
<title>BANCO</title>
</head>
<body>
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
</body>
</html>