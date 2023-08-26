# Prism Mart
---
'Prism Mart' is an ecommerce app developed using Android 
Studio and Firebase. There are two segments. Customer and administrative segment. A store owner who has this role can use the app to manage several aspects of their business, including orders, sales track, and product information (by uploading new products, deleting existing ones, or updating existing ones). On the other side, a person can register as a customer and login to the app using the proper credentials. A customer can search for a certain product, add it to their cart. When placing a order, he or she can add their address through Google Maps.Through the myorder page, a customer can view all of his prior orders. The setting page allows both the admin and the customer to change their login credentials and other information. There is a password reset option in the sign in page  if the user forgets their password.
 All of the information about the user, the product, the order, and the cart is handled using the Firebase database.



### App Specification
 ```
. Language- Java
. Platform -Android Studio
. API Level - Android 10 (API level 29) 
. Permissions – Internet, Storage,Location
. Screen Orientations: Portrait

 ```

 <br/>

--- 

_Here are some images showing this project's features_

<br/>

 &emsp; &emsp;  &emsp; &emsp; &nbsp; &nbsp; &nbsp; &nbsp; ![login collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/b29f967d-b7c4-481f-bcdf-a2695400979f)

By providing the necessary information, including their username and password, a person can open a new account as a customer. A valid email address, an 11-digit phone number, and a password with at least six characters must all be provided by users in order to complete the registration process.
.Then he/she can login to the app as a customer once they've successfully created an account.


&nbsp; ![Slider Collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/27c776f6-7bb1-40df-9ecc-be2ea7193126)

A sliding page will appear for the user  after they have successfully logged into it.

 &emsp; &emsp;  &emsp; &emsp;  &emsp; &emsp; ![Homepage collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/2fc93875-973c-4a51-9251-d7cf59aa8f73)

Providing information quickly and simply is homepage main goal. The homepage will have a page all to itself and will get its data from a linked database.It displays special offers, sales, limited-time discounts, and popular products and product categories.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Customer nav](https://github.com/nayemuddinn/PrismMart/assets/126597905/7371234f-5a68-4efb-9d2a-26ffd7575efd)

The top of a navigation bar displays the user's kind and name. There are also buttons for easily accessing the homepage, cart, order information, and setting.


&emsp; &emsp; &emsp;  &emsp; &emsp; ![Category collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/99092bca-c0cc-4b77-8b61-94d9dd9a3a07)

On the homepage, all products are arranged according to category, allowing users to view all products in a given category.


&emsp; &emsp; &emsp;  &emsp; &emsp; ![search and cart collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/32c82e72-3a33-4a20-8fe9-113f8bee9efe)

Customers can use the search menu to look for any specific goods. The next page displays product information such as the product id, category, name, and description. The customer can choose the quantity and either place the item in their shopping cart or buy it now


&emsp; &emsp; &emsp;  &emsp; ![Cart collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/3abf29fc-29d5-4baf-b019-94f627e256c1)

All of the items that were added to the cart will be displayed there along with the final bill that must be paid. The customer can select their address from Google Map.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![My orders](https://github.com/nayemuddinn/PrismMart/assets/126597905/01f62175-cf1f-410c-bc71-185e77d4f314)

From the "My Orders" tab, a customer can view all of his or her prior orders, along with their order number, time and date, total payment, and provided address.



&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Logged out](https://github.com/nayemuddinn/PrismMart/assets/126597905/ed170892-294a-4e20-b8a1-a3434f00fe79)

User may log out of the application.



&emsp; &emsp; &emsp;  &emsp; &emsp; ![Admin login collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/866c2088-636c-4aee-8b15-ec2f1bc2a9f2)

Admin can log in from the same page as well. Admins have more options than customers have. Admin may keep track of all sales, update any product, and add or remove already-existing products.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![All sales](https://github.com/nayemuddinn/PrismMart/assets/126597905/2c0e69f7-618d-4679-93ca-b3fc4af726a1)

All order information will be stored here with the date, order number and revenue.



&emsp; &emsp; &emsp;  &emsp; &emsp; &emsp; &emsp;  &emsp; &emsp; ![add product collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/144a7018-bcf5-4331-aacf-94e390c796b1)

Admin can add new products on this page. The product's ID, name, category, unit, price, and image can all be set by the admin.


&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![update Product](https://github.com/nayemuddinn/PrismMart/assets/126597905/b615f3ec-7e77-471f-aac0-64a6c2fbd4fa)

Using the product ID, the admin can view, delete, or update any product information on this page. The homepage will be updated right away with the changes.





&emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; &emsp; ![Setting](https://github.com/nayemuddinn/PrismMart/assets/126597905/3d9a2199-ed06-418a-80ef-0ac07abc8890)

Both the customer and the admin can update their login credentials as well as other information in settings. The session will expire if any of them change their email or password, and they will need to log in again. 



&emsp; &emsp; &emsp;  &emsp; &emsp;  &emsp; ![reset pass collage](https://github.com/nayemuddinn/PrismMart/assets/126597905/0680cab5-dd4b-4e47-9b9f-016adc1e42bd)

If a user forgets their password, they can reset it by entering their email address on the forget password page. They will receive a password reset email.

---
