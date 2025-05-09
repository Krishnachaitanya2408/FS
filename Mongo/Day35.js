/*

Collection: 'employees'

Reference Document:
----------------------
{
  "employeeId": "E001",
  "name": "Aarav Sharma",
  "age": 28,
  "gender": "Male",
  "email": "aarav.sharma@example.com",
  "department": "Engineering",
  "salary": 720000,
  "isPermanent": true,
  "joiningDate": {
    "$date": "2019-06-15T00:00:00.000Z"
  },
  "skills": [
    "JavaScript",
    "Node.js",
    "MongoDB"
  ],
  "address": {
    "street": "12 MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "pinCode": 560001
  }
}


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
In db.<collection>.find():
	db -> Refers to the database.
	<collection> -> Your collection name
	find() -> Method to write the selection and projection part of the query.

*/

/*
Query 1

Write a MongoDB query to find all employees who belong to the 'Engineering' 
department

Sample Output:
--------------
[
  { name: 'Aarav Sharma', age: 28, gender: 'Male' },
  { name: 'Neha Reddy', age: 29, gender: 'Female' }
]
*/

printjson(
	db.employees.find({"department": "Engineering"}, {
	    name:1, age : 1, gender:1, _id:0
	})
)

/*
Query 2

Write a MongoDB query to retrieve all employees whose salary is more than INR 700000.

Sample Output:
--------------
[
  { name: 'Aarav Sharma', salary: 720000 },
  { name: 'Neha Reddy', salary: 750000 }
]


*/

printjson(
	db.employees.find({salary:{$gt:700000}}, {name:1, salary:1, _id:0})
)

/*

Query 3

Write a MongoDB query to find employees whose age is between 28 and 35 (inclusive)

Sample Output:
--------------
[
  { name: 'Aarav Sharma', age: 28 },
  { name: 'Isha Verma', age: 32 }
]

*/

	
printjson(
	db.employees.find({age:{$gte:28, $lte:35}},{name:1, age:1, _id:0})
)

/*

Query 4

Write a MongoDB query to find employees who joined after January 1st, 2021.

Sample Output:
--------------
[
  {
    name: 'Rohan Mehta',
    age: 25,
    joiningDate: ISODate('2021-11-01T00:00:00.000Z')
  },
  {
    name: 'Ananya Iyer',
    age: 30,
    joiningDate: ISODate('2022-01-04T00:00:00.000Z')
  }
]

*/

printjson(
	db.employees.find({joiningDate:{$gt:new Date("2021-01-01")}},{name:1, age:1, joiningDate:1, _id:0})
)

/*

Query 5

Write a MongoDB query to find employees who have more than two skills listed.

Sample Output:
--------------
[
  {
    name: 'Aarav Sharma',
    skills: [ 'JavaScript', 'Node.js', 'MongoDB' ]
  },
  { 
    name: 'Rohan Mehta', 
    skills: [ 'Excel', 'Tally', 'GST' ] 
  }
]

*/

printjson(
  db.employees.aggregate([
    {
      $project: {
        name: 1,
        skills: 1,
        skillCount: { $size: "$skills" }
      }
    },
    {
      $match: {
        skillCount: { $gt: 2 }
      }
    },
    {
      $project: {
        name: 1,
        skills: 1,
        _id: 0
      }
    }
  ])
)


/*

Query 6

Write a MongoDB query to find male employees who work in either the Sales or Finance department.

Sample Output:
--------------
[
  { name: 'Rohan Mehta', gender: 'Male', department: 'Finance' },
  { name: 'Vikram Singh', gender: 'Male', department: 'Sales' }
]

*/

printjson(
	db.employees.find({department:{$in :["Sales", "Finance"]}},{name:1, gender:1, department:1, _id:0})
)

/*
Query 7

Write a MongoDB query to find employees who have both "UI/UX" and "Figma" in 
their skillset.

Sample Output:
--------------
[ { name: 'Ananya Iyer', skills: [ 'UI/UX', 'Figma' ] } ]

*/

	
printjson(
	db.employees.find({skills:{$all:["UI/UX", "Figma"]}},{name:1,skills:1,_id:0})
)

/*
Query 8

Write a MongoDB query to find employees who have both "JavaScript" and 
"Node.js" in their skills and are permanent employees

Sample Output:
--------------
[
  {
    name: 'Aarav Sharma',
    skills: [ 'JavaScript', 'Node.js', 'MongoDB' ]
  }
]


*/

printjson(
	db.employees.find({$and:[{skills:{$all:["JavaScript", "Node.js"]}},{isPermanent:{$eq:true}}]},{name:1,skills:1,_id:0})
)


/*

Query 9

Write a MongoDB query to find employees who joined in the year 2021 
(based on the joiningDate field).


Sample Output:
--------------
[
  {
    name: 'Rohan Mehta',
    age: 25,
    joiningDate: ISODate('2021-11-01T00:00:00.000Z')
  },
  {
    name: 'Meera Nair',
    age: 31,
    joiningDate: ISODate('2021-05-12T00:00:00.000Z')
  }
]

*/

printjson(
	db.employees.find({joiningDate:{$gte:new Date("2021-01-01"), $lte:new Date("2021-12-31")}},{name:1, age:1, joiningDate:1, _id:0})
)

/*

Query 10

Write a MongoDB query to find employees whose pin code is even and greater than 400000


Sample Output:
--------------
[
  {
    name: 'Siddharth Joshi',
    address: {
      street: '10 FC Road',
      city: 'Pune',
      state: 'Maharashtra',
      pinCode: 411004
    }
  },
  {
    name: 'Kavya Raj',
    address: {
      street: '3 BTM Layout',
      city: 'Bangalore',
      state: 'Karnataka',
      pinCode: 560076
    }
  }
]

*/

printjson(
	db.employees.find({$and:[{"address.pinCode": {$mod:[2, 0]}}, {"address.pinCode": {$gt:400000}}]}, {_id:0, name:1, address:1})
)


/*
Query 11

Write a MongoDB query to find employees whose names start with the letter 'A'.

Sample Output:
--------------
[                                                                               
  {                                                                             
    name: 'Aarav Sharma'                                                        
  },                                                                            
  {                                                                             
    name: 'Ananya Iyer'                                                         
  }
] 
*/

printjson(
	db.employees.find({name : {$regex : "^A"}},{name:1, _id:0})
)

/*
Query 12

Write a MongoDB query to find employees whose names end with the letter 'a'.


Sample Output:
--------------
[
  { name: 'Aarav Sharma' },
  { name: 'Isha Verma' },
  { name: 'Rohan Mehta' }
]


*/

printjson(
	db.employees.find({name : {$regex : "a$"}},{name:1, _id:0})
)


/*

Query 13

Write a MongoDB query to find employees whose name contains the substring 'vi'
(case-insensitive).

Sample Output:
--------------
[ { name: 'Vikram Singh' }, 
  { name: 'Tanvi Kulkarni' } 
]

*/

printjson(
	db.employees.find({name : {$regex : "vi", $options:"i"}},{name:1, _id:0})
)


/*

Query 14

Write a MongoDB query to find employees whose email is from the domain example.com
and the email username ends with 'a'.

Sample Output:
--------------
[
  { name: 'Aarav Sharma', email: 'aarav.sharma@example.com' },
  { name: 'Isha Verma', email: 'isha.verma@example.com' },
  { name: 'Rohan Mehta', email: 'rohan.mehta@example.com' }
]


*/

printjson(
	db.employees.find({email : {$regex : /a@example.com$/}},  {name:1, email:1, _id:0})
)

/*

Query 15

Write a MongoDB query to find employees who live in cities whose name starts 
with 'B' or 'C'.

Sample Output:
--------------
[                                                                               
  {                                                                             
    name: 'Aarav Sharma',                                                       
    address: {                                                                  
      city: 'Bangalore'                                                         
    }                                                                           
  }                                                                             
]  
*/

printjson(
	db.employees.find({$or : [{"address.city" : {$regex : "^B"}}, {"address.city" : {$regex : "^C"}}]}, {name:1, "address.city":1, _id:0})
)


/*

Query 16

Write a MongoDB query to find employees whose state name in the address starts 
with a capital letter and contains at least one whitespace (multi-word state).

Sample Output:
--------------
[
  { name: 'Ananya Iyer', address: { state: 'Tamil Nadu' } },
  { name: 'Arjun Kapoor', address: { state: 'Uttar Pradesh' } },
  { name: 'Nikhil Das', address: { state: 'West Bengal' } }
]


*/

printjson(
	db.employees.find({$and : [{ "address.state" : {$regex : "^[A-Z]{1}"}}, {"address.state" : {$regex : " "}}]}, {name:1, "address.state":1, _id:0})
)


/*

Query 17

Write a MongoDB query to find employees whose pin code has a repeated digit (e.g., 440001).

Sample Output:
--------------
[
  { name: 'Aarav Sharma', address: { pinCode: '560001' } },
  { name: 'Isha Verma', address: { pinCode: '110001' } },
  { name: 'Rohan Mehta', address: { pinCode: '400001' } },
]

*/

printjson(
  db.employees.find(
    {
      "address.pinCode": {
        $regex: /(\d)\1/
      }
    },
    {
      name: 1,
      "address.pinCode": 1,
      _id: 0
    }
  )
)


/*
Query 18

Write a MongoDB query to find employees whose skills include exactly 6-character words.

Sample Output:
--------------
[ { name: 'Neha Reddy', skills: [ 'Python', 'Django' ] } ]


*/

printjson(
  db.employees.find(
    {
      skills: { $elemMatch: { $regex: /^.{6}$/ } }
    },
    {
      name: 1,
      skills: 1,
      _id: 0
    }
  )
)
