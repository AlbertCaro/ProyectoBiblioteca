package main;

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

public class VentanaEditarLibros extends InternalWindow implements KeyListener, ActionListener {
    private ImageIcon ImgSearch = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    private ImageIcon ImgCancel = new ImageIcon(getClass().getResource("/images/cancel-32.png"));
    private ImageIcon ImgSave = new ImageIcon(getClass().getResource("/images/save-32.png"));
    //tabla
    private JTable Tabla = new JTable();
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][3];
    private Object ColumName[] = new Object[]{"ISBN","Nombre","Editorial"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);
    //JLabel
    JLabel TituloV = new JLabel("Edición de libros");
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
    public VentanaEditarLibros(){
        this.setSize(1100,600);
        addTitleLabel(TituloV,this);
        Tabla.setModel(Modelo);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 600, 310);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
        ///////////////////labels & textFields///////////////////
        addTextField(TxtBuscar,30,40,120,"Buscar",this);
        addLabel(LblISBN, 680,100, TxtISBN, this);
        addTextField(TxtISBN, 790, 100, 230, "ISBN",this);
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
        addButton(BtBuscar,160,40,this);
        BtBuscar.setSize(30,30);
        BtBuscar.setIcon(ImgSearch);
        addButton(BtAgregar,685,40,this);
        addButton(BtModificar,795,40,this);
        addButton(BtEliminar,905,40,this);
        addButton(BtGuardar,685,450,this);
        BtGuardar.setSize(100,80);
        BtGuardar.setIcon(ImgSave);
        BtGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton(BtCancelar,905,450,this);
        BtCancelar.setSize(100,80);
        BtCancelar.setIcon(ImgCancel);
        BtCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        addWindowProperties(this,"Edición de Libros");
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
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
