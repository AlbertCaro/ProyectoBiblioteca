/*
Ventana principal del software
 */
package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Jonny
 */
public class WindowMain extends JFrame implements ActionListener, MouseListener{
    //Conexion
    Conexion conexion;
    //Imagenes
    ImageIcon ImagenFondo = new ImageIcon(getClass().getResource("/images/background.png"));
    ImageIcon IconoBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    JLabel JLSesion = new JLabel("Usuario");
    // Crear un escritorio para JFrame
    JDesktopPane Escritorio = new JDesktopPane();
    //Se usa la clase PanelImagen para dimensionar la imagen de parametro
    PanelImagen Panel = new PanelImagen(ImagenFondo);
    //botones de la barra menu
    JMenuBar BarraMenu = new JMenuBar();
    JMenu MenuArchivo = new JMenu("Archivo");
    JMenu MenuRegistrar = new JMenu("Registrar");
    JMenu MenuConsultar = new JMenu("Consultar");
    JMenuItem MenuArchivoSalir = new JMenuItem("Salir");
    JMenuItem MenuRegistrarUsuarios = new JMenuItem("Usuario");
    JMenuItem MenuRegistrarBiblioteca = new JMenuItem("Biblioteca");
    JMenuItem MenuRegistrarPrestamo = new JMenuItem("Prestamo");
    JMenuItem MenuConsultarPrestamo = new JMenuItem("Prestamos");
    JMenuItem MenuConsultarLibro = new JMenuItem("Libros");
    /////////////////////Constructor/////////////////
    public WindowMain(Conexion conexion){
        this.conexion = conexion;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(IconoBiblio.getImage());//icono de la ventana
        this.setSize(500, 500);// espara que al momento de restaurar la ventana no quede en el tamaño (0,0)
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);//MAXIMIZED_BOTH se maximiza a ambos lados
        this.setTitle("Ventana principal");
        //Agregamos el escritorio
        this.add(Escritorio);
        //decirle que el nuevo contenedor va ser el Escritorio
        this.setContentPane(Escritorio);
        //Agregar la imagen de Fondo
        this.Escritorio.add(Panel);
        this.setContentPane(Panel);
        this.setLayout(null);
        //Agregar la barra Menu al frame
        this.setJMenuBar(BarraMenu);
        BarraMenu.add(MenuArchivo);
        BarraMenu.add(MenuRegistrar);
        BarraMenu.add(MenuConsultar);
        BarraMenu.setBackground(new Color (215,88,21));
        BarraMenu.setFont(new Font("arial", 1, 16));
        BarraMenu.setForeground(new Color(255,255,255));
        //menu archivo
        MenuArchivo.add(MenuArchivoSalir);
        MenuArchivo.setBackground(new Color (215,88,21));
        MenuArchivo.setFont(new Font("arial", 1, 16));
        MenuArchivo.setForeground(new Color(255,255,255));
        MenuArchivoSalir.setBackground(new Color (215,88,21));
        MenuArchivoSalir.setFont(new Font("arial", 1, 16));
        MenuArchivoSalir.setForeground(new Color(255,255,255));
        MenuArchivoSalir.setToolTipText("Salir del sistema");
        //menu Registrar
        MenuRegistrar.add(MenuRegistrarPrestamo);
        MenuRegistrar.add(MenuRegistrarUsuarios);
        MenuRegistrar.add(MenuRegistrarBiblioteca);
        //MenuRegistrar.setBackground(new Color (215,88,21));
        MenuRegistrar.setFont(new Font("arial", 1, 16));
        MenuRegistrar.setForeground(new Color(255,255,255));
        MenuRegistrarBiblioteca.setBackground(new Color (215,88,21));
        MenuRegistrarBiblioteca.setFont(new Font("arial", 1, 16));
        MenuRegistrarBiblioteca.setForeground(new Color(255,255,255));
        MenuRegistrarBiblioteca.setToolTipText("Registrar biblioteca");
        MenuRegistrarPrestamo.setBackground(new Color (215,88,21));
        MenuRegistrarPrestamo.setFont(new Font("arial", 1, 16));
        MenuRegistrarPrestamo.setForeground(new Color(255,255,255));
        MenuRegistrarPrestamo.setToolTipText("Registrar prestamo");
        MenuRegistrarUsuarios.setBackground(new Color (215,88,21));
        MenuRegistrarUsuarios.setFont(new Font("arial", 1, 16));
        MenuRegistrarUsuarios.setForeground(new Color(255,255,255));
        MenuRegistrarUsuarios.setToolTipText("Registrar usuario");
        //menu consultar
        MenuConsultar.add(MenuConsultarLibro);
        MenuConsultar.add(MenuConsultarPrestamo);
        MenuConsultar.setFont(new Font("arial", 1, 16));
        MenuConsultar.setForeground(new Color(255,255,255));
        MenuConsultarPrestamo.setBackground(new Color (215,88,21));
        MenuConsultarPrestamo.setFont(new Font("arial", 1, 16));
        MenuConsultarPrestamo.setForeground(new Color(255,255,255));
        MenuConsultarPrestamo.setToolTipText("Consultar prestamos");
        MenuConsultarLibro.setBackground(new Color (215,88,21));
        MenuConsultarLibro.setFont(new Font("arial", 1, 16));
        MenuConsultarLibro.setForeground(new Color(255,255,255));
        MenuConsultarLibro.setToolTipText("Consultar libros");
        //Poner shorcuts(accesos directos)
        MenuArchivoSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        MenuConsultarLibro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
        //label
        JLSesion.setFont(new Font("arial", 1, 16));
        JLSesion.setToolTipText("Cerrar Sesión");
        JLSesion.setBounds(5,5,100,30);
        Panel.add(JLSesion);
        Listeners();      
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource() == MenuArchivoSalir) {
            System.exit(0);
        }else if (me.getSource() == MenuConsultarLibro) {
            ConsultarLibros VCLibros =  new ConsultarLibros();
            Panel.add(VCLibros);
        }else if (me.getSource() == MenuConsultarPrestamo) {
            ConsultaPrestamos VCPrestamos = new ConsultaPrestamos();
            Panel.add(VCPrestamos);
        }else if (me.getSource() == MenuRegistrarBiblioteca) {
            VentanaBibliotecas VRBiblioteca = new VentanaBibliotecas();
            Panel.add(VRBiblioteca);
        }else if (me.getSource() == MenuRegistrarPrestamo) {
            RegistroPrestamos VRPrestamos = new RegistroPrestamos();
            Panel.add(VRPrestamos);
        }else if (me.getSource() == MenuRegistrarUsuarios) {
            VentanaUsuarios VRUsuario = new VentanaUsuarios();
            Panel.add(VRUsuario);
        }
    }

    private void Listeners() {
        MenuArchivoSalir.addActionListener(this);
        MenuConsultarLibro.addActionListener(this);
        MenuConsultarPrestamo.addActionListener(this);
        MenuRegistrarBiblioteca.addActionListener(this);
        MenuRegistrarPrestamo.addActionListener(this);
        MenuRegistrarUsuarios.addActionListener(this);
        JLSesion.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==JLSesion){
            Login vlogin = new Login(conexion);
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==JLSesion){
            JLSesion.setFont(new Font("arial", 3, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==JLSesion){
            JLSesion.setFont(new Font("arial", 1, 16));
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
