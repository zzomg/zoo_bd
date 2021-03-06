package forms.tables;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import server.Queries;
import entities.Employee;
import entities.Entity;
import forms.categories.*;
import forms.filters.EmployeeFilterForm;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmployeeForm extends JPanel implements NewEntityPanel {
    private JPanel mainPanel;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> sexBox;
    private JComboBox<String> jobBox;
    private JFormattedTextField hireDateText;
    private JTextField salaryText;
    private JScrollPane scrollPane;
    private JLabel jobLabel;
    private JLabel salaryLabel;
    private JLabel sexLabel;
    private JLabel hireDateLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JPanel generalPanel;
    private JPanel categoryPanel;
    private JTextField ageText;
    private JLabel ageLabel;
    private EmployeeFilterForm filterForm;
    private final Map<String, EmployeeCategory> categoryMap = new HashMap<>();

    public EmployeeForm() {
        $$$setupUI$$$();

        initComboBox();
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void initComboBox() {
        jobBox.addActionListener(e -> {
            String name = Objects.requireNonNull(jobBox.getSelectedItem()).toString();
            ((CardLayout) categoryPanel.getLayout()).show(categoryPanel, name);
        });
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
        scrollPane = new JScrollPane();
        scrollPane.setEnabled(true);
        scrollPane.setMaximumSize(new Dimension(-1, -1));
        scrollPane.setMinimumSize(new Dimension(600, 250));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMaximumSize(new Dimension(-1, -1));
        mainPanel.setMinimumSize(new Dimension(600, 250));
        mainPanel.setPreferredSize(new Dimension(600, 250));
        scrollPane.setViewportView(mainPanel);
        generalPanel = new JPanel();
        generalPanel.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(generalPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobLabel = new JLabel();
        jobLabel.setText("??????????????????????????");
        generalPanel.add(jobLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salaryLabel = new JLabel();
        salaryLabel.setText("????????????????");
        generalPanel.add(salaryLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sexLabel = new JLabel();
        sexLabel.setText("??????");
        generalPanel.add(sexLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hireDateLabel = new JLabel();
        hireDateLabel.setText("???????? ???????????? ???? ????????????");
        generalPanel.add(hireDateLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameLabel = new JLabel();
        lastNameLabel.setText("??????????????");
        generalPanel.add(lastNameLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameLabel = new JLabel();
        firstNameLabel.setText("??????");
        generalPanel.add(firstNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameText = new JTextField();
        firstNameText.setMargin(new Insets(2, 2, 2, 2));
        generalPanel.add(firstNameText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameText = new JTextField();
        generalPanel.add(lastNameText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sexBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("??????????????");
        defaultComboBoxModel1.addElement("??????????????");
        sexBox.setModel(defaultComboBoxModel1);
        generalPanel.add(sexBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jobBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("??????????????????");
        defaultComboBoxModel2.addElement("??????????????");
        defaultComboBoxModel2.addElement("????????????????");
        jobBox.setModel(defaultComboBoxModel2);
        generalPanel.add(jobBox, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hireDateText.setFocusLostBehavior(1);
        generalPanel.add(hireDateText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        salaryText = new JTextField();
        generalPanel.add(salaryText, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ageText = new JTextField();
        generalPanel.add(ageText, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ageLabel = new JLabel();
        ageLabel.setText("??????????????");
        generalPanel.add(ageLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainPanel.add(categoryPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        jobLabel.setLabelFor(jobBox);
        salaryLabel.setLabelFor(salaryText);
        sexLabel.setLabelFor(sexBox);
        hireDateLabel.setLabelFor(hireDateText);
        lastNameLabel.setLabelFor(lastNameText);
        firstNameLabel.setLabelFor(firstNameText);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return scrollPane;
    }

    private void createUIComponents() {
        hireDateText = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        hireDateText.setValue(new Date());

        categoryPanel = new JPanel(new CardLayout());

        VetForm vetForm = new VetForm();
        ManagerForm managerForm = new ManagerForm();
        CleanerForm cleanerForm = new CleanerForm();
        categoryMap.put("??????????????????", vetForm);
        categoryMap.put("????????????????", managerForm);
        categoryMap.put("??????????????", cleanerForm);

        categoryPanel.add(vetForm, "??????????????????");
        categoryPanel.add(managerForm, "????????????????");
        categoryPanel.add(cleanerForm, "??????????????");
    }

    @Override
    public Entity getEntity() throws IllegalArgumentException {
        return new Employee(
                firstNameText.getText().trim(),
                lastNameText.getText().trim(),
                hireDateText.getText().trim(),
                Objects.requireNonNull(sexBox.getSelectedItem()).toString().substring(0, 1),
                Integer.parseInt(salaryText.getText()),
                Integer.parseInt(ageText.getText()),
                Objects.requireNonNull(jobBox.getSelectedItem()).toString().trim()
        );
    }

    private String getCategoryName() {
        return Objects.requireNonNull(jobBox.getSelectedItem()).toString();
    }

    @Override
    public void insertEntity() throws SQLException {
        int id = Queries.getNextId() + 1;
        getEntity().insertValues();
        categoryMap.get(getCategoryName()).insertValues(id);
    }

    @Override
    public void initFilterForm(TableViewForm tableView) {
        this.filterForm = new EmployeeFilterForm(tableView);
    }

    @Override
    public JFrame getFilterForm() {
        return filterForm;
    }

}
