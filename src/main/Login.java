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
    Conexion conexion = new Conexion("130.211.214.19","BibliotecaDB","root","dynadev123");
    //Label e icono
    ImageIcon IconBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    ImageIcon IconBiblioM = new ImageIcon(getClass().getResource("/images/logoBiblio143.png"));
    JLabel JBiblio = new JLabel(IconBiblioM);
    JLabel JLUser = new JLabel("Usuario: ");
    JLabel JLContraseña = new JLabel("Contraseña: ");
    JLabel JLTitulo = new JLabel("¡Bienvenido!");
    JLabel JLInstruc = new JLabel("Por favor ingresa usuario y contraseña");
    JTextField JTFUser = new JTextField();
    //TextField TFContraseña = new TextField();// hay un metodo para poner * y cubrir las letras
    JPasswordField JPFPass = new JPasswordField();// se utiliza para password
    JButton JBOk = new JButton("Ingresar");
    JButton JBCancelar = new JButton("Cancelar");
    public Login(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(IconBiblio.getImage());
        this.getContentPane().setBackground(new java.awt.Color(254,223,168));
        this.setTitle("Iniciar sesión");
        //JLabel
        JLTitulo.setBounds(175, 10, 150, 40);
        JLTitulo.setFont(new Font("forte",1,20));
        JLTitulo.setForeground(new Color(215, 92, 30));
        JLTitulo.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        JLInstruc.setBounds(10, 55, 250, 30);
        JLUser.setBounds(10, 100, 100, 30);
        JLContraseña.setBounds(10, 140, 100, 30);
        //TextField
        JTFUser.setBounds(115, 100, 200, 30);
        //TFContraseña.setBounds(115, 140, 200, 30);
        //TFContraseña.setEchoChar('*');
        JPFPass.setBounds(115, 140, 200, 30);
        //JImage
        JBiblio.setBounds(130, 55, 143, 143);
        //JButton
        JBOk.setBounds(350, 220, 120, 30);
        JBOk.setBackground(new Color (215,88,21));
        JBOk.setFont(new Font("arial", 1, 16));
        JBOk.setForeground(new Color(255,255,255));
        //JBCancelar.setBounds(220, 220, 120, 30);
        //JBCancelar.setToolTipText("Salir del sistema");
        //JBCancelar.setBackground(new Color (215,88,21));
        //JBCancelar.setFont(new Font("arial", 1, 16));
        //JBCancelar.setForeground(new Color(255,255,255));
        
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
        this.add(JBOk);
        this.add(JBCancelar);
        //listeners
        JBOk.addActionListener(this);
        JBCancelar.addActionListener(this);
        JTFUser.addKeyListener(this);
        JPFPass.addKeyListener(this);
        JBOk.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource()== JBOk) {
            if (JTFUser.getText().isEmpty() || JPFPass.getPassword().length==0) {
                JOptionPane.showMessageDialog(rootPane, "Los campos no deben de estar vacios");
            } else {
                try {
                    PreparedStatement BuscaUsuarioStm = conexion.getConexion().prepareCall("SELECT * FROM Usuarios WHERE Usuario = ? AND Pass = ?");
                    BuscaUsuarioStm.setString(1, JTFUser.getText());
                    BuscaUsuarioStm.setString(2, md5(new String(JPFPass.getPassword())));
                    ResultSet RsBuscar = BuscaUsuarioStm.executeQuery();
                    if (RsBuscar.next()) {
                        JOptionPane.showMessageDialog(rootPane,"Bienvenido: "+RsBuscar.getObject("Usuario"),"Tipo: "+RsBuscar.getObject("Tipo"),1);
                        WindowMain main = new WindowMain(conexion);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Las credenciales no coinciden.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "ERROR: "+e);
                }
            }
        }else if (me.getSource() == JBCancelar) {
            System.exit(0);
        }
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
                JBOk.requestFocus();
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
