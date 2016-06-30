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

function clearForm(){
	$('#name').val();
	document.getElementById('description').value = '';
}

function addProject() {
	
	var projName =  $('#name').val();
	var projDesc = document.getElementById('description').value;
	alert("Description "+projDesc);
	
	alert("Add Project called");
    $.ajax({
        url: "/oemp/rest/project/createNewProj",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({name:projName,proj_id:projName,proj_desc:projDesc,posts:[]}),
        success: clearForm()
    });
}