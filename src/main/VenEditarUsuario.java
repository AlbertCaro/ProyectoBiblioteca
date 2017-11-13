package main;

import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VenEditarUsuario extends InternalWindow implements KeyListener, ActionListener, WindowInterface, MouseListener {
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
	private JButton BLimpiar = new JButton("Limpiar Campos");
    //tabla
    private JTable Tabla = new JTable();
    private JScrollPane ScrollP = new JScrollPane(Tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private Object[][] FilaInicial = new Object[0][10];
    private Object ColumName[] = new Object[]{"Codigo","Usuario","Nombre","ApPaterno","ApMaterno","Correo","Telefono","Tipo","Sexo","Carrera","Universidad"};
    private DefaultTableModel Modelo = new DefaultTableModel(FilaInicial,ColumName);

	private JComboBox CbSexo = new JComboBox();
	private JComboBox CbCarrera = new JComboBox();
	private JComboBox CbUniversidad = new JComboBox();
	private JComboBox CbCombo = new JComboBox();

	private String Consulta = "SELECT Personas.Codigo, Usuarios.Usuario, Personas.Nombre, Personas.ApPaterno, Personas.ApMaterno, Personas.Correo, Personas.Telefono, Tipos.Tipo, Personas.Sexo, Carreras.Carrera, Universidades.Siglas FROM Personas INNER JOIN Usuarios ON Personas.Codigo = Usuarios.Codigo INNER JOIN Tipos ON Usuarios.idTipos = Tipos.idTipos INNER JOIN Carreras ON Personas.idCarreras = Carreras.idCarreras INNER JOIN Universidades ON Personas.idUniversidades = Universidades.idUniversidades";
	Conexion MiConexion;

	public VenEditarUsuario(Conexion MiConexion) {
		this.MiConexion = MiConexion;
		this.setSize(1050, 660);
		addTitleLabel(LblTitulo, this);
		llenarTabla();

        addTextField(TxtBuscar,30, 40,150,"Buscar",this);
        addComboBox(CbCombo,215,40,150,this);
        llenarComboBox2();;
		addLabel(LblCodigo, 660,20, TxtCodigo, this);
		addTextField(TxtCodigo, 750, 20, 230, "Código",this);
		addLabel(LblNombre, 660,60, TxtNombre, this);
		TxtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		addTextField(TxtNombre, 750, 60, 230, "Nombre(s)",this);
		addTextField(TxtApPaterno, 710, 100, 155, "Apellido paterno",this);
		addTextField(TxtApMaterno, 870, 100, 155, "Apellido materno",this);
		addLabel(LblSexo, 660,140, CbSexo, this);
		addComboBox(CbSexo, 750, 140, 250, this);
		CbSexo.addItem("Seleccionar...");
		llenarComboBox();
		addLabel(LblCarrera,660,180, CbCarrera,this);
		addComboBox(CbCarrera,750,180,250,this);
		CbCarrera.addItem("Seleccionar...");
		comboBoxCarr();
		addLabel(LblUniversidad,660,220, CbUniversidad,this);
		addComboBox(CbUniversidad,750,220,250,this);
		CbUniversidad.addItem("Seleccionar...");
		comboBoxUniv();
		addLabel(LblCorreo, 660,260, TxtCorreo, this);
		addTextField(TxtCorreo, 750, 260, 230, "Correo",this);
		addLabel(LblTelefono, 660,300,TxtCorreo, this);
		addTextField(TxtTelefono, 750, 300, 230, "Telefono",this);
		TxtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		addLabel(LblUsuario, 660,340,TxtUsuario, this);
		addTextField(TxtUsuario, 750, 340, 230, "Usuario",this);
		addLabel(LblPass, 660,380,TxtPass, this);
		addTextField(TxtPass, 750, 380, 230, "Contraseña",this);
		addTextField(TxtPassConf, 750, 420, 230, "Confirma contraseña",this);
		addLabel(LblTipo,660,460,null,this);
		addRadioButton(RbNormalTipo, 755, 460, BgTipo, this);
		addRadioButton(RbAdminTipo, 865, 460, BgTipo, this);
		RbNormalTipo.setSelected(true);
		addLabel(LblEmpleado,660,500,null,this);
		addRadioButton(RbEmpleadoSi, 755, 500, BgEmpleado, this);
		addRadioButton(RbEmpreadoNo, 865, 500, BgEmpleado, this);
		RbEmpreadoNo.setSelected(true);
		BgEmpleado.setSelected(RbEmpreadoNo.getModel(),false);

		addLabel(LblCargo, 660,540,TxtCargo, this);
		addTextField(TxtCargo, 750, 540, 230, "Cargo",this);
		TxtCargo.setEnabled(false);

		addButton(BRegistrar, 665, 580, "Registrar un nuevo usuario",this);
		addButton(BModificar,780,580, "Modificar o actualizar información de un usuario",this);
		BModificar.setEnabled(false);
		addButton(BEliminar,895,580,"Eliminar Usuario",this);
        addButton(BBuscar,180,40,"",this);
        addButton(BLimpiar,450,40,"Limpia los campos",this);
        BLimpiar.setSize(150,30);
        BBuscar.setSize(30,30);
        BBuscar.setIcon(ImgSearch);
        Tabla.addMouseListener(this);
		addWindowProperties(this, "Administrar usuarios");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BRegistrar) {
            if (TxtCodigo.getText().isEmpty() || TxtNombre.getText().isEmpty() || TxtApPaterno.getText().isEmpty() || TxtApMaterno.getText().isEmpty() || CbSexo.getSelectedItem().equals("Seleccionar...") ||
                    TxtCorreo.getText().isEmpty() || TxtTelefono.getText().isEmpty() || TxtUsuario.getText().isEmpty() || TxtPass.getPassword().length == 0 ||
                    TxtPassConf.getPassword().length == 0 || TxtCargo.getText().isEmpty() || CbCarrera.getSelectedItem().equals("Seleccionar...") || CbUniversidad.getSelectedItem().equals("Seleccionar...")) {
                JOptionPane.showMessageDialog(rootPane, "Aún quedan campos obligatorios vacíos.","Error de registro",1);
            }
			else if (TxtPass.getPassword().length < 5 ) {
				JOptionPane.showMessageDialog(rootPane, "Seguridad de la contraseña baja.");
			}
			else if (validarContrasena(TxtPass.getPassword(),TxtPassConf.getPassword())) {
				agregarUsuario();
				buscarUsuario();
			}
			else
				JOptionPane.showMessageDialog(rootPane,"Las contraseñas no coinciden.");
		}
		else if (e.getSource() == BEliminar) {
			if (TxtCodigo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(rootPane, "Cuadro de texto vacío.\nPor favor proporcione algún dato.");
				TxtCodigo.requestFocus();
			}
			else {
				try {
					PreparedStatement Notificar = MiConexion.getConexion().prepareCall(" SELECT FROM Personas WHERE codigo = ?");
					Notificar.setInt(1, Integer.parseInt(TxtCodigo.getText()));
					int opc = JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar toda la información de "+ TxtCodigo.getText() + "?", "CONFIRMAR...", 0, 1);
					if (opc == 0) {
						eliminarUsuario();
					}
					buscarUsuario();
				} catch (Exception ex){

				}
			}
		}
		else if (e.getSource()==BModificar){
			if (TxtCorreo.getText().isEmpty() || TxtTelefono.getText().isEmpty() || TxtUsuario.getText().isEmpty() || TxtPass.getPassword().length == 0 ||
					TxtPassConf.getPassword().length == 0) {
				JOptionPane.showMessageDialog(rootPane, "Aún quedan campos obligatorios vacíos.","Error de registro",1);
			}
			else if (TxtPass.getPassword().length < 5 ) {
				JOptionPane.showMessageDialog(rootPane, "Seguridad de la contraseña baja.");
			}
			else if (validarContrasena(TxtPass.getPassword(),TxtPassConf.getPassword())) {
				actualizarUsuario();
				habilitarTextF();
				buscarUsuario();
			}
			else {
				JOptionPane.showMessageDialog(rootPane, "Las contraseñas no coinciden.");
			}
		}
		else if (e.getSource() == BBuscar){
			if (TxtBuscar.getText().isEmpty()){
				JOptionPane.showMessageDialog(rootPane,"Cuadro de texto vacío");
				TxtBuscar.requestFocus();
			}else if (TxtBuscar.getText().toString().equals("*")) {
				limpiarTabla();
				llenarTabla(MiConexion, Consulta , Modelo , rootPane);
			}else {
				buscarUsuario();
			}
		}
		else if (e.getSource() == BLimpiar){
			limpiarCampos();
			BModificar.setEnabled(false);
			habilitarTextF();

		}
	}
	private boolean validarContrasena(char[] j1,char[] j2) {
		boolean valor = true;
		int puntero = 0;
		if (j1.length != j2.length){
			valor = false;
		}
		else{
			while((valor)&&(puntero < j1.length)){
				if (j1[puntero] != j2[puntero]){
					valor = false;
				}
				puntero++;
			}
		}
		return valor;
	}
	private void agregarUsuario(){
		try{
			PreparedStatement GuardarStm = MiConexion.getConexion().prepareCall(" INSERT INTO Personas (Codigo, Nombre, ApPaterno, ApMaterno, Sexo, Correo, Telefono, idCarreras, idUniversidades) VALUES (? , ?, ?, ?, ?, ? ,?, ?, ?)");
			PreparedStatement GuardarStm2 = MiConexion.getConexion().prepareCall(" INSERT INTO Usuarios (Codigo, Usuario, Pass, idTipos) VALUE (? , ? , ? , 1)");
			GuardarStm.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			GuardarStm.setString(2, TxtNombre.getText());
			GuardarStm.setString(3, TxtApPaterno.getText());
			GuardarStm.setString(4, TxtApMaterno.getText());
			GuardarStm.setString(5, (String)CbSexo.getSelectedItem());
			GuardarStm.setString(6, TxtCorreo.getText());
			GuardarStm.setString(7, TxtTelefono.getText());
			GuardarStm.setInt(8, CbCarrera.getSelectedIndex()+1);
			GuardarStm.setInt(9, CbUniversidad.getSelectedIndex()+1);
			GuardarStm2.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			GuardarStm2.setString(2, TxtUsuario.getText());
			GuardarStm2.setString(3, md5(new String(TxtPass.getPassword())));
			GuardarStm2.setString(4,BgTipo.getSelection().getActionCommand());
			if (BgEmpleado == null){
				PreparedStatement GuardarStm3 = MiConexion.getConexion().prepareCall(" INSERT INTO Empleado (Cargo, Codigo)  VALUE (? , ?)");
				GuardarStm3.setInt(2, Integer.parseInt(TxtCodigo.getText()));
				GuardarStm3.executeUpdate();
			}

			GuardarStm.executeUpdate();
			GuardarStm2.executeUpdate();
			JOptionPane.showMessageDialog(rootPane,"Usuario registrado correctamente","Exito",1);
			limpiarCampos();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "Registro Invalido: " + e);
		}
	}
	private void actualizarUsuario(){
		try{
			PreparedStatement ModificarStm = MiConexion.getConexion().prepareCall("UPDATE Personas SET Correo = ?, Telefono = ? WHERE codigo = "+TxtCodigo.getText());
			PreparedStatement ModificarStm2 = MiConexion.getConexion().prepareCall("UPDATE Usuarios SET Usuario = ?, Pass = ? WHERE codigo = "+TxtCodigo.getText());

			ModificarStm.setString(1, TxtCorreo.getText());
			ModificarStm.setString(2, TxtTelefono.getText());

			ModificarStm2.setString(1, TxtUsuario.getText());
			ModificarStm2.setString(2, md5(new String(TxtPass.getPassword())));
			ModificarStm.executeUpdate();
			ModificarStm2.executeUpdate();

			JOptionPane.showMessageDialog(rootPane,"Usuario actualizado correctamente","Exito",1);
			limpiarCampos();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "ERROR AL ACTUALIZAR : " + e);
		}
	}
	private void eliminarUsuario(){
		try{
			PreparedStatement BorrarStm = MiConexion.getConexion().prepareCall(" DELETE FROM Usuarios WHERE codigo = ? ");
			PreparedStatement BorrarStm2 = MiConexion.getConexion().prepareCall(" DELETE FROM Personas WHERE codigo = ?");
			BorrarStm.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			BorrarStm2.setInt(1, Integer.parseInt(TxtCodigo.getText()));
			BorrarStm.executeUpdate();
			BorrarStm2.executeUpdate();
			JOptionPane.showMessageDialog(rootPane,"Usuario con codigo: "+TxtCodigo.getText()+", eliminado correctamente.","Exito",1);
			limpiarCampos();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, "ERROR, REVISA: " + e);
		}
	}
	private void llenarComboBox(){
		CbSexo.addItem("Hombre");
		CbSexo.addItem("Mujer");
	}
	private void llenarComboBox2() {
		for (int i=0; i<ColumName.length; i++){
			CbCombo.addItem(ColumName[i].toString());
		}
	}
	private void comboBoxUniv() {
		try {
			PreparedStatement buscarStm = MiConexion.getConexion().prepareCall("SELECT idUniversidades , Siglas FROM Universidades");
			ResultSet RsBuscar = buscarStm.executeQuery();
			while (RsBuscar.next()){//mientras haya datos encontrados
				CbUniversidad.addItem(RsBuscar.getObject("Siglas").toString());
			}
		} catch (Exception e){
			JOptionPane.showConfirmDialog(rootPane, "Error: "+e);
		}
	}
	private void comboBoxCarr() {
		try {
			PreparedStatement buscarStm = MiConexion.getConexion().prepareCall("SELECT idCarreras, Carrera FROM Carreras");
			ResultSet RsBuscar = buscarStm.executeQuery();
			while (RsBuscar.next()){//mientras haya datos encontrados
				CbCarrera.addItem(RsBuscar.getObject("Carrera").toString());
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
		TxtUsuario.setText("");
		TxtPass.setText("");
		TxtPassConf.setText("");
		CbSexo.setSelectedItem("Seleccionar...");
		CbCarrera.setSelectedItem("Seleccionar...");
		CbUniversidad.setSelectedItem("Seleccionar...");
		TxtCodigo.requestFocus();
	}
	private void llenarTabla(){
		Tabla = new JTable(Modelo);
		Tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//se autoajusta
		Tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
		Tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
		Tabla.getTableHeader().setReorderingAllowed(false);//se ordena automaticamente
		llenarTabla(MiConexion,Consulta , Modelo, rootPane);
		this.add(Tabla);
		ScrollP.setBounds(30, 100, 600, 500);
		ScrollP.setViewportView(Tabla);
		this.add(ScrollP);
	}
	private void buscarUsuario() {
		limpiarTabla();
		if (CbCombo.getSelectedItem().toString().equals("Codigo")) {
			String SQL = new String(Consulta+" WHERE Personas." + CbCombo.getSelectedItem() + " LIKE '%" + TxtBuscar.getText().toString()) + "%' ORDER BY " + CbCombo.getSelectedItem();
			llenarTabla(MiConexion,SQL,Modelo,rootPane);
		}else {
			String SQL = new String(Consulta+" WHERE " + CbCombo.getSelectedItem() + " LIKE '%" + TxtBuscar.getText().toString()) + "%' order by " + CbCombo.getSelectedItem();
			llenarTabla(MiConexion,SQL,Modelo,rootPane);
		}
	}
	private void limpiarTabla() {
		Modelo.setRowCount(0);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == TxtBuscar){
			if (e.getKeyChar()==e.VK_ENTER)
				buscarUsuario();
		}
		else if (e.getSource() == BModificar) {
			if (e.getKeyChar() == e.VK_ENTER) {

			}
		}
		else if (e.getSource()== BEliminar){
			if (e.getKeyChar()==e.VK_ENTER) {
				int opc = JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar toda la información de " + TxtCodigo.getText() + "?", "CONFIRMAR...", 0, 1);
				if (opc == 0) {
					eliminarUsuario();
				}
			}
		}
		else if (e.getSource() == TxtNombre){
			if (e.getKeyChar()==e.VK_ENTER)
				TxtApPaterno.requestFocus();
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int filaSeleccionada = Tabla.getSelectedRow();
		TxtCodigo.setText(Modelo.getValueAt(filaSeleccionada,0).toString());
		TxtUsuario.setText(Modelo.getValueAt(filaSeleccionada,1).toString());
		TxtNombre.setText(Modelo.getValueAt(filaSeleccionada,2).toString());
		TxtApPaterno.setText(Modelo.getValueAt(filaSeleccionada,3).toString());
		TxtApMaterno.setText(Modelo.getValueAt(filaSeleccionada,4).toString());
		TxtCorreo.setText(Modelo.getValueAt(filaSeleccionada,5).toString());
		TxtTelefono.setText(Modelo.getValueAt(filaSeleccionada,6).toString());
		CbSexo.setSelectedItem(Modelo.getValueAt(filaSeleccionada,8));
		CbCarrera.setSelectedItem(Modelo.getValueAt(filaSeleccionada,9));
		CbUniversidad.setSelectedItem(Modelo.getValueAt(filaSeleccionada,10));
		BModificar.setEnabled(true);
		deshabilitarTextF();
	}
	public void deshabilitarTextF(){
		TxtCodigo.setEnabled(false);
		//TxtCodigo.setFont(new Font("Arial", Font.ITALIC,12));
		TxtCodigo.setDisabledTextColor(Color.blue);
		TxtNombre.setEnabled(false);
		TxtNombre.setDisabledTextColor(Color.blue);
		TxtApPaterno.setEnabled(false);
		TxtApPaterno.setDisabledTextColor(Color.blue);
		TxtApMaterno.setEnabled(false);
		TxtApMaterno.setDisabledTextColor(Color.blue);
		CbSexo.setEnabled(false);
		CbCarrera.setEnabled(false);
		CbUniversidad.setEnabled(false);
		BRegistrar.setEnabled(false);

	}
	public void habilitarTextF(){
		TxtCodigo.setEnabled(true);
		TxtCodigo.setBackground(Color.white);
		TxtNombre.setEnabled(true);
		TxtNombre.setBackground(Color.white);
		TxtApPaterno.setEnabled(true);
		TxtApPaterno.setBackground(Color.white);
		TxtApMaterno.setEnabled(true);
		TxtApMaterno.setBackground(Color.white);
		CbSexo.setEnabled(true);
		CbSexo.setBackground(Color.white);
		CbCarrera.setEnabled(true);
		CbCarrera.setBackground(Color.white);
		CbUniversidad.setEnabled(true);
		CbUniversidad.setBackground(Color.white);
		BRegistrar.setEnabled(true);
		//BModificar.setEnabled(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
