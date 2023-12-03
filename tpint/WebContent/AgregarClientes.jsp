<%@page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="dao.ClienteDao"%>
<%@page import="entidad.Cliente"%>
<%@page import="daoImpl.Conexion"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
		<style type="text/css">
			<jsp:include page="css\StyleSheet.css"></jsp:include>
		</style>
		<title> Alta Cliente </title>
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
		<div align="center">
			<h2> Alta Cliente</h2>
			<h4> Ingrese los datos del nuevo cliente</h4>
			<form method="post" action="ServletCliente">
			
			<%
							ArrayList<Usuario> listUsuarios = null;
							if (request.getAttribute("listaFinal")!=null) {
								listUsuarios = (ArrayList<Usuario>) request.getAttribute("listaFinal");
							}
							%>
				<% if (request.getAttribute("success")!=null) { %>
					<font color="green"><%=request.getAttribute("success")%></font> <br>
				<%} else if (request.getAttribute("error")!=null) {%>
					<font color="red"><%=request.getAttribute("error")%></font> <br>
				<%} %>
				<table>
					<tr>  
						<td>  
							<p>	Asignar Usuario: 
								<select name="usuario"> 
								<%
								if(listUsuarios!=null) {
										for(Usuario user : listUsuarios){ %>
										<option value="<%=user.getIdUsuario()%>"><%=user.getIdUsuario()%></option>
										<%}} else {%>
										<option value="0"> ----- </option>
								<%} %>
						 		 </select> 
						  	 	 <a href="AgregarUsuario.jsp">Crear</a>
						  	 </p>					 		 	
						     <p> DNI:<input required type="number" name="DNI" pattern="[0-9]+"></input></p>
						     <p> CUIL: <input required type="text" name="CUIL"></input></p>
						     <p> Nombre: <input required type="text" name="Nombre"></input></p>
						     <p> Apellido: <input required type="text" name="Apellido"></input></p>
						     <p> Sexo: 			
						     	<select required name="Sexo"> 
									<option value="1">Femenino</option>
									<option value="2">Masculino</option>
								</select>
							</p>
						    <p> Nacionalidad:	
							     <select required name="Nacionalidad"> 
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
							</p>					
						</td>  
						<td>
							<p>  Fecha Nacimiento:	<input required type="date" name="FechaNac"></input></p>
							<p>  Direccion: 			<input required type="text" name="Direccion"></input></p>
							<p>  Localidad: 			<input required type="text" name="Localidad"></input></p>
							<p>  Provincia: 			<input required type="text" name="Provincia"></input></p>
							<p>  Correo: 				<input required type="email" name="Correo"></input></p>
							<p>  Telefono: 		 	<input required type="tel" name="Telefono"></input></p>
							<p align="center">
								<input align="center" type="submit" name="btnRegistrarCliente" value="Registrar cliente"></input>
							</p>										
						</td>   
					</tr>
				</table>
			</form>
		</div>	
	</body>
</html>