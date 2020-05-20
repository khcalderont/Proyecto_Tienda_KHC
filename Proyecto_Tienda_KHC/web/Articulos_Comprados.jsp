<%-- 
    Document   : Articulos_Comprados
    Created on : 11/05/2020, 03:58:10 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
        
        <title>Articulos Comprados</title>
    </head>
    <body>
        <table id="tabla_compra">
            <thead>
                <tr>
                    <th>Cliente</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </body>
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="Js/jquery.dataTables.js" type="text/javascript"></script>
    

    <script>
        $(document).ready(function(){
           listar_Productos_Comprados(); 
        });
        
        function listar_Productos_Comprados(){
            var parametros = {'Operacion': 'listar_Productos_Comprados'};
            $.ajax({
                type: 'POST',
                url: "Controlador_Compra",
                data: parametros,
                dataType: "text",
                success: function (resp){
                var dato= JSON.parse(resp);
                    $('#tabla_compra').DataTable( {
                    data: dato,
                        columns: [
                            {data:'cliente'},
                            {data:'producto'},
                            {data:'cantidad'},
                            {data:'valor'}
                        ]
                    } );
                }
            }).fail(function(jqXHR, textStatus, errorThrown){
               alert("Error:" +jqXHR.Status);
            });
        }
    </script>
</html>
