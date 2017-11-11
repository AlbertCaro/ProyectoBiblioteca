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

public class VenEditarUsuario extends InternalWindow implements KeyListener, ActionListener, WindowInterface {
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
	private JLabel LblTitulo = new JLabel("Administrar Usuario");
	private JLabel LblCodigo = new JLabel("Código*");
	private JLabel LblNombre = new JLabel("Nombre*");
	private JLabel LblSexo = new JLabel("Sexo*");
	private JLabel LblCorreo = new JLabel("Correo*");
	private JLabel LblTelefono = new JLabel("Telefono*");
	private JLabel LblUsuario = new JLabel("Usuario*");
	private JLabel LblPass = new JLabel("Contraseña*");
	private JLabel LblTipo = new JLabel("Tipo*");
	private JLabel LblEmpleado = new JLabel("Empleado*");
	private JLabel LblCargo = new JLabel("Cargo*");
	private JLabel LblUniversidad = new JLabel("Centro*");
	private JLabel LblCarrera = new JLabel("Carrera*");
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
    private Object[][] FilaInicial = new Object[0][6];
    private Object ColumName[] = new Object[]{"Código","Nombre","Correo","Telefono","Usuario","Tipo"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);

	private JComboBox CbSexo = new JComboBox();
	private JComboBox CbCarrera = new JComboBox();
	private JComboBox CbUniversidad = new JComboBox();

	Conexion MiConexion;

	public VenEditarUsuario(Conexion MiConexion) {
		this.MiConexion = MiConexion;
		this.setSize(1050, 660);
		addTitleLabel(LblTitulo, this);
		llenarTabla();

        addTextField(TxtBuscar,30, 40,150,"Buscar",this);
		addLabel(LblCodigo, 660,20, TxtCodigo, this);
		addTextField(TxtCodigo, 750, 20, 230, "Código",this);
		addLabel(LblNombre, 660,60, TxtNombre, this);
		addTextField(TxtNombre, 750, 60, 230, "Nombre(s)",this);
		addTextField(TxtApPaterno, 710, 100, 155, "Apellido paterno",this);
		addTextField(TxtApMaterno, 870, 100, 155, "Apellido materno",this);
		addLabel(LblSexo, 660,140, CbSexo, this);
		addComboBox(CbSexo, 750, 140, 230, this);
		llenarComboBox();
		addLabel(LblCorreo, 660,180, TxtCorreo, this);
		addTextField(TxtCorreo, 750, 180, 230, "Correo",this);
		addLabel(LblTelefono, 660,220,TxtCorreo, this);
		addTextField(TxtTelefono, 750, 220, 230, "Telefono",this);
		addLabel(LblUsuario, 660,260,TxtUsuario, this);
		addTextField(TxtUsuario, 750, 260, 230, "Usuario",this);
		addLabel(LblPass, 660,300,TxtPass, this);
		addTextField(TxtPass, 750, 300, 230, "Contraseña",this);
		addTextField(TxtPassConf, 750, 340, 230, "Confirma contraseña",this);
		addLabel(LblTipo,660,380,null,this);
		addRadioButton(RbNormalTipo, 755, 380, BgTipo, this);
		addRadioButton(RbAdminTipo, 865, 380, BgTipo, this);
		addLabel(LblEmpleado,660,420,null,this);
		addRadioButton(RbEmpleadoSi, 755, 420, BgEmpleado, this);
		addRadioButton(RbEmpreadoNo, 865, 420, BgEmpleado, this);
		addLabel(LblCargo, 660,460,TxtCargo, this);
		addTextField(TxtCargo, 750, 460, 230, "Cargo",this);
		addLabel(LblCarrera,660,500, CbCarrera,this);
		addComboBox(CbCarrera,750,500,230,this);
		CbCarrera.addItem("");
		llenarCampoCarre();
		addLabel(LblUniversidad,660,540, CbUniversidad,this);
		addComboBox(CbUniversidad,750,540,230,this);
		CbUniversidad.addItem("");
		llenarCampoUni();

		addButton(BRegistrar, 665, 580, "",this);
		addButton(BModificar,780,580, "",this);
		addButton(BEliminar,895,580,"",this);
        addButton(BBuscar,180,40,"",this);
        BBuscar.setSize(30,30);
        BBuscar.setIcon(ImgSearch);
		addWindowProperties(this, "Administrar usuarios");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BRegistrar) {
            if (TxtCodigo.getText().isEmpty() || TxtNombre.getText().isEmpty() || TxtApPaterno.getText().isEmpty() || TxtApMaterno.getText().isEmpty() ||
                    TxtCorreo.getText().isEmpty() || TxtTelefono.getText().isEmpty() || TxtUsuario.getText().isEmpty() || TxtPass.getPassword().length == 0 ||
                    TxtPassConf.getPassword().length == 0) {
                JOptionPane.showMessageDialog(rootPane, "NO DEJES CAMPOS VACIOS");
            }else if (TxtPass.getPassword().length < 5 ){
                JOptionPane.showMessageDialog(rootPane,"Contraseña muy corta");
            }
            else {
            	InsertarUsuario();
			}
		}
	}
	private void InsertarUsuario(){
		try{
			PreparedStatement GuardarStm = MiConexion.getConexion().prepareCall(" INSERT INTO Personas (Codigo, Nombre, ApPaterno, ApMaterno, Sexo, Correo, Telefono, idCarreras, idUniversidades) VALUES (? , ?, ?, ?, ?, ? ,?, 1, 1)");
			PreparedStatement GuardarStm2 = MiConexion.getConexion().prepareCall(" INSERT INTO Usuarios (Codigo, Usuario, Pass, idTipos) VALUE (? , ? , ? , 1)");
			GuardarStm.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			GuardarStm.setString(2, TxtNombre.getText());
			GuardarStm.setString(3, TxtApPaterno.getText());
			GuardarStm.setString(4, TxtApMaterno.getText());
			GuardarStm.setString(5, (String)CbSexo.getSelectedItem());
			GuardarStm.setString(6, TxtCorreo.getText());
			GuardarStm.setString(7, TxtTelefono.getText());
			GuardarStm2.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			GuardarStm2.setString(2, TxtUsuario.getText());
			GuardarStm2.setString(3, TxtPass.getPassword().toString());

			GuardarStm.executeUpdate();
			GuardarStm2.executeUpdate();
			JOptionPane.showMessageDialog(rootPane,"Usuario Registrado");
			limpiarCampos();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "ERROR DE REGISTRO: " + e);
		}
	}
	private void llenarComboBox(){
		CbSexo.addItem("Hombre");
		CbSexo.addItem("Mujer");
	}
	public class ObtenerId {
		public int id;
		public String name;

		public ObtenerId(int id,String name) {
			this.id=id;
			this.name=name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public void setId(int id) {
			this.id=id;
		}
		public void setName(String name) {
			this.name=name;
		}
		//Esto es lo que hace que el JComboBox se visualize correctamente
		@Override
		public String toString() {
			return id + " " + name;
		}

	}
	private void llenarCampoUni() {
		try {
			PreparedStatement buscarStm = MiConexion.getConexion().prepareCall("SELECT idUniversidades , Universidad FROM Universidades");
			ResultSet RsBuscar = buscarStm.executeQuery();
			while (RsBuscar.next()){//mientras haya datos encontrados
				ObtenerId uni = new ObtenerId(RsBuscar.getInt(1),RsBuscar.getString(2));
				CbUniversidad.addItem(uni);
			}
		} catch (Exception e){
			JOptionPane.showConfirmDialog(rootPane, "Error: "+e);
		}
	}
	private void llenarCampoCarre() {
		try {
			PreparedStatement buscarStm = MiConexion.getConexion().prepareCall("SELECT idCarreras, Carrera FROM Carreras");
			ResultSet RsBuscar = buscarStm.executeQuery();
			while (RsBuscar.next()){//mientras haya datos encontrados
				ObtenerId carrera = new ObtenerId(RsBuscar.getInt(1),RsBuscar.getString(2));
				CbCarrera.addItem(carrera);
			}
		} catch (Exception e){
			JOptionPane.showConfirmDialog(rootPane, "Error: "+e);
		}
	}
	private void limpiarCampos(){
		TxtCodigo.setText("");
		TxtNombre.setText("");
		TxtApPaterno.setText("");
		TxtApMaterno.setText("");
		TxtCorreo.setText("");
		TxtTelefono.setText("");
		TxtCodigo.requestFocus();
	}
	private void llenarTabla(){
		Tabla = new JTable(Modelo);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//se autoajusta
		Tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
		Tabla.getTableHeader().setReorderingAllowed(false);//se ordena automaticamente
		llenarTabla(MiConexion,"SELECT Personas.Codigo, Personas.Nombre, Personas.Correo, Personas.Telefono, Usuarios.Usuario, Tipos.Tipo FROM Personas INNER JOIN Usuarios ON Personas.Codigo = Usuarios.Codigo INNER JOIN Tipos ON Usuarios.idTipos = Tipos.idTipos ", Modelo, rootPane);
		this.add(Tabla);
		ScrollP.setBounds(30, 100, 600, 500);
		ScrollP.setViewportView(Tabla);
		this.add(ScrollP);
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
