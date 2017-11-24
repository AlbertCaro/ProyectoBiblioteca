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
    private String ISBN;
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
    private JLabel LblISBN = new JLabel("ISBN:");
    private JLabel LblTitulo = new JLabel("Titulo:");
    private JLabel LblAutor = new JLabel("Autor:");
    private JLabel LblDescripcion = new JLabel("Descripcion:");
    private JLabel LblPaginas = new JLabel("Paginas:");
    private JLabel LblEditorial = new JLabel("Editorial:");
    private JLabel LblEdicion = new JLabel("Edicion:");
    private JLabel LblCosto = new JLabel("Costo $:");
    //textField
    private JTextField TxtBuscar = new JTextField();
    private JTextField TxtISBN = new JTextField();
    private JTextField TxtTitulo = new JTextField();
    private JTextField TxtAutor = new JTextField();
    private JTextField TxtDescripcion = new JTextField();
    private JScrollPane JPDescripcion = new JScrollPane(TxtDescripcion);
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

    private boolean punto = false;

    private ArrayList<JTextField> TextFields = new ArrayList<>();

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
        JPDescripcion.setBounds(790,220,230,30);
        this.add(JPDescripcion);
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
        BtModificar.setEnabled(true);
        addButton(BtEliminar,905,40,"",this);
        BtEliminar.setEnabled(true);
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
        llenaArrayList();

        addWindowProperties(this,"Edición de Libros");
    }

    private void llenaArrayList() {
        TextFields.add(TxtISBN);
        TextFields.add(TxtTitulo);
        TextFields.add(TxtAutor);
        TextFields.add(TxtDescripcion);
        TextFields.add(TxtPaginas);
        TextFields.add(TxtEditorial);
        TextFields.add(TxtEdicion);
        TextFields.add(TxtCosto);
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

    private boolean validaCampos() {
        boolean result = true;

        for (JTextField textField : TextFields) {
            if (textField.getText().isEmpty()) {
                colorVoidComponents(textField);
                result = false;
            }
        }
        return result;
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
        if (keyEvent.getSource() == TxtISBN) {
            colorComponent(TxtISBN);
            if (TxtISBN.getText().length() == 30)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtTitulo) {
            colorComponent(TxtTitulo);
            if (TxtTitulo.getText().length() == 110)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtAutor) {
            colorComponent(TxtAutor);
            if (TxtAutor.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtDescripcion) {
            colorComponent(TxtDescripcion);
            if (TxtDescripcion.getText().length() == 700)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtPaginas) {
            if (TxtPaginas.getText().length() == 11) {
                if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9')
                    colorComponent(TxtPaginas);
                else
                    keyEvent.consume();
            } else
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtEdicion) {
            colorComponent(TxtEdicion);
            if (TxtEdicion.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtCosto) {
            if (TxtCosto.getText().indexOf('.') == -1)
                punto = false;
            if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9' || (keyEvent.getKeyChar() == '.' && !punto)) {
                if (keyEvent.getKeyChar() == '.')
                    punto = true;
                TxtCosto.setBackground(Color.WHITE);
                TxtCosto.setForeground(Color.BLACK);
            } else
                keyEvent.consume();
        }
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
        } else if (me.getSource()==BtModificar){
            BtEliminar.setEnabled(false);
            BtAgregar.setEnabled(false);
            TxtISBN.setEnabled(false);
            TxtISBN.setForeground(new java.awt.Color(76, 72, 66));
            llenarTextFields();
        }else if (me.getSource()==BtGuardar){
            if (validaCampos()) {
                BtEliminar.setEnabled(true);
                BtAgregar.setEnabled(true);
                modificar();
                BtCancelar.setEnabled(false);
                BtGuardar.setEnabled(false);
                limpiarCampos();
                TxtISBN.setEnabled(true);
                TxtISBN.setForeground(Color.BLACK);
                ISBN = "";
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ha dejado campos vacíos.");
            }

        }else if (me.getSource()==BtCancelar){
            limpiarCampos();
            BtAgregar.setEnabled(true);
            TxtISBN.setEnabled(true);
            TxtISBN.setForeground(Color.BLACK);
            BtEliminar.setEnabled(true);
            BtCancelar.setEnabled(false);
            BtGuardar.setEnabled(false);
            for (JTextField textField : TextFields) {
                colorComponent(textField);
            }
        }else if (me.getSource()==BtEliminar){
            eliminar();
        }else if (me.getSource()==BtAgregar){
             if (validaCampos())
                agregar();
             else
                 JOptionPane.showMessageDialog(rootPane, "Ha dejado campos vacíos.");
        }
    }

    private void agregar() {
        try{
            if (TxtISBN.getText().isEmpty() ||TxtTitulo.getText().isEmpty()|| TxtAutor.getText().isEmpty() || TxtDescripcion.getText().isEmpty()|| TxtPaginas.getText().isEmpty()
                    || TxtEditorial.getText().isEmpty()|| TxtEdicion.getText().isEmpty() || TxtCosto.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Los campos no deben de estar vacios");
            } else {
                PreparedStatement AgregarStm = MiConexion.getConexion().prepareCall("INSERT INTO Libros (ISBN, Titulo, Autor, Descripcion, Paginas, Editorial, Edicion, Costo) VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");
                AgregarStm.setString(1, TxtISBN.getText().toString());
                AgregarStm.setString(2, TxtTitulo.getText().toString());
                AgregarStm.setString(3, TxtAutor.getText().toString());
                AgregarStm.setString(4, TxtDescripcion.getText().toString());
                AgregarStm.setInt(5, Integer.parseInt(TxtPaginas.getText().toString()));
                AgregarStm.setString(6, TxtEditorial.getText().toString());
                AgregarStm.setString(7, TxtEdicion.getText().toString());
                AgregarStm.setFloat(8, Float.parseFloat(TxtCosto.getText().toString()));
                AgregarStm.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Libro agregado correctamente");
                limpiarCampos();
                limpiarTabla();
                inicializarTabla();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error: "+e);
        }
    }

    private void eliminar() {
        int filaSeleccionada = Tabla.getSelectedRow();//toma la fila seleccionada
        for (JTextField textField : TextFields) {
            colorComponent(textField);
        }
        if (filaSeleccionada>=0) {
            ISBN = Modelo.getValueAt(filaSeleccionada, 0).toString();
            //retorna cero si el usuario acepta
            if (JOptionPane.showConfirmDialog(rootPane,"¿Realmente quiere borrar el libro con el ISBN: "+ISBN+"?", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)==0){
                try {
                    PreparedStatement EliminarStm = MiConexion.getConexion().prepareCall("DELETE FROM Libros WHERE ISBN=?");
                    EliminarStm.setString(1, ISBN);
                    EliminarStm.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Libro borrado correctamente");
                    limpiarCampos();
                    limpiarTabla();
                    inicializarTabla();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Error: " + e);
                }
            }
        }else
            JOptionPane.showMessageDialog(rootPane,"seleccione la fila del libro que quiere Eliminar");
    }

    private void limpiarCampos() {
        TxtISBN.setText("");
        TxtTitulo.setText("");
        TxtAutor.setText("");
        TxtEditorial.setText("");
        TxtDescripcion.setText("");
        TxtCosto.setText("");
        TxtEdicion.setText("");
        TxtPaginas.setText("");
    }

    private void llenarTextFields() {
        int filaSeleccionada = Tabla.getSelectedRow();//toma la fila seleccionada
        BtGuardar.setEnabled(true);
        BtCancelar.setEnabled(true);
        for (JTextField textField : TextFields) {
            colorComponent(textField);
        }
        if (filaSeleccionada>=0) {
            ISBN = new String(Modelo.getValueAt(filaSeleccionada, 0).toString());
            try{
                PreparedStatement BuscarStm = MiConexion.getConexion().prepareCall("SELECT ISBN, Titulo, Autor, Descripcion, Paginas, Editorial, Edicion, Costo FROM Libros WHERE ISBN=?");
                BuscarStm.setString(1, ISBN);
                ResultSet RsConsulta = BuscarStm.executeQuery();
                while (RsConsulta.next()) {
                    TxtISBN.setText(RsConsulta.getObject("ISBN").toString());
                    TxtTitulo.setText(RsConsulta.getObject("Titulo").toString());
                    TxtAutor.setText(RsConsulta.getObject("Autor").toString());
                    TxtDescripcion.setText(RsConsulta.getObject("Descripcion").toString());
                    TxtPaginas.setText(RsConsulta.getObject("Paginas").toString());
                    TxtEditorial.setText(RsConsulta.getObject("Editorial").toString());
                    TxtEdicion.setText(RsConsulta.getObject("Edicion").toString());
                    TxtCosto.setText(RsConsulta.getObject("Costo").toString());
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(rootPane,"Error: "+e);
            }
        }else
            JOptionPane.showMessageDialog(rootPane,"seleccione la fila del libro que quiere modificar");
    }
    private void modificar() {
        try {
            PreparedStatement ModificarStm = MiConexion.getConexion().prepareCall("UPDATE Libros SET Titulo=?, Autor=?, Descripcion=?, Paginas=?, " +
                    "Editorial=?, Edicion=?, Costo=? WHERE ISBN = ?");
            ModificarStm.setString(1, TxtTitulo.getText());
            ModificarStm.setString(2, TxtAutor.getText());
            ModificarStm.setString(3,TxtDescripcion.getText());
            ModificarStm.setInt(4, Integer.parseInt(TxtPaginas.getText()));
            ModificarStm.setString(5,TxtEditorial.getText());
            ModificarStm.setString(6,TxtEdicion.getText());
            ModificarStm.setFloat(7, Float.parseFloat(TxtCosto.getText()));
            ModificarStm.setString(8,ISBN);
            ModificarStm.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Libro modificado correctamente");
            limpiarCampos();
            limpiarTabla();
            inicializarTabla();
            BtCancelar.setEnabled(false);
            BtGuardar.setEnabled(false);
        } catch ( NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error: "+e);//mensaje de error
        }
    }
}
