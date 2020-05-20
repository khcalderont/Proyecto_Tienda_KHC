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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin
 */
public class Controlador_Compra extends HttpServlet {

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
            
            if(request.getParameter("Operacion").equals("Realizar_Compra"))
            {
                try{
                    String id_prod = request.getParameter("id_producto");
                    HttpSession sesion = request.getSession(true);
                    String ident =(String)sesion.getAttribute("Cedula_Usuario");
                    Conexion conex = new Conexion();
                    String sql ="insert into compra (fecha, cantidad, fk_producto,fk_cliente)"
                            + "values(curdate(),1,"+id_prod+","+ident+")";
                    String dato= conex.Insertar_Modificar_Eliminar(sql);
                    out.println(dato);
                }catch(Exception exc){}
            }
            
            if(request.getParameter("Operacion").equals("Cantidad_Comprada"))
            {
                try{
                    String id_prod = request.getParameter("id_producto");
                    HttpSession sesion = request.getSession(true);
                    String ident = (String)sesion.getAttribute("Cedula_Usuario");
                    Conexion conex = new Conexion();
                    String sql ="select count(*) as cant from compra where fk_cliente="+ident;
                    ResultSet res = conex.Consultar_Sql(sql);
                    String dato="";
                    while(res.next()){
                        dato = res.getString("cant");
                    }
                    out.println(dato);
                }catch(Exception exc){}
            }
            
            if(request.getParameter("Operacion").equals("listar_Productos_Comprados"))
            {
                try{
                    String id_prod= request.getParameter("id_producto");
                    HttpSession sesion = request.getSession(true);
                    String ident = (String)sesion.getAttribute("Cedula_Usuario");
                    Conexion conex = new Conexion();
                    String sql ="select cl.nombre as cliente,p.descripcion as producto, sum(c.cantidad) as cantidad,"
                                + "(sum(c.cantidad)*p.precio) as valor from compra c\n"+
                                    "inner join producto p on p.id_producto = c.fk_producto\n"+
                                    "inner join cliente cl on cl.cedula= c.fk_cliente\n"+
                                    "where cl.cedula="+ident+"\n"+
                                    "group by p.id_producto";
                    ResultSet res= conex.Consultar_Sql(sql);
                    String dato="";
                    while(res.next()){
                        dato += "{"+
                                "\"cliente\":\""+res.getString("cliente")+"\","+
                                "\"producto\":\""+res.getString("producto")+"\","+
                                "\"cantidad\":\""+res.getString("cantidad")+"\","+
                                "\"valor\":\""+res.getString("valor")+"\" "+
                                "},";
                    }
                    String datos = dato.substring(0, dato.length()-1);
                    String json="["+datos+"]";
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
