package main;

import com.sun.awt.AWTUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *Splash de bienvenida
 * @author Jonny
 */
public class SplashBienv extends JFrame implements KeyListener{
    Conexion conexion = new Conexion("130.211.214.19","BibliotecaDB","root","dynadev123");
    ImageIcon ImaBiblio = new ImageIcon(getClass().getResource("/images/Bienvenido.jpg"));
    ImageIcon IconBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    JLabel LblImagen = new JLabel(ImaBiblio);
    public SplashBienv(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(649,198);
        this.setLocationRelativeTo(null);
        LblImagen.setBounds(0, 0, 649, 198);
        this.setIconImage(IconBiblio.getImage());
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        
        this.setLayout(null);
        this.add(LblImagen);
        this.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Error: "+ e);
        }finally{
            WindowMain VentPrin = new WindowMain(conexion);
            //Login Login = new Login();
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == ke.VK_ENTER){
            WindowMain VentPrin = new WindowMain(conexion);
            this.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
