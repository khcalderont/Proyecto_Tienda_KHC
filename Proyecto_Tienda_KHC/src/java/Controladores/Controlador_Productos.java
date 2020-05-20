/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kevin
 */
public class Controlador_Productos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            if(request.getParameter("Operacion").equals("Registrar"))
            {
                try{
                String referencia = request.getParameter("referen");
                String descripcion = request.getParameter("descrip");
                String precio = request.getParameter("precio");
                Conexion conec = new Conexion();
                String sql = "insert into producto (referencia, precio, descripcion) VALUES ('"+referencia+"','"+precio+"','"+descripcion+"')";
                String Mensaje = conec.Insertar_Modificar_Eliminar(sql);
                out.println("<center><h3>"+Mensaje+"</h3></center>");
                }catch(Exception exc){}
            }
            if (request.getParameter("Operacion").equals("Listar"))
            {
                try
                {
                    Conexion conec = new Conexion();
                    String sql = "select id_producto, referencia, precio, descripcion from producto";
                    ResultSet res = conec.Consultar_Sql(sql);
                    while(res.next()){
                        out.println("<tr><td>"+res.getString("id_producto")+"</td>");
                        out.println("<td>"+res.getString("referencia")+"</td>");
                        out.println("<td>"+res.getString("precio")+"</td>");
                        out.println("<td>"+res.getString("descripcion")+"</td>");
                        out.println("<td><a href='javascript:EliminarProducto("+res.getString("id_producto")+");'>Eliminar</a></td>");
                        out.println("<td><a href='javascript:BuscarProductos("+res.getString("id_producto")+"); $(\"#mostrar\").hide(); $(\"#formulario\").show();'>Seleccionar</a></td></tr>");
                    }
                }catch(Exception exc){} 
            }
            if(request.getParameter("Operacion").equals("EliminarPro"))
            {
             try
             {
                 int id_prod = Integer.parseInt(request.getParameter("id_Produc"));
                 Conexion conec = new Conexion();
                 String sql =" delete from producto where id_producto="+id_prod+" ";
                 String Mesjaes= conec.Insertar_Modificar_Eliminar(sql);
                 out.println("<center><h3>"+Mesjaes+"</h3></center>");
             }catch(Exception exc){}
            }
            if (request.getParameter("Operacion").equals("BuscarProducto"))
            {
                
                int id_prod= Integer.parseInt(request.getParameter("id_Produc"));
                try
                {
                    Conexion conec= new Conexion();
                    String sql = "select id_producto, referencia, descripcion,precio from producto where id_producto="+id_prod+" ";
                    ResultSet res = conec.Consultar_Sql(sql);
                    String dato ="";
                    while (res.next())
                    {
                        dato += " {"+
                                "\"id_producto\":\""+res.getString(1)+"\","+
                                "\"referencia\":\""+res.getString(2)+"\","+
                                "\"descripcion\":\""+res.getString(3)+"\","+
                                "\"precio\":\""+res.getString(4)+"\" "+
                                "},";
                    }        
                    String datos = dato.substring(0, dato.length()-1);
                    String json ="["+datos+"]";
                    out.println(json);
                }catch(Exception exc){}
            }
            if(request.getParameter("Operacion").equals("Actualizar"))
            {
                try
                {
                    int id_prod = Integer.parseInt(request.getParameter("id_produc"));
                    String referencia = request.getParameter("referen");
                    String descripcion = request.getParameter("descrip");
                    String precio = request.getParameter("pre");
                    Conexion conec = new Conexion();
                    String sql = "update producto set referencia='"+referencia+"', precio='"+precio+"',descripcion='"+descripcion+"'  where id_producto="+id_prod+" ";
                    String Mesjaes = conec.Insertar_Modificar_Eliminar(sql);
                    out.println("<center><h3>"+Mesjaes+"</h3></center>");
                }catch(Exception exc){}
            }
            
            if(request.getParameter("Operacion").equals("Listar_productos")){
                try{
                    Conexion conec = new Conexion();
                    String sql = "SELECT * from producto ORDER BY id_producto DESC";
                    ResultSet Res = conec.Consultar_Sql(sql); 
                    String dato = "";
                    while (Res.next()){
                        dato += " {"+
                                "\"id_producto\":\""+ Res.getString(2)+"\"," +
                                "\"referencia\":\""+Res.getString(1)+"\"," +
                                "\"precio\":\""+Res.getString(3)+"\"," +
                                "\"descripcion\":\""+Res.getString(4)+"\"," + 
                                "\"comprar\":\"<a href='javascript:Comprar_Producto("+Res.getString(2)+")' ><img src='Img/compras.png' style='width:25px'></a>\" " + 
                                "}," ;
                    }
                    String datos = dato.substring(0, dato.length()-1);
                    String json = "["+datos+"]";
                    out.println(json);
                }catch(Exception exc){}  
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
