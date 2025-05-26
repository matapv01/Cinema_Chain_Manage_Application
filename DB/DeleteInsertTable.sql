USE cnpm;

-- Xóa bảng con trước (theo thứ tự quan hệ)
DELETE FROM tbl_ProductItem;
DELETE FROM tbl_Ticket;
DELETE FROM tbl_Invoice;

DELETE FROM tbl_Product;
DELETE FROM tbl_Customer;
DELETE FROM tbl_User;

DELETE FROM tbl_Showtime;
DELETE FROM tbl_Movie;
DELETE FROM tbl_Seat;
DELETE FROM tbl_ScreeningRoom;
DELETE FROM tbl_Cinema;
