# 📊 Ứng Dụng Quản Lý Chi Tiêu Cá Nhân

## 📝 Giới thiệu
Ứng dụng Quản Lý Chi Tiêu là giải pháp giúp người dùng theo dõi, quản lý các khoản thu chi cá nhân một cách hiệu quả. Ứng dụng được phát triển bằng Spring Boot, cung cấp các API để thực hiện các chức năng quản lý tài chính cá nhân.

## 🔧 Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven
- Cơ sở dữ liệu tương thích (MySQL)

## 🚀 Cài đặt và chạy ứng dụng
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

## 📂 Cấu trúc dự án
```
📦 src/main/java/com/example/quanlychitieuv2
 ┣ 📂 configuation     # Cấu hình ứng dụng, bảo mật
 ┣ 📂 controller       # Xử lý các request từ client
 ┃ ┗ 📂 impl           # Cài đặt chi tiết của các controller
 ┣ 📂 dto              # Đối tượng chuyển đổi dữ liệu
 ┣ 📂 entity           # Các entity mapping với cơ sở dữ liệu
 ┣ 📂 enums            # Các enum định nghĩa giá trị cố định
 ┣ 📂 exception        # Xử lý ngoại lệ
 ┣ 📂 mapper           # Chuyển đổi giữa các đối tượng
 ┣ 📂 repository       # Truy vấn cơ sở dữ liệu
 ┣ 📂 service          # Xử lý logic nghiệp vụ
 ┃ ┗ 📂 impl           # Cài đặt chi tiết của các service
 ┗ 📂 util             # Các tiện ích
```

## 📱 Hướng dẫn sử dụng các chức năng chính

### 👤 1. Quản lý người dùng
- **Đăng ký tài khoản**: Người dùng có thể tạo tài khoản mới để sử dụng ứng dụng.
- **Đăng nhập**: Xác thực người dùng và nhận token xác thực.
- **Quản lý thông tin cá nhân**: Người dùng có thể xem và cập nhật thông tin cá nhân.

### 💰 2. Quản lý ví tiền
- **Tạo ví mới**: Tạo ví tiền với các thông tin như tên ví, loại ví, số dư ban đầu.
- **Xem danh sách các ví**: Xem tất cả các ví tiền của người dùng.
- **Cập nhật thông tin ví**: Chỉnh sửa thông tin ví như tên, loại, số dư.
- **Xóa ví**: Xóa ví tiền không còn sử dụng.

### 💸 3. Quản lý khoản thu
- **Thêm khoản thu**: Ghi nhận các khoản thu nhập với thông tin về số tiền, loại khoản thu, nguồn thu, ngày thu.
- **Xem danh sách khoản thu**: Hiển thị tất cả khoản thu hoặc lọc theo thời gian, loại.
- **Cập nhật khoản thu**: Chỉnh sửa thông tin khoản thu đã tạo.
- **Xóa khoản thu**: Xóa khoản thu khỏi hệ thống.

### 💳 4. Quản lý khoản chi
- **Thêm khoản chi**: Ghi nhận các khoản chi tiêu với thông tin về số tiền, loại khoản chi, mục đích chi, ngày chi.
- **Xem danh sách khoản chi**: Hiển thị tất cả khoản chi hoặc lọc theo thời gian, loại.
- **Cập nhật khoản chi**: Chỉnh sửa thông tin khoản chi đã tạo.
- **Xóa khoản chi**: Xóa khoản chi khỏi hệ thống.

### 🏷️ 5. Quản lý loại khoản thu/chi
- **Thêm loại khoản thu/chi**: Tạo các loại khoản thu/chi để phân loại các khoản thu/chi.
- **Xem danh sách loại**: Hiển thị tất cả loại khoản thu/chi đã tạo.
- **Cập nhật loại**: Chỉnh sửa thông tin loại khoản thu/chi.
- **Xóa loại**: Xóa loại khoản thu/chi không còn sử dụng.

### 📊 6. Chức năng thống kê
- **Thống kê theo ngày**: Xem báo cáo thu chi theo ngày.
- **Thống kê theo tháng**: Xem báo cáo tổng hợp thu chi theo tháng.
- **Thống kê theo năm**: Xem báo cáo tổng quan thu chi theo năm.
- **Thống kê theo ví tiền**: Xem chi tiết thu chi theo từng ví tiền.
- **Biểu đồ trực quan**: Hiển thị dữ liệu thu chi dưới dạng biểu đồ để dễ dàng theo dõi.

### 💹 7. Phân tích tài chính
- **Phân tích xu hướng chi tiêu**: Xem các xu hướng chi tiêu qua các thời kỳ.
- **Cảnh báo chi tiêu vượt mức**: Nhận thông báo khi chi tiêu vượt quá hạn mức đã thiết lập.
- **Gợi ý tiết kiệm**: Nhận các gợi ý để tối ưu hóa chi tiêu và tiết kiệm hiệu quả.

## 🔒 Bảo mật
- Ứng dụng sử dụng JWT (JSON Web Token) để xác thực và phân quyền người dùng.
- Mật khẩu người dùng được mã hóa trước khi lưu vào cơ sở dữ liệu.
- Hệ thống phân quyền chi tiết, đảm bảo người dùng chỉ truy cập được vào dữ liệu của mình.

