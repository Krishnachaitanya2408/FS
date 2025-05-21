/*


Customers:
==========
Field   Type    Null    Key     Default Extra                                                                           
CustomerID      int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Email   varchar(255)    YES             NULL                                                                            
Address varchar(255)    YES             NULL                                                                            
PhoneNumber     varchar(20)     YES             NULL                                                                    

Orders:
=======
Field   Type    Null    Key     Default Extra                                                                           
OrderID int     NO      PRI     NULL                                                                                    
CustomerID      int     YES     MUL     NULL                                                                            
OrderDate       date    YES             NULL                                                                            
TotalCost       decimal(10,2)   YES             NULL                                                                    
Status  varchar(20)     YES             NULL                                                                            

OrderItems:
============
Field   Type    Null    Key     Default Extra                                                                           
OrderItemID     int     NO      PRI     NULL                                                                            
OrderID int     YES     MUL     NULL                                                                                    
ProductID       int     YES     MUL     NULL                                                                            
Quantity        int     YES             NULL                                                                            
UnitPrice       decimal(10,2)   YES             NULL                                                                    

Products:
=========
Field   Type    Null    Key     Default Extra                                                                           
ProductID       int     NO      PRI     NULL                                                                            
Name    varchar(255)    YES             NULL                                                                            
Description     varchar(255)    YES             NULL                                                                    
Price   decimal(10,2)   YES             NULL  

*/

/*

List customers who have made at least one order with a total cost higher 
than $1000

Sample Output:
==============
Name    Email                                                                                                           
Alice Johnson   alice.johnson@example.com                                                                               
George Clark    george.clark@example.com 
                                                                    
*/

use fs;

-- Write your query below.

select c.name, c.email from Customers c
join Orders o on c.customerId = o.customerId
where o.totalCost > 1000;


/*

Find all customers who have ordered a "Laptop".

Sample Output:
==============
Name                                                                                                                    
Alice Johnson                                                                                                           
George Clark  

*/

use fs;

-- Write your query below.

select c.name from Customers c 
join Orders o on c.customerId = o.customerId
join OrderItems oi on o.orderId = oi.orderId
join Products p on oi.productId = p.productId
where p.name = "Laptop";


/*

List customers who have never placed an order.

Sample Output:
==============
Name                                                                                                                    
Henry Taylor                                                                                                            
Irene Green 

*/

use fs;

-- Write your query below.

select name from Customers where customerId not in (select customerId from Orders);