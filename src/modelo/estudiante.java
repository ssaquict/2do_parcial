/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SistemasU
 */
public class estudiante extends persona{
    private int id;
    private String carnet;
  
   
    private Conexion cn;
    public estudiante(){}
    public estudiante(int id,String carnet, String nombres, String apellidos, String direccion, String telefono,String genero, String email, String fecha_nacmiento) {
        super(nombres, apellidos, direccion, telefono, genero, email,fecha_nacmiento);
        this.carnet = carnet;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT id_estudiantes AS id,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento FROM estudiantes; ";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"ID","Carnet","Nombres","Apellidos","Direccion","Telefono","Genero"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[10];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("carnet");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("genero");
                datos[7] = consulta.getString("email");
                datos[8] = consulta.getString("fecha_nacimiento"); 
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion(); 
            
            
        }catch(SQLException ex){
             cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
        }
        
        return tabla;
    
    }
    
    @Override
    public void agregar(){
       
        
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento)VALUES(?,?,?,?,?,?,?,?);";
            
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getGenero());
            parametro.setString(7, getEmail());
            parametro.setString(8, getFecha_nacmiento());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(SQLException ex){
            System.out.println("Error......"+ ex.getMessage());
            
        }  
    }    
@Override
    public void actualizar(){
        try{
            PreparedStatement parametro;
            String query = "UPDATE estudiantes SET carnet= ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,genero= ?,email= ?,fecha_nacimiento= ? WHERE id_estudiantes = ?;";
            
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getCarnet());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getGenero());
            parametro.setString(7, getEmail());
            parametro.setString(8, getFecha_nacmiento());
            parametro.setInt(9, getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(SQLException ex){
            System.out.println("Error......"+ ex.getMessage());
            
        }
    }
    
    @Override 
    public void eliminar(){
    try{
            PreparedStatement parametro;
            String query = "DELETE FROM estudiantes WHERE id_estudiantes = ?;";
            
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
              
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);
                        
        }catch(SQLException ex){
            System.out.println("Error......"+ ex.getMessage());
            
        }
    }
    
}
