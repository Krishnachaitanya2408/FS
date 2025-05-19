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

Find customers who have placed orders with a total cost greater than the average
total cost of all orders

Sample Output:
==============

Name                                                                                                                    
Name    Email                                                                                                           
Alice Johnson   alice.johnson@example.com                                                                               
Bob Smith       bob.smith@example.com                                                                                   
Charlie Davis   charlie.davis@example.com                                                                               
Diana Williams  diana.williams@example.com                                                                              
Ethan Brown     ethan.brown@example.com                                                                                 
George Clark    george.clark@example.com                                                                                                          
*/

use fs;

-- Write your query below.


select c.name, c.email from Customers c
join Orders o
on c.CustomerId = o.CustomerId
group by c.CustomerId, c.name, c.email
having sum(o.TotalCost) > (select avg(TotalCost) from Orders);


/*

Retrieve product names that have been ordered more than the average quantity of
all products

Sample Output:
==============

Name                                                                                                                    
Laptop                                                                                                                  
Smartphone                                                                                                              
Keyboard                                                                                                                
Mouse                                                                                                                   
Monitor                                                                                                                 
Smartwatch                                                                                                           
                                                                                          

*/

use fs;

-- Write your query below.
select p.Name from Products p
join OrderItems oi on oi.ProductID = p.ProductID
group by oi.ProductID
having sum(oi.Quantity)>(select avg(Quantity) from OrderItems)
order by p.productId; 


/*

Find customers who ordered the most expensive product
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
join OrderItems as oi on o.orderId = oi.orderId
join Products p on oi.productId = p.productId
where p.price = (select max(price) from Products);


/*

List order IDs where all items in the order are priced above the average product
price

Sample Output:
==============

OrderID                                                                                                                 
1002                                                                                                                    

*/

use fs;

-- Write your query below.

select oi.orderId from OrderItems oi 
join Products p 
on oi.productId = p.productId
group by oi.orderId
having min(p.price) > (select avg(Price) from Products);


/*

Find the names of products that are only ordered by customers who live in a
specific city (e.g., 'New York')

Sample Output:
==============

Name                                                                                                                    
Keyboard                                                                                                          
                                                                                            


*/

use fs;

-- Write your query below.

select distinct p.name from Products p
join OrderItems oi on oi.productId = p.productId
join Orders o on o.OrderId = oi.orderId
join Customers c on c.customerId = o.customerId
where c.address like "%New York%"
and p.productId not in (
    select distinct p2.productId
    from Products p2
    join OrderItems oi2 on oi2.productId = p2.productId
    join Orders o2 on o2.orderId = oi2.orderId
    join Customers c2 on c2.customerid = o2.customerId
    where c2.address not like "%New York%"
    );

