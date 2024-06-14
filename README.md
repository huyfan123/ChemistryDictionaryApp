Ứng dụng Từ điển Hóa học
Giới thiệu
Ứng dụng Từ điển Hóa học là một công cụ hỗ trợ học tập và nghiên cứu hóa học, cung cấp thông tin chi tiết về các thuật ngữ hóa học và bảng tuần hoàn hóa học. Ứng dụng này được phát triển bằng Java với Java Swing cho giao diện người dùng và sử dụng SQL Server để quản lý và truy vấn cơ sở dữ liệu.

Tính năng
Chức năng dành cho Người dùng
Tra cứu thuật ngữ:

Nhập từ khóa để tra cứu các thuật ngữ hóa học.
Hiển thị danh sách kết quả tìm kiếm liên quan.
Hiển thị chi tiết thuật ngữ:

Cung cấp thông tin chi tiết về thuật ngữ bao gồm định nghĩa, ví dụ minh họa, hình ảnh và liên kết tham khảo.
Lưu thuật ngữ yêu thích:

Đánh dấu các thuật ngữ quan trọng để lưu vào danh sách yêu thích.
Truy cập danh sách các thuật ngữ yêu thích từ giao diện chính.
Tìm kiếm thông minh:

Gợi ý từ khóa và sửa lỗi chính tả khi nhập từ khóa.
Xem bảng tuần hoàn hóa học:

Truy cập bảng tuần hoàn hóa học để xem thông tin chi tiết về các nguyên tố hóa học.
Hiển thị thông tin chi tiết như số nguyên tử, ký hiệu hóa học, khối lượng nguyên tử, tính chất hóa học và các thông tin liên quan khác.
Yêu cầu hệ thống
Java Development Kit (JDK) 8 trở lên
SQL Server
IDE hỗ trợ Java (Eclipse, IntelliJ IDEA, NetBeans, ...)
Cài đặt
Bước 1: Clone Repository
sh
Sao chép mã
git clone https://github.com/yourusername/chemical-dictionary.git
cd chemical-dictionary
Bước 2: Thiết lập cơ sở dữ liệu
Tạo cơ sở dữ liệu SQL Server và nhập cấu trúc bảng từ tệp schema.sql.
Cập nhật thông tin kết nối cơ sở dữ liệu trong tệp cấu hình application.properties (Spring Boot).
Bước 3: Chạy ứng dụng
Mở dự án trong IDE.
Biên dịch và chạy ứng dụng.
Sử dụng
Tra cứu thuật ngữ: Sử dụng thanh tìm kiếm để nhập từ khóa và tìm kiếm các thuật ngữ hóa học.
Xem chi tiết: Nhấp vào thuật ngữ trong kết quả tìm kiếm để xem thông tin chi tiết.
Lưu yêu thích: Nhấp vào biểu tượng yêu thích để lưu thuật ngữ vào danh sách yêu thích.
Bảng tuần hoàn hóa học: Truy cập bảng tuần hoàn từ menu chính để xem thông tin các nguyên tố.
Góp ý và Phát triển
Nếu bạn muốn đóng góp cho dự án, vui lòng fork repository này và gửi pull request. Các ý kiến đóng góp và báo lỗi cũng được hoan nghênh thông qua mục Issues trên GitHub.

