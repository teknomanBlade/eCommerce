<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div align="left">
	<a href="/ecommerce-web/ordendecompra/adminconfirmado">Ver compras confirmadas</a>	
</div>
<div align="center">
	<h1>Admin - Verifique las cuentas por favor</h1>
</div>
<br />
<div>
	<table class="table table-striped">
		<tr>
			<td align="center">ID</td>
			<td align="center">Usuario</td>
			<td align="center">Producto</td>
			<td align="center">Estado</td>
		</tr>
		<c:forEach items="${compras}" var="compra">
			<tr>
				<td align="center">${compra.id}</td>
				<td align="center">${compra.usuario.perfil}</td>
				
				<td align="center">
				<c:forEach items="${compra.productos}" var="producto">
					<div align="center">${producto.producto.nombre}<br/></div>
				</c:forEach>
				</td>
				<td>
					<form action="verificarCompras" method="POST">
						<input type="hidden" value="${compra.id}" name="id"/>	
						<select name="estadoCompra">
							<option values="aceptado">Aceptado</option>
							<option values="rechazado">Rechazado</option>
						</select>
						<button type="submit" class="btn btn-primary">Aceptar</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>