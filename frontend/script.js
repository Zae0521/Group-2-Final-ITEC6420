// url for rest api.
//
const url_restapi = 'http://34.125.42.132:8080';

// api endpoints for `cars` table.
// 
const ep_car_create   = 'car/create';
const ep_car_read_all = 'car/get';
const ep_car_update   = 'car/update';
const ep_car_id       = function(id) { return `car/${id}`; }

// api endpoints for `branches` table.
//
const ep_branch_create   = 'branch/create';
const ep_branch_read_all = 'branch/get';
const ep_branch_update   = 'branch/update';
const ep_branch_id       = function(id) { return `branch/${id}`; }

// api endpoints for `inventory` table.
//
const ep_inventory_create   = 'inventory/create';
const ep_inventory_read_all = 'inventory/get';
const ep_inventory_update   = 'inventory/update';
const ep_inventory_id       = function(id) { return `inventory/${id}`; }

// all element id's:
// var vehID        = document.getElementById("vehID").value;
// var vehYear      = document.getElementById("vehYear").value;
// var vehMake      = document.getElementById("vehMake").value;
// var vehType      = document.getElementById("vehType").value;
// var vehPrice     = document.getElementById("vehPrice").value;
//
// var braID        = document.getElementById("braID").value;
// var braName      = document.getElementById("braName").value;
// var braAddress   = document.getElementById("braAddress").value;
// var braCity      = document.getElementById("braCity").value;
// var braZip       = document.getElementById("braZip").value;
// var braPhone     = document.getElementById("braPhone").value;
// var braState     = document.getElementById("braState").value;
//
// var invID        = document.getElementById("invID").value;
// var invVeh       = document.getElementById("invVeh").value;
// var invBra       = document.getElementById("invBra").value;
// var invQuantity  = document.getElementById("invQuantity").value;
// 

function on_car(arg_http_verb) {

  // setup.
  var xhr = new XMLHttpRequest();
  var http_body = null;

  // state change.
  xhr.onreadystatechange = function() { };

  // build http body.
  if (arg_http_verb === 'CREATE') {
    xhr.open('POST', `${url_restapi}/${ep_car_create}`, true);
    http_body = {
      model : document.getElementById('vehMake').value,
      type  : document.getElementById('vehType').value,
      year  : document.getElementById('vehYear').value,
      price : document.getElementById('vehPrice').value
    };
  }
  else if (arg_http_verb === 'UPDATE') {
    xhr.open('PUT', `${url_restapi}/${ep_car_update}`, true);
    http_body = {
      id    : document.getElementById('vehID').value,
      model : document.getElementById('vehMake').value,
      type  : document.getElementById('vehType').value,
      year  : document.getElementById('vehYear').value,
      price : document.getElementById('vehPrice').value,
    };
  }
  else if (arg_http_verb === 'DELETE') {
    const ep_delete_id = ep_car_id(document.getElementById('vehID').value);
    xhr.open('DELETE', `${url_restapi}/${ep_delete_id}`, true);
    http_body = { };
  }
  else {
    console.log(`on_car: unsupported verb ${arg_http_verb}`);
    return;
  }

  console.log(JSON.stringify(http_body));

  // send.
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(http_body));
}

function on_branch(arg_http_verb) {
}

function on_inventory(arg_http_verb) {
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
