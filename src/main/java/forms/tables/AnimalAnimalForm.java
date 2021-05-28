package forms.tables;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import server.Queries;
import entities.AnimalAnimal;
import entities.Entity;
import forms.filters.AnimalAnimalFilterForm;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class AnimalAnimalForm extends JPanel implements NewEntityPanel {
    private JLabel animalSecondLabel;
    private JLabel animalFirstLabel;
    private JComboBox<String> animalSecondBox;
    private JPanel mainPanel;
    private JComboBox<String> animalFirstBox;
    private AnimalAnimalFilterForm filterForm;


    public AnimalAnimalForm() {
        $$$setupUI$$$();
        initComboBox();

        this.add(mainPanel);
        this.setVisible(true);
    }

    private void initComboBox() {
        DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
        String[] animalNames = Queries.getAnimalNames();
        for (String name : animalNames) {
            defaultComboBoxModel.addElement(name);
        }
        animalFirstBox.setModel(defaultComboBoxModel);

        defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (String name : animalNames) {
            defaultComboBoxModel.addElement(name);
        }
        animalSecondBox.setModel(defaultComboBoxModel);
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
        mainPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        animalSecondLabel = new JLabel();
        animalSecondLabel.setText("Совместим с");
        mainPanel.add(animalSecondLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        animalFirstLabel = new JLabel();
        animalFirstLabel.setText("Вид животного");
        mainPanel.add(animalFirstLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        animalSecondBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        animalSecondBox.setModel(defaultComboBoxModel1);
        mainPanel.add(animalSecondBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        animalFirstBox = new JComboBox();
        mainPanel.add(animalFirstBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    @Override
    public Entity getEntity() {
        return new AnimalAnimal(
                Objects.requireNonNull(animalFirstBox.getSelectedItem()).toString(),
                Objects.requireNonNull(animalSecondBox.getSelectedItem()).toString()
        );
    }

    @Override
    public void insertEntity() throws SQLException {
        getEntity().insertValues();
    }

    @Override
    public void initFilterForm(TableViewForm tableView) {
        filterForm = new AnimalAnimalFilterForm(tableView);
    }

    @Override
    public JFrame getFilterForm() {
        return filterForm;
    }
}