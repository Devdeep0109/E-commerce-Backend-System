## E-Commerce Backend (Spring Boot)
A RESTful E-commerce backend built using Spring Boot. The application supports user registration, product and category management, cart operations, order checkout, and currency conversion using an external exchange rate API.
 
### Tech Stack
- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Jakarta Bean Validation
- External Currency Exchange API
## Environment Configuration
Sensitive values are managed using environment variables.
- spring.application.name=E-commerce
- spring.datasource.url=${DB_URL}
- spring.datasource.username=${DB_USERNAME}
- spring.datasource.password=${DB_PASSWORD}
- spring.jpa.hibernate.ddl-auto=update
- exchange.api.base-url=${EXCHANGE_API_BASE_URL}
- exchange.api.key=${EXCHANGE_API_KEY}
- exchange.api.base-currency=${EXCHANGE_API_BASE_CURRENCY}
**Required Variables**
- DB_URL
- DB_USERNAME
- DB_PASSWORD
- EXCHANGE_API_BASE_URL
- EXCHANGE_API_KEY
- EXCHANGE_API_BASE_CURRENCY
## Currency Exchange API Setup
**This project uses exchangerate-api.com for currency conversion during checkout.**
Generate an API key from: `https://www.exchangerate-api.com/`
Example endpoint: `https://v6.exchangerate-api.com/v6/YOUR_KEY/latest/USD`
 
## API Overview (with Paths)
 
### User
**Register User**
`POST http://localhost:8080/user/register`
 
### Category
**Create Category**
`POST http://localhost:8080/categories/createcategory`
 
### Product
**Create Product**
`POST http://localhost:8080/product/createProduct`
 
**Update Product Stock**
`PUT http://localhost:8080/product/2/stock`
 
### Cart
 
**Add Product to Cart**
`POST http://localhost:8080/cart/addtocart`
**View Cart by User**
`GET http://localhost:8080/cart/viewcart/2`
**Remove Cart Item**
`DELETE http://localhost:8080/cart/removecartitem/2`
 
### Order
**Checkout Cart**
`POST http://localhost:8080/order/checkout`
**Get Orders by User**
`GET http://localhost:8080/order/getOrders/3`

## How to Run
1. Clone the repository
2. git clone https://github.com/your-username/e-commerce-backend.git
3. Set environment variables
4. DB_URL=jdbc:mysql://localhost:3306/ecommerce
5. DB_USERNAME=root
6. DB_PASSWORD=your_password
7. Run the application
8. mvn spring-boot:run
