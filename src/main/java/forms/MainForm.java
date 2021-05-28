package forms;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import server.DBConnection;
import server.Schema;
import model.EntityTableProperties;
import forms.tables.TableViewForm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.*;

public class MainForm extends JFrame {
    private void createUIComponents() {
        // place custom component creation code here
        tabbedPane = new JTabbedPaneWithCloseIcons();
    }

    public enum Settings {ADDRESS, LOGIN, PASSWORD, SCHEMA}

    private JButton openTableButton;
    private JPanel mainPanel;
    private JButton settingsButton;
    private JToolBar mainToolBar;
    private JTabbedPane tabbedPane;
    private JComboBox<String> tableBox;
    private JButton helpButton;
    public JButton loadDataButton;
    public JToolBar tablesToolBar;
    private final JFrame settingsForm;
    private final JFrame helpForm;
    public static final Map<Settings, String> settingsMap = new HashMap<>();

    public MainForm() {
        $$$setupUI$$$();
        this.setTitle("База данных зоопарка");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    // завершаем соединение при закрытии приложения
                    DBConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        initTableBox();
        initButtonsListeners();

        this.pack();
        FrameLocation.centerFrameLocation(this);
        this.setVisible(false);
        helpForm = new HelpForm();
        settingsForm = new LoginForm(settingsMap, this);
        loadDataButton.setVisible(false);
        tablesToolBar.setVisible(false);
        settingsButton.doClick();
    }

    private void initTableBox() {
        final DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        ArrayList<String> tableNames =
                new ArrayList<>(EntityTableProperties.tablePropertiesMap.keySet());
        tableNames.sort(String::compareToIgnoreCase);
        tableNames.forEach(boxModel::addElement);
        tableBox.setModel(boxModel);
    }

    public void addNewTab(String title, Component component) {
        tabbedPane.addTab(title, component);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    private void initButtonsListeners() {
        loadDataButton.addActionListener(actionEvent -> {

            if (DBConnection.getConn() == null) {
                displayError("Вы не подключены к базе данных. Нажмите \"Войти/Сменить пользователя\"");
                return;
            }
            Schema.dropSchema();
            Schema.createTables();
            Schema.createTriggers();
            Schema.insertSampleData();
//            tablesToolBar.setVisible(true);
            displaySuccess("Данные успешно загружены!");
        });

        helpButton.addActionListener(actionEvent -> {
            FrameLocation.centerFrameLocation(helpForm);
            helpForm.setVisible(true);
        });

        settingsButton.addActionListener(actionEvent -> {
            loadDataButton.setVisible(false);
            if (DBConnection.getConn() != null) {
                try {
                    DBConnection.close();
                } catch (SQLException e) {
                    displayError(e);
                }
                tablesToolBar.setVisible(false);
            }
            FrameLocation.centerFrameLocation(settingsForm);
            settingsForm.setVisible(true);
        });

        openTableButton.addActionListener((actionEvent -> {
            TableViewForm tableViewForm = new TableViewForm(
                    EntityTableProperties.tablePropertiesMap.get(Objects.requireNonNull(tableBox.getSelectedItem()).toString())
            );

            tabbedPane.addTab(tableBox.getSelectedItem().toString(), tableViewForm);
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        }));
    }

    public static int displayYesNoPane(String msg, String title) {
        return JOptionPane.showOptionDialog(null,
                msg,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Да", "Нет"},
                "Да");
    }


    public static void displayError(Exception e) {
        JOptionPane.showMessageDialog(new JFrame(),
                "ОШИБКА: \n" + e.getLocalizedMessage(),
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayError(String msg) {
        JOptionPane.showMessageDialog(null,
                "ОШИБКА: \n" + msg,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displaySuccess(String msg) {
        JOptionPane.showMessageDialog(new JFrame(),
                msg,
                "OK",
                JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-2359335));
        mainPanel.setInheritsPopupMenu(false);
        mainPanel.setMaximumSize(new Dimension(-1, -1));
        mainPanel.setMinimumSize(new Dimension(950, 800));
        mainPanel.setPreferredSize(new Dimension(950, 800));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16730829)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Comic Sans MS", -1, 16, mainPanel.getFont()), new Color(-16730829)));
        mainToolBar = new JToolBar();
        mainToolBar.setBackground(new Color(-5638753));
        mainPanel.add(mainToolBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(850, 20), new Dimension(1454, 30), null, 0, false));
        mainToolBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        settingsButton = new JButton();
        settingsButton.setBackground(new Color(-16730829));
        Font settingsButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 18, settingsButton.getFont());
        if (settingsButtonFont != null) settingsButton.setFont(settingsButtonFont);
        settingsButton.setForeground(new Color(-2359335));
        settingsButton.setHorizontalAlignment(0);
        settingsButton.setText("Войти/Сменить пользователя");
        mainToolBar.add(settingsButton);
        loadDataButton = new JButton();
        loadDataButton.setBackground(new Color(-16730829));
        Font loadDataButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 18, loadDataButton.getFont());
        if (loadDataButtonFont != null) loadDataButton.setFont(loadDataButtonFont);
        loadDataButton.setForeground(new Color(-2359335));
        loadDataButton.setText("Загрузить данные");
        mainToolBar.add(loadDataButton);
        helpButton = new JButton();
        helpButton.setBackground(new Color(-16730829));
        Font helpButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 18, helpButton.getFont());
        if (helpButtonFont != null) helpButton.setFont(helpButtonFont);
        helpButton.setForeground(new Color(-2359335));
        helpButton.setText("Помощь");
        mainToolBar.add(helpButton);
        tabbedPane.setBackground(new Color(-5638753));
        tabbedPane.setTabPlacement(3);
        mainPanel.add(tabbedPane, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(900, 350), new Dimension(900, 350), null, 0, false));
        tabbedPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/zoo_logo.png")));
        label1.setText(" ");
        mainPanel.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tablesToolBar = new JToolBar();
        tablesToolBar.setBackground(new Color(-5638753));
        mainPanel.add(tablesToolBar, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        openTableButton = new JButton();
        openTableButton.setBackground(new Color(-16730829));
        Font openTableButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 18, openTableButton.getFont());
        if (openTableButtonFont != null) openTableButton.setFont(openTableButtonFont);
        openTableButton.setForeground(new Color(-2359335));
        openTableButton.setText("Открыть таблицу");
        tablesToolBar.add(openTableButton);
        tableBox = new JComboBox();
        tableBox.setBackground(new Color(-2359335));
        Font tableBoxFont = this.$$$getFont$$$("Comic Sans MS", -1, 16, tableBox.getFont());
        if (tableBoxFont != null) tableBox.setFont(tableBoxFont);
        tableBox.setForeground(new Color(-16730829));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        tableBox.setModel(defaultComboBoxModel1);
        tablesToolBar.add(tableBox);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
