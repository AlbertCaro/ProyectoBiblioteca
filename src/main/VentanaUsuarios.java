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

public class VentanaUsuarios extends InternalWindow implements KeyListener, ActionListener {
	private JTextField TxtCodigo = new JTextField();
	private JTextField TxtNombre = new JTextField();
	private JTextField TxtApPaterno = new JTextField();
	private JTextField TxtApMaterno = new JTextField();
	private JComboBox CbSexo = new JComboBox();
	private JTextField TxtCorreo = new JTextField();
	private JTextField TxtTelefono = new JTextField();
	private JTextField TxtUsuario = new JTextField();
	private JPasswordField TxtPass = new JPasswordField();
	private JPasswordField TxtPassConf = new JPasswordField();
	private JTextField TxtCargo = new JTextField();
	private JLabel LblTitulo = new JLabel("Registrar usuario.");
	private JLabel LblCodigo = new JLabel("Codigo");
	private JLabel LblNombre = new JLabel("Nombre");
	private JLabel LblSexo = new JLabel("Sexo");
	private JLabel LblCorreo = new JLabel("Correo");
	private JLabel LblTelefono = new JLabel("Telefono");
	private JLabel LblUsuario = new JLabel("Usuario");
	private JLabel LblPass = new JLabel("Contraseña");
	private JLabel LblTipo = new JLabel("Tipo");
	private JLabel LblEmpleado = new JLabel("Empleado");
	private JLabel LblCargo = new JLabel("Cargo");
	private JRadioButton RbNormalTipo = new JRadioButton("Normal");
	private JRadioButton RbAdminTipo = new JRadioButton("Administrador");
	private JRadioButton RbEmpleadoSi= new JRadioButton("Si");
	private JRadioButton RbEmpreadoNo = new JRadioButton("No");
	private ButtonGroup BgTipo = new ButtonGroup();
	private ButtonGroup BgEmpleado = new ButtonGroup();
	private JButton BtnRegistrar = new JButton("Registrar");

	public VentanaUsuarios() {
		this.setSize(380, 600);
		addTitleLabel(LblTitulo, this);
		addLabel(LblCodigo, 40,90, TxtCodigo, this);
		addTextField(TxtCodigo, 110, 40, 230, "Codigo",this);
		addLabel(LblNombre, 80,90, TxtNombre, this);
		addTextField(TxtNombre, 110, 80, 230, "Nombre",this);
		addTextField(TxtApPaterno, 20, 120, 155, "Apellido paterno",this);
		addTextField(TxtApMaterno, 185, 120, 155, "Apellido materno",this);
		addLabel(LblSexo, 160,90, CbSexo, this);
		addComboBox(CbSexo, 110, 160, 230, this);
		addLabel(LblCorreo, 200,90, TxtCorreo, this);
		addTextField(TxtCorreo, 110, 200, 230, "Correo",this);
		addLabel(LblTelefono, 240,90,TxtCorreo, this);
		addTextField(TxtTelefono, 110, 240, 230, "Telefono",this);
		addLabel(LblUsuario, 280,90,TxtUsuario, this);
		addTextField(TxtUsuario, 110, 280, 230, "Usuario",this);
		addLabel(LblPass, 320,90,TxtPass, this);
		addTextField(TxtPass, 110, 320, 230, "Contraseña",this);
		addTextField(TxtPassConf, 110, 360, 230, "Confirma contraseña",this);
		addLabel(LblTipo,10,400,null,this);
		addRadioButton(RbNormalTipo, 110, 400, BgTipo, this);
		addRadioButton(RbAdminTipo, 220, 400, BgTipo, this);
		addLabel(LblEmpleado,10,90,null,this);
		addRadioButton(RbEmpleadoSi, 110, 440, BgEmpleado, this);
		addRadioButton(RbEmpreadoNo, 220, 440, BgEmpleado, this);
		addLabel(LblCargo, 480,90,TxtCargo, this);
		addTextField(TxtCargo, 110, 480, 230, "Cargo",this);
		addButton(BtnRegistrar, 120, 520, this);
		addWindowProperties(this, "Agregar usuario");
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
