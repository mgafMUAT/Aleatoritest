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
            <li ${user.esProfesor ? '' : 'class="disabled"'}><a ${user.esProfesor ? 'data-toggle="tab" href="#compartidas"' : ''}>Preguntas Compartidas</a></li>
            <li ${user.esProfesor ? '' : 'class="disabled"'}><a ${user.esProfesor ? 'data-toggle="tab" href="#pruebas"' : ''}>Pruebas</a></li>
        </ul>
        <div class="tab-content">
            <!--Preguntas tab-->
            <div id="preguntas" class="tab-pane fade in active">
                <h3>Mis preguntas</h3>
                <input class="form-control" id="preguntasIn" type="text" placeholder="Filtrar">
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
                            <th>Editar / Borrar</th>
                        </tr>
                    </thead>
                    <tbody id="preguntasT">
                        <c:forEach items="${user.preguntaList}" var="preg">
                            <tr>
                                <td>${preg.materiaList[0].nombre}</td>
                                <td>${preg.pregunta}</td>
                                <td>${preg.respuestaCorrecta}</td>
                                <td>${preg.alternativa1}</td>
                                <td>${preg.alternativa2}</td>
                                <td>${preg.alternativa3}</td>
                                <td>
                                    <c:if test="${preg.esVisible}">
                                        <span class="glyphicon glyphicon-ok"></span>
                                    </c:if>
                                </td>
                                <td>
                                    <fmt:formatDate type="date" dateStyle="short" value="${preg.fecha}" />
                                </td>
                                <td>
                                    <form method="post" action="editar">
                                        <input type="hidden" name="pregoprueba" value="1">
                                        <input type="hidden" name="prId" value="${preg.preguntaId}"> 
                                        <button type="submit" class="btn btn-info">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </button>
                                    </form>
                                    <form method="post" action="borrar">
                                        <input type="hidden" name="pregoprueba" value="1">
                                        <input type="hidden" name="prId" value="${preg.preguntaId}"> 
                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmPreg">
                                            <span class="glyphicon glyphicon-floppy-remove"></span>
                                        </button>
                                        <!--Confirmation modal-->
                                        <div class="modal fade" id="confirmPreg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <strong>ADVERTENCIA</strong>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p>¿Está completamente seguro de que desea eliminar esta pregunta?</p>
                                                        <p>"${preg.pregunta}"</p>
                                                        <p><strong>Esta acción no se puede deshacer</strong></p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-deafult" data-dismiss="modal">Cancelar</button>
                                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <form action="editar" method="post">
                    <input type="hidden" name="pregoprueba" value="1">
                    <button type="submit" class="btn btn-primary btn-lg">Crear Pregunta</button>
                </form>
            </div>
            <!--Filtering & modal script-->
            <script>
                $(document).ready(function () {
                    $("#preguntasIn").on("keyup", function () {
                        var value = $(this).val().toLowerCase();
                        $("#preguntasT tr").filter(function () {
                            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                        });
                    });
                });
            </script>

            <c:if test="${user.esProfesor}">
                <!--Preguntas compartidas tab-->
                <div id="compartidas" class="tab-pane fade">
                    <h3>Preguntas Compartidas</h3>
                    <input class="form-control" id="compartidasIn" type="text" placeholder="Filtrar">
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
                                <th>Agregar / Quitar</th>
                            </tr>
                        </thead>
                        <tbody id="compartidasT">
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
                                    <td>
                                        <c:set var="esCompartida" value="${ocupadas.contains(preg.preguntaId)}"/>
                                        <form method="post" action="compartir">
                                            <input type="hidden" name="esCompartida" value="${esCompartida ? '1' : '0'}">
                                            <input type="hidden" name="preguntaId" value="${preg.preguntaId}"> 
                                            <button type="submit" class="btn btn-default">
                                                <span ${'class="glyphicon glyphicon-'.concat(esCompartida ? 'remove"' : 'plus"')}></span>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--Filtering script-->
                <script>
                    $(document).ready(function () {
                        $("#compartidasIn").on("keyup", function () {
                            var value = $(this).val().toLowerCase();
                            $("#compartidasT tr").filter(function () {
                                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                            });
                        });
                    });
                </script>

                <!--Pruebas tab-->
                <div id="pruebas" class="tab-pane fade">
                    <h3>Mis Pruebas</h3>
                    <input class="form-control" id="pruebasIn" type="text" placeholder="Filtrar">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Materia</th>
                                <th>Nº de Preguntas</th>
                                <th>Fecha de creación</th>
                                <th>Editar / Borrar</th>
                            </tr>
                        </thead>
                        <tbody id="pruebasT">
                            <c:forEach items="${user.pruebaList}" var="prueba">
                                <tr>
                                    <td>${prueba.nombre}</td>
                                    <td>${prueba.materia.nombre}</td>
                                    <td>${prueba.cantidadPreguntas}</td>
                                    <td>
                                        <fmt:formatDate type="date" dateStyle="short" value="${prueba.fecha}" />
                                    </td>
                                    <td>
                                        <form method="post" action="editar">
                                            <input type="hidden" name="pregoprueba" value="0">
                                            <input type="hidden" name="prId" value="${prueba.pruebaId}"> 
                                            <button type="submit" class="btn btn-info">
                                                <span class="glyphicon glyphicon-edit"></span>
                                            </button>
                                        </form>
                                        <form method="post" action="borrar">
                                            <input type="hidden" name="pregoprueba" value="0">
                                            <input type="hidden" name="prId" value="${prueba.pruebaId}"> 
                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmPrueba">
                                                <span class="glyphicon glyphicon-floppy-remove"></span>
                                            </button>
                                            <!--Confirmation modal-->
                                            <div class="modal fade" id="confirmPrueba" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <strong>ADVERTENCIA</strong>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>¿Está completamente seguro de que desea eliminar esta pregunta?</p>
                                                            <p>"${prueba.nombre}"</p>
                                                            <p><strong>Esta acción no se puede deshacer</strong></p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                            <button type="submit" class="btn btn-danger">Eliminar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                <form action="editar" method="post">
                    <input type="hidden" name="pregoprueba" value="0">
                    <button type="submit" class="btn btn-primary btn-lg">Crear Prueba</button>
                </form>
                </div>
                <!--Filtering script-->
                <script>
                    $(document).ready(function () {
                        $("#pruebasIn").on("keyup", function () {
                            var value = $(this).val().toLowerCase();
                            $("#pruebasT tr").filter(function () {
                                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                            });
                        });
                    });
                </script>
            </c:if>
        </div>
    </body>
</html>
