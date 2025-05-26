
import Form.LoginFrm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                LoginFrm loginFrm = new LoginFrm();
                loginFrm.setVisible(true);
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}