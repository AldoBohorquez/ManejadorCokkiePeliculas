<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro de Películas</title>
</head>
<body>
    <h1>Registrar Película</h1>
    <form action="../registro_servlet" method="POST">
        <label for="nombre">Nombre de la película:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>
        
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" rows="4" cols="50" required></textarea><br><br>
        
        <label for="fecha">Fecha de lanzamiento:</label>
        <input type="date" id="fecha" name="fecha" required><br><br>
        
        <label for="hora">Hora de función:</label>
        <input type="time" id="hora" name="hora" required><br><br>
        
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" required><br><br>
        
        <input type="submit" value="Registrar Película">
    </form>
</body>
</html>
