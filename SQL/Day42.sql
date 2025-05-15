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

Find customers who have placed at least one order with a cost greater than the
average order cost for that customer:
Sample Output:
==============

Name    OrderID TotalCost                                                                                               
Alice Johnson   1001    1250.00                                                                                         
Bob Smith       1002    850.00                                                                                          
Diana Williams  1005    450.00                                                                                          
Charlie Davis   1010    950.00                                                                                          
                                                                                            

*/

use fs;

-- Write your query below.

select c.Name, o.OrderId, o.TotalCost from Customers c
join Orders o on o.CustomerId = c.CustomerId
having o.TotalCost > (select avg(TotalCost) from Orders where customerId = o.customerId);


/*

Find the distinct names of customers who have placed at least one order that includes the
most expensive product in the catalog.

Sample Output:
==============
Name                                                                                                                    
Alice Johnson                                                                                                           
George Clark    
                                                                                              

*/

use fs;

-- Write your query below.

select c.name from Customers c
join Orders o on o.CustomerId = c.CustomerId
join OrderItems oi on o.orderid = oi.orderId
join Products p on oi.productId = p.productId
where oi.unitPrice = (select max(Price) from Products);

/*

List orders where the total cost is higher than the total cost of any other 
order made by the same customer:

Sample Output:
==============
OrderID CustomerID      TotalCost                                                                                       
1001    1       1250.00                                                                                                 
1002    2       850.00                                                                                                  
1005    4       450.00                                                                                                  
1006    5       550.00                                                                                                  
1007    6       250.00                                                                                                  
1008    7       1200.00                                                                                                 
1010    3       950.00      
                                                                                              

*/

use fs;

-- Write your query below.

select o.orderId, o.customerId, o.Totalcost from Orders o
where o.TotalCost = (select max(TotalCost) from Orders where customerId = o.customerId);

/*

/*

Find the customers who placed orders that include more items than any other 
order they’ve placed:
Sample Output:
==============

OrderID Name    ItemCount                                                                                               
1010    Charlie Davis   2                                                                                               
1005    Diana Williams  2                                                                                               
1006    Ethan Brown     2                                                                                               
1007    Fiona Adams     1                                                                                               
1008    George Clark    2                                                                                               
                                                                                             

*/

use fs;

-- Write your query below.


SELECT oi.OrderID, c.Name, COUNT(*) AS ItemCount
FROM OrderItems oi
JOIN Orders o ON oi.OrderID = o.OrderID
JOIN Customers c ON o.CustomerID = c.CustomerID
GROUP BY oi.OrderID, c.Name, o.CustomerID
HAVING COUNT(*) > ALL (
    SELECT COUNT(*)
    FROM OrderItems oi2
    JOIN Orders o2 ON oi2.OrderID = o2.OrderID
    WHERE o2.CustomerID = o.CustomerID
    AND oi2.OrderID!=oi.OrderID
    GROUP BY oi2.OrderID
);

/*

FFind the names of customers whose total spending is greater than the average 
total spending of all customers.
Sample Output:
==============

Name                                                                                                                    
Alice Johnson                                                                                                           
Bob Smith                                                                                                               
Charlie Davis                                                                                                           
George Clark                                                                                                            
 

*/

use fs;

-- Write your query below.

SELECT c.Name
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
HAVING SUM(o.TotalCost) > (
    SELECT AVG(TotalSpending)
    FROM (
        SELECT SUM(o.TotalCost) AS TotalSpending
        FROM Orders o
        GROUP BY o.CustomerID
    ) AS customer_totals
);
