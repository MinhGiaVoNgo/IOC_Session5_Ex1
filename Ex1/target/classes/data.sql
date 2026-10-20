-- Tạo database
CREATE DATABASE IF NOT EXISTS course_management;
USE course_management;

-- Bảng courses sẽ được tự động tạo bởi Hibernate (ddl-auto=update)
-- Script này dùng để thêm dữ liệu mẫu thủ công nếu cần

INSERT INTO courses (title, description, instructor, price) VALUES
('Java Spring Boot', 'Khóa học Spring Boot cơ bản', 'Nguyen Van A', 500000),
('Angular Frontend', 'Khóa học Angular từ cơ bản đến nâng cao', 'Tran Van B', 600000),
('React JS', 'Khóa học React JS cho người mới bắt đầu', 'Le Thi C', 450000),
('Database MySQL', 'Khóa học MySQL chuyên sâu', 'Pham Van D', 350000),
('Docker & Kubernetes', 'Khóa học container hóa ứng dụng', 'Hoang Van E', 700000);
