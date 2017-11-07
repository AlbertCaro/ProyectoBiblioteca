package main;

import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *Splash de bienvenida
 * @author Jonny
 */
public class SplashBienv extends JFrame implements KeyListener{
    Conexion conexion;
    String Tipo, Usuario;
    ImageIcon ImaBiblio = new ImageIcon(getClass().getResource("/images/fondobienvenida2.png"));
    ImageIcon IconBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    JLabel LblImagenf = new JLabel(ImaBiblio);
    JLabel LblImagen = new JLabel();
    public SplashBienv(Conexion conexion, String Tipo, String Usuario){
        this.conexion = conexion;
        this.Tipo = Tipo;
        this.Usuario = Usuario;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(1000,200);
        this.setLocationRelativeTo(null);
        LblImagenf.setBounds(0,0,1000,200);
        LblImagen.setBounds(0, 0, 1000, 200);
        if (Usuario == "Invitado")
            LblImagen.setText("¡Bienvenido!");
        else
            LblImagen.setText("¡Bienvenido "+Usuario+"!");
        //LblImagen.setBackground(new Color(215,88,21));
        LblImagen.setForeground(new Color(255,255,255));
        LblImagen.setFont(new Font("Aldrich", 1, 55));
        LblImagen.setHorizontalAlignment(JLabel.CENTER);
        LblImagen.setVerticalAlignment(JLabel.CENTER);
        this.setIconImage(IconBiblio.getImage());
        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        
        this.setLayout(null);
        this.add(LblImagenf);
        LblImagenf.add(LblImagen);
        this.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Error: "+ e);
        }finally{
            WindowMain VentPrin = new WindowMain(conexion, Tipo, Usuario);
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == ke.VK_ENTER){
            WindowMain VentPrin = new WindowMain(conexion, Tipo, Usuario);
            this.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
