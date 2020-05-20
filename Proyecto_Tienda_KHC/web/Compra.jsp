<%-- 
    Document   : Compra
    Created on : 11/05/2020, 03:59:30 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
        <title>Compra de Productos</title>
    </head>
    
    <body>
    <center> <h1>PRODUCTOS DISPONIBLES EN LA TIENDA</h1></center>
    <div style="height: 500px; width: 600px; overflow-y: scroll;">
        
        <table id="tabla_productos">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Referencia</th>
                    <th>Precio</th>
                    <th>Descripcion</th>
                    <th>Comprar</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    </body>
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="Js/jquery.dataTables.js" type="text/javascript"></script>

    <script>
        $(document).ready(function(){
           listar_Productos();
           Cantidad_Comprada();
        });
        
        function listar_Productos()
        {
            var parametros = {'Operacion':'Listar_productos'};
            $.ajax({
                type: 'POST',
                url: "Controlador_Productos",
                data: parametros,
                dataType: "text",
                success: function (resp){
                    var dato = JSON.parse(resp);
                     $('#tabla_productos').DataTable({
                data : dato,
                columns : [
                    { data  : 'id_producto'},
                    { data  : 'referencia'},
                    { data  : 'precio'},
                    { data  : 'descripcion'},
                    { data  : 'comprar'}
                ]
            });
                }
            }).fail(function(jqXHR, tetStatus, errorThrown){
                alert("Error:" + jqXHR.Status);
            });
        }
        
        function Comprar_Producto(id_producto){
            alert(id_producto);
            var parametros = {'Operacion':'Realizar_Compra','id_producto':id_producto};
            $.ajax({
                type: 'POST',
                url: "Controlador_Compra",
                data: parametros,
                dataType: "text",
               success: function (resp){
                   alert(resp);
                   Cantidad_Comprada();
               } 
            }).fail(function(jqXHR, tetStatus, errorThrown){
                alert("Error:" + jqXHR.Status);
            });
        }
        
        function Cantidad_Comprada(){
            var parametros = {'Operacion':'Cantidad_Comprada'};
            $.ajax({
                type: 'POST',
                url: "Controlador_Compra",
                data: parametros,
                dataType: "text",
                success: function (resp){
                    $('#cantidad').html(resp+"<img src='Img/conta.png' style='width: 16px'>");
                }
            }).fail(function(jqXHR, tetStatus, errorThrown){
                alert("Error:" + jqXHR.Status);
            });
        }
    </script>
</html>
