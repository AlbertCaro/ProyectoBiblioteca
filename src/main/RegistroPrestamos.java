package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroPrestamos extends JInternalFrame implements ActionListener {
    TextField TxtNombre = new TextField();
    TextField TxtAMaterno = new TextField();
    TextField TxtAPaterno = new TextField();
    TextField TxtCodigo = new TextField();
    TextField TxtISBN = new TextField();
    JLabel JDesUsuario = new JLabel("Datos del usuario");
    JLabel JDesPres = new JLabel("Datos del ejemplar");
    JLabel JCodigo = new JLabel("Código");
    JLabel JNombre = new JLabel("Nombre(s)");
    JLabel JAPaterno = new JLabel("Apellido Paterno");
    JLabel JAMaterno = new JLabel("Apellido Materno");
    JLabel JISBN = new JLabel("ISBN");
    JButton BRegistro = new JButton("Guardar");
    JButton BSalir = new JButton("Salir");
    ImageIcon SalirIma = new ImageIcon(getClass().getResource("/images/exitico.png"));
    ImageIcon GuardarIma = new ImageIcon(getClass().getResource("/images/deliveryico.png"));
    
    public RegistroPrestamos(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(700,500);
        this.setTitle("Registrar prestamo");
        this.setLayout(null);
        this.setResizable(false);
        this.setMaximizable(true);
        this.setClosable(true);
        //this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(254,223,168));

        JDesUsuario.setBounds(10,10,200,25);
        JDesUsuario.setFont(new Font("Arial",3,14));

        JCodigo.setBounds(30,30,100,25);
        TxtCodigo.setBounds(30,50,100,25);

        JNombre.setBounds(30,80,100,25);
        TxtNombre.setBounds(30,100,150,25);

        JAPaterno.setBounds(190,80,100,25);
        TxtAPaterno.setBounds(190,100,150,25);

        JAMaterno.setBounds(350,80,100,25);
        TxtAMaterno.setBounds(350,100,150,25);

        JDesPres.setBounds(10,140,200,25);
        JDesPres.setFont(new Font("Arial",3,14));

        JISBN.setBounds(30,160,100,25);
        TxtISBN.setBounds(30,180,150,25);

        BRegistro.setBounds(30,410,100,25);
        BRegistro.setIcon(GuardarIma);
        BSalir.setBounds(550,410,100,25);
        BSalir.addActionListener(this);
        BSalir.setIcon(SalirIma);
        //TxtNombre.setBounds();
        this.add(JDesUsuario);
        this.add(JDesPres);
        this.add(TxtCodigo);
        this.add(TxtNombre);
        this.add(TxtAPaterno);
        this.add(TxtAMaterno);
        this.add(TxtISBN);
        this.add(JCodigo);
        this.add(JNombre);
        this.add(JAPaterno);
        this.add(JAMaterno);
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
}
