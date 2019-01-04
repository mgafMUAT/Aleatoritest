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
        <title>Editor de Preguntas</title>
    </head>
    <body>
        <form action="grabar" method="post">
            <div class="form-group">
                <label for="pregunta">Pregunta:</label>
                <textarea name="pregunta" class="form-control" id="pregunta" required="true">
                    ${preg.pregunta}
                </textarea>
            </div>
            <div class="form-group">
                <label for="correcta">Respuesta correcta:</label>
                <input type="text" class="form-control" id="correcta" name="correcta" value="${preg.respuestaCorrecta}" required="true">
            </div>
            <div class="form-group">
                <label for="alt1">Respuesta incorrecta 1:</label>
                <input type="text" class="form-control" id="alt1" name="alt1" value="${preg.alternativa1}" required="true">
            </div>
            <div class="form-group">
                <label for="alt2">Respuesta incorrecta 2:</label>
                <input type="text" class="form-control" id="alt2" name="alt2" value="${preg.alternativa2}" required="true">
            </div>
            <div class="form-group">
                <label for="alt3">Respuesta incorrecta 3:</label>
                <input type="text" class="form-control" id="alt3" name="alt3" value="${preg.alternativa3}" required="true">
            </div>
            <div class="form-group">
                <label for="sel1">Materia:</label>
                <select class="form-control" id="sel1" name="materia">
                    <c:forEach items="${materias}" var="materia">
                        <option>${materia.materiaId} - ${materia.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="checkbox">
                <label><input type="checkbox" name="visible">Visible al público?</label>
            </div>
            <input type="hidden" name="tabla" value="preg">
            <input type="hidden" name="id" value="${preg.preguntaId}">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="home" class="btn btn-warning">Cancelar</a>
        </form>
    </body>
</html>
