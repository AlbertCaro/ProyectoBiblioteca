package main;
 //veentana Para consultar usuarios
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VenConsultarUsuario extends InternalWindow implements ActionListener, KeyListener{
    private Conexion MiConexion;
    private JButton BtBuscar = new JButton();
    private JTextField TxTBuscar = new JTextField();
    private ImageIcon ImgBuscar = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    //Combobox
    private JLabel LblCombo = new JLabel("Buscar por:");
    private JComboBox Combo = new JComboBox();
    //tabla
    private JTable Tabla = null;
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][8];
    private Object ColumName[] = new Object[]{"Codigo","Usuario", "Tipo","Nombre","A. Paterno","A. Materno", "Correo", "Telefono"};
    private DefaultTableModel Modelo= new DefaultTableModel(FilaInicial, ColumName);
    public VenConsultarUsuario(Conexion MiConexion){
        this.MiConexion = MiConexion;
        this.setSize(700,500);
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));
        addLabel(LblCombo,30,10,150,Combo,this);
        addComboBox(Combo,30,40,150,this);
        llenarComboBox();
        //textfield
        addTextField(TxTBuscar, 190, 40, 120, "Buscar",this);
        TxTBuscar.setToolTipText("'*' para mostrar todos los resultados");
        //boton
        addButton(BtBuscar, 320, 40,"Buscar Libro",this);
        BtBuscar.setSize(30,30);
        BtBuscar.setIcon(ImgBuscar);
        //tabla
        inicializarTabla();

        addWindowProperties(this,"Consultar Usuarios");
    }

    private void inicializarTabla() {
        Tabla = new JTable(Modelo);
        Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//se autoajusta
        Tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        Tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
        Tabla.getTableHeader().setReorderingAllowed(false);//se ordena automaticamente
        llenarTabla(MiConexion,"SELECT Personas.Codigo, Usuarios.Usuario, Tipos.Tipo, Nombre, ApPaterno, ApMaterno, Correo, Telefono " +
                "FROM BibliotecaDB.Personas inner join BibliotecaDB.Usuarios on Personas.Codigo=Usuarios.Codigo " +
                "inner join BibliotecaDB.Tipos on Tipos.idTipos=Usuarios.idTipos",Modelo,rootPane);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 640, 300);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
    }

    private void llenarComboBox() {
        for (int i=0; i<ColumName.length; i++){
            Combo.addItem(ColumName[i].toString());
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
    public void actionPerformed(ActionEvent me) {
        if(me.getSource()==BtBuscar){
            if (TxTBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane,"El Campo Buscar no debe de estar vacio");
            }else if (TxTBuscar.getText().toString().equals("*")) {
                limpiarTabla();
                llenarTabla(MiConexion,"SELECT Personas.Codigo, Usuarios.Usuario, Tipos.Tipo, Nombre, ApPaterno, ApMaterno, Correo, Telefono " +
                        "FROM BibliotecaDB.Personas inner join BibliotecaDB.Usuarios on Personas.Codigo=Usuarios.Codigo " +
                        "inner join BibliotecaDB.Tipos on Tipos.idTipos=Usuarios.idTipos",Modelo,rootPane);
            }else {
                buscarFrase();
            }
        }
    }
    private void buscarFrase() {
        limpiarTabla();
        if (Combo.getSelectedItem().toString().equals("Codigo")) {
            String SQL = new String("SELECT Personas.Codigo, Usuarios.Usuario, Tipos.Tipo, Nombre, ApPaterno, ApMaterno, Correo, Telefono " +
                    "FROM BibliotecaDB.Personas inner join BibliotecaDB.Usuarios on Personas.Codigo=Usuarios.Codigo " +
                    "inner join BibliotecaDB.Tipos on Tipos.idTipos=Usuarios.idTipos WHERE Personas." +
                    Combo.getSelectedItem() + " LIKE '%" + TxTBuscar.getText().toString()) + "%' order by " + Combo.getSelectedItem();
            llenarTabla(MiConexion,SQL,Modelo,rootPane);
        }else {
            String SQL = new String("SELECT Personas.Codigo, Usuarios.Usuario, Tipos.Tipo, Nombre, ApPaterno, ApMaterno, Correo, Telefono " +
                    "FROM BibliotecaDB.Personas inner join BibliotecaDB.Usuarios on Personas.Codigo=Usuarios.Codigo " +
                    "inner join BibliotecaDB.Tipos on Tipos.idTipos=Usuarios.idTipos WHERE " +
                    Combo.getSelectedItem() + " LIKE '%" + TxTBuscar.getText().toString()) + "%' order by " + Combo.getSelectedItem();
            llenarTabla(MiConexion,SQL,Modelo,rootPane);
        }
    }

    private void limpiarTabla() {
        Modelo.setRowCount(0);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
