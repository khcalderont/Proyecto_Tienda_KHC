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
public class ControladorUsuario extends HttpServlet {

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
            
            
            if(request.getParameter("Operacion").equals("Validar_Usuario"))
            {
                try{
                    String usuario = request.getParameter("usuario");
                    String clave = request.getParameter("clave");
                    
                    HttpSession sesion = request.getSession(true);
                    
                    Conexion conec = new Conexion();
                    String sql ="select nombre, cedula, tipo_usuario from cliente where usuario='"+usuario+"'and clave='"+clave+"' ";
                    ResultSet res = conec.Consultar_Sql(sql);
                    String dato = "";
                    
                    while (res.next()){
                        
                        sesion.setAttribute("Sesion_Usuario", res.getString("nombre"));
                        sesion.setAttribute("Cedula_Usuario", res.getString("cedula"));
                        sesion.setAttribute("Tipo_Usuario", res.getString("Tipo_Usuario"));
                        
                        dato = (String)sesion.getAttribute("Sesion_Usuario");
                    }
                    out.println(dato);
                }catch(Exception exc){
                 out.println(exc.getMessage());
                }
            }
            
            if (request.getParameter("Operacion").equals("Cerrar_sesion"))
            {
                try
                {
                    HttpSession sesion = request.getSession(true);
                    sesion.invalidate();
                    out.println("Se cerro la sesión");
                }catch(Exception exc){
                    out.println(exc.getMessage());
                }
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
