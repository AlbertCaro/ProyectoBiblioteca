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
import java.util.Calendar;

public class VenEditarLibros extends InternalWindow implements KeyListener, ActionListener {
    private Conexion MiConexion;
    private ImageIcon ImgSearch = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    private ImageIcon ImgCancel = new ImageIcon(getClass().getResource("/images/cancel-32.png"));
    private ImageIcon ImgSave = new ImageIcon(getClass().getResource("/images/save-32.png"));
    //Combobox
    private JLabel LblCombo = new JLabel("Buscar por:");
    private JComboBox Combo = new JComboBox();
    //tabla
    private JTable Tabla = new JTable();
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][3];
    private Object ColumName[] = new Object[]{"ISBN","Titulo","Autor","Editorial", "Descripcion"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);
    //JLabel
    private JLabel LblISBN = new JLabel("ISBN");
    private JLabel LblTitulo = new JLabel("Titulo");
    private JLabel LblAutor = new JLabel("Autor");
    private JLabel LblDescripcion = new JLabel("Descripcion");
    private JLabel LblPaginas = new JLabel("Paginas");
    private JLabel LblEditorial = new JLabel("Editorial");
    private JLabel LblEdicion = new JLabel("Edicion");
    private JLabel LblCosto = new JLabel("Costo");
    //textField
    private JTextField TxtBuscar = new JTextField();
    private JTextField TxtISBN = new JTextField();
    private JTextField TxtTitulo = new JTextField();
    private JTextField TxtAutor = new JTextField();
    private JTextField TxtDescripcion = new JTextField();
    private JTextField TxtPaginas = new JTextField();
    private JTextField TxtEditorial = new JTextField();
    private JTextField TxtEdicion = new JTextField();
    private JTextField TxtCosto = new JTextField();
    //Button
    private JButton BtBuscar = new JButton();
    private JButton BtAgregar = new JButton("Agregar");
    private JButton BtModificar = new JButton("Modificar");
    private JButton BtEliminar = new JButton("Eliminar");
    private JButton BtGuardar = new JButton("Guardar");
    private JButton BtCancelar = new JButton("Cancelar");

    public VenEditarLibros(Conexion MiConexion){
        this.MiConexion = MiConexion;
        this.setSize(1100,600);
        addLabel(LblCombo,30,10,150,Combo,this);
        addComboBox(Combo,30,40,150,this);
        llenarComboBox();
        //textfield
        addTextField(TxtBuscar, 190, 40, 120, "Buscar",this);
        TxtBuscar.setToolTipText("'*' para mostrar todos los resultados");
        Tabla.setModel(Modelo);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 600, 310);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
        ///////////////////labels & textFields///////////////////
        addLabel(LblISBN, 680,100, TxtISBN, this);
        addTextField(TxtISBN, 790, 100, 230, "ISBN",this);
        //TxtISBN.setEnabled(false);
        //TxtISBN.setBackground(new Color(194, 192, 193));
        addLabel(LblTitulo, 680,140,TxtTitulo, this);
        addTextField(TxtTitulo, 790, 140, 230, "Titulo",this);
        addLabel(LblAutor, 680,180, TxtAutor, this);
        addTextField(TxtAutor, 790, 180, 230, "Autor",this);
        addLabel(LblDescripcion, 680,220, TxtDescripcion, this);
        addTextField(TxtDescripcion, 790, 220, 230, "Descripcion",this);
        addLabel(LblPaginas, 680,260, TxtPaginas, this);
        addTextField(TxtPaginas, 790, 260, 230, "Paginas",this);
        addLabel(LblEditorial, 680,300, TxtEditorial, this);
        addTextField(TxtEditorial, 790, 300, 230, "Editorial",this);
        addLabel(LblEdicion, 680,340, TxtEdicion, this);
        addTextField(TxtEdicion, 790, 340, 230, "Edicion",this);
        addLabel(LblCosto, 680,380, TxtCosto, this);
        addTextField(TxtCosto, 790, 380, 230, "Costo",this);
        /////////////////////Buttons///////////////
        addButton(BtBuscar, 320, 40,"Buscar Libro",this);
        BtBuscar.setSize(30,30);
        BtBuscar.setIcon(ImgSearch);
        addButton(BtAgregar,685,40,"Agregar Ejemplar",this);
        addButton(BtModificar,795,40,"Editar Ejemplar",this);
        BtModificar.setEnabled(false);
        addButton(BtEliminar,905,40,"",this);
        BtEliminar.setEnabled(false);
        addButton(BtGuardar,685,450,"Guardar Cambios",this);
        BtGuardar.setEnabled(false);
        BtGuardar.setSize(100,80);
        BtGuardar.setIcon(ImgSave);
        BtGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton(BtCancelar,905,450,"Cancelar cambios",this);
        BtCancelar.setEnabled(false);
        BtCancelar.setSize(100,80);
        BtCancelar.setIcon(ImgCancel);
        BtCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        inicializarTabla();

        addWindowProperties(this,"Edición de Libros");
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

    private void llenarComboBox() {
        for (int i=0; i<ColumName.length; i++){
            Combo.addItem(ColumName[i].toString());
        }
    }

    private void buscarFrase() {
        limpiarTabla();
        String SQL = new String("SELECT ISBN, Titulo, Autor, Editorial, Descripcion FROM Libros WHERE " +
                Combo.getSelectedItem()+" LIKE '%"+TxtBuscar.getText().toString())+"%' order by "+Combo.getSelectedItem();
        llenarTabla(MiConexion,SQL,Modelo,rootPane);
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
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if(me.getSource()==BtBuscar){
            if (TxtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane,"El Campo Buscar no debe de estar vacio");
            }else if (TxtBuscar.getText().toString().equals("*")) {
                limpiarTabla();
                llenarTabla(MiConexion,"SELECT ISBN, Titulo, Autor, Editorial, Descripcion FROM Libros",Modelo,rootPane);
            }else {
                buscarFrase();
            }
        }
    }
}
