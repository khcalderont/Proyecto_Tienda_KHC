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
public class ControladorClientes extends HttpServlet {

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
            
            if(request.getParameter("Operacion").equals("Registrar_Cliente")){
                try {
                float cedula = Float.parseFloat(request.getParameter("cedula"));
                String nombre = request.getParameter("nombre");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String usuario = request.getParameter("usuario");
                String clave = request.getParameter("clave");
                
                Conexion conec = new Conexion();
                String sql = "insert into cliente (cedula,nombre,direccion,telefono,usuario,clave) "
                        + "VALUES ('"+cedula+"','"+nombre+"','"+direccion+"','"+telefono+"','"+usuario+"','"+clave+"')";
                String Mensaje = conec.Insertar_Modificar_Eliminar(sql);
                out.println("<center><h3>"+Mensaje+"</h3></center>");
                }catch(Exception exc){}
            }
            if (request.getParameter("Operacion").equals("Lista_Clientes"))
            {
                try
                {
                    Conexion conec = new Conexion();
                    String sql = "select cedula, nombre, direccion, telefono, usuario, clave from cliente";
                    ResultSet res = conec.Consultar_Sql(sql);
                    while(res.next()){
                        out.println("<tr><td>"+res.getString("cedula")+"</td>");
                        out.println("<td>"+res.getString("nombre")+"</td>");
                        out.println("<td>"+res.getString("direccion")+"</td>");
                        out.println("<td>"+res.getString("telefono")+"</td>");
                        out.println("<td>"+res.getString("usuario")+"</td>");
                        out.println("<td>"+res.getString("clave")+"</td>");
                        out.println("<td><a href='javascript:EliminarCliente("+res.getString("cedula")+");'>Eliminar</a></td>");
                        out.println("<td><a href='javascript:BuscarClientes("+res.getString("cedula")+");'>Seleccionar</a></td></tr>");
                    }
                }catch(Exception exc){}
            }
            if(request.getParameter("Operacion").equals("Eliminar_cliente"))
            {
             try
             {
                 int id_clien = Integer.parseInt(request.getParameter("id_Clien"));
                 Conexion conec = new Conexion();
                 String sql =" delete from cliente where cedula="+id_clien+" ";
                 String Mesjaes= conec.Insertar_Modificar_Eliminar(sql);
                 out.println("<center><h3>"+Mesjaes+"</h3></center>");
             }catch(Exception exc){}
            }
             if (request.getParameter("Operacion").equals("Buscar"))
            {
                
                int id_clien= Integer.parseInt(request.getParameter("id_Clien"));
                try
                {
                    Conexion conec= new Conexion();
                    String sql = "select cedula, nombre, direccion, telefono, usuario, clave from cliente where cedula="+id_clien+" ";
                    ResultSet res = conec.Consultar_Sql(sql);
                    String dato ="";
                    while (res.next())
                    {
                        dato += " {"+
                                "\"cedula\":\""+res.getString(1)+"\","+
                                "\"nombre\":\""+res.getString(2)+"\","+
                                "\"direccion\":\""+res.getString(3)+"\","+
                                "\"telefono\":\""+res.getString(4)+"\","+
                                "\"usuario\":\""+res.getString(5)+"\","+
                                "\"clave\":\""+res.getString(6)+"\" "+
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
                    int id_clien = Integer.parseInt(request.getParameter("id_Clien"));
                    String nombre = request.getParameter("nom");
                    String direccion = request.getParameter("dire");
                    String telefono = request.getParameter("telf");
                    String usuario = request.getParameter("usu");
                    String clave = request.getParameter("pass");
                    Conexion conec = new Conexion();
                    String sql = "update cliente set nombre='"+nombre+"', direccion='"+direccion+"',telefono='"+telefono+"',usuario='"+usuario+"'"
                            + ",clave='"+clave+"'  where cedula="+id_clien+" ";
                    String Mesjaes = conec.Insertar_Modificar_Eliminar(sql);
                    out.println("<center><h3>"+Mesjaes+"</h3></center>");
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
