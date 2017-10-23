package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GenericMethods.*;

public class VentanaBibliotecas extends JInternalFrame implements KeyListener, ActionListener {
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
        addLabel(LblNombre, 40, TxtNombre, this);
        addLabel(LblDireccion, 80, TxtCalle, this);
        addTextField(TxtNombre, 110, 40, 250, this);
        addTextField(TxtCalle, 110, 80, 180, this);
        addTextField(TxtNumero, 300, 80, 60, this);
        addComboBox(CbEstado, 20, 120, 78, this);
        addComboBox(CbMunicipio, 108, 120, 78, this);
        addTextField(TxtColonia, 196, 120, 77, this);
        addTextField(TxtCodigoPostal, 283, 120, 77, this);
        addButton(BtnRegistrar, 120, 160, this);
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
}
