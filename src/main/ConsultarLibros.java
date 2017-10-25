package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static main.InternalWindow.*;

/**
 * @author Jonny
 */
public class ConsultarLibros extends JInternalFrame implements ActionListener, KeyListener {
    JButton JBBuscar = new JButton("Consultar");
    JTextField JTxTBuscar = new JTextField();
    //tabla
    JScrollPane JSCTabla = new JScrollPane();
    JTable JtResultados = null;
    Object[][] FilaInicial = new Object[1][3];
    Object ColumName[] = new Object[]{"ISBN","Nombre","Editorial"};
    DefaultTableModel DTMResultados = new DefaultTableModel(FilaInicial, ColumName);//FilaInicial para la fila i ncial y ColumName para los nombres de las columnas
    public ConsultarLibros(){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(700,500);
        this.setTitle("Consultar Libros");
        this.setLayout(null);
        this.setResizable(false);
        this.setMaximizable(true);
        this.setClosable(true);
        //this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new java.awt.Color(254,223,168));
        //boton
        addButton(JBBuscar, 100, 30, this);
        JBBuscar.setBounds(631,40,25,25);
        //textfield
        addTextField(JTxTBuscar, 120, 30, 20, "Buscar",this);
        JTxTBuscar.setBounds(510,40,120,25);
        //tabla
        JtResultados = new JTable(DTMResultados);
        this.add(JtResultados);
        JSCTabla.setBounds(30, 80, 640, 300);
        JSCTabla.setViewportView(JtResultados);
        this.add(JSCTabla);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent me) {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
