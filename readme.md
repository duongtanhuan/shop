
 Project structure
  * configuration: config package.
  * controller: api package.
  * dto: tranfer data package.
  * exception: throw exception package.
  * mapper: convert object package.
  * model: entity package.
  * repository. 
  * security: authentication and authorization package. 
  * service: business logic interface.
  * severvice implement: business logic implement.

Project enviroment
 
| Middleware | Version    | Remark   |
| :---:   | :---: | :---: |
| JDK | 11.0.18+   | 11.0.18 and above  |
| MySQL | 8.0+   | 8.0 and above   |


Technology stack

| Component | Version    | Remark   |
| :---:   | :---: | :---: |
| Spring boot | 2.7.9   | Latest release stable version  |
| JWT    | 0.9.1   | Latest release stable version |
| Mapstruct    | 1.5.3.Final   | Latest release stable version |
| Lombok    | 1.18.26   | Latest release stable version |
| Spring framework    | 2.7.9   | Latest release stable version |
| Validation    | 2.0.1.FInal   | Latest release stable version |
| Junit    | 4.13.2   | Latest release stable version |
| Mockito    | 4.5.1   | Latest release stable version |

Database connection

* spring.datasource.url=jdbc:mysql://localhost:3306/shop?useSSL=false
* spring.datasource.username=root
* spring.datasource.password=123456
* spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
* spring.jpa.database-platform = org.hibernate.dialect.MySQL8Dialect

Application port
* server.port=8080

API

Item

| Mehtod name | HTTP    | API   |
| :---:   | :---: | :---: |
| getItems | GET  | localhost:8080/items   |
| addItem | POST  | localhost:8080/items   |
| updateItem | PUT  | localhost:8080/items   |
| deleteItem | DELTE  | localhost:8080/items/{id}   |

Cart

| Mehtod name | HTTP    | API   |
| :---:   | :---: | :---: |
| findCartByCustomerId | GET  | localhost:8080/carts/{customerId} |
| addItemsTocart | POST  | localhost:8080/carts   |
| updateItemsTocart | PUT  | localhost:8080/carts   |
| deleteItemInCartByCartDetailId | DELETE  | localhost:8080/carts/{cartDetailId}   |
 
 
 
Order

| Mehtod name | HTTP    | API   |
| :---:   | :---: | :---: |
| getOrdersAllByCustomerId | GET  | localhost:8080/orders/{customerId} |
| createOrder | POST  | localhost:8080/orders   |
| getLatestOrderByCustomerId | POST  | localhost:8080/orders   |
| getPendingOrdersByStatus | GET  | localhost:8080/orders/pendingOrders |

Auth

| Mehtod name | HTTP    | API   |
| :---:   | :---: | :---: |
| login | POST  | localhost:8080/auth/login |
| signup | POST  | localhost:8080/auth/signup  |


Cart detail

| Mehtod name | HTTP    | API   |
| :---:   | :---: | :---: |
| findCartDetailByItemId | GET  | localhost:8080/cartDetails/{itemId} |


