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

public class VentanaBibliotecas extends InternalWindow implements KeyListener, ActionListener {
    private JLabel LblTitulo = new JLabel("Bibliotecas");
    private JLabel LblNombre = new JLabel("Nombre");
    private JLabel LblDireccion = new JLabel("Direccion");
    private JTextField TxtNombre = new JTextField();
    private JTextField TxtCalle = new JTextField();
    private JTextField TxtNumero = new JTextField();
    private JComboBox CbEstado = new JComboBox();
    private JComboBox CbMunicipio = new JComboBox();
    private JTextField TxtColonia = new JTextField();
    private JTextField TxtCodigoPostal = new JTextField();
    private JButton BtnRegistrar = new JButton("Registrar");

    public VentanaBibliotecas() {
        this.setSize(390, 240);
        this.getContentPane().setBackground(new java.awt.Color(254,223,168));
        addTitleLabel(LblTitulo, this);
        addLabel(LblNombre, 40, 90, TxtNombre, this);
        addLabel(LblDireccion, 80, 90, TxtCalle, this);
        addTextField(TxtNombre, 110, 40, 250, "Nombre(s)",this);
        addTextField(TxtCalle, 110, 80, 180, "Calle", this);
        addTextField(TxtNumero, 300, 80, 60, "Numero",this);
        addComboBox(CbEstado, 20, 120, 78, this);
        addComboBox(CbMunicipio, 108, 120, 78, this);
        addTextField(TxtColonia, 196, 120, 77, "Colonia",this);
        addTextField(TxtCodigoPostal, 283, 120, 77, "Codigo postal",this);
        addButton(BtnRegistrar, 120, 160,"Registar Biblioteca", this);
        addWindowProperties(this, "Agregar biblioteca");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Agregar los eventos correspondientes
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Agregar los eventos correspondientes
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Agregar los eventos correspondientes
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Agregar los eventos correspondientes
    }


    @Override
    public PreparedStatement addStatementParams(PreparedStatement statement, int type) throws NoSuchAlgorithmException, SQLException {
        return statement;
    }

    @Override
    public ArrayList<JComponent> fillListTexts() {
        ArrayList<JComponent> ListTexts = new ArrayList<>();
        return null;
    }

    @Override
    public void returnQueryResults(ResultSet resultSet) throws SQLException {

    }

    @Override
    public void cleanForm() {

    }
}
