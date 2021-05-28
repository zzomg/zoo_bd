package forms.filters;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import forms.FrameLocation;
import forms.tables.TableViewForm;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class EmployeeFilterForm extends JFrame {
    private JLabel jobLabel;
    private JLabel hireDateLabel;
    private JLabel sexLabel;
    private JLabel salaryLabel;
    private JComboBox<String> jobBox;
    private JComboBox<String> sexBox;
    private JFormattedTextField fromHireDateText;
    private JTextField fromSalaryText;
    private JButton acceptButton;
    private JPanel mainPanel;
    private JRadioButton dateButton;
    private JTextField toSalaryText;
    private JLabel fromSalary;
    private JLabel toSalary;
    private JPanel salaryPanel;
    private JFormattedTextField toHireDateText;
    private JLabel fromDate;
    private final TableViewForm tableView;

    public EmployeeFilterForm(TableViewForm tableView) {
        this.tableView = tableView;
        $$$setupUI$$$();
        this.setTitle("Фильтрация сотрудников");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initButtons();
        this.pack();
        FrameLocation.centerFrameLocation(this);
    }

    private void initButtons() {
        acceptButton.addActionListener(e -> {
            acceptFilter();
        });
    }

    private void acceptFilter() {
        String whereClause = " where 1=1 ";
        if (!"Все".equalsIgnoreCase(Objects.requireNonNull(jobBox.getSelectedItem()).toString())) {
            whereClause += " and job_type = '"
                    + Objects.requireNonNull(jobBox.getSelectedItem()).toString() + "'";
        }

        if (!dateButton.isSelected()) {
            whereClause += " and hire_date between to_date('" + fromHireDateText.getText() + "', 'YYYY-MM-DD')"
                    + " and to_date('" + toHireDateText.getText() + "', 'YYYY-MM-DD')";
        }
        if (!"Все".equalsIgnoreCase(Objects.requireNonNull(sexBox.getSelectedItem()).toString())) {
            whereClause += " and sex = '" + Objects.requireNonNull(sexBox.getSelectedItem())
                    .toString().substring(0, 3).toLowerCase() + "'";
        }
        if (!fromSalaryText.getText().isEmpty()) {
            whereClause += " and salary >= " + fromSalaryText.getText();
        }
        if (!toSalaryText.getText().isEmpty()) {
            whereClause += " and salary <= " + toSalaryText.getText();
        }
        tableView.filterTable(whereClause);
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
        mainPanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        jobLabel = new JLabel();
        jobLabel.setText("Специальность");
        mainPanel.add(jobLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hireDateLabel = new JLabel();
        hireDateLabel.setText("Дата приема на работу");
        mainPanel.add(hireDateLabel, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sexLabel = new JLabel();
        sexLabel.setText("Пол");
        mainPanel.add(sexLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salaryLabel = new JLabel();
        salaryLabel.setText("Заработная плата");
        mainPanel.add(salaryLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jobBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Все");
        defaultComboBoxModel1.addElement("Ветеринар");
        defaultComboBoxModel1.addElement("Уборщик");
        defaultComboBoxModel1.addElement("Дрессировщик");
        defaultComboBoxModel1.addElement("Стpоитель");
        defaultComboBoxModel1.addElement("Менеджер");
        jobBox.setModel(defaultComboBoxModel1);
        mainPanel.add(jobBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sexBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Все");
        defaultComboBoxModel2.addElement("Мужской");
        defaultComboBoxModel2.addElement("Женский");
        sexBox.setModel(defaultComboBoxModel2);
        mainPanel.add(sexBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        acceptButton = new JButton();
        acceptButton.setText("Применить фильтр");
        mainPanel.add(acceptButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dateButton = new JRadioButton();
        dateButton.setSelected(true);
        dateButton.setText("Любая дата");
        mainPanel.add(dateButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salaryPanel = new JPanel();
        salaryPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(salaryPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        fromSalaryText = new JTextField();
        salaryPanel.add(fromSalaryText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        toSalaryText = new JTextField();
        salaryPanel.add(toSalaryText, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fromSalary = new JLabel();
        fromSalary.setText("От:");
        salaryPanel.add(fromSalary, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toSalary = new JLabel();
        toSalary.setText("До:");
        salaryPanel.add(toSalary, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.add(fromHireDateText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panel1.add(toHireDateText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fromDate = new JLabel();
        fromDate.setText("С:");
        panel1.add(fromDate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("До:");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        fromHireDateText = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        fromHireDateText.setValue(new Date(0));

        toHireDateText = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        toHireDateText.setValue(new Date());
    }
}
