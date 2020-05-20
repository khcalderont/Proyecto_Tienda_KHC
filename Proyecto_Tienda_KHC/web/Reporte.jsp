<%-- 
    Document   : Reporte
    Created on : 12/05/2020, 12:15:46 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="Css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="Css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>

        <title>Reportes con Pulgin de Datatable</title>
    </head>
    <body>
    <center><h1>REPORTE TOTAL DE VENTAS</h1></center>
        <table id="tabla_id" class="display nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>CÉDULA</th>
                    <th>NOMBRE</th>
                    <th>FECHA</th>
                    <th>DESCRIPCIÓN</th>
                    <th>CANTIDAD</th>                  
                    <th>TOTAL</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </body>
    <script src="Js/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="Js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="Js/dataTables.buttons.min.js" type="text/javascript"></script>
    <script src="Js/buttons.flash.min.js" type="text/javascript"></script>
    <script src="Js/jszip.min.js" type="text/javascript"></script>
    <script src="Js/pdfmake.min.js" type="text/javascript"></script>
    <script src="Js/vfs_fonts.js" type="text/javascript"></script>
    <script src="Js/buttons.html5.min.js" type="text/javascript"></script>
    <script src="Js/buttons.print.min.js" type="text/javascript"></script>


    <script>
     
$(document).ready(function() {
           
       Reporte_Total_Ventas();
} );
        
        function Reporte_Total_Ventas(){
            var parametros = {'Operacion': 'Reporte_Total_Ventas'};
            $.ajax({
                type: 'POST',
                url: "Cont_Reporte",
                data: parametros,
                dataType: "text",
                success: function (resp){
                var dato= JSON.parse(resp);
                    $('#tabla_id').DataTable( {
                    dom: 'Bfrtip',
                    data: dato,
                    buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                        columns: [
                            {data:'cedula'},
                            {data:'nombre'},
                            {data:'fecha'},
                            {data:'descripcion'},
                            {data:'cantidad'},
                            {data:'total'}
                        ]
                    } );
                }
            }).fail(function(jqXHR, textStatus, errorThrown){
               alert("Error:" +jqXHR.Status);
            });
        }
    </script>
</html>
