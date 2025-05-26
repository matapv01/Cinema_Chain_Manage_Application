package Form;

import DAO.RevenueReportDAO;
import Entity.RevenueReportByCinema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RevenueReportByCinemaFrm extends JFrame {
    private JPanel panel;
    private JButton btnReturnRevenueReport;
    private JTable tbl_RevenueReportByCinema;
    private String startDate;
    private String endDate;

    public RevenueReportByCinemaFrm(String startDate, String endDate, RevenueReportFrm parentFrame) {
        this.startDate = startDate;
        this.endDate = endDate;

        initializeUI(parentFrame);
        loadRevenueData();
    }

    private void initializeUI(RevenueReportFrm parentFrame) {
        setTitle("Revenue Report By Cinema (" + startDate + " to " + endDate + ")");
        // Tăng kích thước cửa sổ
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        // Tăng padding của panel
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        // Tăng khoảng cách giữa các thành phần
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;  // Thay đổi để fill cả chiều dọc và ngang

        // Khởi tạo bảng với DefaultTableModel trống
        tbl_RevenueReportByCinema = new JTable();
        // Tăng cỡ chữ
        tbl_RevenueReportByCinema.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbl_RevenueReportByCinema.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tbl_RevenueReportByCinema.setFont(new Font("Arial", Font.PLAIN, 16));
        // Tăng chiều cao hàng
        tbl_RevenueReportByCinema.setRowHeight(35);
        JScrollPane scrollPane = new JScrollPane(tbl_RevenueReportByCinema);
        // Tăng kích thước bảng
        scrollPane.setPreferredSize(new Dimension(1100, 600));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;  // Cho phép bảng mở rộng theo chiều ngang
        gbc.weighty = 1.0;  // Cho phép bảng mở rộng theo chiều dọc
        panel.add(scrollPane, gbc);

        btnReturnRevenueReport = new JButton("Return");
        btnReturnRevenueReport.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnRevenueReport.setBackground(new Color(200, 50, 50));
        btnReturnRevenueReport.setForeground(Color.BLACK);
        btnReturnRevenueReport.setPreferredSize(new Dimension(100, 35));  // Giảm kích thước nút
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;  // Reset weightx để nút không bị kéo giãn
        gbc.weighty = 0.0;  // Reset weighty để nút không bị kéo giãn
        gbc.fill = GridBagConstraints.NONE;  // Không cho phép nút fill
        gbc.anchor = GridBagConstraints.CENTER;  // Căn giữa nút
        panel.add(btnReturnRevenueReport, gbc);

        add(panel);

        btnReturnRevenueReport.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        tbl_RevenueReportByCinema.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tbl_RevenueReportByCinema.getSelectedRow();
                    if (selectedRow >= 0) {
                        Integer cinemaId = (Integer) tbl_RevenueReportByCinema.getValueAt(selectedRow, 1); // column 1 thay vì 0
                        String cinemaName = (String) tbl_RevenueReportByCinema.getValueAt(selectedRow, 2); // column 2 thay vì 1

                        RevenueReportByMovieShowtimeFrm showtimeReport = new RevenueReportByMovieShowtimeFrm(
                                startDate,
                                endDate,
                                true,
                                cinemaId,
                                cinemaName,
                                RevenueReportByCinemaFrm.this,
                                parentFrame
                        );
                        showtimeReport.setVisible(true);
                        setVisible(false);
                    }
                }
            }
        });
    }

    private void loadRevenueData() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            List<RevenueReportByCinema> reports = new RevenueReportDAO().getRevenueReportByCinema(start, end);

            updateTable(reports);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi định dạng ngày tháng",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<RevenueReportByCinema> revenueReportByCinemaList) {
        String[] columnNames = {"STT", "Mã rạp", "Tên rạp", "Tổng số lượng vé bán ra", "Tổng doanh thu"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#,### VNĐ");

        // Sort list theo tổng doanh thu (giảm dần)
        revenueReportByCinemaList.sort((a, b) -> b.getTotalRevenueByCinema().compareTo(a.getTotalRevenueByCinema()));

        int stt = 1;
        for (RevenueReportByCinema report : revenueReportByCinemaList) {
            Object[] row = {
                    stt++,
                    report.getID(),
                    report.getName(),
                    report.getTicket_count(),
                    currencyFormat.format(report.getTotalRevenueByCinema())
            };
            model.addRow(row);
        }

        tbl_RevenueReportByCinema.setModel(model);

        // Căn chỉnh cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        // Căn giữa cho STT, mã rạp, tên rạp và số lượng vé
        tbl_RevenueReportByCinema.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl_RevenueReportByCinema.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl_RevenueReportByCinema.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbl_RevenueReportByCinema.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        // Căn phải cho cột doanh thu
        tbl_RevenueReportByCinema.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        // Set kích thước cho các cột
        tbl_RevenueReportByCinema.getColumnModel().getColumn(0).setPreferredWidth(50);   // STT
        tbl_RevenueReportByCinema.getColumnModel().getColumn(1).setPreferredWidth(80);   // Mã rạp
        tbl_RevenueReportByCinema.getColumnModel().getColumn(2).setPreferredWidth(200);  // Tên rạp
        tbl_RevenueReportByCinema.getColumnModel().getColumn(3).setPreferredWidth(150);  // Số lượng vé
        tbl_RevenueReportByCinema.getColumnModel().getColumn(4).setPreferredWidth(150);  // Doanh thu
    }
}