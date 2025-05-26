use cnpm;

INSERT INTO tbl_Cinema VALUES 
(1, 'CGV Vincom', '72 Le Thanh Ton, Q1', 'Rạp trung tâm sang chảnh'),
(2, 'Lotte Landmark', '720A Dien Bien Phu, Binh Thanh', 'Rạp lớn chuẩn Hàn Quốc'),
(3, 'Galaxy Kinh Duong Vuong', '718 Kinh Duong Vuong, Q6', 'Rạp giá rẻ sinh viên');

INSERT INTO tbl_ScreeningRoom VALUES 
(1, 150, 'IMAX Hall', 1),
(2, 100, 'Dolby Cinema', 1),
(3, 80, '4DX Room', 2),
(4, 120, 'Standard Hall', 3);

INSERT INTO tbl_Movie VALUES 
(1, 'Avatar 3', 1, 2025, 'Kỳ quan điện ảnh 3D'),
(2, 'John Wick 5', 2, 2025, 'Hành động cực mạnh'),
(3, 'Inside Out 2', 2, 2024, 'Hoạt hình gia đình');

INSERT INTO tbl_Showtime VALUES 
(1, '2025-06-01 18:00', 1, 1), -- Avatar 3 @ CGV Vincom
(2, '2025-06-01 20:30', 2, 2), -- John Wick 5 @ CGV Vincom
(3, '2025-06-02 17:00', 3, 3), -- Inside Out 2 @ Lotte Landmark
(4, '2025-06-02 19:00', 4, 3); -- Inside Out 2 @ Galaxy KDV

INSERT INTO tbl_Customer VALUES 
(1, 'Nguyen Van A', '0909000111'),
(2, 'Le Thi B', '0909000222'),
(3, 'Tran Van C', '0909000333');

INSERT INTO tbl_User VALUES 
(1, 'Nhan Vien 1', 'staff'),
(2, 'Nhan Vien 2', 'staff');

INSERT INTO tbl_Product VALUES 
(1, 'Bắp rang bơ', 50000),
(2, 'Coca-Cola', 30000),
(3, 'Pepsi', 30000);

INSERT INTO tbl_Invoice VALUES 
(1, '2025-06-01', 300000, 1, 1),
(2, '2025-06-02', 450000, 2, 2),
(3, '2025-06-02', 400000, 3, 1);

INSERT INTO tbl_ProductItem VALUES 
(1, 2, 100000, 1, 1), -- 2 bắp
(2, 2, 60000, 2, 1),  -- 2 coca
(3, 3, 90000, 3, 2),  -- 3 pepsi
(4, 1, 50000, 1, 3);  -- 1 bắp

INSERT INTO tbl_Seat VALUES 
(1, 'A1', 1),  -- IMAX Hall @ CGV Vincom
(2, 'A2', 1),  -- IMAX Hall @ CGV Vincom
(3, 'B1', 2),  -- Dolby Cinema @ CGV Vincom
(4, 'B2', 3),  -- 4DX Room @ Lotte Landmark
(5, 'C1', 4),  -- Standard Hall @ Galaxy KDV
(6, 'C2', 1);  -- IMAX Hall @ CGV Vincom (cho trường hợp Invoice 3 mua vé suất khác)


INSERT INTO tbl_Ticket VALUES 
(1, 0.0, 120000, 1, 1, 1), -- Avatar 3 @ CGV Vincom (A1)
(2, 0.1, 108000, 1, 2, 1), -- Avatar 3 @ CGV Vincom (A2, -10%)
(3, 0.0, 120000, 2, 1, 2), -- John Wick 5 @ CGV Vincom (A1)

(4, 0.0, 90000, 3, 1, 2), -- Inside Out 2 @ Lotte Landmark (B1)
(5, 0.0, 90000, 3, 2, 2), -- Inside Out 2 @ Lotte Landmark (B2)

(6, 0.05, 114000, 4, 1, 3), -- Inside Out 2 @ Galaxy KDV (C1, -5%)
(7, 0.0, 120000, 1, 3, 3);  -- Avatar 3 @ CGV Vincom (C2) → khác suất nhưng cùng Invoice 3

