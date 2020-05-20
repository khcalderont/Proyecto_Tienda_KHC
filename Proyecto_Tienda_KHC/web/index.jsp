<%-- 
    Document   : index
    Created on : 11/05/2020, 10:40:05 AM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    
    body
    {
    background: #1EB0D7;
    font-family: Tahoma;
    font-size: 11px;
    margin: auto;
    }
    .cont
    {
        padding-top: 10px;
        border: 1px solid #fc7323;
        width: 300px;
        height: 150px;
        background-color: rgba(248, 248, 248, 0.493);
        border-radius: 20px;
    }
    
    .bott
    {
        height: 38px;
        background: #fc7323;
        border: 0;
        padding-left: 20px;
        padding-right: 20px;
        color: #fff;
        margin-left: 90px;
        margin-top: 30px;
    }
    
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <title>Sesiones con JSP</title>
    </head>
    <body>
    <center>
        <form method="POST">
        <h3>Ingresar al Sistema</h3>
        <div class="cont">
            <table>
                <tr>
                    <td><label>Usuario</label></td>
                    <td>
                        <input type="text" id="usuario" required="">
                    </td>
                </tr>
                <tr>
                    <td><label>Contraseña</label></td>
                    <td>
                        <input type="password" id="clave" required="">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" aling="center">
                        <input type="button" class="bott" onclick="Validar_Usuario()" value="VALIDAR"/>
                    </td>
                </tr>
            </table>
        </div>
        <div id="respuesta_servidor"></div>
        </form>
    </center>
    </body>
    
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        
        function Validar_Usuario(){
            var usuario = $("#usuario").val();
            var clave = $("#clave").val();
            var Parametros = {'Operacion':'Validar_Usuario','usuario':usuario,'clave':clave};
            $.ajax({
                type: "POST",
                url: "ControladorUsuario",
                data: Parametros,
                dataType: "text",
                success: function (resp){
                    var dato = resp;
                    
                    if(parseInt(dato.length) >2){
                        $(location).attr('href',"Principal.jsp");
                    }
                    else{
                        alert("No autorizado");
                    }
                }
            }).fail(function(jqXHR, textStatus, errorThrown){
               alert("Error: " + jqXHR.Status); 
            });
        }
        
    </script>
</html>
