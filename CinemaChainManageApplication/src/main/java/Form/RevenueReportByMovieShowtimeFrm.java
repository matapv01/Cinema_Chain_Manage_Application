package Form;

import DAO.RevenueReportDAO;
import Entity.Movie;
import Entity.Cinema;
import Entity.RevenueReportByCinema;
import Entity.RevenueReportByMovieShowtime;
import Entity.Showtime;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevenueReportByMovieShowtimeFrm extends JFrame {
    private JPanel panel;
    private JButton btnReturnRevenueReport;
    private JButton btnReturnRevenueReportDetail;
    private JTable tbl_RevenueReportByMovieShowtime;
    private String startDate;
    private String endDate;

    public RevenueReportByMovieShowtimeFrm(String startDate, String endDate,Boolean isCinema, Integer movieId,
                                           String movieName, JFrame previousFrame, RevenueReportFrm parentFrame) {
        this.startDate = startDate;
        this.endDate = endDate;

        initializeUI(previousFrame, parentFrame, movieName);
        loadRevenueData(movieId, movieName, isCinema);
    }


    private void initializeUI(JFrame previousFrame, RevenueReportFrm parentFrame, String movieName) {
        setTitle("Thống kê theo suất chiếu phim: " + movieName);
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
        tbl_RevenueReportByMovieShowtime = new JTable();
        tbl_RevenueReportByMovieShowtime.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbl_RevenueReportByMovieShowtime.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl_RevenueReportByMovieShowtime.setFont(new Font("Arial", Font.PLAIN, 16));
        tbl_RevenueReportByMovieShowtime.setRowHeight(35);
        JScrollPane scrollPane = new JScrollPane(tbl_RevenueReportByMovieShowtime);
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
        btnReturnRevenueReportDetail = new JButton("Return list");
        btnReturnRevenueReportDetail.setFont(new Font("Arial", Font.BOLD, 14));
        btnReturnRevenueReportDetail.setBackground(new Color(50, 150, 50));
        btnReturnRevenueReportDetail.setForeground(Color.BLACK);
        btnReturnRevenueReportDetail.setPreferredSize(new Dimension(110, 35));
        buttonPanel.add(btnReturnRevenueReportDetail);

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


        btnReturnRevenueReportDetail.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        btnReturnRevenueReport.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        tbl_RevenueReportByMovieShowtime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tbl_RevenueReportByMovieShowtime.getSelectedRow();
                    if (selectedRow >= 0) {
                        String showtimeInfo = (String) tbl_RevenueReportByMovieShowtime.getValueAt(selectedRow, 1);
                        int showtimeId = (int) tbl_RevenueReportByMovieShowtime.getValueAt(selectedRow, 0);

                        Showtime showtime = new Showtime();
                        showtime.setID(showtimeId);

                        RevenueReportByInvoiceFrm invoiceReport = new RevenueReportByInvoiceFrm(
                                startDate, endDate, showtimeId, showtimeInfo,
                                RevenueReportByMovieShowtimeFrm.this, parentFrame);
                        invoiceReport.setVisible(true);
                        setVisible(false);
                    }
                }
            }
        });
    }

    private void loadRevenueData(Integer id, String name, boolean isCinema) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            List<RevenueReportByMovieShowtime> reports;

            if (isCinema) {
                Cinema cinema = new Cinema(id, name, null, null, null);
                reports = new RevenueReportDAO().getRevenueReportByCinemaShowtime(start, end, cinema);
            } else {
                Movie movie = new Movie(id, name, null, null, null);
                reports = new RevenueReportDAO().getRevenueReportByMovieShowtime(start, end, movie);
            }

            updateTable(reports);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi định dạng ngày tháng",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<RevenueReportByMovieShowtime> revenueReportByMovieShowtimeList) {
        String[] columnNames = {"STT", "Suất chiếu", "Số lượng vé bán ra", "Tổng tiền thu được"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DecimalFormat currencyFormat = new DecimalFormat("#,### VNĐ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // Sort list theo thời gian chiếu (tăng dần - từ cũ đến mới)
        revenueReportByMovieShowtimeList.sort((a, b) -> a.getDateTime().compareTo(b.getDateTime()));

        int stt = 1;
        for (RevenueReportByMovieShowtime report : revenueReportByMovieShowtimeList) {
            String showtimeInfo;
            if (report.getMovie() != null && report.getMovie().getName() != null) {
                // Nếu có thông tin phim, hiển thị cả tên phim và thời gian
                showtimeInfo = dateFormat.format(report.getDateTime()) + " - " + report.getMovie().getName();
            } else {
                // Nếu không có thông tin phim, chỉ hiển thị thời gian
                showtimeInfo = dateFormat.format(report.getDateTime());
            }

            Object[] row = {
                    stt++,
                    showtimeInfo,
                    report.getTicket_count(),
                    currencyFormat.format(report.getTotalRevenueByMovieShowtime())
            };
            model.addRow(row);
        }

        tbl_RevenueReportByMovieShowtime.setModel(model);

        // Căn chỉnh cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        // Căn giữa cho STT, Suất chiếu và số lượng vé
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        // Căn phải cho cột doanh thu
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        // Set kích thước cho các cột
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(0).setPreferredWidth(50);   // STT
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(1).setPreferredWidth(300);  // Suất chiếu (tăng độ rộng vì có thêm tên phim)
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(2).setPreferredWidth(150);  // Số lượng vé
        tbl_RevenueReportByMovieShowtime.getColumnModel().getColumn(3).setPreferredWidth(200);  // Doanh thu
    }
}