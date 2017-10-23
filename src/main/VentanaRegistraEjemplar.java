package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GenericMethods.*;

public class VentanaRegistraEjemplar extends JInternalFrame implements ActionListener, KeyListener {
    private JLabel LblTitulo = new JLabel("Registrar ejemplar");
    private JLabel LblISBN = new JLabel("ISBN");
    private JLabel LblNumero = new JLabel("Numero");
    private JTextField TxtISBN = new JTextField();
    private JTextField TxtNumero = new JTextField();
    private JButton BtnRegistrar = new JButton("Registrar");

    public VentanaRegistraEjemplar() {
        this.setSize(380, 160);
        addTitleLabel(LblTitulo, this);
        addLabel(LblISBN, 40, TxtISBN, this);
        addTextField(TxtISBN, 110, 40, 230, this);
        addLabel(LblNumero, 80, TxtNumero, this);
        addTextField(TxtNumero, 110, 80, 230, this);
        addButton(BtnRegistrar, 120, 120, this);
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
}
