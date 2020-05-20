<%-- 
    Document   : Cliente
    Created on : 11/05/2020, 03:58:44 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de clientes</title>
    </head>
    <body>
        <%
                HttpSession sesion = request.getSession(true);
            try{
                String usuario = (String)sesion.getAttribute("Sesion_Usuario");
                if(usuario==null){
                    response.sendRedirect("login.jsp");
                    
                }
            }
            catch(Exception exc){
                
            }
            %>
        <form>
            <center>
                <h3>Formulario registro de clientes</h3>
                <br>
                <div class="contenedor">
                    <table>
                        <tr><td>Identificacion</td><td><input type="number" id="cedula"/></td></tr>
                        <tr><td>Nombre</td><td><input type="text" id="nombre"/></td></tr>
                        <tr><td>Direccion</td><td><input type="text" id="direccion"/></td></tr>
                        <tr><td>Telefono</td><td><input type="number" id="telefono"/></td></tr>
                        <tr><td>Correo</td><td><input type="text" id="usuario"/></td></tr>
                        <tr><td>clave</td><td><input type="password" id="clave"/></td></tr>
                        <tr>
                            <td><button type="button" class="boton1" onclick="Registrar_Cliente();" id="Registrar">Registrar</button></td>
                            <td><button type="button" class="boton1" onclick="Lista_Clientes();">Listar</button></td>  
                            <td><button type="button" class="boton1" onclick="Actualizar();" id="listar">Exict</button></td>
                        </tr>    
                    </table>
                    <div id="respuesta"> </div>
                </div>
            </center>
            <br>
            <h3 align="center">Clientes Tienda on line</h3>
            <table align="center" width="70%" border="1">
                
                <br>
                <thead>
                     <tr>
                    <br>
                        <th>Identificacion</th>
                        <th>Nombre</th>
                        <th>Direccion</th>
                        <th>Telefono</th>
                        <th>Usuario</th>
                        <th>Clave</th>
                        <th>Admin</th>
                        <th>Select</th>

                    </tr>
                </thead>
                <tbody id="Lista_Clientes">
                    
                </tbody>
            </table>
        </form> 
    </body>
    <script type="text/javascript">
            function Registrar_Cliente(){
                var cedula =$('#cedula').val();
                var nombre =$('#nombre').val();
                var direccion =$('#direccion').val();
                var telefono =$('#telefono').val();
                var usuario =$('#usuario').val();
                var clave =$('#clave').val();
                var Parametros ={'Operacion' : 'Registrar', 'cedula' : cedula, 'nombre' : nombre, 'direccion' : direccion,
                     'telefono' : telefono, 'usuario' : usuario, 'clave' : clave};
                 
                
                $.ajax({
                    type: "POST",
                    url: "ControladorClientes",
                    data: Parametros,
                    dataType: "text",
                    success: function (respuesta_servidor){
                        $("#cedula").val('');
                        $("#nombre").val('');
                        $("#direccion").val('');
                        $("#telefono").val('');
                        $("#usuario").val('');
                        $("#clave").val('');
                      Lista_Clientes();
                      alert(respuesta_servidor);
                        
                    }
                }).fail(function (jqXHR, textStatus, errorThrown){
                    alert('Error: ' + jqXHR.status);
                });
            }
            
            
            function Lista_Clientes(){
                var Parametros = { 'Operacion' : 'Lista_Clientes'};
                $.ajax({
                    type:"POST",
                    url: "ControladorClientes",
                    data: Parametros,
                    dataType: "text",
                    success: function (respuesta_servidor){
                        $('#Lista_Clientes').html(respuesta_servidor);
                              
                    }
                }).fail(function (jqXHR, textStatus, errorThrown){
                    alert('Error: ' + jqXHR.status);
                });
            }
            function Eliminar_cliente(id_Clien){
                var parametros ={
                    'Operacion' : 'Eliminar_cliente', 'cedula':id_Clien
                };
                $.ajax({
                    type: "POST",
                    url: "ControladorClientes",
                    data: parametros,
                    dataType: "text",
                    success: function(respuesta_servidor){
                        $('#Lista_Clientes').html(respuesta_servidor);
                        Lista_Clientes();
                    }
                }).fail(function (jqXHR, textStatus, errorThrown){
                    alert('Error: ' + jqXHR.status);
                });
            }
            function Buscar(id_Clien){
                var parametros={
                    'Operacion': 'Buscar', 'cedula': id_Clien
                };
                $.ajax({
                    type: "POST",
                    url: "ControladorClientes",
                    data: parametros,
                    dataType: "text",
                    success: function (respuesta_servidor){
                        var datos=JSON.parse(respuesta_servidor)
                        $.each(datos, function (Cliente, val){
                            $("#cedula").val(val.cedula);
                            $("#nombre").val(val.nombre);
                            $("#direccion").val(val.direccion);
                            $("#telefono").val(val.telefono);
                            $("#usuario").val(val.usuario);
                            $("#clave").val(val.clave);                       
                        });
                    }
                }).fail(function (jqXHR, textStatus, errorThrown){
                    alert('Error: ' + jqXHR.status);
                });
            }
            function Actualizar(){
                var cedula = $('#cedula').val();
                var nombre = $('#nombre').val();
                var direccion = $('#direccion').val();
                var telefono = $('#telefono').val();
                var usuario = $('#usuario').val();
                var clave = $('#clave').val();
                var parametros ={
                    'Operacion' : 'Actualizar' , 'cedula':cedula, 'nombre':nombre, 'direccion':direccion,
                    'telefono':telefono, 'usuario':usuario, 'clave':clave
                    
                };
                
                $.ajax({
                    type: "POST",
                    url: "ControladorClientes",
                    data: parametros,
                    dataType: "text",
                    success: function (respuesta_servidor){
                        Lista_Clientes();
                        $('#cedula').val('');
                        $('#nombre').val('');
                        $('#direccion').val('');
                        $('#telefono').val('');
                        $('#usuario').val('');
                        $('#clave').val('');
                        alert(respuesta_servidor);
                    }
                }).fail(function (jqXHR, textStatus, errorThrown){
                    alert('Error: ' + jqXHR.status);
                });
            }
            
            
            
            
 
            
        </script>
        <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
</html>
