-- Drop child tables first (foreign key dependent tables)
DROP TABLE IF EXISTS tbl_Ticket;
DROP TABLE IF EXISTS tbl_ProductItem;
DROP TABLE IF EXISTS tbl_Invoice;
DROP TABLE IF EXISTS tbl_Showtime;
DROP TABLE IF EXISTS tbl_Seat;
DROP TABLE IF EXISTS tbl_ScreeningRoom;

-- Drop master tables
DROP TABLE IF EXISTS tbl_Product;
DROP TABLE IF EXISTS tbl_Movie;
DROP TABLE IF EXISTS tbl_Customer;
DROP TABLE IF EXISTS tbl_User;
DROP TABLE IF EXISTS tbl_Cinema;
