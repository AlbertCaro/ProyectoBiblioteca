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
    ImageIcon ImgExit = new ImageIcon(getClass().getResource("/images/exit1.png"));
    ImageIcon ImgAdd = new ImageIcon(getClass().getResource("/images/add.png"));
    ImageIcon ImgSearch = new ImageIcon(getClass().getResource("/images/search.png"));
    ImageIcon ImgDelete = new ImageIcon(getClass().getResource("/images/delete.png"));
    ImageIcon ImgEdit = new ImageIcon(getClass().getResource("/images/edit.png"));
    // Crear un escritorio para JFrame
    JDesktopPane Escritorio = new JDesktopPane();
    //Se usa la clase PanelImagen para dimensionar la imagen de parametro
    PanelImagen Panel = new PanelImagen(ImagenFondo);
    //botones de la barra menu
    JMenuBar BarraMenu = new JMenuBar();
    JMenu MenuArchivo = new JMenu("Archivo");
    JMenu MenuLibros = new JMenu("Libros");
    JMenu MenuPrestamos = new JMenu("Prestamos");
    JMenu MenuUsuarios = new JMenu("Usuarios");
    JMenu MenuBibliotecas = new JMenu("Bibliotecas");
    JMenuItem MenuArchivoSalir = new JMenuItem("Salir");
    JMenuItem MenuArchioLogOut = new JMenuItem();

    JMenuItem MenuUsuarioRegistrar = new JMenuItem("Registrar");
    JMenuItem MenuBibliotecaRegistrar = new JMenuItem("Registrar");
    JMenuItem MenuLibroRegistrar = new JMenuItem("Registrar");
    JMenuItem MenuPrestamoRegistrar = new JMenuItem("Registrar");

    JMenuItem MenuPrestamoConsultar = new JMenuItem("Consultar");
    JMenuItem MenuLibroConsultar = new JMenuItem("Consultar");
    JMenuItem MenuUsuarioConsultar = new JMenuItem("Consultar");
    JMenuItem MenuBibliotecaConsultar = new JMenuItem("Consultar");

    JMenuItem MenuPrestamoEliminar = new JMenuItem("Liquidar");
    JMenuItem MenuLibroEliminar = new JMenuItem("Eliminar");
    JMenuItem MenuUsuarioEliminar= new JMenuItem("Eliminar");
    JMenuItem MenuBibliotecaEliminar = new JMenuItem("Eliminar");

    JMenuItem MenuPrestamoModificar = new JMenuItem("Modificar");
    JMenuItem MenuLibroModificar = new JMenuItem("Modificar");
    JMenuItem MenuUsuarioModificar= new JMenuItem("Modificar");
    JMenuItem MenuBibliotecaModificar = new JMenuItem("Modificar");
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
        this.setTitle("AdmyBook - Sesión iniciada como: "+Usuario);
        //Agregamos el escritorio
        this.add(Escritorio);
        //decirle que el nuevo contenedor va ser el Escritorio
        this.setContentPane(Escritorio);
        //Agregar la imagen de Fondo
        this.Escritorio.add(Panel);
        this.setContentPane(Panel);
        this.setLayout(null);
        //this.setUndecorated(true);
        //Agregar la barra Menu al frame
        this.setJMenuBar(BarraMenu);
        BarraMenu.setBackground(new Color (53, 81, 181));
        BarraMenu.setFont(new Font("arial", 1, 16));
        BarraMenu.setForeground(new Color(255,255,255));
        //menu archivo
        BarraMenu.add(MenuArchivo);
        MenuArchivo.add(MenuArchioLogOut);
        MenuArchivo.add(MenuArchivoSalir);
        JMenuColorAndFont(MenuArchivo);
        addJMenuItem(MenuArchioLogOut,"Iniciar o cerrar sesión", ImgSesion);
        addJMenuItem(MenuArchivoSalir,"Salir del sistema",ImgExit);
        //menu Libros
        BarraMenu.add(MenuLibros);
        MenuLibros.add(MenuLibroConsultar);
        JMenuColorAndFont(MenuLibros);
        addJMenuItem(MenuLibroConsultar,"Consultar libros",ImgSearch);
        addJMenuItem(MenuLibroRegistrar,"Registrar libros",ImgAdd);
        addJMenuItem(MenuLibroEliminar,"Eliminar libros", ImgDelete);
        addJMenuItem(MenuLibroModificar,"Modificar libros",ImgEdit);
        //menu Prestamos
        MenuPrestamos.add(MenuPrestamoConsultar);
        JMenuColorAndFont(MenuPrestamos);
        addJMenuItem(MenuPrestamoConsultar,"Consultar prestamos",ImgSearch);
        addJMenuItem(MenuPrestamoRegistrar,"Registrar prestamos",ImgAdd);
        addJMenuItem(MenuPrestamoEliminar,"Eliminar prestamos", ImgDelete);
        addJMenuItem(MenuPrestamoModificar,"Modificar prestamos",ImgEdit);
        //menu Usuarios
        JMenuColorAndFont(MenuUsuarios);
        addJMenuItem(MenuUsuarioConsultar,"Consultar usuarios",ImgSearch);
        addJMenuItem(MenuUsuarioRegistrar,"Registrar usuarios",ImgAdd);
        addJMenuItem(MenuUsuarioEliminar,"Eliminar usuarios", ImgDelete);
        addJMenuItem(MenuUsuarioModificar,"Modificar usuarios",ImgEdit);
        //menu biblioteca
        MenuBibliotecas.add(MenuBibliotecaConsultar);
        JMenuColorAndFont(MenuBibliotecas);
        addJMenuItem(MenuBibliotecaConsultar,"Consultar bibliotecas",ImgSearch);
        addJMenuItem(MenuBibliotecaRegistrar,"Registrar bibliotecas",ImgAdd);
        addJMenuItem(MenuBibliotecaEliminar,"Eliminar bibliotecas", ImgDelete);
        addJMenuItem(MenuBibliotecaModificar,"Modificar bibliotecas",ImgEdit);
        //Poner shorcuts(accesos directos)
        MenuArchivoSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
        MenuLibroConsultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
        MenuArchioLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        restriccionesUsuarios();
        this.setVisible(true);
    }
    private void addJMenuItem(JMenuItem MenuItem, String TipText, ImageIcon Icon){
        MenuItem.setBackground(new Color (53, 81, 181));
        MenuItem.setFont(new Font("arial", 1, 16));
        MenuItem.setForeground(new Color(255,255,255));
        MenuItem.setToolTipText(TipText);
        MenuItem.setIcon(Icon);
        MenuItem.addActionListener(this);
    }
    private void JMenuColorAndFont(JMenu JMenu){
        JMenu.setBackground(new Color (53, 81, 181));
        JMenu.setFont(new Font("arial", 1, 16));
        JMenu.setForeground(new Color(255,255,255));
    }

    private void restriccionesUsuarios() {
        if (Tipo.equals("SuperAdministrador")) {//Super Administrador
            MenuArchioLogOut.setText("Cerrar Sesión");
            //accesos libros
            MenuLibros.add(MenuLibroRegistrar);
            MenuLibros.add(MenuLibroEliminar);
            MenuLibros.add(MenuLibroModificar);
            //accesos prestamos
            BarraMenu.add(MenuPrestamos);
            MenuPrestamos.add(MenuPrestamoRegistrar);
            MenuPrestamos.add(MenuPrestamoEliminar);
            MenuPrestamos.add(MenuPrestamoModificar);
            //accesos usuarios
            BarraMenu.add(MenuUsuarios);
            MenuUsuarios.add(MenuUsuarioConsultar);
            MenuUsuarios.add(MenuUsuarioRegistrar);
            MenuUsuarios.add(MenuUsuarioEliminar);
            MenuUsuarios.add(MenuUsuarioModificar);
            //accesos bibliotecas
            BarraMenu.add(MenuBibliotecas);
            MenuBibliotecas.add(MenuBibliotecaRegistrar);
            MenuBibliotecas.add(MenuBibliotecaEliminar);
            MenuBibliotecas.add(MenuBibliotecaModificar);
        }else if(Tipo.equals("Normal")){
            MenuArchioLogOut.setText("Cerrar Sesión");
            //libros
            MenuLibros.add(MenuLibroConsultar);
            //accesos prestamos
            BarraMenu.add(MenuPrestamos);
            //accesos bibliotecas
            BarraMenu.add(MenuBibliotecas);
        }else if(Tipo.equals("Invitado")) {//Invitado
            MenuArchioLogOut.setText("Iniciar Sesión");
        }
    }

    @Override
    public void actionPerformed(ActionEvent me) {
        if (me.getSource() == MenuArchivoSalir) {
            System.exit(0);
        }else if (me.getSource() == MenuLibroConsultar) {
            ConsultarLibros VCLibros =  new ConsultarLibros();
            Panel.add(VCLibros);
        }else if (me.getSource() == MenuPrestamoConsultar) {
            VenConsultaPrestamos VCPrestamos = new VenConsultaPrestamos(conexion, Tipo, Usuario);
            Panel.add(VCPrestamos);
        }else if (me.getSource() == MenuBibliotecaRegistrar) {
            VentanaBibliotecas VRBiblioteca = new VentanaBibliotecas();
            Panel.add(VRBiblioteca);
        }else if (me.getSource() == MenuPrestamoRegistrar) {
            VenRegistroPrestamos VRPrestamos = new VenRegistroPrestamos();
            Panel.add(VRPrestamos);
        }else if (me.getSource() == MenuUsuarioRegistrar) {
            VentanaUsuarios VRUsuario = new VentanaUsuarios();
            Panel.add(VRUsuario);
        }else if (me.getSource()==MenuArchioLogOut){
            Login vlogin = new Login(conexion);
            this.dispose();
        }
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

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
