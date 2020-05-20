<%-- 
    Document   : Principal
    Created on : 11/05/2020, 03:50:45 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
         <link href="Css/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
         <link href="Css/Css_Principal.css" rel="stylesheet" type="text/css"/>
        <title>Tienda Online</title>
    </head>
     <body>
        <% 
            HttpSession sesion = request.getSession(true);
            try
            {
                String usuario = (String)sesion.getAttribute("Sesion_Usuario");
                String Tipo_Usuario = (String)sesion.getAttribute("Tipo_Usuario");
                if (usuario==null){
                    response.sendRedirect("index.jsp");
                }
            }catch(Exception exc){}
        %>
        <header id="logo">
            <h1><a>Tienda En Linea</a></h1>
            <p id="subtitulo">Bienvenido a nuestro sitio</p>
            <div>
                <% out.println(session.getAttribute("Tipo_Usuario")) ;%>
                Usuario : <% out.println(session.getAttribute("Sesion_Usuario")) ;%>                           
            </div>
        </header>
        <a href='javascript:Cerrar_Sesion()'>Cerrar Sesión</a>
        <section>
            <nav id="menu">
                <ul>
                    <li><a href="Principal.jsp" title="Pagina Principal">Inicio</a></li>
                    <% 
                        String Tipo_Usuario = (String) session.getAttribute("Tipo_Usuario");
                        if (Tipo_Usuario.equals("Administrador"))
                        {
                    %>
                    <li><a href="javascript:Cargar_Pagina_Productos()" title="Articulos">Articulos</a></li>
                    <li><a href="javascript:Cargar_Pagina_Reporte()" title="Reportes">Reporte</a></li>
                    <li><a href="javascript:Cargar_Pagina_Grafica()" title="Graficar">Graficar</a></li>
                    <li><a href="javascript:Cargar_Pagina_Contacto()" title="Contactos">Contacto</a></li>
                    <% 
                        }else
                        {
                    %>
                    <li><a href="javascript:Cargar_Pagina_Compra()" title="Realizar Compra">Compras</a></li>
                    <li><a href="javascript:Cargar_Pagina_Carrito()" title="Compras Realizadas">
                        <div id="cantidad">0<img src="Img/carrito.png" style="width: 16px"></div></a></li>
                    <li><a href="javascript:Cargar_Pagina_Contacto()" title="Contactos">Contacto</a></li>
                    <% 
                        }
                    %>
                </ul>
            </nav>
        </section>
        <section id="seccion">
            <header id="subtitulo2">
                <h4>Los mejores Productos</h4>
            </header><img src="Img/productos.jpg" style="width:300px; height:200px" id="imagen">
            <aside id="ladoderecho">
                <div id="paginas">
                    <p>
                        Somos una marca Colombiana de moda, enfocada es un estilo de vida joven, fresco y urbano.
                        Encuentra en nuestra tienda online ropa para mujer, hombre, niña y niño perfectamente para todas
                        las ocasiones y momentos:
                        <br><br>
                        trabajo, universidad, fiesta, comida familiar, tarde de amigos. Encuentra tambien en Tennis
                        las ultimas tendencias de moda en jeans,
                        zapatos, accesorios, camisas, vestidos, chaquetas, buzos y demas lineas para estar perfecto
                        en cada ocasion. Nuestras colecciones estan pensadas en ropa de moda del momento para que lleves
                        lo mejor de las tendencias a tu closet y disfrutemos del estilo de vida.
                    </p>
                </div>
                 
            </aside>
        </section>
        <footer id="pie"><p>Todos los derechos reservados - 2020</p></footer>
    </body>
    <script src="Js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="Js/jquery.dataTables.js" type="text/javascript"></script>
    <script>
        function Cargar_Pagina_Grafica(){
            $("#paginas").load("Graficar_Ventas.jsp");
        }
    
        function Cargar_Pagina_Productos(){
            $("#paginas").load("producto.jsp");
        }
        function Cargar_Pagina_Compra(){
            $("#paginas").load("Compra.jsp");
        }
        function Cargar_Pagina_Reporte(){
            $("#paginas").load("Reporte.jsp");
        }
        function Cargar_Pagina_Carrito(){
            $("#paginas").load("Articulos_Comprados.jsp");
        }
        function Cargar_Pagina_Contacto(){
            $("#paginas").load("Contacto.jsp");
        }
        function Cerrar_Sesion(){
            var parametros = {'Operacion':'Cerrar_sesion'};
            $.ajax({
                type:"POST",
                url:"ControladorUsuario",
                data:parametros,
                dataType:"text",
                success: function (resp){
                    alert(resp);
                    location.href="index.jsp";
                }
            }).fail(function(jqXHR, texStatus, erroThrown){
                alert("Error: "+jqXHR.Status);
            });
        }
    </script>
</html>
