# Prism Mart
---
'Prism Mart' is an ecommerce app developed using Android 
Studio and Firebase. There are two segments. Customer and Admin segment. An admin who has this role can use the app to manage several aspects of their business, including orders, sales track, and product information (by uploading new products, deleting existing ones, or updating existing ones). On the other side, a person can register as a customer and login to the app using the proper credentials. A customer can search for a certain product, add it to their cart. When placing a order, he or she can add their address through Google Maps.Through the myorder page, a customer can view all of his prior orders. The setting page allows both the admin and the customer to change their login credentials and other information. There is a password reset option in the sign in page  if the user forgets their password.
 All of the information about the user, the product, the order, and the cart is handled using the Firebase database.



### App Specification
 ```
. Language     - Java
. Platform     - Android Studio
. API Level    - Android 10 (API level 29)
. Backend      - Firebase
. Permissions  – Internet, Storage,Location
. Screen Orientations - Portrait

 ```

 <br/>

--- 

_Here are some images showing this app's features and UI_

<br/>

 &emsp; &emsp;  &emsp; &emsp; &nbsp; &nbsp; &nbsp; &nbsp;![login collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/39ec1078-44ab-42b6-a1f5-f3990d9a058e)


By providing the necessary information, including their username and password, a person can open a new account as a customer. A valid email address, an 11-digit phone number, and a password with at least six characters must all be provided by users in order to complete the registration process.
.Then he/she can login to the app as a customer once they've successfully created an account.


&nbsp; ![Slider Collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/ef3b5f35-3064-44a3-921b-3a4dc897dbf5)


A sliding page will appear for the user  after they have successfully logged into it.

 &emsp; &emsp;  &emsp; &emsp;  &emsp; &emsp; ![Homepage collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/6ef04378-7986-4655-8490-98dec4a7e924)


Providing information quickly and simply is homepage main goal. The homepage will have a page all to itself and will get its data from a linked database.It displays special offers, sales, limited-time discounts, and popular products and product categories.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Customer nav](https://github.com/nayemuddinn/PrismMart/assets/126597905/fe5844ff-546d-4ceb-9e88-83e93b19b213)


The top of a navigation bar displays the user's kind and name. There are also buttons for easily accessing the homepage, cart, order information, and setting.


&emsp; &emsp; &emsp;  &emsp; &emsp; ![Category collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/c085c10a-2b07-4cce-9835-045444277f19)


On the homepage, all products are arranged according to category, allowing users to view all products in a given category.


&emsp; &emsp; &emsp;  &emsp; &emsp; ![search and cart collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/338e11c2-72da-4500-b4ea-15765e7cde63)

Customers can use the search menu to look for any specific goods. The next page displays product information such as the product id, category, name, and description. The customer can choose the quantity and either place the item in their shopping cart or buy it now


&emsp; &emsp; &emsp;  &emsp; ![Cart collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/93876346-3dcb-4e13-a7e9-df4d1905f714)


All of the items that were added to the cart will be displayed there along with the final bill that must be paid. The customer can select their address from Google Map.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![My orders](https://github.com/nayemuddinn/PrismMart/assets/126597905/49e330f0-0123-47d2-abde-5759be8ca391)


From the "My Orders" tab, a customer can view all of his or her prior orders, along with their order number, time and date, total payment, and provided address.



&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Logged out](https://github.com/nayemuddinn/PrismMart/assets/126597905/82cd4023-c77d-4f8b-87f3-ac8125d31fbe)


User may log out of the application.



&emsp; &emsp; &emsp;  &emsp; &emsp; ![Admin login collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/e8127565-5b8d-4137-9cf7-0abea7029743)


Admin can log in from the same page as well. Admins have more options than customers have. Admin may keep track of all sales, update any product, and add or remove already-existing products.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![All sales](https://github.com/nayemuddinn/PrismMart/assets/126597905/e35d0727-f3be-4b0b-ab4e-6414b3d7dc2e)


All order information will be stored here with the date, order number and revenue.



&emsp; &emsp; &emsp;  &emsp; &emsp; &emsp; &emsp;  &emsp; &emsp; ![add product collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/84a4f0ed-47e2-45a5-8dc5-6b66f087392e)


Admin can add new products on this page. The product's ID, name, category, unit, price, and image can all be set by the admin.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![update Product](https://github.com/nayemuddinn/PrismMart/assets/126597905/ee284637-1431-45bd-9214-8fc4024869d4)


Using the product ID, the admin can view, delete, or update any product information on this page. The homepage will be updated right away with the changes.





&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Setting](https://github.com/nayemuddinn/PrismMart/assets/126597905/f8cec190-a3ee-4772-b38f-23fee2a58a41)


Both the customer and the admin can update their login credentials as well as other information in settings. The session will expire if any of them change their email or password, and they will need to log in again. 



&emsp; &emsp; &emsp;  &emsp; &emsp;  &emsp; ![reset pass collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/e124fb50-987c-447d-b79e-40118a39cc76)


If a user forgets their password, they can reset it by entering their email address on the forget password page. They will receive a password reset email.

---
