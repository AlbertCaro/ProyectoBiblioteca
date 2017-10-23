package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GenericMethods.*;

public class VentanaRegistraLibros extends JInternalFrame implements ActionListener, KeyListener {
    private JLabel LblTituloVent = new JLabel("Registrar libro");
    private JLabel LblISBN = new JLabel("ISBN");
    private JLabel LblTitulo = new JLabel("Titulo");
    private JLabel LblAutor = new JLabel("Autor");
    private JLabel LblDescripcion = new JLabel("Descripcion");
    private JLabel LblPaginas = new JLabel("Paginas");
    private JLabel LblEditorial = new JLabel("Editorial");
    private JLabel LblEdicion = new JLabel("Edicion");
    private JLabel LblCosto = new JLabel("Costo");
    private JTextField TxtISBN = new JTextField();
    private JTextField TxtTitulo = new JTextField();
    private JTextField TxtAutor = new JTextField();
    private JTextField TxtDescripcion = new JTextField();
    private JTextField TxtPaginas = new JTextField();
    private JTextField TxtEditorial = new JTextField();
    private JTextField TxtEdicion = new JTextField();
    private JTextField TxtCosto = new JTextField();
    private JButton BtnRegistrar = new JButton("Registrar");

    public VentanaRegistraLibros() {
        this.setSize(380, 600);
        addTitleLabel(LblTituloVent, this);
        addLabel(LblISBN, 40, TxtISBN, this);
        addTextField(TxtISBN, 110, 40, 230, this);
        addLabel(LblTitulo, 80, TxtTitulo, this);
        addTextField(TxtTitulo, 110, 80, 230, this);
        addLabel(LblAutor, 120, TxtAutor, this);
        addTextField(TxtAutor, 110, 120, 230, this);
        addLabel(LblDescripcion, 160, TxtDescripcion, this);
        addTextField(TxtDescripcion, 110, 160, 230, this);
        addLabel(LblDescripcion, 200, TxtDescripcion, this);
        addTextField(TxtDescripcion, 110, 200, 230, this);
        addLabel(LblPaginas, 240, TxtPaginas, this);
        addTextField(TxtPaginas, 110, 240, 230, this);
        addLabel(LblEditorial, 280, TxtEditorial, this);
        addTextField(TxtEditorial, 110, 280, 230, this);
        addLabel(LblEdicion, 320, TxtEdicion, this);
        addTextField(TxtEdicion, 110, 320, 230, this);
        addLabel(LblCosto, 360, TxtCosto, this);
        addTextField(TxtCosto, 110, 360, 230, this);
        addButton(BtnRegistrar,130, 400, this);
        addWindowProperties(this, "Registrar libros");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
