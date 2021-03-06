var refreshAllProjPageContent = null;

$(document).ready(function() {

	function init(){
		var user_id = checkUserCookie();
		refreshAllProjPageContent(false,user_id);
	}
	
	function populateprojectList(data,userId){
		var projList = document.getElementById('projectList');
		var members = data.names;
		if(members.length != 0){
			for(i=0;i<members.length;i++){
				var linkItem = document.createElement('a');
				var listItem = document.createElement('li');
				
				linkItem.setAttribute("id",+members[i].name);
				linkItem.setAttribute("href","./projectPage.jsp?proj_id="+members[i].name+"&user_id="+userId);
				linkItem.innerHTML=members[i].name;
				listItem.appendChild(linkItem);
				listItem.innerHTML=listItem.innerHTML+'<a href="#" value='+members[i].name+' style="float:right" onclick="deleteProject(\'' + members[i].name +'\')">delete</a>';
				projList.appendChild(listItem);
			}
		}else{
			while (projList.hasChildNodes()) {
				projList.removeChild(projList.lastChild);
			}
			var pElement = document.createElement('p');
			pElement.innerHTML = "NO PROJECTS";
			
			projList.appendChild(pElement);
		}
	}
	
	refreshAllProjPageContent = function refreshAllProjPageContent(renderAll,userId){
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
	};
	
	init();
	
})

function deleteProject(proj_id){
	 var projectId = '';
	    if (proj_id == '') {
	    	alert("Project Id passed is empty");
	    } else {
	    	projectId = 'proj_Id=' + proj_id;
	    }
	    
	    
	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/project/deleteProj?"+projectId, true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send(projectId);
	   
	    hr.onreadystatechange = function() {
	        if(hr.readyState == 4 && hr.status == 200) {
	        	 var data = hr.responseText;
	        	 if(data=="success"){
	        	 	alert("Project "+proj_id+" successfully deleted");
	        	 	var user_id = checkUserCookie();
	        	 	refreshAllProjPageContent(false,user_id);
	        	 }else{
	        		 alert("Project "+proj_id+" delete operation failed");
	        	 }
	    }  
	    };
}