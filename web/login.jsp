<%-- 
    Document   : login
    Created on : 02-01-2019, 19:38:54
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
        <title>AleatoriTest</title>
    </head>
    <body>
        <c:if test="${wrong}">
            <div class="alert alert-danger alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Datos erróneos.</strong> Por favor, inténtelo de nuevo.
            </div>
        </c:if>
        <h1 style="text-align: center">Ingresar a AleatoriTest</h1>
        <form class="form-horizontal" method="post" action="ingreso">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Correo:</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Clave:</label>
                <div class="col-sm-10"> 
                    <input type="password" class="form-control" id="pwd" name="pass" placeholder="Enter password">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Ingresar</button>
                </div>
            </div>
        </form>
    </body>
</html>

