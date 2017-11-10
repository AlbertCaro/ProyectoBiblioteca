package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static main.InternalWindow.*;

/**
 * @author Jonny
 */
public class VenConsultarLibros extends InternalWindow implements ActionListener, KeyListener {
    private Conexion MiConexion;
    private JButton BtBuscar = new JButton();
    private JTextField TxTBuscar = new JTextField();
    private ImageIcon ImgBuscar = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    //Combobox
    private JLabel LblCombo = new JLabel("Buscar por:");
    private JComboBox Combo = new JComboBox();
    //tabla
    private JTable Tabla = null;
    private JScrollPane ScrollP = new JScrollPane(Tabla);
    private Object[][] FilaInicial = new Object[0][4];
    private Object ColumName[] = new Object[]{"ISBN","Titulo","Autor","Editorial", "Descripcion"};
    private DefaultTableModel Modelo= new DefaultTableModel(FilaInicial, ColumName);//FilaInicial para la fila i ncial y ColumName para los nombres de las columnas
    public VenConsultarLibros(Conexion MiConexion) throws SQLException {
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

        addWindowProperties(this,"Consultar Libros");
    }

    private void llenarComboBox() {
            for (int i=0; i<ColumName.length; i++){
                Combo.addItem(ColumName[i].toString());
            }
    }

    private void inicializarTabla() {
        Tabla = new JTable(Modelo);
        Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//se autoajusta
        Tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//tamaño de columna inicial
        Tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        Tabla.getColumnModel().getColumn(2).setPreferredWidth(180);
        Tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        Tabla.getColumnModel().getColumn(4).setPreferredWidth(400);
        Tabla.getTableHeader().setReorderingAllowed(false);//se ordena automaticamente
        llenarTabla(MiConexion,"SELECT ISBN, Titulo, Autor, Editorial, Descripcion FROM Libros",Modelo,rootPane);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 640, 300);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if(me.getSource()==BtBuscar){
            if (TxTBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane,"El Campo Buscar no debe de estar vacio");
            }else if (TxTBuscar.getText().toString().equals("*")) {
                limpiarTabla();
                llenarTabla(MiConexion,"SELECT ISBN, Titulo, Autor, Editorial, Descripcion FROM Libros",Modelo,rootPane);
            }else {
                buscarFrase();
            }
        }
    }

    private void buscarFrase() {
        limpiarTabla();
        String SQL = new String("SELECT ISBN, Titulo, Autor, Editorial, Descripcion FROM Libros WHERE " +
                Combo.getSelectedItem()+" LIKE '%"+TxTBuscar.getText().toString())+"%' order by "+Combo.getSelectedItem();
        llenarTabla(MiConexion,SQL,Modelo,rootPane);
    }

    private void limpiarTabla() {
        Modelo.setRowCount(0);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

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
