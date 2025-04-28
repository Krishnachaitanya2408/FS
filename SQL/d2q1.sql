/*
Write a query to assign a rank to each employee based on their salary within 
their department (higher salary → higher rank). If multiple employees have the 
same salary, they should share the same rank.


---------------
Database : univ
---------------

employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary INT,
    hire_date DATE
);

projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100),
    department VARCHAR(50)
);

employee_projects (
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);


Sample Output:
--------------
name    department      salary  dept_rank                                       
Diana   Engineering     71000   1                                               
Charlie Engineering     70000   2                                                   


*/

use univ;
select name, department, salary,
Rank() over (Partition by department order by salary desc) as dept_rank
from employees;