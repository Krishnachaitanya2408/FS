/*
You are given two sorted arrays, arr1 and arr2. 
Your task is to merge these two arrays into a single sorted array. 
Both input arrays are already sorted in non-decreasing order. 
The output should also be a sorted array that contains all elements from 
both arr1 and arr2.

Input Format:
-------------
Line-1: Space separated numbers, represents arr1
Line-2: Space separated numbers, represents arr2

Output Format:
--------------
Line-1 Space separated numbers, represents sorted arr

Sample Input:
-------------
1 3 5
2 4 6

Sample Output:
--------------
1 2 3 4 5 6

Constraints:
-------------
Each array can contain distinct integers.
Both arrays are already sorted.
*/


const readline = require('readline');

// Set up readline interface for reading input
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Function to merge two sorted arrays
const mergeSortedArrays = (arr1, arr2) => {
  //Write your code here and return merged array
  let mergedArray = [];
  let i=0, j=0;
  let n1 = arr1.length, n2 = arr2.length;
  while(i<n1 && j<n2){
      if(arr1[i]<arr2[j]){
          mergedArray.push(arr1[i]);
          i++;
      }
      else{
          mergedArray.push(arr2[j]);
          j++;
      }
  }
  while(i<n1){
      mergedArray.push(arr1[i]);
      i++;
  }
  while(j<n2){
      mergedArray.push(arr2[j]);
      j++;
  }
  
  return mergedArray;
};

// Function to read input and process arrays
const processInput = () => {
  rl.question('', (input1) => {
    const arr1 = input1.split(' ').map(Number); // Convert input string to an array of numbers

    rl.question('', (input2) => {
      const arr2 = input2.split(' ').map(Number); // Convert input string to an array of numbers

      // Merge the two sorted arrays
      const mergedArray = mergeSortedArrays(arr1, arr2);

      // Output the merged, sorted array
      console.log(mergedArray);

      // Close the readline interface
      rl.close();
    });
  });
};

// Start the process
processInput();

