package main;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface WindowInterface {

    /**
     * Dentro de éste método se setean los valores de la consulta, es sobreescribible porque cada tabla y ventana tiene
     * valores distintos.
     * @param statement El PreparedStatement en el cual se setean los datos.
     * @param type El tipo de la consulta, aquí es dónde por medio de un if else se determinan los seteos para cada consulta
     * @return Se retorna el statement ya con los datos correspondientes para usarse en {@link InternalWindow#doQuery(Conexion, String, int, JRootPane)}
     *         y en todas las funciones relacionadas.
     * @throws NoSuchAlgorithmException Se lanza esta excepción que es catchada en el try catch, corresponde al método
     *                                  {@link InternalWindow#md5(String)}
     * @throws SQLException Otra excepción que es arrojada, esta corresponde al statement
     */
    PreparedStatement addStatementParams(PreparedStatement statement, int type) throws NoSuchAlgorithmException, SQLException;

    /**
     * En este método sobreescribible se define nuestro ArrayList para las validaciones, además que se agregan los componentes
     * pertenecientes al mismo.
     * @return Retona el ArrayList llenado para ser usado en {@link InternalWindow#validateForm(JComponent)}
     */
    ArrayList<JComponent> fillListTexts();

    /**
     * En esta función se llenan los componentes dependiendo de los resultados obtenidos en la consulta SELECT al buscar
     * los datos.
     * @param resultSet Se usa el result ser para llenar los componentes haciendo uso del getObject.
     * @throws SQLException Exepción arrojada por el resultSet.
     */
    void returnQueryResults(ResultSet resultSet) throws SQLException;

    /**
     * Método sobreescribible para vaciar los campos del formulario en caso de una inserción o modificación exitosa en
     * {@link InternalWindow#doQuery(Conexion, String, int, boolean, JRootPane)}
     */
    void cleanForm();
}
