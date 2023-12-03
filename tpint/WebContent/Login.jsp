
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
		<title>Login</title>
	</head>
	<body>
		<div align="center">
			<h1>BIENVENIDOS!</h1>
			<p>Ingrese las credenciales para continuar</p>
			<form method="post" action="servlet">
				<table>
					<tr>  
						<td>  				
							<p align="right">Usuario</p> 
							<p align="right">Contraseña</p>
						</td>
						<td>
				   			<p> <input type="text" name= "txtUsuario" align="left"/></p>				
				  			<p> <input type="password" name= "txContraseña"/></p>										
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name= "btnIngresar" value="Ingresar" align="center"/></td>
					</tr>
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