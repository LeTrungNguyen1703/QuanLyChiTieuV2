# Ứng Dụng Quản Lý Chi Tiêu Cá Nhân

## Giới thiệu
Ứng dụng Quản Lý Chi Tiêu là giải pháp giúp người dùng theo dõi, quản lý các khoản thu chi cá nhân một cách hiệu quả. Ứng dụng được phát triển bằng Spring Boot, cung cấp các API để thực hiện các chức năng quản lý tài chính cá nhân.

## Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven
- Cơ sở dữ liệu tương thích (MySQL/PostgreSQL)

## Cài đặt và chạy ứng dụng
1. Clone dự án về máy của bạn:
```
git clone <đường-dẫn-tới-repository>
cd QuanLyChiTieuV2
```

2. Cấu hình kết nối cơ sở dữ liệu trong file `application.properties` hoặc `application.yml`

3. Chạy lệnh Maven để build dự án:
```
mvn clean install
```

4. Chạy ứng dụng:
```
mvn spring-boot:run
```

5. Truy cập ứng dụng tại: `http://localhost:8080`

## Hướng dẫn sử dụng các chức năng chính

### 1. Quản lý người dùng
- **Đăng ký tài khoản**: Người dùng có thể tạo tài khoản mới để sử dụng ứng dụng.
- **Đăng nhập**: Xác thực người dùng và nhận token xác thực.
- **Quản lý thông tin cá nhân**: Người dùng có thể xem và cập nhật thông tin cá nhân.

### 2. Quản lý ví tiền
- **Tạo ví mới**: Tạo ví tiền với các thông tin như tên ví, loại ví, số dư ban đầu.
- **Xem danh sách các ví**: Xem tất cả các ví tiền của người dùng.
- **Cập nhật thông tin ví**: Chỉnh sửa thông tin ví như tên, loại, số dư.
- **Xóa ví**: Xóa ví tiền không còn sử dụng.

### 3. Quản lý khoản thu
- **Thêm khoản thu**: Ghi nhận các khoản thu nhập với thông tin về số tiền, loại khoản thu, nguồn thu, ngày thu.
- **Xem danh sách khoản thu**: Hiển thị tất cả khoản thu hoặc lọc theo thời gian, loại.
- **Cập nhật khoản thu**: Chỉnh sửa thông tin khoản thu đã tạo.
- **Xóa khoản thu**: Xóa khoản thu khỏi hệ thống.

### 4. Quản lý khoản chi
- **Thêm khoản chi**: Ghi nhận các khoản chi tiêu với thông tin về số tiền, loại khoản chi, mục đích chi, ngày chi.
- **Xem danh sách khoản chi**: Hiển thị tất cả khoản chi hoặc lọc theo thời gian, loại.
- **Cập nhật khoản chi**: Chỉnh sửa thông tin khoản chi đã tạo.
- **Xóa khoản chi**: Xóa khoản chi khỏi hệ thống.

### 5. Quản lý loại khoản thu/chi
- **Thêm loại khoản thu/chi**: Tạo các loại khoản thu/chi để phân loại các khoản thu/chi.
- **Xem danh sách loại**: Hiển thị tất cả loại khoản thu/chi đã tạo.
- **Cập nhật loại**: Chỉnh sửa thông tin loại khoản thu/chi.
- **Xóa loại**: Xóa loại khoản thu/chi không còn sử dụng.

### 6. Quản lý phương thức thanh toán
- **Thêm phương thức thanh toán**: Tạo các phương thức thanh toán như tiền mặt, chuyển khoản, thẻ tín dụng...
- **Xem danh sách phương thức**: Hiển thị tất cả phương thức thanh toán đã tạo.
- **Cập nhật phương thức**: Chỉnh sửa thông tin phương thức thanh toán.
- **Xóa phương thức**: Xóa phương thức thanh toán không còn sử dụng.

### 7. Quản lý hạn mức chi
- **Thiết lập hạn mức chi**: Đặt hạn mức chi tiêu cho các loại chi tiêu khác nhau.
- **Theo dõi hạn mức**: Xem tình trạng sử dụng hạn mức chi tiêu.
- **Cập nhật hạn mức**: Điều chỉnh hạn mức chi tiêu đã thiết lập.

### 8. Thống kê tài chính
- **Thống kê khoản thu theo ngày**: Xem tổng thu và số giao dịch theo từng ngày.
- **Thống kê khoản thu theo tháng**: Xem tổng thu, thu cao nhất, thu thấp nhất, thu trung bình và số giao dịch trong một tháng, bao gồm thống kê chi tiết theo từng ngày.
- **Thống kê khoản thu theo năm**: Xem thống kê khoản thu cho từng tháng trong một năm cụ thể, cho phép người dùng phân tích xu hướng thu nhập theo thời gian.
- **Phân tích dữ liệu**: Hệ thống tự động tính toán và hiển thị các chỉ số thống kê quan trọng như tổng thu, thu cao nhất, thu thấp nhất, thu trung bình để người dùng có cái nhìn tổng quan về tình hình tài chính.
- **Thống kê theo ví tiền**: Người dùng có thể xem thống kê riêng cho từng ví tiền, giúp quản lý nguồn thu chi cho từng mục đích khác nhau.

## Sử dụng API qua Postman
Dự án cung cấp các tệp Postman để dễ dàng kiểm thử API:
- `AuthenticationPostman.json`: API xác thực người dùng
- `UserPostman.json`: API quản lý người dùng
- `ViTienPostman.json`: API quản lý ví tiền
- `KhoanThuPostman.json`: API quản lý khoản thu
- `LoaiKhoanThuPostman.json`: API quản lý loại khoản thu
- `LoaiViPostman.json`: API quản lý loại ví
- `PhuongThucThanhToanPostman.json`: API quản lý phương thức thanh toán
- `RolePostman.json`: API quản lý vai trò người dùng
- `PermissionPostman.json`: API quản lý quyền hạn

## Phân quyền và bảo mật
Ứng dụng sử dụng Spring Security và OAuth2 để xác thực và phân quyền. Người dùng cần đăng nhập để nhận token JWT và sử dụng token này cho các yêu cầu API sau đó.