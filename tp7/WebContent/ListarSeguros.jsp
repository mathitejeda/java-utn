<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="dominio.Seguro"%>
<%@page import="dominio.TipoSeguro"%>
<%@page import="dominio.TipoSeguroDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<a href="Inicio.jsp">Inicio</a> <a href="servletSeguro?op=a">Agregar seguro</a> <a href="servletSeguro?op=l">Listar seguros</a>
		<br><br>
		<b>"Tipo de seguros de la base de datos"</b>
		<br><br>
		<% 
			ArrayList<Seguro> listaSeguros = null;
			if(request.getAttribute("listaS")!=null)
			{
				listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaS");
			}
			TipoSeguroDao tipoSeguroDao = new TipoSeguroDao();
		%>				
		<form action="servletSeguro" method="get">
			Busqueda por tipo de Seguros: 
			<select class="form-control" id="cbSeguro1" name="cbSeguro1">
				<option value="0"> Tipos </option>
				<%	TipoSeguroDao tipoSegDao = new TipoSeguroDao();
					ArrayList<TipoSeguro> tipos = tipoSegDao.obtenerTipoSeguros();

					for(TipoSeguro tipo : tipos){%>
						<option value=<%=tipo.getIdTipo() %>><%=tipo.getDescripcion() %></option>
				<%}	%>
			</select>
			<input type="submit" name="btnFiltrar" value="Filtrar">
		</form>
		
		<table border="1">
			<tr> <th>ID</th> <th>Descripción</th><th>Descripcion Tipo Seguro</th><th>Costo Contratacion</th><th>Costo Máximo Asegurado</th></tr>
			<%  if(listaSeguros!=null)
				for(Seguro seg : listaSeguros) 
				{
			%>
			<tr>  
			     <form method="post" action="servletSeguro">  
				     <td><%=seg.getId() %></td>  
				     <td><%=seg.getDescripcion() %></td>   
					 <td><%=tipoSeguroDao.obtenerDescripcion(seg.getTipoSeguro().getIdTipo())%></td> 
				     <td><%=seg.getCostoContratacion() %></td>      
				     <td><%=seg.getCostoAsegurado() %></td>    
			     </form>  
			</tr>
			<%  } %>		
		</table>		
	</body>
</html>