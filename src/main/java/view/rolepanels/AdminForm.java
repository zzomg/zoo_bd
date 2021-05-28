package view.rolepanels;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import view.MainForm;
import view.panel.RegistrationForm;
import view.tables.TableViewForm;

import javax.swing.*;
import java.awt.*;

public class AdminForm extends JPanel {
    private JPanel mainPanel;
    private JButton addUserButton;
    private JButton showUserButton;
    private final MainForm mainForm;

    public AdminForm(MainForm mainForm) {
        this.mainForm = mainForm;
        this.add(mainPanel);
        initButtons();
    }

    private void initButtons() {
        addUserButton.addActionListener(e -> {
            RegistrationForm registrationForm = new RegistrationForm();
            mainForm.addNewTab("Регистрация пользователя", registrationForm);
        });

        showUserButton.addActionListener(e -> {
            TableViewForm tableViewForm = new TableViewForm("Пользователи");
            mainForm.addNewTab("Пользователи", tableViewForm);
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
        mainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        addUserButton = new JButton();
        addUserButton.setText("Создать нового пользователя");
        mainPanel.add(addUserButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showUserButton = new JButton();
        showUserButton.setText("Просмотреть пользователей");
        mainPanel.add(showUserButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
