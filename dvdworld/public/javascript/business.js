var validTotalAmountOfDaysString = "Please set <i>total amount of days</i> field to a <i><b>valid positive integer</b></i>.";

function ComputeTotalAmount(outputDivName, inputTextName) {
	// Get output div.
	var outputDiv = document.getElementById(outputDivName);
	// Get input text field (= number of days the user wishes to rent DVDs).
	var inputText = document.getElementById(inputTextName);
	// Get total amount of cash per day.
	var costPerDay = document.getElementById('_CostPerDay');
	if (outputDiv == null || costPerDay == null ||
		costPerDay.innerHTML == null || inputText == null) {
		alert ('Internal script error.');
		return false;
	}
	
	// Convert total amount of days to a number.
	var totalNumberOfDays = parseInt(inputText.value);
	
	if (inputText.value == "" || !ValidateDaysToRent(inputTextName) || isNaN(totalNumberOfDays)) {
		outputDiv.innerHTML = validTotalAmountOfDaysString;
		return false;
	}
	
	// Convert and compute total amount of cash per day to a number.
	var costPerDaysFinal = parseFloat(costPerDay.innerHTML);
	totalNumberOfDays = totalNumberOfDays * 1.0;
	costPerDaysFinal = costPerDaysFinal * totalNumberOfDays;
	
	outputDiv.innerHTML =
		"Final Amount: <b>" + costPerDaysFinal.toString() +
		"</b> euro for <b>" + totalNumberOfDays + "</b> day(s).<br />" +
		"Start Date: " + DateToString(new Date()) + "<br />" + 
		"Due Date: " + DateToString(AddDaysToDate(new Date(), totalNumberOfDays));
	
	return true;
}

function ValidateCheckOutForm (checkOutForm, outputDivName, daysToRentFieldName) {
	var outputDiv = document.getElementById(outputDivName);
	if (checkOutForm == null || outputDiv == null || daysToRentFieldName == null)
		return false;
	
	if (!ValidateDaysToRent(daysToRentFieldName)) {
		outputDiv.innerHTML = validTotalAmountOfDaysString;
		return false;
	}
	
	return true;
}

function ValidateDaysToRent(htmlFieldName) {
	var htmlField = document.getElementById(htmlFieldName);
	if (htmlField == null || htmlField.value == "")
		return false;
	var daysToRentString = htmlField.value;
	var daysToRentVal = parseInt(daysToRentString);
	if (isNaN(daysToRentVal) || daysToRentVal <= 0)
		return false;
	return true;
}

function AddDaysToDate(startDate, numberOfDays) {
	var newDate = startDate;
	newDate.setDate(newDate.getDate() + numberOfDays);
	return newDate;
}

function DateToString (dateObj) {
	var d = new Date();
	var weekday = new Array(7);
	weekday[0]="Sunday";
	weekday[1]="Monday";
	weekday[2]="Tuesday";
	weekday[3]="Wednesday";
	weekday[4]="Thursday";
	weekday[5]="Friday";
	weekday[6]="Saturday";
	
	var monthName = new Array(12);
	monthName[0] = "January";
	monthName[1] = "February";
	monthName[2] = "March";
	monthName[3] = "April";
	monthName[4] = "May";
	monthName[5] = "June";
	monthName[6] = "July";
	monthName[7] = "August";
	monthName[8] = "September";
	monthName[9] = "October";
	monthName[10] = "November";
	monthName[11] = "December";
	
	return weekday[dateObj.getDay()] + ", " + monthName[dateObj.getMonth()] + " " + dateObj.getDate() + ", " + dateObj.getFullYear();
}