package forms.rolepanels;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import forms.MainForm;
import forms.panel.NewEntityForm;
import forms.tables.*;

import javax.swing.*;
import java.awt.*;

public class ManagerForm extends JPanel {
    private JPanel mainPanel;
    private JButton addEmployeeButton;
    private JButton showEmployeeButton;
    private JButton setRespForSpeciesButton;
    private JButton setRespForAnimalButton;
    private JButton addSupplyButton;
    private JButton showSupplyButton;
    private JButton showRespForSpeciesButton;
    private JButton showRespForAnimalButton;
    private final MainForm mainForm;

    public ManagerForm(MainForm mainForm) {
        this.mainForm = mainForm;
        this.add(mainPanel);
        initButtons();
    }

    private void initButtons() {
        addEmployeeButton.addActionListener(e -> {
            NewEntityForm newEntityForm = new NewEntityForm(new EmployeeForm());
            mainForm.addNewTab("Добавление сотрудника", newEntityForm);
        });

        showEmployeeButton.addActionListener(e -> {
            TableViewForm tableViewForm = new TableViewForm("Сотрудники");
            mainForm.addNewTab("Сотрудники", tableViewForm);
        });

        setRespForSpeciesButton.addActionListener(e -> {
            NewEntityForm newEntityForm = new NewEntityForm(new EmployeeAnimalForm());
            mainForm.addNewTab("Добавление ответственного за вид", newEntityForm);
        });

        showRespForSpeciesButton.addActionListener(e -> {
            TableViewForm tableViewForm = new TableViewForm("Ответственные за вид");
            mainForm.addNewTab("Ответственные за вид", tableViewForm);
        });

        setRespForAnimalButton.addActionListener(e -> {
            NewEntityForm newEntityForm = new NewEntityForm(new EmployeeAnimalCardForm());
            mainForm.addNewTab("Добавление ответственного за особь", newEntityForm);
        });

        showRespForAnimalButton.addActionListener(e -> {
            TableViewForm tableViewForm = new TableViewForm("Ответственные за особи");
            mainForm.addNewTab("Ответственные за особи", tableViewForm);
        });

        addSupplyButton.addActionListener(e -> {
            NewEntityForm newEntityForm = new NewEntityForm(new SupplyForm());
            mainForm.addNewTab("Добавление поставки", newEntityForm);
        });

        showSupplyButton.addActionListener(e -> {
            TableViewForm tableViewForm = new TableViewForm("Поставки");
            mainForm.addNewTab("Поставки", tableViewForm);
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        addEmployeeButton = new JButton();
        addEmployeeButton.setText("Регистрация нового сотрудника");
        mainPanel.add(addEmployeeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showEmployeeButton = new JButton();
        showEmployeeButton.setText("Просмотр сотрудников");
        mainPanel.add(showEmployeeButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setRespForSpeciesButton = new JButton();
        setRespForSpeciesButton.setText("Назначить ответственных за вид");
        mainPanel.add(setRespForSpeciesButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addSupplyButton = new JButton();
        addSupplyButton.setText("Регистрация новой поставки корма");
        mainPanel.add(addSupplyButton, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showSupplyButton = new JButton();
        showSupplyButton.setText("Просмотр поставок");
        mainPanel.add(showSupplyButton, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showRespForSpeciesButton = new JButton();
        showRespForSpeciesButton.setText("Просмотр ответственных за виды");
        mainPanel.add(showRespForSpeciesButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showRespForAnimalButton = new JButton();
        showRespForAnimalButton.setText("Просмотр ответственных за особей");
        mainPanel.add(showRespForAnimalButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("РАБОТА С ПЕРСОНАЛОМ:");
        mainPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("РАБОТА С ЖИВОТНЫМИ:");
        mainPanel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("РАБОТА С ПОСТАВКАМИ:");
        mainPanel.add(label3, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setRespForAnimalButton = new JButton();
        setRespForAnimalButton.setText("Назначить ответственных за особь");
        mainPanel.add(setRespForAnimalButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
