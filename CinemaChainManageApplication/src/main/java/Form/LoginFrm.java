package Form;

import javax.swing.*;
import java.awt.*;

public class LoginFrm extends JFrame {
    private JPanel panel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrm() {
        // Thiết lập JFrame
        setTitle("Login");
        setSize(500, 400);  // Tăng kích thước form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính với GridBagLayout
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));  // Tăng padding
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Tăng khoảng cách giữa các component
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel lblTitle = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));  // Font lớn cho tiêu đề
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);  // Thêm khoảng cách dưới tiêu đề
        panel.add(lblTitle, gbc);

        // Reset lại insets cho các component khác
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        // Username label và textfield
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));  // Tăng font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        txtUsername.setPreferredSize(new Dimension(200, 35));  // Tăng kích thước ô input
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtUsername, gbc);

        // Password label và textfield
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        txtPassword.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        // Login button
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(50, 150, 50));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(200, 40));  // Tăng kích thước nút
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 0, 0);  // Thêm khoảng cách trên nút
        panel.add(btnLogin, gbc);

        // Thêm panel vào JFrame
        add(panel);

        // Xử lý sự kiện đăng nhập
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.equals("Minh") && password.equals("123456")) {
                MainMenuFrm mainMenu = new MainMenuFrm();
                mainMenu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });
    }
}