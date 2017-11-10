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

public class VenConsultaPrestamos extends InternalWindow implements ActionListener {
    JButton BDevueltos = new JButton("Devueltos");
    JButton BPendientes = new JButton("Pendientes");
    JButton BTodos = new JButton("Todos");
    JButton BSalir = new JButton("Salir");
    JButton BConUsuario = new JButton();
    //JButton BAmpliarPrestamo = new JButton("Ampliar Prestamo");
    JButton BRegistrar = new JButton("Nuevo prestamo");
    JLabel JPrestamos = new JLabel("Tipo de consulta");
    JLabel JResultados = new JLabel("Resultado de la busqueda");
    JLabel JUsuario = new JLabel("CONSULTAR USUARIO");
    ImageIcon SearchIma = new ImageIcon(getClass().getResource("/images/searchico.png"));
    ImageIcon SalirIma = new ImageIcon(getClass().getResource("/images/exitico.png"));
    ImageIcon PendienteIma = new ImageIcon(getClass().getResource("/images/clockico.png"));
    ImageIcon EntregadoIma = new ImageIcon(getClass().getResource("/images/deliveryico.png"));
    ImageIcon TodoIma = new ImageIcon(getClass().getResource("/images/allico.png"));
    TextField TxtBuscar = new TextField();
    Conexion conexion;
    String Tipo, Usuario;

    public VenConsultaPrestamos(Conexion conexion, String Tipo, String Usuario){
        this.Tipo = Tipo;
        this.conexion = conexion;
        this.Usuario = Usuario;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(700,500);
        this.setTitle("Consultar Prestamos");
        this.setLayout(null);
        this.setResizable(false);
        this.setMaximizable(true);
        this.setClosable(true);
        //this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));

        BDevueltos.setBounds(40,40,120,25);
        BDevueltos.setIcon(EntregadoIma);
        BDevueltos.setToolTipText("Consulta los datos de los libros devueltos");
        BPendientes.setBounds(170,40,120,25);
        BPendientes.setIcon(PendienteIma);
        BPendientes.setToolTipText("Consulta los libros pendientes de entrega");
        BTodos.setBounds(300,40,120,25);
        BTodos.setIcon(TodoIma);
        BTodos.setToolTipText("Consulta todos los prestamos");
        BConUsuario.setBounds(631,40,25,25);
        BConUsuario.setIcon(SearchIma);
        BConUsuario.setToolTipText("Consultar prestamos");
        //BAmpliarPrestamo.setBounds(100,415,150,25);
        BSalir.setBounds(555,415,100,25);
        BSalir.setIcon(SalirIma);
        BSalir.addActionListener(this);
        if (Tipo.equals("Super Administrador") || Tipo.equals("SuperAdministrador")) {
            BRegistrar.setBounds(270, 415, 150, 25);
            BRegistrar.setToolTipText("Registrar un nuevo prestamo");
            TxtBuscar.setBounds(510,40,120,25);
        }

        JPrestamos.setBounds(10,10,100,20);
        JResultados.setBounds(10,70,150,20);
        JUsuario.setBounds(510,10,150,20);

        this.add(BDevueltos);
        this.add(BPendientes);
        this.add(BTodos);
        this.add(JPrestamos);
        this.add(JResultados);
        this.add(JUsuario);
        this.add(TxtBuscar);
        this.add(BConUsuario);
        this.add(BSalir);
        this.add(BRegistrar);
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
