use cnpm;
-- Cinema
INSERT INTO tbl_Cinema VALUES (1, 'Galaxy Cinema', '123 Le Loi, Q1', 'Rạp chiếu phim lớn ở trung tâm TP');
INSERT INTO tbl_Cinema VALUES (2, 'CGV Crescent Mall', '101 Ton Dat Tien, Q7', 'Rạp chuẩn quốc tế');

-- ScreeningRoom
INSERT INTO tbl_ScreeningRoom VALUES (1, 100, 'Phòng chiếu chính', 1);
INSERT INTO tbl_ScreeningRoom VALUES (2, 80, 'Phòng chiếu VIP', 2);

-- Seat
INSERT INTO tbl_Seat VALUES (1, 'A1', 1);
INSERT INTO tbl_Seat VALUES (2, 'A2', 1);
INSERT INTO tbl_Seat VALUES (3, 'B1', 2);

-- Movie
INSERT INTO tbl_Movie VALUES (1, 'Avengers: Endgame', 1, 2019, 'Bom tấn siêu anh hùng');
INSERT INTO tbl_Movie VALUES (2, 'Dune Part 2', 2, 2024, 'Sci-fi epic');

-- Showtime
INSERT INTO tbl_Showtime VALUES (1, '2025-05-14 19:00', 1, 1);
INSERT INTO tbl_Showtime VALUES (2, '2025-05-15 20:00', 2, 2);

-- Customer
INSERT INTO tbl_Customer VALUES (1, 'Nguyen Van A', '0909123456');
INSERT INTO tbl_Customer VALUES (2, 'Tran Thi B', '0988123456');

-- User (nhân viên bán vé)
INSERT INTO tbl_User VALUES (1, 'Le Van C', 'staff');
INSERT INTO tbl_User VALUES (2, 'Pham Thi D', 'staff');

-- Invoice
INSERT INTO tbl_Invoice VALUES (1, '2025-05-14', 250000, 1, 1);
INSERT INTO tbl_Invoice VALUES (2, '2025-05-15', 300000, 2, 2);

-- Product (bắp, nước)
INSERT INTO tbl_Product VALUES (1, 'Bắp rang bơ', 50000);
INSERT INTO tbl_Product VALUES (2, 'Coca-Cola', 30000);

-- ProductItem (trong hóa đơn có bán bắp + nước)
INSERT INTO tbl_ProductItem VALUES (1, 2, 100000, 1, 1); -- 2 bắp 50k
INSERT INTO tbl_ProductItem VALUES (2, 2, 60000, 2, 1);  -- 2 coca 30k
INSERT INTO tbl_ProductItem VALUES (3, 1, 50000, 1, 2);  -- 1 bắp 50k

-- Ticket
INSERT INTO tbl_Ticket VALUES (1, 0.0, 100000, 1, 1, 1);
INSERT INTO tbl_Ticket VALUES (2, 0.1, 90000, 1, 2, 1);  -- giảm giá 10%
INSERT INTO tbl_Ticket VALUES (3, 0.0, 120000, 2, 3, 2);
