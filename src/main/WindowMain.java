/*
Ventana principal del software
 */
package main;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Jonny
 */
public class WindowMain extends JFrame implements ActionListener, MouseListener{
    //Conexion y parametros
    Conexion conexion;
    String Tipo, Usuario;
    //Imagenes
    ImageIcon ImagenFondo = new ImageIcon(getClass().getResource("/images/windowMainIcons/background.png"));
    ImageIcon IconoBiblio = new ImageIcon(getClass().getResource("/images/iconBook.png"));
    ImageIcon ImgSesion = new ImageIcon(getClass().getResource("/images/windowMainIcons/sesion1.png"));///iconos archivo
    ImageIcon ImgExit = new ImageIcon(getClass().getResource("/images/windowMainIcons/exit1.png"));
    ImageIcon ImgAdd = new ImageIcon(getClass().getResource("/images/windowMainIcons/add.png"));////iconos prestamos
    ImageIcon ImgSearch = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
    ImageIcon ImgDelete = new ImageIcon(getClass().getResource("/images/windowMainIcons/delete.png"));
    ImageIcon ImgSearchBook = new ImageIcon(getClass().getResource("/images/windowMainIcons/searchBook.png"));////iconos libros
    ImageIcon ImgEditBook = new ImageIcon(getClass().getResource("/images/windowMainIcons/editBook.png"));
    ImageIcon ImgSearchUser = new ImageIcon(getClass().getResource("/images/windowMainIcons/searchUser.png"));////iconos user
    ImageIcon ImgEditUser = new ImageIcon(getClass().getResource("/images/windowMainIcons/editUser.png"));
    ImageIcon ImgSearchLibrary = new ImageIcon(getClass().getResource("/images/windowMainIcons/library.png"));////iconos biblioteca
    ImageIcon ImgEditLibrary = new ImageIcon(getClass().getResource("/images/windowMainIcons/editLibrary.png"));
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

    JMenuItem MenuPrestamoConsultar = new JMenuItem("Consultar");
    JMenuItem MenuPrestamoRegistrar = new JMenuItem("Registrar");
    JMenuItem MenuPrestamoLiquidar = new JMenuItem("Liquidar");

    JMenuItem MenuLibroConsultar = new JMenuItem("Consultar");
    JMenuItem MenuUsuarioConsultar = new JMenuItem("Consultar");
    JMenuItem MenuBibliotecaConsultar = new JMenuItem("Consultar");

    JMenuItem MenuLibroEditar = new JMenuItem("Editar");
    JMenuItem MenuUsuarioEditar= new JMenuItem("Editar");
    JMenuItem MenuBibliotecaEditar= new JMenuItem("Editar");
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
        //this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.blue, 2));
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
        addJMenuItem(MenuLibroConsultar,"Consultar libros",ImgSearchBook);
        addJMenuItem(MenuLibroEditar,"Editar libros",ImgEditBook);
        //menu Prestamos
        MenuPrestamos.add(MenuPrestamoConsultar);
        JMenuColorAndFont(MenuPrestamos);
        addJMenuItem(MenuPrestamoConsultar,"Consultar prestamos",ImgSearch);
        addJMenuItem(MenuPrestamoRegistrar,"Registrar prestamos",ImgAdd);
        addJMenuItem(MenuPrestamoLiquidar,"Eliminar prestamos", ImgDelete);
        //menu Usuarios
        JMenuColorAndFont(MenuUsuarios);
        addJMenuItem(MenuUsuarioConsultar,"Consultar usuarios",ImgSearchUser);
        addJMenuItem(MenuUsuarioEditar,"Registrar usuarios",ImgEditUser);
        //menu biblioteca
        MenuBibliotecas.add(MenuBibliotecaConsultar);
        JMenuColorAndFont(MenuBibliotecas);
        addJMenuItem(MenuBibliotecaConsultar,"Consultar bibliotecas",ImgSearchLibrary);
        addJMenuItem(MenuBibliotecaEditar,"Registrar bibliotecas",ImgEditLibrary);
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
            MenuLibros.add(MenuLibroEditar);
            //accesos prestamos
            BarraMenu.add(MenuPrestamos);
            MenuPrestamos.add(MenuPrestamoConsultar);
            MenuPrestamos.add(MenuPrestamoRegistrar);
            MenuPrestamos.add(MenuPrestamoLiquidar);
            //accesos usuarios
            BarraMenu.add(MenuUsuarios);
            MenuUsuarios.add(MenuUsuarioConsultar);
            MenuUsuarios.add(MenuUsuarioEditar);
            //accesos bibliotecas
            BarraMenu.add(MenuBibliotecas);
            MenuBibliotecas.add(MenuBibliotecaConsultar);
            MenuBibliotecas.add(MenuBibliotecaEditar);
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
        }else if (me.getSource() == MenuLibroConsultar) {///////////////seccion libros
            VenConsultarLibros VCLibros = null;
            try {
                VCLibros = new VenConsultarLibros(conexion);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(Panel, "Error: "+e);
            }
            Panel.add(VCLibros);
        }else if (me.getSource()== MenuLibroEditar){
            VentanaEditarLibros VELibros = new VentanaEditarLibros();
            Panel.add(VELibros);
            //VentanaRegistraLibros VRLibros = new VentanaRegistraLibros();
            //Panel.add(VRLibros);
        }else if (me.getSource() == MenuPrestamoConsultar) {//////////////seccion prestamos
            VenConsultaPrestamos VCPrestamos = new VenConsultaPrestamos(conexion, Tipo, Usuario);
            Panel.add(VCPrestamos);
        }else if (me.getSource() == MenuPrestamoRegistrar) {
            VenRegistroPrestamos VRPrestamos = new VenRegistroPrestamos();
            Panel.add(VRPrestamos);
        }else if (me.getSource() == MenuPrestamoLiquidar) {

        }else if (me.getSource() == MenuUsuarioConsultar){///////////seccion usuarios
            VenConsultarUsuario VCUsuarios = new VenConsultarUsuario(conexion);
            Panel.add(VCUsuarios);
        }else if (me.getSource() == MenuUsuarioEditar){
            VentanaUsuarios VRUsuario = new VentanaUsuarios();
            Panel.add(VRUsuario);
        } else if (me.getSource() == MenuBibliotecaConsultar) {/////////////seccion bibliotecas
            VenConsultarBiblio VCBiblioteca = new VenConsultarBiblio(conexion);
            Panel.add(VCBiblioteca);
        }else if (me.getSource() == MenuBibliotecaEditar){
            VentanaBibliotecas VRBiblioteca = new VentanaBibliotecas();
            Panel.add(VRBiblioteca);
        } else if (me.getSource()==MenuArchioLogOut){
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
