# saleappv1

Bài thực hành Spring Boot - website bán hàng mini.

## Công nghệ
- Java 17+
- Spring Boot
- Spring Web
- Thymeleaf
- Jackson JSON

## Chạy bằng VS Code

Yêu cầu:
- JDK 17+
- Maven 3.9+

Mở terminal tại thư mục project:

```bash
mvn spring-boot:run
```

Sau đó mở:

http://localhost:8080/

Các URL:
- `/`
- `/products`
- `/products/1`
- `/products?categoryId=1`
- `/products?categoryId=2`
- `/products?keyword=iphone`
- `/products?fromPrice=5000000&toPrice=20000000`
