/*
You are given a date-time string dt in the format YYYY-MM-DDTHH:MM:SS (24-hour format). 
Your task is to convert this date-time string into the following format:

Input Format:
-------------
Line-1: A single date-time string in the format YYYY-MM-DDTHH:MM:SS (24-hour format).

Output Format:
--------------
Line-1: The formatted date-time string in the format :
DaySuffix MonthName, Year Hour:Minute:SecondAM/PM.

Sample Input-1:
---------------
2019-07-18T16:34:21

Sample Output-1:
----------------
18th July, 2019 04:34:21PM


Sample Input-2:
---------------
2022-03-01T23:59:59

Sample Output-2:
----------------
1st March, 2022 11:59:59PM

NOTE:
The output should include:
	Day with an ordinal suffix (e.g., 18th)
	Month as a word (e.g., July)
	12-hour time format with AM/PM

*/

const readline = require('readline');

// Set up readline interface for reading input
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const months = {
    "01": "January",
    "02": "February",
    "03": "March",
    "04": "April",
    "05": "May",
    "06": "June",
    "07": "July",
    "08": "August",
    "09": "September",
    "10": "October",
    "11": "November",
    "12": "December"
}

// Function to get day suffix
const getDaySuffix = (day) => {
    const lastDigit = day.toString().slice(-1);
    if (day > 10 && day < 20) return 'th';
    switch (lastDigit) {
        case '1': return 'st';
        case '2': return 'nd';
        case '3': return 'rd';
        default: return 'th';
    }
};

// Function to convert date string
const convertDateString = (dateStr) => {
    const [date, time] = dateStr.split('T');
    const [year, month, day] = date.split('-');
    const [hours, minutes, seconds] = time.split(':');

    // Convert to 12-hour format
    const period = hours >= 12 ? 'PM' : 'AM';
    const hour12 = hours % 12 || 12;
    const formattedTime = `${hour12.toString().padStart(2, '0')}:${minutes}:${seconds}${period}`;

    return `${parseInt(day)}${getDaySuffix(parseInt(day))} ${months[month]}, ${year} ${formattedTime}`;
};

// Function to read input and process the date string
const processDateInput = () => {
  rl.question("", (input) => {
    const formattedDate = convertDateString(input);
    console.log(formattedDate);
    rl.close();
  });
};

// Start the process
processDateInput();

