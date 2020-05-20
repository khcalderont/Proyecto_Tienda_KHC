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
public class Cont_Reporte extends HttpServlet {

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
            
            if(request.getParameter("Operacion").equals("Reporte_Total_Ventas"))
            {
                try{

                    Conexion conex = new Conexion();
                    String sql ="select c.cedula, c.nombre, com.fecha, prod.descripcion, com.cantidad, (com.cantidad*prod.precio) as total "
                                + "from cliente c\n"+
                                    "join compra com on com.fk_cliente = c.cedula\n"+
                                    "join producto prod on prod.id_producto = com.fk_producto"; 

                    ResultSet Res= conex.Consultar_Sql(sql);
                    String dato="";
                    while(Res.next()){
                        dato += "{"+
                                "\"cedula\":\""+Res.getString("cedula")+"\","+
                                "\"nombre\":\""+Res.getString("nombre")+"\","+
                                "\"fecha\":\""+Res.getString("fecha")+"\","+
                                "\"descripcion\":\""+Res.getString("descripcion")+"\","+
                                "\"cantidad\":\""+Res.getString("cantidad")+"\","+
                                "\"total\":\""+Res.getString("total")+"\" "+
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



