package Form;

import DAO.RevenueReportDAO;
import Entity.RevenueReportByCinema;
import Entity.RevenueReportByMovie;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RevenueReportFrm extends JFrame {
    private JPanel panel;
    private JTextField startDate;
    private JTextField endDate;
    private JButton btnRevenueReportByCinema;
    private JButton btnRevenueReportByMovie;
    private JButton btnRevenueReport;

    public RevenueReportFrm() {
        // Thiết lập JFrame
        setTitle("Revenue Report");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        // Tạo GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nhãn và ô nhập Start Date
        JLabel lblStartDate = new JLabel("Start Date (dd/MM/yyyy):");
        lblStartDate.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblStartDate, gbc);

        startDate = new JTextField(15);
        startDate.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(startDate, gbc);

        // Nhãn và ô nhập End Date
        JLabel lblEndDate = new JLabel("End Date (dd/MM/yyyy):");
        lblEndDate.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblEndDate, gbc);

        endDate = new JTextField(15);
        endDate.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(endDate, gbc);

        // Nút By Cinema
        btnRevenueReportByCinema = new JButton("By Cinema");
        btnRevenueReportByCinema.setFont(new Font("Arial", Font.BOLD, 14));
        btnRevenueReportByCinema.setPreferredSize(new Dimension(120, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnRevenueReportByCinema, gbc);

        // Nút By Movie
        btnRevenueReportByMovie = new JButton("By Movie");
        btnRevenueReportByMovie.setFont(new Font("Arial", Font.BOLD, 14));
        btnRevenueReportByMovie.setPreferredSize(new Dimension(120, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnRevenueReportByMovie, gbc);

        // Nút Generate Report
        btnRevenueReport = new JButton("Generate Report");
        btnRevenueReport.setFont(new Font("Arial", Font.BOLD, 14));
        btnRevenueReport.setBackground(new Color(229, 17, 17));
        btnRevenueReport.setForeground(Color.BLACK);
        btnRevenueReport.setPreferredSize(new Dimension(260, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnRevenueReport, gbc);

        // Thêm panel vào JFrame
        add(panel);

        // Xử lý sự kiện cho btnRevenueReportByCinema
        btnRevenueReportByCinema.addActionListener(e -> {
            btnRevenueReportByCinema.setBackground(Color.LIGHT_GRAY);
            btnRevenueReportByMovie.setBackground(null);
        });

        // Xử lý sự kiện cho btnRevenueReportByMovie
        btnRevenueReportByMovie.addActionListener(e -> {
            btnRevenueReportByMovie.setBackground(Color.LIGHT_GRAY);
            btnRevenueReportByCinema.setBackground(null);
        });

        // Xử lý sự kiện cho btnRevenueReport
        btnRevenueReport.addActionListener(e -> {
            String start = startDate.getText().trim();
            String end = endDate.getText().trim();

            // Kiểm tra ô nhập rỗng
            if (start.isEmpty() || end.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Xin vui lòng điền đầy đủ cả ngày bắt đầu và ngày kết thúc");
                return;
            }

            // Kiểm tra định dạng ngày
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                Date startDate = sdf.parse(start);
                Date endDate = sdf.parse(end);

                RevenueReportDAO reportDAO = new RevenueReportDAO();

                // Kiểm tra loại báo cáo
                if (btnRevenueReportByCinema.getBackground() == Color.LIGHT_GRAY) {
                    List<RevenueReportByCinema> reports = reportDAO.getRevenueReportByCinema(startDate, endDate);
                    boolean hasRevenue = false;
                    for (RevenueReportByCinema report : reports) {
                        if (report.getTotalRevenueByCinema() > 0) {
                            hasRevenue = true;
                            break;
                        }
                    }

                    if (!hasRevenue) {
                        JOptionPane.showMessageDialog(this,
                                "Không thể thực hiện thống kê do chưa có doanh thu của rạp trong khoảng thời gian đã chọn",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    RevenueReportByCinemaFrm cinemaReport = new RevenueReportByCinemaFrm(start, end, this);
                    cinemaReport.setVisible(true);
                    setVisible(false);

                } else if (btnRevenueReportByMovie.getBackground() == Color.LIGHT_GRAY) {
                    List<RevenueReportByMovie> reports = reportDAO.getRevenueReportByMovie(startDate, endDate);
                    boolean hasRevenue = false;
                    for (RevenueReportByMovie report : reports) {
                        if (report.getTotalRevenueByMovie() > 0) {
                            hasRevenue = true;
                            break;
                        }
                    }

                    if (!hasRevenue) {
                        JOptionPane.showMessageDialog(this,
                                "Không thể thực hiện thống kê do chưa có doanh thu của phim trong khoảng thời gian đã chọn",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    RevenueReportByMovieFrm movieReport = new RevenueReportByMovieFrm(start, end, this);
                    movieReport.setVisible(true);
                    setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Xin vui lòng chọn loại thống kê");
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Xin vui lòng nhập ngày theo đúng định dạng dd/MM/yyyy");
                return;
            }
        });
    }

}