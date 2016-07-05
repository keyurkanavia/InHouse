function allowDrop(ev) {
    	ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    source = document.getElementById(ev.dataTransfer.getData("text"));
    target = ev.target;

  		if(target.getAttribute('id')=='targetList' || target.parentElement.getAttribute('id')=='targetList'){
  			target = document.getElementById('targetList');
  		}else if( target.getAttribute('id')=='sourceList' || target.parentElement.getAttribute('id')=='sourceList'){
	  		target = document.getElementById('sourceList');
  		}
  		target.appendChild(document.getElementById(data));
    
}

function redirectToProjectPage(proj_author,projName){
	$('#name').val();
	document.getElementById('description').value = '';
	window.location="http://localhost:8080/oemp/projectPage.jsp?proj_id="+projName+"&user_id="+proj_author;
}

function checkValidation(){
	var name = $('#name').val();
	var description = document.getElementById('description').value;
	var emptyFields = new Array();
	if(name == ''){
		emptyFields.push('Name');
	}
	if(description == ''){
		emptyFields.push('Description');
	}
	
	if(emptyFields.length == 0){
		return true;
	}else{
		var danger = document.getElementById('danger');
		danger.innerHTML = emptyFields.toString() + ' cannot be empty';
		danger.removeAttribute('hidden');
		return false;
	}
}

function insert(str, index, value) {
    return str.substr(0, index) + value + str.substr(index);
}

function addProject() {
		
	if(checkValidation()){
		var proj_author = checkUserCookie();
		var projName =  $('#name').val();
		var projDesc = document.getElementById('description').value;
		
		var teamList = document.getElementById('targetList').childNodes;
		var team = '';
		
		for(i=0;i<teamList.length;i++){
			var M = teamList[i].getAttribute('id');
			var teamMember = {};
			teamMember['profile_id'] = teamList[i].getAttribute('id');
			team = team + JSON.stringify(teamMember);
			
			if(i!=(teamList.length-1)){
				team = team+",";
			}
		}

		console.log('team = '+team);
		
		//alert("Description "+projDesc);
		var dataVar =JSON.stringify({name:projName,proj_id:projName,proj_desc:projDesc,lastModifiedDate:new Date(),lastMofifiedFields:[],author:proj_author,team:[]});
		
		var newData = insert(dataVar,dataVar.length-2,team)
		console.log('NewData = '+newData);
		//alert("Add Project called");
	    $.ajax({
	        url: "/oemp/rest/project/createNewProj",
	        type: 'post',
	        dataType: 'json',
	        contentType: 'application/json',
	        data: newData,
	        success: redirectToProjectPage(proj_author,projName),
	    });
	}
}

function getCountryCode(country){
	var countryCode;
	switch(country.toLowerCase()) {
		case 'india':
			countryCode = 'IN';
			break;
		case 'brazil':
			countryCode = 'BR';
			break;
		case 'us':
			countryCode = 'US';
			break;
		default:
			countryCode = 'UNO';
	}
	
	return countryCode;
}

function populateFormFields(){
	
	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/profileUpdate/getAllProfiles?", true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send();
   
    hr.onreadystatechange = function() {
        if(hr.readyState == 4 && hr.status == 200) {
        	 var data = JSON.parse(hr.responseText);
        	 var profiles = data.profiles;
        	 var sourceList = document.getElementById('sourceList');
        	 for(i=0;i<profiles.length;i++){
	        	 var listItem = document.createElement('li');
	     		 var spanItem = document.createElement('span');
	     	
	     		listItem.setAttribute('id',profiles[i].id);
	     		listItem.setAttribute('class','list-group-item');
	     		listItem.setAttribute('draggable','true');
	     		listItem.setAttribute('ondragstart','drag(event)');

	     		listItem.innerHTML = '<span class="badge">'+getCountryCode(profiles[i].country)+'</span>'+profiles[i].fname+' '+profiles[i].lname;
	     		sourceList.appendChild(listItem);
        	 }
        }
    };
	
}

$(document).ready(function() {
	 
	populateFormFields();
	 
})
