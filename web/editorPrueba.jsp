<%-- 
    Document   : editor
    Created on : 02-01-2019, 20:36:22
    Author     : MauricioGabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editor de Pruebas</title>
    </head>
    <body>
        <form action="grabar" method="post">
            <div class="form-group">
                <label for="nombre">Nombre de la prueba:</label>
                <textarea name="nombre" class="form-control" id="nombre" required="true">
                    ${prueba.nombre}
                </textarea>
            </div>
            <div class="form-group">
                <label for="sel1">Materia:</label>
                <select class="form-control" id="sel1" name="materia">
                    <c:forEach items="${materias}" var="materia">
                        <option>${materia.materiaId} - ${materia.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="preguntas">Mutiple select list (hold shift to select more than one):</label>
                <select multiple class="form-control" id="preguntas" name="preguntas">
                    <c:forEach items="${pregs}" var="preg">
                        <option>${preg.preguntaId} - ${preg.pregunta}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="tabla" value="preg">
            <input type="hidden" name="id" value="${prueba.pruebaId}">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="home" class="btn btn-warning">Cancelar</a>
        </form>
    </body>
</html>
