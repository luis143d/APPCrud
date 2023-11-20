
package com.raesoft.proyectocrudapp.model;

import com.raesoft.proyectocrudapp.connect.conexionCrud;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CAlumnos {
    public void mostrarAlumnos(JTable paramTablaAlumnos){
        conexionCrud objetoConexion=new conexionCrud();
        DefaultTableModel modelo=new DefaultTableModel();
        String sql="";
        modelo.addColumn("Cdigo");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Edad");
        paramTablaAlumnos.setModel(modelo);
        sql="select * from dbAlumnos";
        String [ ] datos=new String[5];
        Statement st;
        try {
            st=objetoConexion.obtenerConexion().createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()) {
                datos[0 ]=rs.getString(1);
                datos[1 ]=rs.getString(2);
                datos[2 ]=rs.getString(3);
                datos[3 ]=rs.getString(4);
                datos[4 ]=rs.getString(5);
                modelo.addRow(datos);
            }
            paramTablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se mostraron los registros:"+ e.toString());
        }
        
    }
    public void seleccionarAlumnos(JTable paramTablaAlumnos, JTextField paramCodigo, JTextField paramDni, JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad){
        try {
            int fila=paramTablaAlumnos.getSelectedRow();
            if (fila>=0) {
                paramCodigo.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramDni.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 3).toString());
                paramEdad.setText(paramTablaAlumnos.getValueAt(fila, 4).toString());
            } else {
                JOptionPane.showMessageDialog(null, "nose selecciono registros error:   ");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error de seleccion, error:"+e.toString());
        }
    }
    public void insertarAlumnos(JTextField paramDni , JTextField paramNombres , JTextField paramApellidos , JTextField paramEdad){
        conexionCrud objetoConeccion = new conexionCrud();
        String consulta="insert into dbAlumnos (dni,nombres,apellidos,edad) values(?,?,?,?)";
        try {
            CallableStatement cs=objetoConeccion.obtenerConexion().prepareCall(consulta);
            cs.setString(1, paramDni.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setInt(4, Integer.parseInt(paramEdad.getText()));
            cs.execute();
            JOptionPane.showConfirmDialog(null, "Se inserto correctamente");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error de insercion, error: "+e.toString());
        }
    }
    public void modificarAlumnos( JTextField paramCodigo, JTextField paramDni, JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad){
   conexionCrud objetoConexion=new conexionCrud();
   String consulta="update dbAlumnos set dbAlumnos.dni=?,dbAlumnos.nombres=?,dbAlumnos.apellidos=?,"+"dbAlumnos.edad=? where dbAlumnos.codigo=?";
        try {
            CallableStatement cs=objetoConexion.obtenerConexion().prepareCall(consulta);
             cs.setString(1, paramDni.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setInt(4, Integer.parseInt(paramEdad.getText()));
            cs.setInt(5, Integer.parseInt(paramCodigo.getText()));
            cs.execute();
            JOptionPane.showConfirmDialog(null, "Se modifico correctamente");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error de modificacion, error: "+e.toString());
        }
    }
    public void eliminarAlumnos(JTextField paraCodigo){
        conexionCrud objetoConexion=new conexionCrud();
        String consulta="delete from dbAlumnos where dbAlumnos.codigo=?;";
        try {
            CallableStatement cs=objetoConexion.obtenerConexion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(paraCodigo.getText()));
            cs.execute();
            JOptionPane.showConfirmDialog(null, "Se elimino correctamente");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error de eliminacion, error: "+ e.toString());
        }
    }
}
