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
    //Conexion y parametros
    Conexion conexion;
    String Tipo, Usuario;
    //Imagenes
    ImageIcon ImagenFondo = new ImageIcon(getClass().getResource("/images/background.png"));
    ImageIcon IconoBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    ImageIcon ImgSesion = new ImageIcon(getClass().getResource("/images/sesion1.png"));
    ImageIcon ImgBook = new ImageIcon(getClass().getResource("/images/book1.png"));
    ImageIcon ImgExit = new ImageIcon(getClass().getResource("/images/exit1.png"));
    ImageIcon ImgPrest = new ImageIcon(getClass().getResource("/images/prestbook.png"));
    ImageIcon ImgBiblio = new ImageIcon(getClass().getResource("/images/biblioIco.png"));
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
    JMenuItem MenuArchioLogOut = new JMenuItem("Cerrar Sesión");
    JMenuItem MenuRegistrarUsuarios = new JMenuItem("Usuario");
    JMenuItem MenuRegistrarBiblioteca = new JMenuItem("Biblioteca");
    JMenuItem MenuRegistrarPrestamo = new JMenuItem("Prestamo");
    JMenuItem MenuConsultarPrestamo = new JMenuItem("Prestamos");
    JMenuItem MenuConsultarLibro = new JMenuItem("Libros");
    /////////////////////Constructor/////////////////
    public WindowMain(Conexion conexion, String Tipo, String Usuario){
        this.Tipo = Tipo;
        this.conexion = conexion;
        this.Usuario = Usuario;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //UIManager.put("activeCaption", new javax.swing.plaf.ColorUIResource(new Color(198, 5, 4)));
        //this.setDefaultLookAndFeelDecorated(true);
        this.setIconImage(IconoBiblio.getImage());
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);//MAXIMIZED_BOTH se maximiza a ambos lados
        this.setTitle("AdmyBook Sesión iniciada como: "+Usuario);
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
        //BarraMenu.add(MenuRegistrar);
        BarraMenu.add(MenuConsultar);
        BarraMenu.setBackground(new Color (215,88,21));
        BarraMenu.setFont(new Font("arial", 1, 16));
        BarraMenu.setForeground(new Color(255,255,255));
        //menu archivo
        MenuArchivo.add(MenuArchioLogOut);
        MenuArchivo.add(MenuArchivoSalir);
        MenuArchivo.setBackground(new Color (215,88,21));
        MenuArchivo.setFont(new Font("arial", 1, 16));
        MenuArchivo.setForeground(new Color(255,255,255));
        MenuArchioLogOut.setBackground(new Color (215,88,21));
        MenuArchioLogOut.setFont(new Font("arial",1,16));
        MenuArchioLogOut.setForeground(new Color(255,255,255));
        MenuArchioLogOut.setToolTipText("Cerrar sesión o cambiar de usuario");
        MenuArchioLogOut.setIcon(ImgSesion);
        MenuArchioLogOut.setText("Cerrar Sesión");
        MenuArchivoSalir.setBackground(new Color (215,88,21));
        MenuArchivoSalir.setFont(new Font("arial", 1, 16));
        MenuArchivoSalir.setForeground(new Color(255,255,255));
        MenuArchivoSalir.setToolTipText("Salir del sistema");
        MenuArchivoSalir.setIcon(ImgExit);
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
        MenuRegistrarBiblioteca.setIcon(ImgBiblio);
        MenuRegistrarPrestamo.setBackground(new Color (215,88,21));
        MenuRegistrarPrestamo.setFont(new Font("arial", 1, 16));
        MenuRegistrarPrestamo.setForeground(new Color(255,255,255));
        MenuRegistrarPrestamo.setToolTipText("Registrar prestamo");
        MenuRegistrarPrestamo.setIcon(ImgPrest);
        MenuRegistrarUsuarios.setBackground(new Color (215,88,21));
        MenuRegistrarUsuarios.setFont(new Font("arial", 1, 16));
        MenuRegistrarUsuarios.setForeground(new Color(255,255,255));
        MenuRegistrarUsuarios.setToolTipText("Registrar usuario");
        MenuRegistrarUsuarios.setIcon(ImgSesion);//mas adelante cambio el icono
        //menu consultar
        MenuConsultar.add(MenuConsultarLibro);
        MenuConsultar.setFont(new Font("arial", 1, 16));
        MenuConsultar.setForeground(new Color(255,255,255));
        MenuConsultarPrestamo.setBackground(new Color (215,88,21));
        MenuConsultarPrestamo.setFont(new Font("arial", 1, 16));
        MenuConsultarPrestamo.setForeground(new Color(255,255,255));
        MenuConsultarPrestamo.setToolTipText("Consultar prestamos");
        MenuConsultarPrestamo.setIcon(ImgPrest);
        MenuConsultarLibro.setBackground(new Color (215,88,21));
        MenuConsultarLibro.setFont(new Font("arial", 1, 16));
        MenuConsultarLibro.setForeground(new Color(255,255,255));
        MenuConsultarLibro.setToolTipText("Consultar libros");
        MenuConsultarLibro.setIcon(ImgBook);
        //Poner shorcuts(accesos directos)
        MenuArchivoSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
        MenuConsultarLibro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
        MenuArchioLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        restriccionesUsuarios();
        Listeners();
        this.setVisible(true);
    }

    private void restriccionesUsuarios() {
        if (Tipo.equals("SuperAdministrador")) {//Super Administrador
            BarraMenu.add(MenuRegistrar);
            MenuConsultar.add(MenuConsultarPrestamo);
            /*JMenuItem MenuRegistrarUsuarios = new JMenuItem("Usuario");
            JMenuItem MenuRegistrarBiblioteca = new JMenuItem("Biblioteca");
            JMenuItem MenuRegistrarPrestamo = new JMenuItem("Prestamo");*/
            //MenuRegistrarUsuarios.setEnabled(true);
        }else if(Tipo.equals("Invitado")) {
            //MenuUsuarioAdministrar.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource() == MenuArchivoSalir) {
            System.exit(0);
        }else if (me.getSource() == MenuConsultarLibro) {
            ConsultarLibros VCLibros =  new ConsultarLibros();
            Panel.add(VCLibros);
        }else if (me.getSource() == MenuConsultarPrestamo) {
            VenConsultaPrestamos VCPrestamos = new VenConsultaPrestamos(conexion, Tipo, Usuario);
            Panel.add(VCPrestamos);
        }else if (me.getSource() == MenuRegistrarBiblioteca) {
            VentanaBibliotecas VRBiblioteca = new VentanaBibliotecas();
            Panel.add(VRBiblioteca);
        }else if (me.getSource() == MenuRegistrarPrestamo) {
            VenRegistroPrestamos VRPrestamos = new VenRegistroPrestamos();
            Panel.add(VRPrestamos);
        }else if (me.getSource() == MenuRegistrarUsuarios) {
            VentanaUsuarios VRUsuario = new VentanaUsuarios();
            Panel.add(VRUsuario);
        }else if (me.getSource()==MenuArchioLogOut){
            Login vlogin = new Login(conexion);
            this.dispose();
        }
    }

    private void Listeners() {
        MenuArchivoSalir.addActionListener(this);
        MenuConsultarLibro.addActionListener(this);
        MenuConsultarPrestamo.addActionListener(this);
        MenuRegistrarBiblioteca.addActionListener(this);
        MenuRegistrarPrestamo.addActionListener(this);
        MenuRegistrarUsuarios.addActionListener(this);
        MenuArchioLogOut.addActionListener(this);
        MenuArchioLogOut.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==MenuArchioLogOut) {
            MenuArchioLogOut.setText("Cerrar Sesión");
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource()==MenuArchioLogOut)
            MenuArchioLogOut.setText(Usuario);
    }
}
