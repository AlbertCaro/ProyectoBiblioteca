/*
Clase que sirve para alojar una imagen redimesionable sobre
un JPanel
 */
package main;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Jonny
 */
public class PanelImagen extends JPanel{
    ImageIcon ImagenFondo; // sin el new hacemos que la imagen que le mandemos (sea cualquiera) la tome
    public PanelImagen(ImageIcon ImagenFondo){
        this.ImagenFondo = ImagenFondo;
    }

    @Override //PARA INSERTAR ESTE METODO SE UTILIZA LAS TECLAS ALT+INSERT
    //y selececcionamos el metodo
    protected void paintComponent(Graphics g) {
        Dimension Tam = getSize();
        g.drawImage(ImagenFondo.getImage(), 0, 0, Tam.width, Tam.height, null);
    }
    
}
