/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kevin
 */
public class Conexion {
    
 protected String Servidor="localhost";   
 protected  String Usuario="root";    
 protected  String Clave=""; 
 protected String Bd="tiendaonline";  

 Statement St;
 ResultSet Rs=null;
 Connection llave=null;
 
 
    
    
    public  void Abrir(){
        try{    
            Class.forName("com.mysql.jdbc.Driver");  
            String url  = "jdbc:mysql://"+this.Servidor+":3306/"+this.Bd+"" ;      
           llave= DriverManager.getConnection(url,this.Usuario,this.Clave);      
            } catch (SQLException ex) {
                    ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();

                }
   } // fin de la funcion abriri la base de datos
    
    
  // se utiliza para iserta, modificr, eliminar en la bd
public String Insertar_Modificar_Eliminar(String Sql){
      try{
          Abrir();
       St=llave.createStatement();
       St.executeUpdate(Sql); 
        return "La operación se ejecutó de manera exitosa!!";
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
      return "No se ejecutó la operación!";
   }
 
    }// fin de la funcion Insertar_Modificar_Eliminar
  
// se utiliza para realizar cualquier consulta en la bd  

public  ResultSet Consultar_Sql(String Sql){

 try{ 
     Abrir();
    St=llave.createStatement();
    return St.executeQuery(Sql); 

    }catch(SQLException se){
    
      se.printStackTrace();
      return null;
    
   }

}// fin de la funcion Consultar_SQL
    
}
