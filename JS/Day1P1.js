/*
You are given a string str, and your task is to find the number of vowels and consonants in the string.

Input Format:
-------------
Line-1: A string

Output Format:
--------------
Line-1: A number, Vowels count
Line-2: A number, Consonants count

Sample Input-1:
---------------
Hello

Sample Output-1:
----------------
Vowels: 2
Consonants: 3

Sample Input-1:
---------------
Hello world

Sample Output-1:
----------------
Vowels: 3
Consonants: 7

*/

const readline = require('readline');

// Set up readline interface for reading input
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Function to count vowels in a string
const countVowels = (str) => {
  //Write your code here
  let vowels = "aeiou"
  let count1 = 0, count2 = 0;
  for(let i=0;i<str.length;i++){
      let c = str.charAt(i).toLowerCase();
      if(vowels.includes(c)) count1++;
      else if(c >= 'a' && c<='z') count2++;
  }
  return {count1, count2};
};

// Reading input synchronously
console.log("Enter a string:");
rl.on('line', (input) => {
  const len = input.length;
  const {count1, count2} = countVowels(input);
  console.log(`Vowels: ${count1}\nConsonants: ${count2}`);
  rl.close();
});
