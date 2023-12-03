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
<title> Modificar Clientes </title>
</head>
<body>
<div class="encabezado">
<nav class="dropdownmenu">
  <ul>
    <li><a href="HomeAdmin.jsp">Home</a></li>
    <li><a href="">Clientes</a>
      <ul id="submenu">
        <li><a href="AgregarClientes.jsp">Alta</a></li>
        <li><a href="ModificarClientes.jsp">Modificar</a></li>
        <li><a href="#">Baja</a></li>
        <li><a href="#">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Cuentas</a>
       <ul id="submenu">
        <li><a href="#">Alta</a></li>
        <li><a href="#">Modificar</a></li>
        <li><a href="#">Baja</a></li>
        <li><a href="#">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Préstamos</a>
      <ul id="submenu">
        <li><a href="#">Autorizar</a></li>
        <li><a href="#">Listar</a></li>
      </ul>
    </li>
    <li><a href="#">Informes</a>
      <ul id="submenu">
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
      </ul>
    </li>
  </ul> 
</div>    
<br> 
<h2>Modificar Clientes</h2>
<h4> Modificación de datos cliente</h4>
<form method="post" action="ServletHTML">
<table>
<tr>  
		 <td>  
		 		Usuario: 			lblMuestraUsuario
		      <p>  DNI: 			<input type="number" name="DNI"></input>
		      <p>  CUIL: 			<input type="number" name="CUIL"></input>
		      <p>  Nombre: 			<input type="text" name="Nombre"></input>
		      <p>  Apellido:		<input type="text" name="Apellido"></input>
		      <p>  Sexo: 			<select name="Sexo"> 
										<option value="1">Femenino</option>
										<option value="2">Masculino</option>
									</select>
		      <p>  Nacionalidad: 	<select name="Nacionalidad"> 
										<option value="1">Argentina</option>
									</select>
		 
		 </td>  
	     <td>
			  <p>  Fecha Nacimiento:	<input type="date" name="FechaNac"></input>
		      <p>  Dirección: 			<input type="text" name="Dirección"></input>
		      <p>  Localidad: 			<input type="text" name="Localidad"></input>
		      <p>  Provincia: 			<input type="text" name="Provincia"></input>
		      <p>  Correo electrónico:  <input type="email" name="Correo"></input>
		      <p>  Teléfono: 		   	<input type="tel" name="Telefono"></input></p>
		      <p>  Estado: 				<select name="Estado"> 
											<option value="1">Activo</option>
											<option value="2">Inactivo</option>
										</select>    
	     	 <br><br><p><input type="submit" name="btnModificarCliente" value="Modificar cliente"></input></br></p>
	     
	     </td>   
</tr>
</table>
</form>

</body>
</html>