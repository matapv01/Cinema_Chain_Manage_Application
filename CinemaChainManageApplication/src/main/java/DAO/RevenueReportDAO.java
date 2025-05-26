package DAO;

import Entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevenueReportDAO {
    private Connection dbCon = null;

    public RevenueReportDAO() {
        String dbUrl = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=cnpm;"
                + "user=sa;"
                + "password=123456;"
                + "encrypt=false;"
                + "trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dbCon = DriverManager.getConnection(dbUrl);
            System.out.println("Connected to SQL Server successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RevenueReportByCinema> getRevenueReportByCinema(Date startDate, Date endDate) {
        List<RevenueReportByCinema> reports = new ArrayList<>();
        String sql = """
            SELECT c.*, 
                   COUNT(t.id) as ticket_count,
                   SUM(t.price * (1 - t.discount)) as total_revenue
            FROM tbl_Cinema c
            LEFT JOIN tbl_ScreeningRoom sr ON sr.tbl_Cinemaid = c.id 
            LEFT JOIN tbl_Showtime s ON s.tbl_ScreeningRoomid = sr.id
            LEFT JOIN tbl_Ticket t ON t.tbl_Showtimeid = s.id
            LEFT JOIN tbl_Invoice i ON t.tbl_Invoiceid = i.id
            WHERE i.date_time BETWEEN ? AND ?
            GROUP BY c.id, c.name, c.address, c.description
            ORDER BY total_revenue DESC
        """;

        try (PreparedStatement stmt = dbCon.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime())); // thay vào dấu ? trong câu lệnh sql
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery(); // thực thi câu lệnh sql
            while (rs.next()) {
                RevenueReportByCinema report = new RevenueReportByCinema(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("description"),
                        null,
                        rs.getDouble("total_revenue"),
                        rs.getInt("ticket_count")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<RevenueReportByMovie> getRevenueReportByMovie(Date startDate, Date endDate) {
        List<RevenueReportByMovie> reports = new ArrayList<>();
        String sql = """
            SELECT m.*, 
                   COUNT(t.id) as ticket_count,
                   SUM(t.price * (1 - t.discount)) as total_revenue
            FROM tbl_Movie m
            LEFT JOIN tbl_Showtime s ON s.tbl_Movieid = m.id
            LEFT JOIN tbl_Ticket t ON t.tbl_Showtimeid = s.id
            LEFT JOIN tbl_Invoice i ON t.tbl_Invoiceid = i.id
            WHERE i.date_time BETWEEN ? AND ?
            GROUP BY m.id, m.name, m.genre, m.production_year, m.description
            ORDER BY total_revenue DESC
        """;

        try (PreparedStatement stmt = dbCon.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RevenueReportByMovie report = new RevenueReportByMovie(
                        rs.getInt("id"),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("genre")),
                        rs.getInt("production_year"),
                        rs.getString("description"),
                        rs.getDouble("total_revenue"),
                        rs.getInt("ticket_count")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<RevenueReportByMovieShowtime> getRevenueReportByMovieShowtime(Date startDate, Date endDate, Movie movie) {
        List<RevenueReportByMovieShowtime> reports = new ArrayList<>();
        String sql = """
            SELECT s.id, s.date_time,
                   sr.id as room_id, sr.seat_number, sr.description as room_desc,
                   COUNT(t.id) as ticket_count,
                   SUM(t.price * (1 - t.discount)) as total_revenue
            FROM tbl_Showtime s
            LEFT JOIN tbl_ScreeningRoom sr ON s.tbl_ScreeningRoomid = sr.id
            LEFT JOIN tbl_Ticket t ON t.tbl_Showtimeid = s.id
            LEFT JOIN tbl_Invoice i ON t.tbl_Invoiceid = i.id
            WHERE s.tbl_Movieid = ? 
            AND s.date_time BETWEEN ? AND ?
            GROUP BY s.id, s.date_time, sr.id, sr.seat_number, sr.description
            ORDER BY s.date_time ASC
        """;

        try (PreparedStatement stmt = dbCon.prepareStatement(sql)) {
            stmt.setInt(1, movie.getID());
            stmt.setDate(2, new java.sql.Date(startDate.getTime()));
            stmt.setDate(3, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ScreeningRoom room = new ScreeningRoom(
                        rs.getInt("room_id"),
                        rs.getInt("seat_number"),
                        rs.getString("room_desc")
                );

                RevenueReportByMovieShowtime report = new RevenueReportByMovieShowtime(
                        rs.getInt("id"),
                        rs.getTimestamp("date_time"),
                        movie,
                        room,
                        rs.getDouble("total_revenue"),
                        rs.getInt("ticket_count")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<RevenueReportByMovieShowtime> getRevenueReportByCinemaShowtime(Date startDate, Date endDate, Cinema cinema) {
        List<RevenueReportByMovieShowtime> reports = new ArrayList<>();
        String sql = """
            SELECT s.id, s.date_time, 
                   sr.id as room_id, sr.seat_number, sr.description as room_desc,
                   m.id as movie_id, m.name as movie_name, m.genre, m.production_year, m.description as movie_desc,
                   COUNT(t.id) as ticket_count,
                   SUM(t.price * (1 - t.discount)) as total_revenue
            FROM tbl_Showtime s
            LEFT JOIN tbl_ScreeningRoom sr ON s.tbl_ScreeningRoomid = sr.id
            LEFT JOIN tbl_Movie m ON s.tbl_Movieid = m.id
            LEFT JOIN tbl_Ticket t ON t.tbl_Showtimeid = s.id
            LEFT JOIN tbl_Invoice i ON t.tbl_Invoiceid = i.id
            WHERE sr.tbl_Cinemaid = ? 
            AND i.date_time BETWEEN ? AND ?
            GROUP BY s.id, s.date_time, sr.id, sr.seat_number, sr.description,
                     m.id, m.name, m.genre, m.production_year, m.description
            ORDER BY s.date_time ASC
        """;

        try (PreparedStatement stmt = dbCon.prepareStatement(sql)) {
            stmt.setInt(1, cinema.getID());
            stmt.setDate(2, new java.sql.Date(startDate.getTime()));
            stmt.setDate(3, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ScreeningRoom room = new ScreeningRoom(
                        rs.getInt("room_id"),
                        rs.getInt("seat_number"),
                        rs.getString("room_desc")
                );

                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        String.valueOf(rs.getInt("genre")),
                        rs.getInt("production_year"),
                        rs.getString("movie_desc")
                );

                RevenueReportByMovieShowtime report = new RevenueReportByMovieShowtime(
                        rs.getInt("id"),
                        rs.getTimestamp("date_time"),
                        movie,
                        room,
                        rs.getDouble("total_revenue"),
                        rs.getInt("ticket_count")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public List<RevenueReportByInvoice> getRevenueReportByInvoice(Date startDate, Date endDate, Showtime showtime) {
        List<RevenueReportByInvoice> reports = new ArrayList<>();
        String sql = """
            SELECT i.id, i.date_time, i.total_price,
                   c.id as cust_id, c.name as cust_name, c.phone,
                   u.id as user_id, u.name as user_name, u.role,
                   COUNT(t.id) as ticket_count,
                   SUM(t.price * (1 - t.discount)) as total_revenue
            FROM tbl_Invoice i
            JOIN tbl_Ticket t ON t.tbl_Invoiceid = i.id
            LEFT JOIN tbl_User u ON i.tbl_Userid = u.id
            LEFT JOIN tbl_Customer c ON i.tbl_Customerid = c.id
            WHERE t.tbl_Showtimeid = ?
            AND i.date_time BETWEEN ? AND ?
            GROUP BY i.id, i.date_time, i.total_price,
                     c.id, c.name, c.phone,
                     u.id, u.name, u.role
            ORDER BY i.date_time DESC
        """;

        try (PreparedStatement stmt = dbCon.prepareStatement(sql)) {
            stmt.setInt(1, showtime.getID());
            stmt.setDate(2, new java.sql.Date(startDate.getTime()));
            stmt.setDate(3, new java.sql.Date(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = null;
                if (rs.getObject("cust_id") != null) {
                    customer = new Customer(
                            rs.getInt("cust_id"),
                            rs.getString("cust_name"),
                            rs.getString("phone")
                    );
                }

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("role")
                );

                RevenueReportByInvoice report = new RevenueReportByInvoice(
                        rs.getInt("id"),
                        rs.getTimestamp("date_time"),
                        rs.getDouble("total_price"),
                        null,
                        user,
                        customer,
                        rs.getDouble("total_revenue"),
                        rs.getInt("ticket_count")
                );
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}