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

public class VenEditarUsuario extends InternalWindow implements KeyListener, ActionListener {
    private ImageIcon ImgSearch = new ImageIcon(getClass().getResource("/images/windowMainIcons/search.png"));
	//TextField
	private JTextField TxtCodigo = new JTextField();
	private JTextField TxtNombre = new JTextField();
	private JTextField TxtApPaterno = new JTextField();
	private JTextField TxtApMaterno = new JTextField();
	private JTextField TxtCorreo = new JTextField();
	private JTextField TxtTelefono = new JTextField();
	private JTextField TxtUsuario = new JTextField();
	private JTextField TxtCargo = new JTextField();
	private JTextField TxtBuscar = new JTextField();
	//PasswordField
	private JPasswordField TxtPass = new JPasswordField();
	private JPasswordField TxtPassConf = new JPasswordField();
	//JLabels
	private JLabel LblTitulo = new JLabel("Editar Usuario");
	private JLabel LblCodigo = new JLabel("Código");
	private JLabel LblNombre = new JLabel("Nombre");
	private JLabel LblSexo = new JLabel("Sexo");
	private JLabel LblCorreo = new JLabel("Correo");
	private JLabel LblTelefono = new JLabel("Telefono");
	private JLabel LblUsuario = new JLabel("Usuario");
	private JLabel LblPass = new JLabel("Contraseña");
	private JLabel LblTipo = new JLabel("Tipo");
	private JLabel LblEmpleado = new JLabel("Empleado");
	private JLabel LblCargo = new JLabel("Cargo");
	//RadioButtons
	private JRadioButton RbNormalTipo = new JRadioButton("Normal");
	private JRadioButton RbAdminTipo = new JRadioButton("Administrador");
	private JRadioButton RbEmpleadoSi= new JRadioButton("Si");
	private JRadioButton RbEmpreadoNo = new JRadioButton("No");
	//
	private ButtonGroup BgTipo = new ButtonGroup();
	private ButtonGroup BgEmpleado = new ButtonGroup();
	//JButtons
	private JButton BRegistrar = new JButton("Registrar");
	private JButton BModificar = new JButton("Modificar");
	private JButton BEliminar = new JButton("Eliminar");
	private JButton BBuscar = new JButton();
    //tabla
    private JTable Tabla = new JTable();
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][7];
    private Object ColumName[] = new Object[]{"Código","Nombre","Correo","Telefono","Usuario","Tipo","Cargo"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);

	private JComboBox CbSexo = new JComboBox();

	public VenEditarUsuario() {
		this.setSize(1050, 600);
		addTitleLabel(LblTitulo, this);

        Tabla.setModel(Modelo);
        this.add(Tabla);
        ScrollP.setBounds(30, 100, 600, 350);
        ScrollP.setViewportView(Tabla);
        this.add(ScrollP);
        addTextField(TxtBuscar,30, 40,150,"Buscar",this);

		addLabel(LblCodigo, 660,40, TxtCodigo, this);
		addTextField(TxtCodigo, 750, 40, 230, "Código",this);

		addLabel(LblNombre, 660,80, TxtNombre, this);
		addTextField(TxtNombre, 750, 80, 230, "Nombre",this);
		addTextField(TxtApPaterno, 710, 120, 155, "Apellido paterno",this);
		addTextField(TxtApMaterno, 870, 120, 155, "Apellido materno",this);

		addLabel(LblSexo, 660,160, CbSexo, this);
		addComboBox(CbSexo, 750, 160, 230, this);

		addLabel(LblCorreo, 660,200, TxtCorreo, this);
		addTextField(TxtCorreo, 750, 200, 230, "Correo",this);

		addLabel(LblTelefono, 660,240,TxtCorreo, this);
		addTextField(TxtTelefono, 750, 240, 230, "Telefono",this);

		addLabel(LblUsuario, 660,280,TxtUsuario, this);
		addTextField(TxtUsuario, 750, 280, 230, "Usuario",this);

		addLabel(LblPass, 660,320,TxtPass, this);
		addTextField(TxtPass, 750, 320, 230, "Contraseña",this);
		addTextField(TxtPassConf, 750, 360, 230, "Confirma contraseña",this);

		addLabel(LblTipo,660,400,null,this);
		addRadioButton(RbNormalTipo, 755, 400, BgTipo, this);
		addRadioButton(RbAdminTipo, 865, 400, BgTipo, this);

		addLabel(LblEmpleado,660,440,null,this);
		addRadioButton(RbEmpleadoSi, 755, 440, BgEmpleado, this);
		addRadioButton(RbEmpreadoNo, 865, 440, BgEmpleado, this);

		addLabel(LblCargo, 660,480,TxtCargo, this);
		addTextField(TxtCargo, 750, 480, 230, "Cargo",this);

		addButton(BRegistrar, 665, 520, this);
		addButton(BModificar,780,520, this);
		addButton(BEliminar,895,520,this);
        addButton(BBuscar,180,40,this);
        BBuscar.setSize(30,30);
        BBuscar.setIcon(ImgSearch);
		addWindowProperties(this, "Administrar usuarios");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/// TODO Agregar los eventos correspondientes
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Agregar los eventos correspondientes
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Agregar los eventos correspondientes
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Agregar los eventos correspondientes
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
