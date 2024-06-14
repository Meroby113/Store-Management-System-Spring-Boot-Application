Store Management System
Store Management System is a spring boot application that keeps data about brands, brand products, purchases and coupons. The store owner can store all the information about their products and purchases while perform operations such as adding brands, organising products, keeping track of past/ongoing purchases, and more. The system stores all products in relation to their brands, and the purchase module allows keeping track of product stock and coupons.

Overview of The Project
![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/8881f741-088c-45ba-a8f4-5f438e134449)

![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/a786d01b-8731-49c0-9ed9-f31d69048fb1)

Entity Relationships
![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/cd747012-be26-47b7-9ce9-d59ecb86ad22)

Database Structure
![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/f65bf0ea-6d35-463a-a96a-f8eef34c36aa)

Code Examples
![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/5462e00e-7e1d-4413-9255-14b7a9979137)
The code above is a small part of the PurchaseController class. The first function “createPurchase” is a basic one that starts the purchasing process. It gets a purchase object using the Post method, and then saves it to the database.
The second function continues the purchasing process by adding the products to our shopping cart. It obtains the purchaseId, productId and buyCount from the path url. We still used the Post method to avoid accidental data writes when using basic browsers. The function gets the purchase and product objects using their Ids, and then we perform a couple if-checks to ensure that the information we have is correct and applicable. Then we decrement the currentStock values of the given product to manage our inventory correctly, and after that we finally add the product(s) to the purchase object. We finally perform flush() operations on our repositories to make sure our database gets updated.

![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/3a783ce2-6148-41c9-8897-355b1564d814)
In our addCouponToPurchase function we get purchaseId and couponId from the user as path variables. We use post method again to ensure that a basic browser can’t perform this important operation. The function performs multiple checks to ensure a smooth operation, and then just adds the coupon to the purchase. flush() methods are used to update the database.

![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/67848b3e-c83d-43ec-8d44-19fd918eb9ba)
The purchasing process can come to an end with the completePurchase function. The purchase is found using the id, and necessary checks are done to ensure that the purchase object exists while being ready to be completed. Then we add up the total cost of the items, apply a coupon if the purchase object has it defined, set the purchase as completed, and flush() to update our database.

Output Examples
![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/d5acd014-59f6-4e23-b52c-1946c13abf6c)
The json file on the left is the result of following operations: 
- POST: http://localhost:8080/purchases/create
(used 3 times to create each “purchase”)
- POST: http://localhost:8080/purchases/1/addProduct/4/1
- GET: http://localhost:8080/purchases
(the output lists the contents of our “purchases” table)

![image](https://github.com/Meroby113/Store-Management-Systema-spring-boot-application-/assets/91911696/704129af-8e12-4847-a932-bf7ff8e78023)

The second example is our “products” table. We used the following operations to get it to its current output. 
- POST: http://localhost:8080/brands/create
(used 3 times to create each brand)
- POST: http://localhost:8080/brands/1/createProduct
(used 4 times to create each product)
- GET: http://localhost:8080/products
(the output lists the contents of our “products” table)









