<%-- 
    Document   : newjsphome
    Created on : 01-01-2019, 17:20:26
    Author     : MauricioGabriel
--%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Inicio</title>
    </head>
    <body>
        <!--Basic navbar-->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Aleatoritest</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="home">Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <a href="logout" class="btn btn-danger navbar-btn">
                        <span class="glyphicon glyphicon-log-in"></span> Salir
                    </a>
                </ul>
            </div>
        </nav>

        <!--Dynamic nav tabs-->
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#preguntas">Preguntas</a></li>
            <li><a data-toggle="tab" href="#compartidas">Preguntas Compartidas</a></li>
            <li><a data-toggle="tab" href="#pruebas">Pruebas</a></li>
        </ul>
        <div class="tab-content">
            <div id="preguntas" class="tab-pane fade in active">
                <h3>Mis preguntas</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Materia</th>
                            <th>Pregunta</th>
                            <th>Respuesta Correcta</th>
                            <th>Resp. Incorrecta 1</th>
                            <th>Resp. Incorrecta 2</th>
                            <th>Resp. Incorrecta 3</th>
                            <th>Visible</th>
                            <th>Fecha de creación</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${user.preguntaList}" var="preg">
                            <tr>
                                <td>${preg.materiaList[0].nombre}</td>
                                <td>${preg.pregunta}</td>
                                <td>${preg.respuestaCorrecta}</td>
                                <td>${preg.alternativa1}</td>
                                <td>${preg.alternativa2}</td>
                                <td>${preg.alternativa3}</td>
                                <td><c:if test="${preg.esVisible}">
                                        <span class="glyphicon glyphicon-ok"></span>
                                </c:if></td>
                                <td>
                                    <fmt:formatDate type="date" dateStyle="short" value="${preg.fecha}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="compartidas" class="tab-pane fade">
                <h3>Preguntas Compartidas</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Materia</th>
                            <th>Pregunta</th>
                            <th>Respuesta Correcta</th>
                            <th>Resp. Incorrecta 1</th>
                            <th>Resp. Incorrecta 2</th>
                            <th>Resp. Incorrecta 3</th>
                            <th>Fecha de creación</th>
                            <th>Autor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${compartibles}" var="preg">
                            <tr>
                                <td>${preg.materiaList[0].nombre}</td>
                                <td>${preg.pregunta}</td>
                                <td>${preg.respuestaCorrecta}</td>
                                <td>${preg.alternativa1}</td>
                                <td>${preg.alternativa2}</td>
                                <td>${preg.alternativa3}</td>
                                <td>
                                    <fmt:formatDate type="date" dateStyle="short" value="${preg.fecha}" />
                                </td>
                                <td>${preg.usuario.nombre}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="pruebas" class="tab-pane fade">
                <h3>Menu 1</h3>
                <p>Some content in menu 1.</p>
            </div>
        </div>
    </body>
</html>
