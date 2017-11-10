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

public class VentanaRegistraEjemplar extends InternalWindow implements ActionListener, KeyListener {
    private JLabel LblTitulo = new JLabel("Registrar ejemplar");
    private JLabel LblISBN = new JLabel("ISBN");
    private JLabel LblNumero = new JLabel("Numero");
    private JTextField TxtISBN = new JTextField();
    private JTextField TxtNumero = new JTextField();
    private JButton BtnRegistrar = new JButton("Registrar");

    public VentanaRegistraEjemplar() {
        this.setSize(380, 160);
        addTitleLabel(LblTitulo, this);
        addLabel(LblISBN, 40,90, TxtISBN, this);
        addTextField(TxtISBN, 110, 40, 230, "ISBN",this);
        addLabel(LblNumero, 80,90, TxtNumero, this);
        addTextField(TxtNumero, 110, 80, 230, "Numero de ejemplar",this);
        addButton(BtnRegistrar, 120, 120,"Registrar Ejemplar", this);
        addWindowProperties(this, "Registrar ejemplar");
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
