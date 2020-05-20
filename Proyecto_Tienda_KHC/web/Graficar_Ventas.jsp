<%-- 
    Document   : Graficar_Ventas
    Created on : 13/05/2020, 11:18:58 AM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
     
        <title>Gráficas</title>
    </head>
    <body>
        <div id="myfirstchart" style="height: 250px;"></div>
    </body>
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
    
    <script>
     
$(document).ready(function() {
           
       Gaficar_Productos();
} );



        function Gaficar_Productos(){
            var parametros = {'Operacion': 'Gaficar_Productos'};
            $.ajax({
                type: 'POST',
                url: "Cont_Grafica",
                data: parametros,
                dataType: "text",
                success: function (resp){
                dato= JSON.parse(resp);
                
                new Morris.Bar({
                    element: 'myfirstchart',
                    data: dato,
                    xkey: 'descripcion',
                    ykeys: ['cantidad'],
                    labels: ['cantidad']
                });
                }
            }).fail(function(jqXHR, textStatus, errorThrown){
               alert("Error:" +jqXHR.Status);
            });
        }

    </script>
</html>
