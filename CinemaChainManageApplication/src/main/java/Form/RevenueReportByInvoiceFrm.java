package Form;

import DAO.RevenueReportDAO;
import Entity.RevenueReportByCinema;
import Entity.RevenueReportByInvoice;
import Entity.Invoice;
import Entity.Showtime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevenueReportByInvoiceFrm extends JFrame {
    private JPanel panel;
    private JButton btnReturnRevenueReport;
    private JButton btnReturnRevenueReportByMovieShowtime;
    private JTable tbl_RevenueReportByInvoice;
    private String startDate;
    private String endDate;

    public RevenueReportByInvoiceFrm(String startDate, String endDate, Integer showtimeId,
                                     String showtimeInfo, RevenueReportByMovieShowtimeFrm movieShowtimeFrm,
                                      RevenueReportFrm parentFrame) {
        this.startDate = startDate;
        this.endDate = endDate;

        initializeUI(movieShowtimeFrm, parentFrame, showtimeInfo);
        loadRevenueData(showtimeId);
    }

    private void initializeUI(RevenueReportByMovieShowtimeFrm movieShowtimeFrm, RevenueReportFrm parentFrame, String showtimeInfo) {
        setTitle("Thống kê hóa đơn theo suất chiếu: " + showtimeInfo);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        // Cài đặt bảng
        tbl_RevenueReportByInvoice = new JTable();
        tbl_RevenueReportByInvoice.setFont(new Font("Arial", Font.PLAIN, 16));
        tbl_RevenueReportByInvoice.setRowHeight(35);
        // Tùy chỉnh header của bảng
        tbl_RevenueReportByInvoice.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // Căn giữa header của bảng
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbl_RevenueReportByInvoice.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(tbl_RevenueReportByInvoice);
        scrollPane.setPreferredSize(new Dimension(1100, 600));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setPreferredSize(new Dimension(400, 40));


        // Cài đặt nút quay lại danh sách
        btnReturnRevenueReportByMovieShowtime = new JButton("Return list");
        btnReturnRevenueReportByMovieShowtime.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnRevenueReportByMovieShowtime.setBackground(new Color(50, 150, 50));
        btnReturnRevenueReportByMovieShowtime.setForeground(Color.BLACK);
        btnReturnRevenueReportByMovieShowtime.setPreferredSize(new Dimension(110, 35));
        buttonPanel.add(btnReturnRevenueReportByMovieShowtime);

        // Cài đặt nút quay lại
        btnReturnRevenueReport = new JButton("Return");
        btnReturnRevenueReport.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnRevenueReport.setBackground(new Color(200, 50, 50));
        btnReturnRevenueReport.setForeground(Color.BLACK);
        btnReturnRevenueReport.setPreferredSize(new Dimension(110, 35));
        buttonPanel.add(btnReturnRevenueReport);

        // Thêm panel chứa nút vào layout chính
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;  // Cho phép buttonPanel mở rộng theo chiều ngang
        gbc.weighty = 0.0;  // Đặt weighty = 0 để không cho panel mở rộng theo chiều dọc
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Cho phép fill theo chiều ngang
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel);

        btnReturnRevenueReportByMovieShowtime.addActionListener(e -> {
            movieShowtimeFrm.setVisible(true);
            dispose();
        });

        btnReturnRevenueReport.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });
    }

    private void loadRevenueData(Integer showtimeId) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            Showtime showtime = new Showtime(showtimeId, null, null, null);
            List<RevenueReportByInvoice> reports = new RevenueReportDAO().getRevenueReportByInvoice(start, end, showtime);

            updateTable(reports);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi định dạng ngày tháng",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<RevenueReportByInvoice> revenueReportByInvoiceList) {
        String[] columnNames = {"STT", "Mã HĐ", "Tên KH", "Số vé", "Tổng tiền", "Thời gian thanh toán"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#,### VNĐ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        int stt = 1;
        for (RevenueReportByInvoice report : revenueReportByInvoiceList) {
            String customerName = report.getCustomer() != null ?
                    report.getCustomer().getName() : "Khách vãng lai";

            Object[] row = {
                    stt++,
                    "HD" + String.format("%06d", report.getID()),  // Định dạng mã HD thành HD000001
                    customerName,
                    report.getTicket_count(),
                    currencyFormat.format(report.getTotalRevenueByInvoice()),
                    dateFormat.format(report.getDateTime())
            };
            model.addRow(row);
        }

        tbl_RevenueReportByInvoice.setModel(model);

        // Căn chỉnh cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        // Căn giữa cho TT, Mã HĐ, Tên KH, Số vé, Thời gian
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        // Căn phải cho cột tổng tiền
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        // Set kích thước cho các cột
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(0).setPreferredWidth(50);   // TT
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(1).setPreferredWidth(100);  // Mã HĐ
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(2).setPreferredWidth(200);  // Tên KH
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(3).setPreferredWidth(80);   // Số vé
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(4).setPreferredWidth(150);  // Tổng tiền
        tbl_RevenueReportByInvoice.getColumnModel().getColumn(5).setPreferredWidth(150);  // Thời gian
    }
}