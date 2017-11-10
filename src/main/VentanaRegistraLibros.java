package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaRegistraLibros extends InternalWindow implements ActionListener, KeyListener {
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
        addLabel(LblISBN, 40,40, TxtISBN, this);
        addTextField(TxtISBN, 110, 40, 230, "ISBN",this);
        addLabel(LblTitulo, 40,80,TxtTitulo, this);
        addTextField(TxtTitulo, 110, 80, 230, "Titulo",this);
        addLabel(LblAutor, 40,120, TxtAutor, this);
        addTextField(TxtAutor, 110, 120, 230, "Autor",this);
        addLabel(LblDescripcion, 40,120, TxtDescripcion, this);
        addTextField(TxtDescripcion, 110, 160, 230, "Descripcion",this);
        addLabel(LblPaginas, 200,90, TxtPaginas, this);
        addTextField(TxtPaginas, 110, 200, 230, "Paginas",this);
        addLabel(LblEditorial, 240,90, TxtEditorial, this);
        addTextField(TxtEditorial, 110, 240, 230, "Editorial",this);
        addLabel(LblEdicion, 280,90, TxtEdicion, this);
        addTextField(TxtEdicion, 110, 280, 230, "Edicion",this);
        addLabel(LblCosto, 320,90, TxtCosto, this);
        addTextField(TxtCosto, 110, 320, 230, "Costo",this);
        addButton(BtnRegistrar,130, 360,"Registrar Libro", this);
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
