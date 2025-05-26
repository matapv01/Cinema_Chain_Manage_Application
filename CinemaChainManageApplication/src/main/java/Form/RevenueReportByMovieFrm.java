package Form;

import DAO.RevenueReportDAO;
import Entity.RevenueReportByMovie;

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

public class RevenueReportByMovieFrm extends JFrame {
    private JPanel panel;
    private JButton btnReturnRevenueReport;
    private JTable tbl_RevenueReportByMovie;
    private String startDate;
    private String endDate;

    public RevenueReportByMovieFrm(String startDate, String endDate, RevenueReportFrm parentFrame) {
        this.startDate = startDate;
        this.endDate = endDate;

        initializeUI(parentFrame);
        loadRevenueData();
    }

    private void initializeUI(RevenueReportFrm parentFrame) {
        setTitle("Thống kê doanh thu theo phim (" + startDate + " đến " + endDate + ")");
        // Tăng kích thước cửa sổ
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
        tbl_RevenueReportByMovie = new JTable();
        tbl_RevenueReportByMovie.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbl_RevenueReportByMovie.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl_RevenueReportByMovie.setFont(new Font("Arial", Font.PLAIN, 16));
        tbl_RevenueReportByMovie.setRowHeight(35);
        JScrollPane scrollPane = new JScrollPane(tbl_RevenueReportByMovie);
        scrollPane.setPreferredSize(new Dimension(1100, 600));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // Cài đặt nút quay lại
        btnReturnRevenueReport = new JButton("Return");
        btnReturnRevenueReport.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnRevenueReport.setBackground(new Color(200, 50, 50));
        btnReturnRevenueReport.setForeground(Color.BLACK);
        btnReturnRevenueReport.setPreferredSize(new Dimension(100, 35));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnReturnRevenueReport, gbc);

        add(panel);

        btnReturnRevenueReport.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        tbl_RevenueReportByMovie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tbl_RevenueReportByMovie.getSelectedRow();
                    if (selectedRow >= 0) {
                        int movieId = (int) tbl_RevenueReportByMovie.getValueAt(selectedRow, 1);
                        String movieName = (String) tbl_RevenueReportByMovie.getValueAt(selectedRow, 2);

                        RevenueReportByMovieShowtimeFrm showtimeReport = new RevenueReportByMovieShowtimeFrm(
                                startDate, endDate, false, movieId, movieName,
                                RevenueReportByMovieFrm.this, parentFrame);
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

            List<RevenueReportByMovie> reports = new RevenueReportDAO().getRevenueReportByMovie(start, end);

            updateTable(reports);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi định dạng ngày tháng",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<RevenueReportByMovie> revenueReportByMovieList) {
        String[] columnNames = {"STT", "Mã phim", "Tên phim", "Tổng số lượng vé bán ra", "Tổng doanh thu"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#,### VNĐ");
        // Sort list theo tổng doanh thu (giảm dần)
        revenueReportByMovieList.sort((a, b) -> b.getTotalRevenueByMovie().compareTo(a.getTotalRevenueByMovie()));

        int stt = 1;

        for (RevenueReportByMovie report : revenueReportByMovieList) {
            Object[] row = {
                    stt++,
                    report.getID(),
                    report.getName(),
                    report.getTicket_count(),
                    currencyFormat.format(report.getTotalRevenueByMovie())
            };
            model.addRow(row);
        }

        tbl_RevenueReportByMovie.setModel(model);

        // Căn chỉnh cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        // Căn giữa cho TT, Mã phim, Tên phim, Số lượng vé
        for (int i = 0; i < 4; i++) {
            tbl_RevenueReportByMovie.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Căn phải cho cột doanh thu
        tbl_RevenueReportByMovie.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        // Set kích thước cho các cột
        tbl_RevenueReportByMovie.getColumnModel().getColumn(0).setPreferredWidth(50);   // TT
        tbl_RevenueReportByMovie.getColumnModel().getColumn(1).setPreferredWidth(80);   // Mã phim
        tbl_RevenueReportByMovie.getColumnModel().getColumn(2).setPreferredWidth(200);  // Tên phim
        tbl_RevenueReportByMovie.getColumnModel().getColumn(3).setPreferredWidth(150);  // Số lượng vé
        tbl_RevenueReportByMovie.getColumnModel().getColumn(4).setPreferredWidth(150);  // Doanh thu
    }
}