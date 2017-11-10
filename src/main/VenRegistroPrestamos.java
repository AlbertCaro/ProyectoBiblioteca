package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VenRegistroPrestamos extends InternalWindow implements KeyListener, ActionListener {
    private JTextField TxtCodigo = new JTextField();
    private JTextField TxtISBN = new JTextField();
    private JLabel JCodigo = new JLabel("Código");
    private JLabel JISBN = new JLabel("ISBN");
    private JButton BRegistro = new JButton("Guardar");
    private JButton BSalir = new JButton("Salir");
    ImageIcon ImaSalir = new ImageIcon(getClass().getResource("/images/exitico.png"));
    ImageIcon ImaGuardar = new ImageIcon(getClass().getResource("/images/deliveryico.png"));
    
    public VenRegistroPrestamos() {
        this.setSize(300, 300);

        addLabel(JCodigo,30,40,TxtCodigo,this);
        addTextField(TxtCodigo, 100, 40, 150, "Código", this);
        addLabel(JISBN,30,80,TxtISBN,this);
        addTextField(TxtISBN, 100, 80, 150, "ISBN", this);

        addButton(BRegistro,30,150,this);
        addButton(BSalir,30,190,this);
        BRegistro.setIcon(ImaGuardar);
        BSalir.setIcon(ImaSalir);
        BRegistro.setSize(150,30);
        BSalir.setSize(150,30);
        addWindowProperties(this,"Nuevo Prestamo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BSalir){
            this.dispose();
        }

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