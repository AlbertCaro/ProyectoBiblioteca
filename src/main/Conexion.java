package main;
/**
 *
 * @author Jonny
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Conexion  {
    private Connection Conexion;
    private String Servidor;
    private String Database;
    private String Usuario;
    private String Password;
    private String Url;    
    public Conexion(String Servidor, String Database, String Usuario, String Password){
        try {
            this.Servidor = Servidor;
            this.Database = Database;
            this.Usuario = Usuario;
            this.Password = Password;
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Url="jdbc:mysql://"+this.Servidor+"/"+this.Database;
            Conexion=DriverManager.getConnection(this.Url, this.Usuario, this.Password);
            System.out.println("Conexion a Base de Datos "+Url+" . . . . .Exitosa");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo conectar, "+ex.toString());
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    public Connection getConexion(){
        return Conexion;
    }
    public Connection cerrarConexion(){
        try {
            Conexion.close();
             JOptionPane.showMessageDialog(null,"Cerrando conexion a "+Url+" . . . . . Exitosa");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.toString());
        }
        Conexion=null;
        return Conexion;
    }
}
