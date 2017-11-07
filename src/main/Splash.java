package main;

import com.sun.awt.AWTUtilities;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *Splash
 * @author Jonny
 */
public class Splash extends JFrame {
    ImageIcon ImaBiblio = new ImageIcon(getClass().getResource("/images/LogoFinal.png"));
    ImageIcon IconBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    JLabel LblImagen = new JLabel(ImaBiblio);
    public Splash(){
        Conexion conexion = new Conexion("130.211.214.19","BibliotecaDB","root","dynadev123");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,256);
        this.setLocationRelativeTo(null);
        LblImagen.setBounds(0, 0, 600, 256);
        this.setIconImage(IconBiblio.getImage());
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        
        this.setLayout(null);
        this.add(LblImagen);
        this.setVisible(true);
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Error: "+ e);
        }finally{
            Login Login = new Login(conexion);
            this.dispose();
        }
    }
}
