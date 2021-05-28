package main;

import view.MainForm;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        UIManager.put("OptionPane.background", new Color(219,255,217));
        UIManager.put("OptionPane.foreground", new Color(0, 181, 51));
        UIManager.put("OptionPane.messageForeground", new Color(0, 181, 51));
        UIManager.put("OptionPane.messageFont", new Font("Comic Sans MS", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Comic Sans MS", Font.PLAIN, 18));

        UIManager.put("Panel.background", new Color(219,255,217));
        UIManager.put("Panel.foreground", new Color(0, 181, 51));
        UIManager.put("Panel.font", new Font("Comic Sans MS", Font.PLAIN, 16));

        UIManager.put("Label.background", new Color(219,255,217));
        UIManager.put("Label.foreground", new Color(0, 181, 51));
        UIManager.put("Label.font", new Font("Comic Sans MS", Font.PLAIN, 16));

        UIManager.put("Button.background", new Color(0, 181, 51));
        UIManager.put("Button.foreground", new Color(219,255,217));
        UIManager.put("Button.font", new Font("Comic Sans MS", Font.BOLD, 18));

        UIManager.put("Table.font", new Font("Comic Sans MS", Font.PLAIN, 16));
        UIManager.put("Table.background", new Color(219,255,217));
        UIManager.put("Table.gridColor", new Color(0, 181, 51));

        UIManager.put("TableHeader.background", new Color(0, 181, 51));
        UIManager.put("TableHeader.foreground", new Color(219,255,217));
        UIManager.put("TableHeader.font", new Font("Comic Sans MS", Font.PLAIN, 16));

        UIManager.put("ToolBar.background", new Color(219,255,217));

        UIManager.put("CheckBox.foreground", new Color(0, 181, 51));
        UIManager.put("CheckBox.background", new Color(219,255,217));
        UIManager.put("CheckBox.font", new Font("Comic Sans MS", Font.PLAIN, 18));

        UIManager.put("RadioButton.foreground", new Color(0, 181, 51));
        UIManager.put("RadioButton.background", new Color(219,255,217));
        UIManager.put("RadioButton.font", new Font("Comic Sans MS", Font.BOLD, 18));

        UIManager.put("ComboBox.foreground", new Color(0, 181, 51));
        UIManager.put("ComboBox.background", new Color(219,255,217));
        UIManager.put("ComboBox.font", new Font("Comic Sans MS", Font.PLAIN, 16));
        UIManager.put("ComboBox.buttonBackground", new Color(0, 181, 51));

        UIManager.put("TextField.foreground", new Color(0, 181, 51));
        UIManager.put("TextField.background", new Color(219,255,217));
        UIManager.put("TextField.font", new Font("Comic Sans MS", Font.PLAIN, 18));

        UIManager.put("FormattedTextField.foreground", new Color(0, 181, 51));
        UIManager.put("FormattedTextField.background", new Color(219,255,217));
        UIManager.put("FormattedTextField.font", new Font("Comic Sans MS", Font.PLAIN, 18));

//       CheckBox.background

        new MainForm();
    }
}
