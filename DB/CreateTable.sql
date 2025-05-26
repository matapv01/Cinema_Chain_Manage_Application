use cnpm;

CREATE TABLE tbl_Cinema (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    address VARCHAR(125),
    description VARCHAR(255)
);

CREATE TABLE tbl_ScreeningRoom (
    id INT PRIMARY KEY,
    seat_number INT,
    description VARCHAR(255),
    tbl_Cinemaid INT,
    FOREIGN KEY (tbl_Cinemaid) REFERENCES tbl_Cinema(id)
);

CREATE TABLE tbl_Seat (
    id INT PRIMARY KEY,
    number VARCHAR(10),
    tbl_ScreeningRoomid INT,
    FOREIGN KEY (tbl_ScreeningRoomid) REFERENCES tbl_ScreeningRoom(id)
);

CREATE TABLE tbl_Movie (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    genre INT,
    production_year INT,
    description VARCHAR(255)
);

CREATE TABLE tbl_Showtime (
    id INT PRIMARY KEY,
    date_time DATETIME,
    tbl_ScreeningRoomid INT,
    tbl_Movieid INT,
    FOREIGN KEY (tbl_ScreeningRoomid) REFERENCES tbl_ScreeningRoom(id),
    FOREIGN KEY (tbl_Movieid) REFERENCES tbl_Movie(id)
);

CREATE TABLE tbl_Customer (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    phone VARCHAR(25)
);

CREATE TABLE tbl_User (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    role VARCHAR(10)
);

CREATE TABLE tbl_Invoice (
    id INT PRIMARY KEY,
    date_time DATE,
    total_price INT,
    tbl_Customerid INT,
    tbl_Userid INT,
    FOREIGN KEY (tbl_Customerid) REFERENCES tbl_Customer(id),
    FOREIGN KEY (tbl_Userid) REFERENCES tbl_User(id)
);

CREATE TABLE tbl_Product (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    unit_price FLOAT
);

CREATE TABLE tbl_ProductItem (
    id INT PRIMARY KEY,
    quantity INT,
    total_amount FLOAT,
    Productid INT,
    tbl_Invoiceid INT,
    FOREIGN KEY (Productid) REFERENCES tbl_Product(id),
    FOREIGN KEY (tbl_Invoiceid) REFERENCES tbl_Invoice(id)
);

CREATE TABLE tbl_Ticket (
    id INT PRIMARY KEY,
    discount FLOAT,
    price INT,
    tbl_Showtimeid INT,
    tbl_Seatid INT,
    tbl_Invoiceid INT,
    FOREIGN KEY (tbl_Showtimeid) REFERENCES tbl_Showtime(id),
    FOREIGN KEY (tbl_Seatid) REFERENCES tbl_Seat(id),
    FOREIGN KEY (tbl_Invoiceid) REFERENCES tbl_Invoice(id)
);

