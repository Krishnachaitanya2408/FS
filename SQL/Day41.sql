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

Find customers who have spent more than the average order total

Sample Output:
==============

Name    Email                                                                                                           
Alice Johnson   alice.johnson@example.com                                                                               
Bob Smith       bob.smith@example.com                                                                                   
Diana Williams  diana.williams@example.com                                                                              
Ethan Brown     ethan.brown@example.com                                                                                 
George Clark    george.clark@example.com                                                                                
Charlie Davis   charlie.davis@example.com                                                                                               
*/

use fs;

-- Write your query below.

select c.name, c.email from Customers c
join Orders o
on c.CustomerId = o.CustomerId
where o.TotalCost > (select avg(TotalCost) from Orders);


/*

List product names that were included in the most expensive order

Sample Output:
==============

Name                                                                                                                    
Laptop                                                                                                                  
Keyboard                                                                                               
*/

use fs;

-- Write your query below.

select distinct p.name from Products p
join OrderItems oi on oi.ProductId = p.ProductId
join Orders o on o.OrderId = oi.OrderId
where o.TotalCost = (select Max(Totalcost) from Orders);

/*


Show customers who placed more orders than the average number of orders per 
customer

Sample Output:
==============

CustomerID      Name    NumOrders                                                                                       
1       Alice Johnson 4                                                                                               
2       Bob Smith       3                                                                                               
3       Charlie Davis   3 
*/

use fs;

-- Write your query below.

select c.CustomerId, c.name, count(o.OrderId) as NumOrders from Customers c
join Orders o on c.CustomerId = o.CustomerId
group by c.CustomerId, c.name   
having count(o.OrderID) > (select avg(OrderCount) from (
            select count(*) as OrderCount from Orders group by CustomerID) as CustomerOrderCounts);

/*
Find the name of the customer who spent the most in total

Sample Output:
==============

Name    TotalSpent                                                                                                      
Alice Johnson   1625.00                                                                                                 
George Clark    1200.00                                                                                                 
Bob Smith       1050.00                                                                                                 
Charlie Davis   1050.00                                                                                                 
Diana Williams  750.00                                                                                                  
Ethan Brown     550.00                                                                                                  
Fiona Adams     250.00                                                                                               

*/

use fs;

-- Write your query below.

select c.name, sum(o.TotalCost) as TotalSpent from Customers as c
join Orders as o 
on c.CustomerId = o.CustomerId
group by c.name order by TotalSpent desc;

/*

Get product names that were never ordered

Sample Output:
==============
Name                                                                                                                    
Headphones      
*/

use fs;

-- Write your query below.

select p.name from Products as p
where p.ProductId not in (select ProductId from OrderItems);

