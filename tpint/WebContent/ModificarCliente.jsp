<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title> Modificar Cliente </title>
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
      <ul id="submenu">
		<li><a href="ServletInformes?ListarMovimientosPorMes=1">Movimientos</a></li>
         <li><a href="ServletInformes?ListarPrestamosPorMes=1">Prestamos</a></li>

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
<br> 
 <div align="center">
<h2>Modificar Cliente</h2>
<form method="post" action="ServletCliente">
<h4> Modificar datos del cliente <%=(String)request.getAttribute("id") %></h4>

<% String id = (String)request.getAttribute("id");%>
<% String dni = (String)request.getAttribute("dni");%>
<% String cuil = (String)request.getAttribute("cuil");%>
<% String nombre = (String)request.getAttribute("nombre");%>
<% String apellido = (String)request.getAttribute("apellido");%>
<% String sexo = (String)request.getAttribute("sexo");%>
<% String nacionalidad = (String)request.getAttribute("nacionalidad");%>
<% String fecha = (String)request.getAttribute("fecha");%>
<% String direccion = (String)request.getAttribute("direccion");%>
<% String localidad = (String)request.getAttribute("localidad");%>
<% String provincia = (String)request.getAttribute("provincia");%>
<% String email = (String)request.getAttribute("email");%>
<% String telefono = (String)request.getAttribute("telefono");%>
<% String estado = (String)request.getAttribute("estado");%>
<table>
<tr>  
		 <td>  
		 		Usuario: 		<%=id%><input type="hidden" name="idUsuario" value="<%=id%>"></input>
		      <p>  DNI: 			<input type="number" type ="hidden" name="dni"  value="<%=dni%>"></input>
		      <p>  CUIL: 			<input type="text" type ="hidden" name="cuil"  value="<%=cuil%>"></input>
		      <p>  Nombre: 			<input type="text" type ="hidden" name="nombre" value="<%=nombre%>"></input>
		      <p>  Apellido:		<input type="text" name="apellido" type ="hidden" value="<%=apellido%>"></input>
		      <p>  Sexo: 			<select name="sexo"  type ="hidden" value="<%=sexo%>"> 
										<option value="1">Femenino</option>
										<option value="2">Masculino</option>
									</select>
		      <p>  Nacionalidad: 	<select required name="nacionalidad"> 
								    <option value="1">Argentina</option>
								    <option value="20">Afganistan</option>
								    <option value="30">Albania</option>
								    <option value="40">Alemania</option>
								    <option value="50">Andorra</option>
								    <option value="60">Angola</option>
								    <option value="110">Arabia Saudita</option>
								    <option value="120">Argelia</option>
								    <option value="130">Armenia</option>
								    <option value="140">Aruba</option>
								    <option value="150">Australia</option>
								    <option value="160">Austria</option>
								    <option value="200">Bangladesh</option>
								    <option value="220">Belgica</option>
								    <option value="230">Belice</option>
								    <option value="235">Bolivia</option>
								    <option value="240">Brasil</option>
								    <option value="370">Camerun</option>
								    <option value="380">Canadá</option>
								    <option value="390">Chile</option>
								    <option value="410">China</option>
								    <option value="430">Colombia</option>
								    <option value="460">Corea del Norte</option>
								    <option value="470">Corea del Sur</option>
								    <option value="510">Cuba</option>
								    <option value="530">Dinamarca</option>
								    <option value="560">Ecuador</option>
								    <option value="570">Egipto</option>
								    <option value="580">El Salvador</option>
								    <option value="583">España</option>
								    <option value="640">Estados Unidos</option>
								    <option value="650">Estonia</option>
								    <option value="690">Finlandia</option>
								    <option value="700">Francia</option>
								    <option value="780">Groenlandia</option>
								    <option value="870">Haiti</option>
								    <option value="880">Holanda</option>
								    <option value="920">India</option>
								    <option value="930">Indonesia</option>
								    <option value="940">Irak</option>
								    <option value="950">Iran</option>
								    <option value="960">Irlanda</option>
								    <option value="1070">Israel</option>
								    <option value="1080">Italia</option>
								    <option value="1090">Jamaica</option>
								    <option value="1100">Japón</option>
								    <option value="1380">Mexico</option>
								    <option value="1490">Nicaragua</option>
								    <option value="1510">Nigeria</option>
								    <option value="1520">Noruega</option>
								    <option value="1540">Nueva Zelandia</option>
								    <option value="1600">Paraguay</option>
								    <option value="1610">Peru</option>
								    <option value="1650">Portugal</option>
								    <option value="1660">Puerto Rico</option>
								    <option value="1750">Rusia</option>
								    <option value="1860">Senegal</option>
								    <option value="1940">Sudafrica</option>
								    <option value="1950">Sudán</option>
								    <option value="1960">Suecia</option>
								    <option value="1970">Suiza</option>
								    <option value="2000">Taiwán</option>
								    <option value="2001">Uruguay</option>	
									</select>
		 
		 </td>  
	     <td>
	     
			  <p>  Fecha Nacimiento:	<input type="date" type ="hidden" name="fecha" value="<%=fecha%>"></input>
		      <p>  Direccion: 			<input type="text" type ="hidden" name="direccion" value="<%=direccion%>"></input>
		      <p>  Localidad: 			<input type="text" type ="hidden" name="localidad" value="<%=localidad%>"></input>
		      <p>  Provincia: 			<input type="text" type ="hidden" name="provincia" value="<%=provincia%>"></input>
		      <p>  Correo:  			<input type="email" type ="hidden" name="email" value="<%=email%>"></input>
		      <p>  Telefono: 		   	<input type="tel"  type ="hidden" name="telefono" value="<%=telefono%>"></input></p>
			  <p>  Estado: 
								<select name="estado" type="hidden">
									<option value="1">Activo</option>
									<option value="2">Inactivo</option>
								</select>
							</p>
	     	 <br><br><p><input type="submit" name="btnModificarCliente" value="Modificar cliente"></input></br></p>
	     
	     </td>   
</tr>
</table>
</form>
</div>
</body>
</html>