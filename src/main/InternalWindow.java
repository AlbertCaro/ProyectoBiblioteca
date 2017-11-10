package main;
/**
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

abstract class InternalWindow extends JInternalFrame implements WindowInterface {
    //Se agregaron sólo estas constantes ya que son las únicas que se repiten
    private final static int COMPONENTS_HEIGHT = 30;
    private final static Color BACKGROUND_COLOR = new java.awt.Color(168, 220, 255);
    private final static Color BACKGROUND_COLOR_BUTTON = new java.awt.Color(53, 81, 181);
    private final static Color FOREGROUND_COLOR_BUTTON = new java.awt.Color(255,255,255);

    /**
     * Método para añadir un título a la ventana con su formato correspondiente.
     * @param label Es el componente que será establecido como título
     * @param Frame El JInternalFrame en el que será añadido el label.
     */
    void addTitleLabel(JLabel label, JInternalFrame Frame) {
        label.setFont(new Font("Arial", Font.BOLD,18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(10, 10, 360, 20);
        Frame.add(label);
    }

    /**
     * Método sobrecargado que permite añadir los labels a la ventana.
     * @param label El componente a ser añadido
     * @param x Posición en el eje x
     * @param y Posición en el eje y
     * @param width Tamaño del largo del componente
     * @param component Componente al que pertenecerá el label
     * @param Frame Ventana en la que será añadido el label
     */
    void addLabel(JLabel label, int x, int y, int width, JComponent component, JInternalFrame Frame) {
        label.setBounds(x, y, width, COMPONENTS_HEIGHT);
        label.setLabelFor(component);
        Frame.add(label);
    }

    /**
     * Método sobrecargado el cual asume la posición del Label y su tamaño para centrarlo y ubicarlo al lado izquierdo
     * del componente.
     */
    void addLabel(JLabel label, int y, JComponent component, JInternalFrame Frame) {
        addLabel(label, 20, y, 150, component, Frame);
    }

    /**
     * Método sobrecargado que sólo asume el largo del label.
     */
    void addLabel(JLabel label, int x, int y, JComponent component, JInternalFrame Frame) {
        addLabel(label, x, y, 150, component, Frame);
    }

    /**
     * Método que añade el Textfield a la ventana
     * @param textField Es el componente a añadir
     * @param prompt El hint o placeholder que se pone dentro de cada textField (se usó la clase de Rob Camick para
     *               llevar a cabo esto. (https://tips4java.wordpress.com/2009/11/29/text-prompt/)
     * @param Frame Ventana a la que se añadirá el textField.
     */
    void addTextField(JTextField textField, int x, int y, int width, String prompt, JInternalFrame Frame) {
        TextPrompt textPrompt = new TextPrompt(prompt, textField);
        textPrompt.changeAlpha((float) 0.7);
        textField.setBounds(x, y, width, COMPONENTS_HEIGHT);
        textField.addKeyListener((KeyListener) Frame);
        colorComponent(textField, Color.WHITE, Color.BLACK, Color.BLACK);
        Frame.add(textField);
    }

    /**
     * Este método recibe un boton para posteriormente añadir sus características
     * @param button Boton
     * @param x Posición en el eje x
     * @param y Posición en el eje y
     * @param Frame Ventana en dónde será añadido
     */
    void addButton(JButton button, int x, int y, String TipText, JInternalFrame Frame) {
        button.setBounds(x, y, 100, COMPONENTS_HEIGHT);
        button.setBackground(BACKGROUND_COLOR_BUTTON);
        button.setFont(new Font("arial", 1, 14));
        button.setForeground(FOREGROUND_COLOR_BUTTON);
        button.addActionListener((ActionListener) Frame);
        button.addKeyListener((KeyListener) Frame);
        button.setToolTipText(TipText);
        Frame.add(button);
    }

    /**
     * Este método recibe un radio button para añadirle sus características y colocarlo en el frame
     * @param radio Radio button a ser añadido
     * @param x Posición en el eje x
     * @param y Posición en el eje y
     * @param group ButtonGroup al cual pertenece el radio button
     * @param Frame Ventana
     */
    void addRadioButton(JRadioButton radio, int x, int y, ButtonGroup group, JInternalFrame Frame) {
        radio.setBounds(x, y, 110, COMPONENTS_HEIGHT);
        group.add(radio);
        Frame.add(radio);
    }

    /**
     * Método que añade los combo box a la ventana.
     * @param comboBox El componente que se va a añadir
     * @param x Posición en el eje x
     * @param y Posición en el eje y
     * @param width Ancho del componente
     * @param Frame Ventana
     */
    void addComboBox(JComboBox comboBox, int x, int y, int width, JInternalFrame Frame) {
        comboBox.setBounds(x, y, width, COMPONENTS_HEIGHT);
        colorComponent(comboBox, Color.WHITE, Color.BLACK, Color.BLACK);
        comboBox.addActionListener((ActionListener) Frame);
        comboBox.addKeyListener((KeyListener) Frame);
        Frame.add(comboBox);
    }

    /**
     * Método que toma la ventana y le asigna sus características
     * @param Frame Ventana
     * @param Title Título de la ventana
     */
    void addWindowProperties(JInternalFrame Frame, String Title) {
        Frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Frame.setBackground(BACKGROUND_COLOR);
        Frame.setTitle(Title);
        Frame.setLayout(null);
        Frame.setResizable(false);
        Frame.setMaximizable(false);
        Frame.setClosable(true);
        Frame.setVisible(true);
    }

    /**
     * Método que encripta una cadena a md5.
     * @param string Cadena a encriptar.
     * @return La cadena convertida a md5.
     */
    String md5(String string) throws NoSuchAlgorithmException {
        MessageDigest MD = MessageDigest.getInstance("MD5");
        MD.update(string.getBytes(),0, string.length());
        return new BigInteger(1, MD.digest()).toString(16);
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
        for (JComponent component : arrayList) {
            result = validateForm(component);
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
     * Este metodo es para llenar una tabla con una consulta ResultSet, y despues ser vizualizada
     *@param MiConexion la conexion que se necesita para la consulta
     *@param //BuscarStm consulta que se va a reealizar para vizualisar en la tabla
     *@param CadenaSQL String con la consulta
     *@param Modelo DefaultTableModel para el modelo de la tabla
     *@param rootPane panel donde se agregara el mensaje de error en caso que exista un error
     */
    void llenarTabla(Conexion MiConexion,String CadenaSQL, DefaultTableModel Modelo, JRootPane rootPane){
        try{
            PreparedStatement BuscarStm = MiConexion.getConexion().prepareCall(CadenaSQL);
            ResultSet RSBuscar = BuscarStm.executeQuery();
            //metadatos sirve para obtener informacion de la consulta como nombre de las columnas y de la tabla
            ResultSetMetaData RsMd = RSBuscar.getMetaData();
            int numeroDeColumnas = RsMd.getColumnCount();
            while (RSBuscar.next()){
                Object Fila[] = new Object[numeroDeColumnas];
                for (int i= 0; i < Fila.length; i++){
                    Fila[i] = RSBuscar.getObject(i+1);
                }
                Modelo.addRow(Fila);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error: "+e);
        }
    }
}
