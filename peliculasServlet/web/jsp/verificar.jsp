<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Película</title>
    </head>
    <body>
        <h1>Verificar Datos de la Película</h1>

        <%
            Cookie[] cookies = request.getCookies();
            String datosPelicula = "";
            String nombre = "";
            String descripcion = "";
            String fecha = "";
            String hora = "";
            String precio = "";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("datosPelicula")) {
                        datosPelicula = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    }
                }
            }

            if (!datosPelicula.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(datosPelicula);
                    nombre = json.optString("nombre", "");
                    descripcion = json.optString("descripcion", "");
                    fecha = json.optString("fecha", "");
                    hora = json.optString("hora", "");
                    precio = json.optString("precio", "");
                } catch (Exception e) {
                    out.println("<p>Error al procesar los datos de la cookie: " + e.getMessage() + "</p>");
                }
            }
        %>

        <form action="${pageContext.request.contextPath}/guardar_servlet" method="post">
            <label for="nombre">Nombre de la película:</label>
            <input type="text" id="nombre" name="nombre" value="<%= nombre %>" required><br><br>

            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion" value="<%= descripcion %>" required><br><br>

            <label for="fecha">Fecha:</label>
            <input type="date" id="fecha" name="fecha" value="<%= fecha %>" required><br><br>

            <label for="hora">Hora:</label>
            <input type="time" id="hora" name="hora" value="<%= hora %>" required><br><br>

            <label for="precio">Precio:</label>
            <input type="number" id="precio" name="precio" step="0.01" value="<%= precio %>" required><br><br>

            <input type="submit" value="Guardar">
        </form>
    </body>
</html>
