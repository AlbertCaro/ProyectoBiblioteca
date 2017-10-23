package main;
/*
* Métodos génericos para el ahorro de líneas y tiempo.
* Creado por Alberto Caro Navarro el 21 de Octubre de 2017 a las 12:16 pm
* En constante actualización
*
* Propiedad de Dynamite Developers (DynaDevs)
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public abstract class GenericMethods {
    //Se agregó sólo esta constante ya que es la única que se repite
    private final static int COMPONENTS_HEIGHT = 30;
	public static void addTitleLabel(JLabel label, JInternalFrame Frame) {
        label.setFont(new Font("Arial", Font.BOLD,18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(10, 10, 360, 20);
        Frame.add(label);
	}
	
	public static void addLabel(JLabel label, int y, JComponent component, JInternalFrame Frame) {
        label.setBounds(20, y, 150, COMPONENTS_HEIGHT);
        label.setLabelFor(component);
        Frame.add(label);
    }
	
	public static void addTextField(JTextField textField, int x, int y, int width, JInternalFrame Frame) {
        textField.setBounds(x, y, width, COMPONENTS_HEIGHT);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.addKeyListener((KeyListener) Frame);
        Frame.add(textField);
    }
	
	public static void addButton(JButton button, int x, int y, JInternalFrame Frame) {
        button.setBounds(x, y, 100, COMPONENTS_HEIGHT);
        button.addActionListener((ActionListener) Frame);
        button.addKeyListener((KeyListener) Frame);
        Frame.add(button);
    }
	
	public static void addRadioButton(JRadioButton radio, int x, int y, ButtonGroup group, JInternalFrame Frame) {
	    radio.setBounds(x, y, 110, COMPONENTS_HEIGHT);
		group.add(radio);
        Frame.add(radio);
	}

	public static void addComboBox(JComboBox comboBox, int x, int y, int width, JInternalFrame Frame) {
	    comboBox.setBounds(x, y, width, COMPONENTS_HEIGHT);
        Frame.add(comboBox);
    }

    public static void addWindowProperties(JInternalFrame Frame, String Title) {
        Frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Frame.setTitle(Title);
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setMaximizable(true);
        Frame.setClosable(true);
        Frame.setVisible(true);
    }
}
