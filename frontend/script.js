var backendURL = "http://34.125.42.132:8080";

window.onload = function(event)
{
	let command = {commandType:"GetCurrentDataset"};
    PostToServer(command);
};

function executeCMD(cmdType)
{
	var vehID = document.getElementById("vehID").value;
	var vehYear = document.getElementById("vehYear").value;
	var vehMake = document.getElementById("vehMake").value;
	var vehType = document.getElementById("vehType").value;
	var vehPrice = document.getElementById("vehPrice").value;
	var braID = document.getElementById("braID").value;
	var braName = document.getElementById("braName").value;
	var braAddress = document.getElementById("braAddress").value;
	var braCity = document.getElementById("braCity").value;
	var braZip = document.getElementById("braZip").value;
	var braPhone = document.getElementById("braPhone").value;
	var braState = document.getElementById("braState").value;
	var invID = document.getElementById("invID").value;
	var invVeh  = document.getElementById("invVeh").value;
	var invBra = document.getElementById("invBra").value;
	var invQuantity  = document.getElementById("invQuantity").value;
	
	let command = {commandType:cmdType, vehID: vehID, vehYear: vehYear, vehMake: vehMake, vehType: vehType, vehPrice: vehPrice, braID: braID, braName: braName, braAddress: braAddress, braCity: braCity, braZip: braZip, braPhone: braPhone, braState: braState, invID: invID, invVeh: invVeh, invBra: invBra, invQuantity: invQuantity};
    //alert(command.cmdType + ", " + command.vehID + ", " + command.vehYear + ", " + command.vehMake + ", " + command.vehType + ", " + command.vehPrice + ", " + command.braID + ", " + command.braName + ", " + command.braAddress + ", " + command.braCity + ", " + command.braZip + ", " + command.braPhone + ", " + command.braState + ", " + command.invID + ", " + command.invVeh + ", " + command.invBra + ", " + command.invQuantity);
	
	PostToServer(command);
}

function PostToServer(data)
{
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function()
	{
		if (xhr.readyState == XMLHttpRequest.DONE)
		{
			if(data.commandType == "GetCurrentDataset")
			{
				jsonRecordsToTables(xhr.responseText);
			}
			else
			{
				//other response handlers, honestly though, refresh works as sa catch-all in this case so...
				window.location.reload();
			}
		}
	}
	xhr.open("POST", backendURL, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({ data }));
}

function jsonRecordsToTables(jsonRecords)
{
	//example JSON = "jsonRecords vehicles: [ { name: 'Alice', age: 25 }, { name: 'Bob', age: 30 }, { name: 'Charlie', age: 35 } ], branches: ..."
	//So, vehicles = object array, branches = object array, inventory = object array
	var data = JSON.parse(jsonRecords);
	
	//Vehicles
	var tblVehiclesBody = document.getElementById("tblVehiclesBody");
	for (var vehicle of data.vehicles)
	{
		var newRow = "<tr>";
		for (var key in vehicle)
		{
			//console.log(key + ": " + vehicle[key]);
			tblVehiclesBody.append("<td>" + vehicle[key] + "</td>");
		}
		tblVehiclesBody.append(newRow + "</tr>");
	}
	//Branches
	var tblBranchesBody = document.getElementById("tblBranchesBody");
	for (var branch of data.branches)
	{
		var newRow = "<tr>";
		for (var key in branch)
		{
			//console.log(key + ": " + branch[key]);
			tblBranchesBody.append("<td>" + branch[key] + "</td>");
		}
		tblBranchesBody.append(newRow + "</tr>");
	}
	
	//Inventories
	var tblInventoriesBody = document.getElementById("tblInventoriesBody");
	for (var inventory of data.inventories)
	{
		var newRow = "<tr>";
		for (var key in inventory)
		{
			//console.log(key + ": " + inventory[key]);
			tblInventoriesBody.append("<td>" + inventory[key] + "</td>");
		}
		tblInventoriesBody.append(newRow + "</tr>");
	}
}
