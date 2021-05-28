package forms.tables;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import entities.Entity;
import entities.FeedSupplier;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FeedSupplierForm extends JPanel implements NewEntityPanel {
    private JLabel supplierName;
    private JTextField supplierNameText;
    private JPanel mainPanel;

    public FeedSupplierForm() {
        $$$setupUI$$$();

        this.add(mainPanel);
        this.setVisible(true);
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
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        supplierName = new JLabel();
        supplierName.setText("Название поставщика");
        mainPanel.add(supplierName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        supplierNameText = new JTextField();
        mainPanel.add(supplierNameText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    @Override
    public Entity getEntity() {
        return new FeedSupplier(supplierNameText.getText());
    }

    @Override
    public void initFilterForm(TableViewForm tableView) {

    }

    @Override
    public void insertEntity() throws SQLException {
        getEntity().insertValues();
    }

    @Override
    public JFrame getFilterForm() {
        return null;
    }
}
