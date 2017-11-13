package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

public class VenConsultaPrestamos extends InternalWindow implements KeyListener, ActionListener {
    //JButtons
    private JButton BDevueltos = new JButton("Devueltos");
    private JButton BPendientes = new JButton("Pendientes");
    private JButton BTodos = new JButton("Todo");
    private JButton BBuscar = new JButton();
    private JButton BNuevo = new JButton("Nuevo Prestamo");
    private JButton BLiquidar = new JButton("Liquidar");
    //JTextField
    private JTextField TxtBuscar = new JTextField();
    //JLabels
    private JLabel Titulo = new JLabel("Consultar Prestamos");
    //tabla
    private JTable Tabla = new JTable();
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][3];
    private Object ColumName[] = new Object[]{"ISBN","Código","Fecha"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);
    //Iconos
    private ImageIcon ImaBuscar = new ImageIcon(getClass().getResource("/images/searchico.png"));
    private ImageIcon ImaSalir = new ImageIcon(getClass().getResource("/images/exitico.png"));
    private ImageIcon ImaPendiente = new ImageIcon(getClass().getResource("/images/clockico.png"));
    private ImageIcon ImaEntregado = new ImageIcon(getClass().getResource("/images/deliveryico.png"));
    private ImageIcon ImaTodos = new ImageIcon(getClass().getResource("/images/allico.png"));
    private ImageIcon ImaNuevoP = new ImageIcon(getClass().getResource("/images/newprestamo.png"));
    private ImageIcon ImaLiquidar = new ImageIcon(getClass().getResource("/images/money.png"));

    private String Consulta = "";
    Conexion MiConexion;

    public VenConsultaPrestamos(Conexion MiConexion){
        this.MiConexion = MiConexion;
        this.setSize(850,400);
        addTitleLabel(Titulo,this);
        Tabla.setModel(Modelo);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 600, 240);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);

        addTextField(TxtBuscar,30,40,150,"Usuario",this);
        addButton(BBuscar,180,40,"",this);
        BBuscar.setSize(30,30);
        BBuscar.setIcon(ImaBuscar);
        addButton(BDevueltos,655,110,"Consulta los libros devueltos",this);
        addButton(BPendientes,655,150,"Consulta los libros aun por entregar",this);
        addButton(BTodos,655,190,"Consulta general de todos los prestamos",this);
        addButton(BNuevo,655,270,"Registrar un nuevo prestamo",this);
        addButton(BLiquidar,655,310,"",this);
        BDevueltos.setIcon(ImaEntregado);
        BPendientes.setIcon(ImaPendiente);
        BTodos.setIcon(ImaTodos);
        //BNuevo.setIcon(ImaNuevoP);
        BLiquidar.setIcon(ImaLiquidar);

        BDevueltos.setSize(150,30);
        BPendientes.setSize(150,30);
        BTodos.setSize(150,30);
        BNuevo.setSize(150,30);
        BLiquidar.setSize(150,30);
        addWindowProperties(this,"Consultar Prestamo");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BNuevo){
            this.dispose();
            VenRegistroPrestamos VRP = new VenRegistroPrestamos();
        }else if (e.getSource() == BLiquidar){

        }
        else if (e.getSource() == BDevueltos){

        }
        else if (e.getSource() == BPendientes){

        }
        else if (e.getSource() == BTodos){
            limpiarTabla();
            llenarTabla(MiConexion, Consulta , Modelo , rootPane);

        }
    }
    private void limpiarTabla() {
        Modelo.setRowCount(0);
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
