<%-- 
    Document   : producto
    Created on : 11/05/2020, 03:44:36 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>REGISTRO DE PRODUCTOS</title>
    </head>
    <body>

        <form>
            <center>
                <div class="contenedor"> 
                <div id="formulario">
                        <h3>FORMULARIO DE REGISTRO DE PRODUCTOS</h3>
                        
                    <table>
                        <tr>
                            <td>ID</td>
                            <td><input type="text" id="id_producto"/></td>
                        </tr>
                        <tr>
                            <td>REFERENCIA</td>
                            <td><input type="text" id="referencia"/></td>
                        </tr>
                        <tr>
                            <td>NOMBRE DEL PRODUCTO</td>
                            <td><input type="text" id="descripcion"/></td>
                        </tr>
                        <tr>
                            <td>VALOR DEL PRODUCTO</td>
                            <td><input type="text" id="precio"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input style="background: #1EB0D7" type="button" class="boton1" onclick="RegistrarProducto()" id="REGISTRAR" value="REGISTRAR" require/>
                            </td>
                        </tr>
                        <th></th>
                        <th></th>
                        <tr>
                            <td colspan="2">
                                <input style="background: #1EB0D7" type="button" class="boton1" onclick="listarProducto()" id="LISTRAR" value="LISTRAR"/>
                            </td>
                        </tr>
                        <th></th>
                        <th></th>
                        <tr>
                            <td colspan="2">
                                <input style="background: #1EB0D7" type="button" class="boton1" onclick="ActualizarProducto()" id="ACTUALIZAR" value="ACTUALIZAR"/>
                            </td>
                        </tr>
                    </table>
                    </div>
                </div>
                <div id="respuesta"> </div>
            </center>
        </form>

        
        <hr>
   <div id="mostrar" style="height: 500px; width: 600px; overflow-y: scroll;">
        <center>

            <caption>PRODUCTOS TIENDA ON LINE</caption>
            
            <table id="table_id" class="display"><font></font>
                <thead><font></font>
                    <tr><font></font>
                        <th>ID</th><font></font>
                        <th>REFERENCIA</th><font></font>
                        <th>PRECIO</th><font></font>
                        <th>DESCRIPCIÓN</th><font></font>
                        <th>Eliminar</th><font></font>
                        <th>Seleccionar</th><font></font>
                    </tr><font></font>
                </thead><font></font>
                <tbody id="ListaProductos"><font></font>

                </tbody><font></font>
            </table> <font></font>

    </center>
    </div>
        </di>

    </body>
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="Js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="Js/JsProductos.js" type="text/javascript"></script>
    <script>
        $(document).ready(function(){
            $("#formulario").show();
            $("#mostrar").hide();
        });
        function Nuevo_Producto(){
            $("#formulario").show(true);
        }
        
    </script>
</html>
