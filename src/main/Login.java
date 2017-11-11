package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * ventana de login
 * @author Jonny
 */
public class Login extends Window implements ActionListener, MouseListener, KeyListener{
    Conexion conexion;
    String Tipo, Usuario;
    //Label e icono
    ImageIcon IconBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    JLabel JLUser = new JLabel("Usuario: ");
    JLabel JLContraseña = new JLabel("Contraseña: ");
    JLabel JLTitulo = new JLabel("AdmyBook");
    JLabel JLInstruc = new JLabel("Por favor ingresa usuario y contraseña");
    JTextField JTFUser = new JTextField();
    //TextField TFContraseña = new TextField();// hay un metodo para poner * y cubrir las letras
    JPasswordField JPFPass = new JPasswordField();// se utiliza para password
    JButton JBLogin = new JButton("Iniciar Sesión");
    JButton JBInvitado = new JButton("Modo Invitado");
    private InputMap Enter = new InputMap(); //Enter
    public Login(Conexion conexion){
        this.conexion = conexion;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(IconBiblio.getImage());
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));
        this.setTitle("Iniciar sesión");
        //JLabel
        JLTitulo.setBounds(75, 10, 250, 40);
        JLTitulo.setFont(new Font("arial",1,26));
        JLTitulo.setForeground(new Color(53, 81, 181));
        JLTitulo.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        JLInstruc.setBounds(10, 55, 300, 30);
        JLInstruc.setFont(new Font("Arial",1,14));
        JLUser.setBounds(10, 100, 100, 30);
        JLUser.setFont(new Font("Arial",1,13));
        JLContraseña.setBounds(10, 140, 100, 30);
        JLContraseña.setFont(new Font("Arial",1,13));
        //TextField
        addTextField(JTFUser,115,100,200,"Usuario",this);
        addTextField(JPFPass,115,100,200,"Contraseña",this);
        //JTFUser.setBounds(115, 100, 200, 30);
        JPFPass.setBounds(115, 140, 200, 30);
        //JButton
        JBLogin.setBounds(215, 220, 150, 30);
        JBLogin.setToolTipText("Iniciar sesión");
        JBLogin.setBackground(new Color(53, 81, 181));
        JBLogin.setFont(new Font("arial", 1, 16));
        JBLogin.setForeground(new Color(255,255,255));
        JBInvitado.setBounds(30, 220, 150, 30);
        JBInvitado.setToolTipText("Iniciar sesión en modo Invitado");
        JBInvitado.setBackground(new Color(53, 81, 181));
        JBInvitado.setFont(new Font("arial", 1, 16));
        JBInvitado.setForeground(new Color(255,255,255));
        Enter.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        Enter.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
        JBLogin.setInputMap(0, Enter);
        Oyentes();
        this.setLayout(null);
        this.setVisible(true);
    }
    private void Oyentes() {
        this.add(JLTitulo);
        this.add(JLInstruc);
        this.add(JLUser);
        this.add(JLContraseña);
        this.add(JTFUser);
        this.add(JPFPass);
        this.add(JBLogin);
        this.add(JBInvitado);
        //listeners
        JBLogin.addActionListener(this);
        JBInvitado.addActionListener(this);
        JTFUser.addKeyListener(this);
        JPFPass.addKeyListener(this);
        JBLogin.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource()== JBLogin) {
            if (JTFUser.getText().isEmpty() || JPFPass.getPassword().length==0) {
                JOptionPane.showMessageDialog(rootPane, "Los campos no deben de estar vacios");
            } else {
                try {
                    PreparedStatement BuscaUsuarioStm = conexion.getConexion().prepareCall("SELECT * FROM Usuarios INNER JOIN Tipos ON Tipos.idTipos = Usuarios.idTipos WHERE Usuario = ? AND Pass = ?");
                    BuscaUsuarioStm.setString(1, JTFUser.getText());
                    BuscaUsuarioStm.setString(2, md5(new String(JPFPass.getPassword())));
                    ResultSet RsBuscar = BuscaUsuarioStm.executeQuery();
                    if (RsBuscar.next()) {
                        //JOptionPane.showMessageDialog(rootPane,"Bienvenido: "+RsBuscar.getObject("Usuario"),"Tipo: "+RsBuscar.getObject("Tipo"),1);
                        Tipo = new String(RsBuscar.getString("Tipo"));
                        Usuario = new String(RsBuscar.getString("Usuario"));
                        SplashBienv VBienvenida = new SplashBienv(conexion, Tipo, Usuario);
                        //WindowMain main = new WindowMain(conexion, Tipo, Usuario);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Acceso denegado: \nUsuario o Contraseña incorrecto.", "Error de Autentificación", 0);
                        limpiarCampos();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "ERROR: "+e);
                }
            }
        }else if (me.getSource() == JBInvitado) {
            SplashBienv VBienvenida = new SplashBienv(conexion, "SuperAdministrador", "SuperAdministrador");
            //WindowMain Principal = new WindowMain(conexion,"Invitado", "Invitado");
            this.dispose();
        }
    }
    private void limpiarCampos(){
        JTFUser.setText("");
        JPFPass.setText("");
        JTFUser.requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {//hacemos que se des place al siguiente JTextField cada que apretemos la tecla enter
        if (ke.getSource()==JTFUser){
            if (ke.getKeyChar()== ke.VK_ENTER)
                JPFPass.requestFocus();
        }else if (ke.getSource()==JPFPass){
            if (ke.getKeyChar()==ke.VK_ENTER)
                JBLogin.requestFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public PreparedStatement addStatementParams(PreparedStatement statement, int type) throws NoSuchAlgorithmException, SQLException {
        return null;
    }

    @Override
    public ArrayList<JComponent> fillListTexts() {
        return null;
    }

    @Override
    public void returnQueryResults(ResultSet resultSet) throws SQLException {

    }

    @Override
    public void cleanForm() {

    }
}
