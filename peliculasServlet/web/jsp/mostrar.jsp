<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de la Película</title>
        <script>
            function eliminarCookie() {
                document.cookie = "datosPelicula=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                alert('Cookie eliminada');
                window.location.href = "./index.jsp";
            }
        </script>
    </head>
    <body>
        <h1>Detalles de la Película</h1>

    <c:if test="${not empty nombre}">
        <p><strong>Nombre:</strong> ${nombre}</p>
        <p><strong>Descripción:</strong> ${descripcion}</p>
        <p><strong>Fecha:</strong> ${fecha}</p>
        <p><strong>Hora:</strong> ${hora}</p>
        <p><strong>Precio:</strong> ${precio}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/guardar_servlet" method="get">
        <input type="submit" value="Realizar Consulta">
    </form>

    <button type="button" onclick="eliminarCookie()">Eliminar Cookie</button>

</body>
</html>
