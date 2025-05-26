package Form;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrm extends JFrame {
    private JPanel panel;
    private JButton btnRevenueReport;

    public MainMenuFrm() {
        // Thiết lập JFrame
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        // Tạo nút Revenue Report
        btnRevenueReport = new JButton("Revenue Report");
        btnRevenueReport.setFont(new Font("Arial", Font.BOLD, 14));
        btnRevenueReport.setPreferredSize(new Dimension(150, 40));
        btnRevenueReport.setBackground(new Color(50, 150, 50));
        btnRevenueReport.setForeground(Color.WHITE);

        // Thêm nút vào panel
        panel.add(btnRevenueReport);

        // Thêm panel vào JFrame
        add(panel);

        // Xử lý sự kiện cho nút Revenue Report
        btnRevenueReport.addActionListener(e -> {
            RevenueReportFrm revenueReport = new RevenueReportFrm();
            revenueReport.setVisible(true);
            dispose();
        });
    }
}