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
public class ConsultarLibros extends InternalWindow implements ActionListener, KeyListener {
    JButton BtBuscar = new JButton();
    JTextField TxTBuscar = new JTextField();
    ImageIcon ImgBuscar = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    //tabla
    JTable Tabla = null;
    JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    Object[][] FilaInicial = new Object[0][3];
    Object ColumName[] = new Object[]{"ISBN","Nombre","Editorial"};
    DefaultTableModel Modelo= new DefaultTableModel(FilaInicial, ColumName);//FilaInicial para la fila i ncial y ColumName para los nombres de las columnas
    public ConsultarLibros(){
        this.setSize(700,500);
        this.getContentPane().setBackground(new java.awt.Color(168, 220, 255));
        //textfield
        addTextField(TxTBuscar, 30, 30, 120, "Buscar",this);
        //boton
        addButton(BtBuscar, 160, 30, this);
        BtBuscar.setSize(30,30);
        BtBuscar.setIcon(ImgBuscar);
        //tabla
        Tabla = new JTable(Modelo);
        this.add(Tabla);
        ScrollP.setBounds(30, 80, 640, 300);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);

        addWindowProperties(this,"Consultar Libros");
    }
    
    @Override
    public void actionPerformed(ActionEvent me) {
        
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
