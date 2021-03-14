var btn = document.selectElementById("viewEmployees");
console.log("begin button code");
btn.addEventListener("click", console.log("button was clicked"));
console.log("end button code");
function showAllEmployees() {
    console.log("Show employees function was called");
    let xhr = new XMLHttpRequest();

    
    //xhr.onreadystatechange = populateTable(employeeList);
      

       
    
		
		xhr.open("POST", "http://localhost:8080/project-1/employees");
		xhr.send();
        var employeeList = JSON.parse(responseText);
        console.log(employeeList);
		
}

function populateTable(someArray) {
    console.log("Hit the populateTable function");
    var table = document.getElementById("myTable");

    someArray.forEach( (obj) => {

        // 1. for each object create a new row (<tr>) and stick it onto (append) the table that exists
        let tr = document.createElement("tr");
        table.appendChild(tr);

        // 2. for each obj enter some data (<td>)
        let td = document.createElement("td");
            // - firstName <td>
            tr.appendChild(td);
            td.innerHTML = obj.firstName;
            // - color <td>;
            td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj.lastName;
            // - cheese <td>;
            td = document.createElement("td");
            tr.appendChild(td);
            td.innerHTML = obj.email;
        });
}