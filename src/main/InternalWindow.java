package main;
/*
* Métodos génericos para el ahorro de líneas y tiempo.
* Creado por Alberto Caro Navarro el 21 de Octubre de 2017 a las 12:16 pm
* En constante actualización
*
* Propiedad de Dynamite Developers (DynaDevs)
*/

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

abstract class InternalWindow extends JInternalFrame {
    //Se agregó sólo esta constante ya que es la única que se repite
    private final static int COMPONENTS_HEIGHT = 30;
    private final static Color BACKGROUND_COLOR = new java.awt.Color(254,223,168);

	static void addTitleLabel(JLabel label, JInternalFrame Frame) {
        label.setFont(new Font("Arial", Font.BOLD,18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(10, 10, 360, 20);
        Frame.add(label);
	}
	
	static void addLabel(JLabel label, int y, int width, JComponent component, JInternalFrame Frame) {
        label.setLabelFor(component);
        addLabel(label, 10, y, width, Frame);
    }

    static void addLabel(JLabel label, int x, int y, int width, JComponent component, JInternalFrame Frame) {
        label.setLabelFor(component);
        addLabel(label, x, y, width, Frame);
    }

    static void addLabel(JLabel label, int x, int y, int width, JInternalFrame Frame) {
        label.setBounds(x, y, width, COMPONENTS_HEIGHT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Frame.add(label);
    }
	
	static void addTextField(JTextField textField, int x, int y, int width, String prompt, JInternalFrame Frame) {
        TextPrompt textPrompt = new TextPrompt(prompt, textField);
        textPrompt.changeAlpha((float) 0.7);
        textField.setBounds(x, y, width, COMPONENTS_HEIGHT);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.addKeyListener((KeyListener) Frame);
        Frame.add(textField);
    }
	
	static void addButton(JButton button, int x, int y, JInternalFrame Frame) {
        button.setBounds(x, y, 100, COMPONENTS_HEIGHT);
        button.addActionListener((ActionListener) Frame);
        button.addKeyListener((KeyListener) Frame);
        Frame.add(button);
    }
	
	static void addRadioButton(JRadioButton radio, int x, int y, ButtonGroup group, JInternalFrame Frame) {
	    radio.setBounds(x, y, 110, COMPONENTS_HEIGHT);
		group.add(radio);
        Frame.add(radio);
	}

	static void addComboBox(JComboBox comboBox, int x, int y, int width, JInternalFrame Frame) {
	    comboBox.setBounds(x, y, width, COMPONENTS_HEIGHT);
        Frame.add(comboBox);
    }

    static void addWindowProperties(JInternalFrame Frame, String Title) {
        Frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Frame.setTitle(Title);
        Frame.getContentPane().setBackground(BACKGROUND_COLOR);
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setMaximizable(true);
        Frame.setClosable(true);
        Frame.setVisible(true);
    }

    static String md5(String Password) throws NoSuchAlgorithmException {
        MessageDigest Md = MessageDigest.getInstance("MD5");
        Md.update(Password.getBytes(),0, Password.length());
        return new BigInteger(1, Md.digest()).toString(16);
    }

    /**
     * Esta función está diseñada para llevar a cabo las consultas.
     * @param conexion Obviamente representa la conexión a la base de datos.
     * @param SQL Especifica la consulta en SQL a realizar.
     * @param type Determina el tipo de consulta a realizar para así setear los datos en el método sobreescribible
     *             {@link #addStatementParams(PreparedStatement, int)} ya que también es un párametro de dicho método
     * @param show Esta bandera permite retornar datos a las entradas en caso de que sea una consulta que no modifique
     *             la base de datos. Se establecen los datos de las entradas en {@link #returnQueryResults(ResultSet)}
     * @param rootPane Se utiliza esta variable para ubicar el JOptionPane de error en la ventana que corresponde.
     * @return Se retorna un boleano el cual es usado para saber si se realizó la modificación de datos, o no, al igual
     * que permite saber si se encontró un registro o no.
     */
    boolean doQuery(Conexion conexion, String SQL, int type, boolean show, JRootPane rootPane) {
        boolean result = false;
        try {
            PreparedStatement statement = conexion.getConexion().prepareStatement(SQL);
            statement = addStatementParams(statement, type);
            if(type > 0) {
                statement.executeUpdate();
                result = true;
            }
            else {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (show)
                        returnQueryResults(resultSet);
                    result = true;
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(rootPane, "ERROR: "+e);
            result = false;
        }
        return result;
    }

    /**
     * Método sobrecargado que ya asume que no se va a retornar datos a las entradas, se creó con la finalidad de no
     * utilizar parametros inecesarios en consultas para determinar si insertar o no, o también en la misma inserción.
     */
    boolean doQuery(Conexion conexion, String SQL, int type, JRootPane rootPane) {
        return doQuery(conexion, SQL, type,false, rootPane);
    }

    /**
     * Este método realiza primero busca si existe un registro en la base de datos, para determinar si se hará la
     * modificación o no.
     * @param searchSQL Consulta SELECT que determina si se encontró el registro.
     * @param actionSQL Consulta de modificación a la base de datos.
     * @param MssgSuccess Debido a que el mensaje cambia dependiendo del la consulta que se realice, se una esta cadena para
     *                    asignarle su respectivo texto al JOptionPane.
     * @param MssgDoesntFind Lo que la anterior, pero para cuando no se encuentra el registro.
     */
    void findDoQuery(Conexion conexion, String searchSQL, String actionSQL, int type, String MssgSuccess, String MssgDoesntFind) {
        if (doQuery(conexion, searchSQL, 0, rootPane)) {
            if (doQuery(conexion, actionSQL, type, rootPane)) {
                JOptionPane.showMessageDialog(rootPane, MssgSuccess);
                cleanForm();
            }
        } else
            JOptionPane.showMessageDialog(rootPane, MssgDoesntFind);
    }

    /**
     * Se podría decir que es la contraparte del método anterior. En consultas en las cuales es necesario que el
     * registro no exista para poder hacer la modificación se aplica esta función.
     */
    void dontFindDoQuery(Conexion conexion, String searchSQL, String actionSQL, String MssgSucces, String MssgDoesntFind) {
        if (!doQuery(conexion, searchSQL,0, rootPane)) {
            if (doQuery(conexion, actionSQL, 1, rootPane)) {
                JOptionPane.showMessageDialog(rootPane, MssgSucces);
                cleanForm();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, MssgDoesntFind);
        }
    }

    /**
     * Método el cual aplica el color especificado a los objetos que heredan de JComponent.
     * @param component Es el objeto a aplicar el color, se usa el polimorfismo para aplicarle los atributos.
     * @param Background Color que corresponde al background del componente.
     * @param Foreground Color correspondiente a la letra del componente.
     * @param Border Color para los bordes del componente.
     */
    void colorComponent(JComponent component, Color Background, Color Foreground, Color Border) {
        component.setBackground(Background);
        component.setForeground(Foreground);
        component.setBorder(BorderFactory.createLineBorder(Border));
    }

    /**
     * Este método sobrecargado asume el color de los componentes y solo requiere el componente.
     */
    void colorComponent(JComponent component) {
        colorComponent(component, Color.WHITE, Color.BLACK, Color.BLACK);
    }

    /**
     * Método para colorear de rojos los componentes cuando están vacíos.
     */
    void colorVoidComponents(JComponent component) {
        colorComponent(component, Color.PINK, Color.BLACK, Color.RED);
    }

    /**
     * Este método es el encargado de validar todos las entradas. Las entradas son almacenadas en un ArrayList de
     * JComponents, usando el método sobreescribible {@link #fillListTexts()} para su llenado. Hace uso del método
     * {@link #validateForm(JComponent)} para hacer las validaciones de cada componente individualmente.
     * @return Se retorna un boleano para especificar si pasó la validación o no.
     */
    boolean validateForm(JRootPane rootPane) {
        ArrayList<JComponent> arrayList = fillListTexts();
        boolean result = true;
        for (JComponent anArrayList : arrayList) {
            result = validateForm(anArrayList);
        }
        if (!result)
            JOptionPane.showMessageDialog(rootPane, "Ha dejado campo(s) vacío(s)");
        return result;
    }

    /**
     * Este método valida solo un campo de manera individual, está diseñado para evitar la creación de un ArrayList
     * cuando sólo se usa un componente a validar, también usa {@link #validateForm(JComponent)} para realizar las
     * validaciones.
     */
    boolean validateForm(JComponent component, String Mssg) {
        boolean result = validateForm(component);
        if (!result)
            JOptionPane.showMessageDialog(rootPane, Mssg);
        return result;
    }

    /**
     * Este método es el que utilizan los dos métodos anteriores, recibe un componente para identificar su instancia
     * para posteriormente obtener el texto, validarlo y añadirle el color rojo en caso de que no cumpla dicha validación
     * @return Retorna un boleano que especifica si pasó o no la validación.
     */
    boolean validateForm(JComponent component) {
        boolean result = true;
        if (component instanceof JTextField) {
            if (((JTextField) component).getText().isEmpty()) {
                colorVoidComponents(component);
                result = false;
            }
        } else if (component instanceof JComboBox) {
            if (((JComboBox) component).getSelectedItem() == "Seleccionar...") {
                colorVoidComponents(component);
                result = false;
            }
        }
        return result;
    }

    /**
     * Dentro de éste método se setean los valores de la consulta, es sobreescribible porque cada tabla y ventana tiene
     * valores distintos.
     * @param statement El PreparedStatement en el cual se setean los datos.
     * @param type El tipo de la consulta, aquí es dónde por medio de un if else se determinan los seteos para cada consulta
     * @return Se retorna el statement ya con los datos correspondientes para usarse en {@link #doQuery(Conexion, String, int, JRootPane)}
     *         y en todas las funciones relacionadas.
     * @throws NoSuchAlgorithmException Se lanza esta excepción que es catchada en el try catch, corresponde al método
     *                                  {@link #md5(String)}
     * @throws SQLException Otra excepción que es arrojada, esta corresponde al statement
     */
    public abstract PreparedStatement addStatementParams(PreparedStatement statement, int type) throws NoSuchAlgorithmException, SQLException;

    /**
     * En este método sobreescribible se define nuestro ArrayList para las validaciones, además que se agregan los componentes
     * pertenecientes al mismo.
     * @return Retona el ArrayList llenado para ser usado en {@link #validateForm(JComponent)}
     */
    public abstract ArrayList<JComponent> fillListTexts();

    /**
     * En esta función se llenan los componentes dependiendo de los resultados obtenidos en la consulta SELECT al buscar
     * los datos.
     * @param resultSet Se usa el result ser para llenar los componentes haciendo uso del getObject.
     * @throws SQLException Exepción arrojada por el resultSet.
     */
    public abstract void returnQueryResults(ResultSet resultSet) throws SQLException;

    /**
     * Método sobreescribible para vaciar los campos del formulario en caso de una inserción o modificación exitosa en
     * {@link #doQuery(Conexion, String, int, boolean, JRootPane)}
     */
    public abstract void cleanForm();
}
