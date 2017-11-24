package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VenEditarBiblio extends InternalWindow implements KeyListener, ActionListener, MouseListener {
    Conexion MiConexion;
    private int id;
    private JButton BtBuscar = new JButton();
    private ImageIcon ImgBuscar = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    private ImageIcon ImgCancel = new ImageIcon(getClass().getResource("/images/cancel-32.png"));
    private ImageIcon ImgSave = new ImageIcon(getClass().getResource("/images/save-32.png"));
    //label
    private JLabel LblTitulo = new JLabel("Bibliotecas");
    private JLabel LblNombre = new JLabel("Nombre");
    private JLabel LblCalle = new JLabel("Calle");
    private JLabel LblNumero = new JLabel("Numero");
    private JLabel LblEstado = new JLabel("Estado");
    private JLabel LblMunicipio = new JLabel("Municipio");
    private JLabel LblColonia = new JLabel("Colonia");
    private JLabel LblCP = new JLabel("CP");
    //textfield
    private JTextField TxTBuscar = new JTextField();
    private JTextField TxTNombre = new JTextField();
    private JTextField TxtCalle = new JTextField();
    private JTextField TxtNumero = new JTextField();
    private JTextField TxtEstados = new JTextField();
    private JTextField TxtMunicipio = new JTextField();
    private JTextField TxtColonia = new JTextField();
    private JTextField TxtCP = new JTextField();
    //Combobox
    private JLabel LblCombo = new JLabel("Buscar por:");
    private JComboBox Combo = new JComboBox();
    private JComboBox CbEstado = new JComboBox();
    private Object[] Estados = new Object[]{"Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua",
            "Coahuila de Zaragoza", "Colima", "Durango","Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán de Ocampo","Morelos",
            "Nayarit", "Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco", "Tamaulipas", "Tlaxcala",
            "Veracruz de Ignacio de la Llave","Yucatán", "Zacatecas"};
    //tabla
    private JTable Tabla = null;
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][8];
    private Object ColumName[] = new Object[]{"ID","Nombre","Calle","Numero","Estado", "Municipio", "Colonia", "CP"};
    private DefaultTableModel Modelo= new DefaultTableModel(FilaInicial, ColumName);
    //Buttons
    private JButton BtAgregar = new JButton("Agregar");
    private JButton BtModificar = new JButton("Modificar");
    private JButton BtEliminar = new JButton("Eliminar");
    private JButton BtGuardar = new JButton("Guardar");
    private JButton BtCancelar = new JButton("Cancelar");
    private ArrayList<JTextField> textFields = new ArrayList<>();

    public VenEditarBiblio(Conexion MiConexion){
        this.MiConexion = MiConexion;
        this.setSize(1100,600);
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));
        addLabel(LblCombo,30,10,150,Combo,this);
        addComboBox(Combo,30,40,150,this);
        llenarComboBox(ColumName, Combo);
        //textfield
        addTextField(TxTBuscar, 190, 40, 120, "Buscar",this);
        TxTBuscar.setToolTipText("'*' para mostrar todos los resultados");
        //edicion
        addLabel(LblNombre, 680,100, TxTNombre, this);
        addTextField(TxTNombre, 790, 100, 230, "Nombre",this);
        addLabel(LblCalle, 680,140,TxtCalle, this);
        addTextField(TxtCalle, 790, 140, 230, "Calle",this);
        addLabel(LblNumero, 680,180, TxtNumero, this);
        addTextField(TxtNumero, 790, 180, 230, "Numero",this);
        addLabel(LblEstado, 680,220, CbEstado, this);
        addTextField(TxtEstados, 790, 220, 230, "Estado",this);
        //addComboBox(CbEstado,790,220,230,this);
        llenarComboBox(Estados, CbEstado);
        addLabel(LblMunicipio, 680,260, TxtMunicipio, this);
        addTextField(TxtMunicipio, 790, 260, 230, "Municipios",this);
        addLabel(LblColonia, 680,300, TxtColonia, this);
        addTextField(TxtColonia, 790, 300, 230, "Colonia",this);
        addLabel(LblCP, 680,340, TxtCP, this);
        addTextField(TxtCP, 790, 340, 230, "Codigo Postal",this);
        //boton
        addButton(BtBuscar, 320, 40,"Buscar Libro",this);
        BtBuscar.setSize(30,30);
        BtBuscar.setIcon(ImgBuscar);
        //edicion
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
        //tabla
        inicializarTabla();
        llenarArrayList();
        Tabla.addMouseListener(this);
        addWindowProperties(this,"Consultar Bilbioteca");
    }

    private void llenarArrayList() {
        textFields.add(TxTNombre);
        textFields.add(TxtCalle);
        textFields.add(TxtNumero);
        textFields.add(TxtEstados);
        textFields.add(TxtMunicipio);
        textFields.add(TxtColonia);
        textFields.add(TxtCP);
    }

    private boolean validarCampos() {
        boolean result = true;

        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                colorVoidComponents(textField);
                result = false;
            }
        }
        return result;
    }

    private void inicializarTabla() {
        Tabla = new JTable(Modelo);
        Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//se autoajusta
        Tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        Tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        Tabla.getTableHeader().setReorderingAllowed(false);//se ordena automaticamente
        llenarTabla(MiConexion,"SELECT * FROM Bibliotecas",Modelo,rootPane);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 640, 300);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
    }

    private void llenarComboBox(Object[] ArrayObject, JComboBox Combo) {
        for (int i=0; i<ArrayObject.length; i++){
            Combo.addItem(ArrayObject[i].toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if(me.getSource()==BtBuscar){
            buscarBiblio();
        } else if (me.getSource()==BtModificar){
            for (JTextField textField : textFields) {
                colorComponent(textField);
            }
            BtEliminar.setEnabled(false);
            BtAgregar.setEnabled(false);
            BtGuardar.setEnabled(true);
            BtCancelar.setEnabled(true);
            //modificar(this.id);
            llenarTextField();
        } else if (me.getSource()==BtGuardar){
            modificar(this.id);
            BtAgregar.setEnabled(true);
            BtAgregar.setEnabled(true);
            BtCancelar.setEnabled(false);
            BtGuardar.setEnabled(false);
            for (JTextField textField : textFields) {
                colorComponent(textField);
            }
        } else if (me.getSource()==BtCancelar){
            limpiarCampos();
            BtAgregar.setEnabled(true);
            BtEliminar.setEnabled(true);
            BtCancelar.setEnabled(false);
            BtGuardar.setEnabled(false);
            for (JTextField textField : textFields) {
                colorComponent(textField);
            }
        } else if (me.getSource()==BtEliminar){
            eliminar();
            for (JTextField textField : textFields) {
                colorComponent(textField);
            }
        } else if (me.getSource()==BtAgregar){
            if (validarCampos())
                agregar();
            else
                JOptionPane.showMessageDialog(rootPane, "Ha dejado campos vacíos.");
        }
    }

    private void agregar() {
        try{
            if (TxTNombre.getText().isEmpty() ||TxtCalle.getText().isEmpty()|| TxtNumero.getText().isEmpty() || TxtEstados.getText().isEmpty()|| TxtMunicipio.getText().isEmpty()
                    || TxtColonia.getText().isEmpty()|| TxtCP.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Los campos no deben de estar vacios");
            }else {
                PreparedStatement EliminarStm = MiConexion.getConexion().prepareCall("INSERT INTO Bibliotecas (Nombre, Calle, Numero, Estado, Municipio, Colonia, CP) VALUES(?, ?, ?, ?, ?, ?, ?) ");
                EliminarStm.setString(1, TxTNombre.getText().toString());
                EliminarStm.setString(2, TxtCalle.getText().toString());
                EliminarStm.setInt(3, Integer.parseInt(TxtNumero.getText().toString()));
                EliminarStm.setString(4, TxtEstados.getText().toString());
                EliminarStm.setString(5, TxtMunicipio.getText().toString());
                EliminarStm.setString(6, TxtColonia.getText().toString());
                EliminarStm.setInt(7, Integer.parseInt(TxtCP.getText().toString()));
                EliminarStm.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Biblioteca agregada correctamente");
                limpiarCampos();
                limpiarTabla();
                inicializarTabla();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(rootPane,"Error: "+e);
        }
    }

    private void eliminar() {
        for (JTextField textField : textFields) {
            colorComponent(textField);
        }
        int filaSeleccionada = Tabla.getSelectedRow();//toma la fila seleccionada
        if (filaSeleccionada>=0) {
            id = Integer.parseInt(Modelo.getValueAt(filaSeleccionada, 0).toString());
            if (JOptionPane.showConfirmDialog(rootPane,"¿Realmente quiere borrar la biblioteca con el ID: "+id+"?", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)==0) {
                try {
                    PreparedStatement EliminarStm1 = MiConexion.getConexion().prepareCall("DELETE FROM EjemplaresXBibliotecas WHERE idBibliotecas=?");
                    EliminarStm1.setInt(1, id);
                    PreparedStatement EliminarStm = MiConexion.getConexion().prepareCall("DELETE FROM Bibliotecas WHERE idBibliotecas=?");
                    EliminarStm.setInt(1, id);
                    EliminarStm1.executeUpdate();
                    EliminarStm.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Biblioteca borrada correctamente");
                    limpiarCampos();
                    limpiarTabla();
                    inicializarTabla();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Error: " + e);
                }
            }
        }else
            JOptionPane.showMessageDialog(rootPane,"seleccione la fila de la biblioteca que quiere Eliminar");
    }

    private void modificar(int id) {
        for (JTextField textField : textFields) {
            colorComponent(textField);
        }
        try {
            int ID = id;
            PreparedStatement ModificarStm = MiConexion.getConexion().prepareCall("UPDATE Bibliotecas SET Nombre=?, Calle=?, Numero=?, Estado=?, Municipio=?, Colonia=?, CP=? WHERE idBibliotecas = ?");
            ModificarStm.setString(1, TxTNombre.getText());
            ModificarStm.setString(2, TxtCalle.getText());
            ModificarStm.setInt(3, Integer.parseInt(TxtNumero.getText()));
            ModificarStm.setString(4,TxtEstados.getText());
            ModificarStm.setString(5,TxtMunicipio.getText());
            ModificarStm.setString(6,TxtColonia.getText());
            ModificarStm.setInt(7, Integer.parseInt(TxtCP.getText()));
            ModificarStm.setInt(8, ID);
            ModificarStm.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Biblioteca modificada correctamente");
            limpiarCampos();
            limpiarTabla();
            inicializarTabla();
            BtCancelar.setEnabled(false);
            BtGuardar.setEnabled(false);
        } catch ( NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error: "+e);//mensaje de error
        }
    }
    private void limpiarCampos(){
        TxTNombre.setText("");
        TxtCalle.setText("");
        TxtNumero.setText("");
        TxtEstados.setText("");
        TxtMunicipio.setText("");
        TxtColonia.setText("");
        TxtCP.setText("");
    }
    private void llenarTextField() {
        int filaSeleccionada = Tabla.getSelectedRow();//toma la fila seleccionada
        if (filaSeleccionada>=0) {
            String ID = new String(Modelo.getValueAt(filaSeleccionada, 0).toString());
            id = Integer.parseInt(ID);
            TxTNombre.setText(Modelo.getValueAt(filaSeleccionada, 1).toString());
            TxtCalle.setText(Modelo.getValueAt(filaSeleccionada, 2).toString());
            TxtNumero.setText(Modelo.getValueAt(filaSeleccionada, 3).toString());
            TxtEstados.setText(Modelo.getValueAt(filaSeleccionada, 4).toString());
            TxtMunicipio.setText(Modelo.getValueAt(filaSeleccionada, 5).toString());
            TxtColonia.setText(Modelo.getValueAt(filaSeleccionada, 6).toString());
            TxtCP.setText(Modelo.getValueAt(filaSeleccionada, 7).toString());
            BtGuardar.setEnabled(true);
            BtCancelar.setEnabled(true);
        }else
            JOptionPane.showMessageDialog(rootPane,"seleccione la fila de la biblioteca que quiere modificar");
    }

    private void buscarFrase() {
        limpiarTabla();
        if (Combo.getSelectedItem().toString().equals("ID")){
            String SQL = new String("SELECT * FROM Bibliotecas WHERE idBibliotecas" +
                    " LIKE '%"+TxTBuscar.getText().toString())+"%' order by idBibliotecas";
            llenarTabla(MiConexion,SQL,Modelo,rootPane);
        }else{
            String SQL = new String("SELECT * FROM Bibliotecas WHERE " +
                    Combo.getSelectedItem()+" LIKE '%"+TxTBuscar.getText().toString())+"%' order by "+Combo.getSelectedItem();
            llenarTabla(MiConexion,SQL,Modelo,rootPane);
        }
    }

    private void limpiarTabla() {
        Modelo.setRowCount(0);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getSource() == TxTNombre) {
            colorComponent(TxTNombre);
            if (TxtNumero.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtCalle) {
            colorComponent(TxtCalle);
            if (TxtCalle.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtNumero) {
            colorComponent(TxtNumero);
            if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9') {
                if (TxtNumero.getText().length() == 11)
                    keyEvent.consume();
            } else
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtEstados) {
            colorComponent(TxtEstados);
            if (TxtEstados.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtMunicipio) {
            colorComponent(TxtMunicipio);
            if (TxtMunicipio.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtColonia) {
            colorComponent(TxtColonia);
            if (TxtMunicipio.getText().length() == 45)
                keyEvent.consume();
        } else if (keyEvent.getSource() == TxtCP) {
            colorComponent(TxtCP);
            if (keyEvent.getKeyChar() >= '0' && keyEvent.getKeyChar() <= '9') {
                if (TxtCP.getText().length() == 11)
                    keyEvent.consume();
            } else
                keyEvent.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource()==TxTBuscar){
            TxTBuscar.setBackground(Color.white);
            TxTBuscar.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            if (e.getKeyChar()==e.VK_ENTER)
                buscarBiblio();
        }else if (e.getSource()==TxTNombre){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtCalle.requestFocus();
            }
        }else if (e.getSource()==TxtCalle){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtNumero.requestFocus();
            }
        }else if (e.getSource()==TxtNumero){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtEstados.requestFocus();
            }
        }else if (e.getSource()==TxtEstados){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtMunicipio.requestFocus();
            }
        }else if (e.getSource()==TxtMunicipio){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtColonia.requestFocus();
            }
        }else if (e.getSource()==TxtColonia){
            if (e.getKeyChar()==e.VK_ENTER) {
                TxtCP.requestFocus();
            }
        }
    }
    private void buscarBiblio() {
        if (TxTBuscar.getText().isEmpty()){
            limpiarTabla();
            llenarTabla(MiConexion,"SELECT * FROM Bibliotecas",Modelo,rootPane);
        }else {
            buscarFrase();
        }
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {

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
    public void mouseClicked(MouseEvent mouseEvent) {
        /**if (mouseEvent.getSource()==Tabla){
            llenarTextField();
            BtModificar.setEnabled(true);
        }**/
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
