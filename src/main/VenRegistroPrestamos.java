package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VenRegistroPrestamos extends InternalWindow implements ActionListener {
    TextField TxtCodigo = new TextField();
    TextField TxtISBN = new TextField();
    JLabel JDesUsuario = new JLabel("Datos del usuario");
    JLabel JDesPres = new JLabel("Datos del ejemplar");
    JLabel JCodigo = new JLabel("Código");
    JLabel JISBN = new JLabel("ISBN");
    JButton BRegistro = new JButton("Guardar prestamo");
    JButton BSalir = new JButton("Salir");
    ImageIcon SalirIma = new ImageIcon(getClass().getResource("/images/exitico.png"));
    ImageIcon GuardarIma = new ImageIcon(getClass().getResource("/images/deliveryico.png"));
    
    public VenRegistroPrestamos(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(270,300);
        this.setTitle("Registrar prestamo");
        this.setLayout(null);
        this.setResizable(false);
        this.setMaximizable(true);
        this.setClosable(true);
        //this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));

        //JDesUsuario.setBounds(10,10,200,25);
        //JDesUsuario.setFont(new Font("Arial",3,14));

        JCodigo.setBounds(30,30,100,25);
        TxtCodigo.setBounds(30,50,100,25);

        //JDesPres.setBounds(10,140,200,25);
        //JDesPres.setFont(new Font("Arial",3,14));

        JISBN.setBounds(30,80,100,25);
        TxtISBN.setBounds(30,100,150,25);

        BRegistro.setBounds(20,200,200,25);
        BRegistro.setIcon(GuardarIma);
        BSalir.setBounds(140,250,100,25);
        BSalir.addActionListener(this);
        BSalir.setIcon(SalirIma);
        //TxtNombre.setBounds();
        this.add(JDesUsuario);
        this.add(JDesPres);
        this.add(TxtCodigo);
        this.add(TxtISBN);
        this.add(JCodigo);
        this.add(JISBN);
        this.add(BRegistro);
        this.add(BSalir);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==BSalir)
            dispose();
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
