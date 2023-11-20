
package com.raesoft.proyectocrudapp.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexionCrud {
    Connection conexion=null;    
    String usuario="LUIS";
    String contrasena="sql";
    String db="dbAlumnos2";
    String ip="localhost";
    String puerto="1433";
    
    public Connection obtenerConexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+db+";"+
                            "encrypt=true;trustServerCertificate=true";            
            conexion=DriverManager.getConnection(cadena,usuario,contrasena);
            JOptionPane.showMessageDialog(null,"conexion exitosa");     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
            e.printStackTrace();                   
        }
        return conexion;
    }
}
