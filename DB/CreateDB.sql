-- Kiểm tra và tạo database cnpm (SQL Server chuẩn)
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'cnpm')
BEGIN
    CREATE DATABASE cnpm;
    PRINT 'Database cnpm created';
END
ELSE
BEGIN
    PRINT 'Database cnpm already exists';
END;

GO