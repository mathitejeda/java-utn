<%@page import="entidad.Cliente"%>
<%@page import="entidad.Usuario" %>
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
		<title>Cuentas </title>
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
			<h2>Alta cuenta</h2>	
			<form method="post" action="ServletCuenta">
			<table>
			<tr>  
				 <td>  

				<% String CBU = (String)request.getAttribute("nextCBU");%>
			 	<%
				ArrayList<String> listUsuarios = null;
				if (request.getAttribute("usuariosClientes")!=null) {
					listUsuarios = (ArrayList<String>) request.getAttribute("usuariosClientes");
				}
				%>
				Asignar Cliente: <select name="usuario"> 
								<%
								if(listUsuarios!=null) {
										for(String user : listUsuarios){ %>
										<option value="<%=user%>"><%=user%></option>
										<%}} else {%>
										<option value="0"> ----- </option>
								<%} %>
						 		</select><!--<a align="right" href="AgregarUsuario.jsp"> Crear</a><br>	  -->		
			
				<br><br>
				CBU: <%=CBU%><input type="hidden" name="nextCBU" value="<%=CBU%>">
				<br><br>
				<p>Numero de cuenta:<input required type="number" name="txtcuenta"></input>
				<br><br>
				Tipo de cuenta:
				<select name="TipoCuenta">
					<option value="1">Cuenta corriente</option>
					<option value="2">Caja de ahorro</option>
				</select> <br><br>
				Saldo:
				<input  placeholder="$10.000" required type="number" name="Saldo" value="10000" readonly></input><br><br>
				<p align="center"><input align="center" required type="submit" name="btnRegistrarCuenta" value="Agregar cuenta"></input></p><br>
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