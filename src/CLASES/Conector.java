

//Eliott Reyes Almanzar #20220848
package CLASES;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conector {
    
    public static Connection cx;
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/bdejemplo";
    
    public Conector(){
        cx = null;
        try {
           Class.forName(Driver);
           cx = DriverManager.getConnection(URL, user, password);
           
           if(cx != null){
               System.out.println("Conexion Exitosa");
           }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar "+e);
        }
    }
   
    
    public Connection getConnection(){
        return cx;
    }
    
    public void desconectar(){
        cx = null;
        if(cx == null){
            System.out.println("Conexion Terminada");
        }
    }
    
   public int RegisterDB(String Usuario,String Nombre,String Apellido,String Telefono,String Correo,String Clave) {
    
       int resultado = 0;
       
       try { 
           ps = cx.prepareStatement("insert into usuario(Usuario,Nombre,Apellido,Telefono,Correo,Clave)values(?,?,?,?,?,?)");
           
           
           ps.setString(1, Usuario);
           ps.setString(2, Nombre);
           ps.setString(3, Apellido);
           ps.setString(4, Telefono);
           ps.setString(5, Correo);
           ps.setString(6, Clave);
           
           resultado = ps.executeUpdate();
           
           System.out.println("Usuario Registrado Correctamente"+resultado);
           
           
           
           
       }catch (Exception e){
           
           System.out.println("Error al Registar usuario"+e);
           
       }return resultado;
      
       
     
}
    public ResultSet ConsultarRegistro(String Sentencia){
         try {
            PreparedStatement ps = cx.prepareStatement(Sentencia);
            ResultSet respuesta = ps.executeQuery();
            
            return respuesta;
        } catch (Exception e) {
             System.out.println(e);
             return null;
        }
    }
    
    public int EjecutarSentencia(String Sentencia){
        
        try {
            PreparedStatement ps = cx.prepareStatement(Sentencia);
            ps.execute();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        
    }
            
   
}
