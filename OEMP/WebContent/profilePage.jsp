<jsp>

<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">

function ajaxViewEmp(){
	
    var emailId = '';
    if ($("#searchEmpId").val() == '' && $("#searchemail").val() == '') {
    	emailId = 'emp_Id=' + $("#empId").val()+'&emailId=' + $("#email").val();
    } else {
    	emailId = 'emp_Id=' + $("#searchEmpId").val()+'&emailId=' + $("#searchemail").val();
    }
     
    alert("email arg "+ emailId);

	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/profile/findEmployee?"+emailId, true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send(emailId);
   
    hr.onreadystatechange = function() {
        if(hr.readyState == 4 && hr.status == 200) {
        	 var data = JSON.parse(hr.responseText);
        	    //alert ('data:'+ data);
        	$("#name").val(data.name);
        	$("#location").val(data.location);
        	$("#empId").val(data.emp_id);
        		$("#fname").val(data.fname);
        		$("#lname").val(data.lname);
        		$("#email").val(data.email);
        }
    };
    
}

function addEmp() {
	alert("Add called");
    $.ajax({
        url: "/oemp/rest/profile/addEmployee",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({name:$("#name").val(),location:$("#location").val(),emp_id:$("#empId").val(),fname:$("#fname").val(),lname:$("#lname").val(),email:$("#email").val()}),
        success: ajaxViewEmp()
    });
}
</script>


</head>

<body>
	<div id="addArea">
	Add/Modify/View Employee: 
	<form name="addEmpForm" id="addEmpForm" action="/oemp/rest/profile/addEmployee">
		Name : <input type="text" name="name" id="name"/>
		Location : <input type="text" name="location" id="location"/>
		Emp Id : <input type="text" name="empId" id="empId"/>
		First Name : <input type="text" name="fname" id="fname"/>
		Last Name : <input type="text" name="lname" id="lname"/>
		Email : <input type="text" name="email" id="email"/>
		<input type="button" name="submit" value="submit" onclick="addEmp();"/>
	</form>
	</div>
	<hr>
	Search Employee
	<div id="searchArea">
	<form name="searchEmp"  id="searchEmpForm" action="/rest/findEmployee">
		Email : <input type="text" name="searchemail" id="searchemail"/>
		Emp Id : <input type="text" name="searchEmpId" id="searchEmpId"/>		
		<input type="button" name="submitSearch" value="Search" onclick="ajaxViewEmp();"/>
	</form>
	</div>
</body>
</html>
</jsp>