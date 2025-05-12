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
List the names and emails of customers who placed orders with the status "Delivered".

Sample Output:
==============
Name    Email                                                                                                           
Alice Johnson   alice.johnson@example.com                                                                               
George Clark    george.clark@example.com


*/

use fs;

-- Write your query below.


select distinct c.name as Name, c.email as Email from Customers as c
join Orders as o
on c.CustomerId = o.CustomerId
where o.status = "Delivered";


/*

Calculate the total amount spent by each customer and display customer names
along with their total spending, ordered by highest spending first.

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

Show product names along with the total quantity sold for each product. Display 
only products where the total quantity sold is greater than or equal to 2.

Sample Output:
==============
ProductName     TotalQuantitySold                                                                                       
Laptop  2                                                                                                               
Keyboard        5                                                                                                       
Smartphone      2                                                                                                       
Mouse   8                                                                                                               
Smartwatch      2                                                                                                       
Monitor 2                                                                                                                

*/

use fs;

-- Write your query below.

select p.name as ProductName, sum(o.Quantity) as TotalQuantitySold from Products p
join OrderItems o
on p.productId = o.productId
group by p.name
having TotalQuantitySold >=2;


/*

Find all orders placed by customers living in "Texas (TX)".Include customer name,
order date, total cost, and status.

Sample Output:
==============
Name    OrderDate       TotalCost       Status                                                                          
Diana Williams  2024-10-13      450.00  Shipped                                                                         
Diana Williams  2024-10-14      300.00  Processing  

*/

use fs;

-- Write your query below.

select c.name, o.orderDate, o.totalCost, o.status from Customers c
join Orders o
on c.customerId = o.customerId
where c.address like  "%TX%";

/*
List orders that contain more than one item. Show OrderID, customer name, and 
the total number of items in the order.

Sample Output:
==============

OrderID CustomerName    NumberOfItems                                                                                   
1001    Alice Johnson   2                                                                                               
1003    Alice Johnson   2                                                                                               
1010    Charlie Davis   2                                                                                               
1005    Diana Williams  2                                                                                               
1006    Ethan Brown     2                                                                                               
1008    George Clark    2                                                                                               

*/

use fs;

-- Write your query below.

select o.OrderID, c.Name as CustomerName, count(oi.OrderItemID) as NumberOfItems
from Orders o
join Customers c on o.CustomerID = c.CustomerID
join OrderItems oi on o.OrderID = oi.OrderID
group by o.OrderID, c.Name
having count(oi.OrderItemID) > 1;

