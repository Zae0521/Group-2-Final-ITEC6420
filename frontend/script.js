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

  // send.
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(http_body));
}

function on_branch(arg_http_verb) {

  // setup.
  var xhr = new XMLHttpRequest();
  var http_body = null;

  // state change.
  xhr.onreadystatechange = function() { };

  // build http body.
  if (arg_http_verb === 'CREATE') {
    xhr.open('POST', `${url_restapi}/${ep_branch_create}`, true);
    http_body = {
      branchName : document.getElementById('braName').value,
      address    : document.getElementById('braAddress').value,
      city       : document.getElementById('braCity').value,
      zip        : document.getElementById('braZip').value,
      phone      : document.getElementById('braPhone').value,
      state      : document.getElementById('braState').value
    };
  }
  else if (arg_http_verb === 'UPDATE') {
    xhr.open('PUT', `${url_restapi}/${ep_branch_update}`, true);
    http_body = {
      id         : document.getElementById('braID').value,
      branchName : document.getElementById('braName').value,
      address    : document.getElementById('braAddress').value,
      city       : document.getElementById('braCity').value,
      zip        : document.getElementById('braZip').value,
      phone      : document.getElementById('braPhone').value,
      state      : document.getElementById('braState').value
    };
  }
  else if (arg_http_verb === 'DELETE') {
    const ep_delete_id = ep_branch_id(document.getElementById('braID').value);
    xhr.open('DELETE', `${url_restapi}/${ep_delete_id}`, true);
    http_body = { };
  }
  else {
    console.log(`on_branch: unsupported verb ${arg_http_verb}`);
    return;
  }

  // send.
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(http_body));
}

function on_inventory(arg_http_verb) {

  // setup.
  var xhr = new XMLHttpRequest();
  var http_body = null;

  // state change.
  xhr.onreadystatechange = function() { };

  // build http body.
  if (arg_http_verb === 'CREATE') {
    xhr.open('POST', `${url_restapi}/${ep_inventory_create}`, true);
    http_body = {
      carId    : document.getElementById("invVeh").value,
      branchId : document.getElementById("invBra").value,
      quantity : document.getElementById("invQuantity").value
    };
  }
  else if (arg_http_verb === 'UPDATE') {
    xhr.open('PUT', `${url_restapi}/${ep_inventory_update}`, true);
    http_body = {
      id       : document.getElementById('invID').value,
      carId    : document.getElementById("invVeh").value,
      branchId : document.getElementById("invBra").value,
      quantity : document.getElementById("invQuantity").value
    };
  }
  else if (arg_http_verb === 'DELETE') {
    const ep_delete_id = ep_inventory_id(document.getElementById('invID').value);
    xhr.open('DELETE', `${url_restapi}/${ep_delete_id}`, true);
    http_body = { };
  }
  else {
    console.log(`on_inventory: unsupported verb ${arg_http_verb}`);
    return;
  }

  // send.
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify(http_body));
}

function get_cars() {

  // vars.
  var xhr = new XMLHttpRequest();
  var data = '';
  var tblVehiclesBody = document.getElementById("tblVehiclesBody");

  // setup.
  xhr.onreadystatechange = function() {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      data = xhr.responseText;
    }
  }

  // send.
  xhr.open('GET', `${url_restapi}/${ep_car_read_all}`, false);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({ }));

  // report.
  var json_data = JSON.parse(data);
  for (var each_vehicle of json_data) {
    var to_append = '';
    to_append = to_append + ("<tr>");
    to_append = to_append + ("<td>" + each_vehicle.id + "</td>");
    to_append = to_append + ("<td>" + each_vehicle.model + "</td>");
    to_append = to_append + ("<td>" + each_vehicle.type + "</td>");
    to_append = to_append + ("<td>" + each_vehicle.year + "</td>");
    to_append = to_append + ("<td>" + each_vehicle.price + "</td>");
    to_append = to_append + ("</tr>");

    let old = tblVehiclesBody.innerHTML;
    tblVehiclesBody.innerHTML = old + to_append;
  }
}

function get_branches() {

  // vars.
  var xhr = new XMLHttpRequest();
  var data = '';
  var tblBranchesBody = document.getElementById("tblBranchesBody");

  // setup.
  xhr.onreadystatechange = function() {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      data = xhr.responseText;
    }
  }

  // send.
  xhr.open('GET', `${url_restapi}/${ep_branch_read_all}`, false);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({ }));

  // report.
  var json_data = JSON.parse(data);
  for (var each_branch of json_data) {
    var to_append = '';
    to_append = to_append + ("<tr>");
    to_append = to_append + ("<td>" + each_branch.id + "</td>");
    to_append = to_append + ("<td>" + each_branch.branchName + "</td>");
    to_append = to_append + ("<td>" + each_branch.address + "</td>");
    to_append = to_append + ("<td>" + each_branch.city + "</td>");
    to_append = to_append + ("<td>" + each_branch.state + "</td>");
    to_append = to_append + ("<td>" + each_branch.zip + "</td>");
    to_append = to_append + ("<td>" + each_branch.phone + "</td>");
    to_append = to_append + ("</tr>");

    let old = tblBranchesBody.innerHTML;
    tblBranchesBody.innerHTML = old + to_append;
  }
}

function get_inventories() {

  // vars.
  var xhr = new XMLHttpRequest();
  var data = '';
  var tblInventoryBody = document.getElementById("tblInventoryBody");

  // setup.
  xhr.onreadystatechange = function() {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      data = xhr.responseText;
    }
  }

  // send.
  xhr.open('GET', `${url_restapi}/${ep_inventory_read_all}`, false);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(JSON.stringify({ }));

  // report.
  var json_data = JSON.parse(data);
  for (var each_inventory of json_data) {
    var to_append = '';
    to_append = to_append + ("<tr>");
    to_append = to_append + ("<td>" + each_inventory.id + "</td>");
    to_append = to_append + ("<td>" + each_inventory.carId + "</td>");
    to_append = to_append + ("<td>" + each_inventory.branchId + "</td>");
    to_append = to_append + ("<td>" + each_inventory.quantity + "</td>");
    to_append = to_append + ("</tr>");

    let old = tblInventoryBody.innerHTML;
    tblInventoryBody.innerHTML = old + to_append;
  }
}

