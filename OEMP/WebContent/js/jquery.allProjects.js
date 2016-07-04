$(document).ready(function() {

	function init(){
		var user_id = checkUserCookie();
		refreshPageContent(false,user_id);
	}
	
	function populateprojectList(data,userId){
		var projList = document.getElementById('projectList');
		var members = data.names;
		for(i=0;i<members.length;i++){
			var linkItem = document.createElement('a');
			var listItem = document.createElement('li');
			
			linkItem.setAttribute("id",+members[i].name);
			linkItem.setAttribute("href","./projectPage.jsp?proj_id="+members[i].name+"&user_id="+userId);
			linkItem.innerHTML=members[i].name;
			listItem.appendChild(linkItem);
			listItem.innerHTML=listItem.innerHTML+'<a href="#" value='+members[i].name+' style="float:right">delete</a>';
			projList.appendChild(listItem);
		}
	}
	
	function refreshPageContent(renderAll,userId){
		var hr = new XMLHttpRequest();
	    hr.open("GET", "/oemp/rest/project/getProjectList", true);
	    hr.setRequestHeader("Content-type", "application/json",true);
	    hr.send();
	   
	    hr.onreadystatechange = function() {
	        if(hr.readyState == 4 && hr.status == 200) {
	        	 var data = JSON.parse(hr.responseText);
	        	 populateprojectList(data,userId);
	        }
	    };
	}
	
	init();
	
	var deletebtn = document.getElementById("allTeam");
	
	deletebtn.onclick = function() {
	    
		var projName = $('#team').clone();
	}
	
})