<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Perfil Cliente</title>
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
			    <li><a href="ServletCuenta?MisCuentas=1">Cuentas</a></li>
			    <li><a href="ServletTransferencias?Transfer=1">Transferir</a></li>
			    <li><a href="#">Prestamos</a>
				   	<ul id="submenu">
				   		<li><a href="ServletPrestamos?op=mp">Mis prestamos</a></li>
				   		<li><a href="ServletPrestamos?op=s">Solicitar prestamo</a></li>
				   	</ul>
			    
			     <li><a href="ServletCliente?InfoCliente=1" id="usuario"><%= session.getAttribute("Session_us") %></a>
			            <ul id="submenu">
			        <li><a href="servlet?CerrarSession=1">Cerrar sesion</a></li>
			      </ul>
			      <li>
			  </ul>
			</nav>
		</div>	
		<% if (request.getAttribute("noExiste") == null){
			String usuario = (String)request.getAttribute("usuario");
			String dni = (String)request.getAttribute("dni");
			String cuil = (String)request.getAttribute("cuil");
			String nombre = (String)request.getAttribute("nombre");
			String apellido = (String)request.getAttribute("apellido");
			String sexo = (String)request.getAttribute("sexo");
			String nacionalidad = (String)request.getAttribute("nacionalidad");
			String direccion = (String)request.getAttribute("direccion");
			String localidad = (String)request.getAttribute("localidad");
			String provincia = (String)request.getAttribute("provincia");
			String correo = (String)request.getAttribute("correo");
			String telefono = (String)request.getAttribute("telefono");
			%>	
			<div align="center">
			
			<h2><%=usuario%></h2>
			<p> DNI: <%=dni%>
			<p> CUIL: <%=cuil%>
			<p> Nombre: <%=nombre%>
			<p> Apellido: <%=apellido%>
			<p> Sexo: <%=sexo%>
			<p> Nacionalidad: <%=nacionalidad%>
			<p> Direccion:  <%=direccion%>
			<p> Localidad: <%=localidad%>
			<p> Provincia: <%=provincia%>
			<p> Correo:  <%=correo%>
			<p> Telefono:  <%=telefono%>
		</div>	
		<% } else { %>	
			<h2 colspan="7"><p align="center">No ha sido registrado como Cliente</p></h2>
		<% } %>	

	</body>
</html>


