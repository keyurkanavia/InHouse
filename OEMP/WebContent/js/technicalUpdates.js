/**
 * JS for technicalUpdates.jsp 
 * @author sachin.yadav
 *
 */

$(document).ready(function(){
	getAlltechnologyUpdates();
	$("#btn_post").click(function(){
		 var txt_post=$(".post").val();
		 if($.trim(txt_post) != ''){
			 
			 $.ajax({
				url:'/oemp/rest/technicalUpdate/addTechnicalUpdate',
				method:"post",
				dataType: 'json',
			    contentType: 'application/json',
			    data: JSON.stringify({technicalUpdate:$(".post").val()}),
			    success:afterPost()
			 });
			 
		 }
	});
	
	
});

function addComment(id){
	
	 var txt_post=$(".addComment"+id+"").val();
	 	if($.trim(txt_post) != ''){
		 $.ajax({
			url:'/oemp/rest/technicalUpdate/addComment',
			method:"post",
			dataType: 'json',
		    contentType: 'application/json',
		    data: JSON.stringify({id:id,addComment:$(".addComment"+id+"").val(),name:"sachin"}),
		    success:afterPost()
		 });
	 	}
}	 

function afterPost(){
	$(".post").val("");
	getAlltechnologyUpdates();
}
function getAlltechnologyUpdates(){
	$(".load_post").empty();
	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/technicalUpdate/getTechnicalUpdate", true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send();
   
    hr.onreadystatechange = function() {
        if(hr.readyState == 4 && hr.status == 200) {
        	//console.log(hr.responseText);
        	var json = $.parseJSON(hr.responseText);
        	var arr = []; 
    		for(i=0;i<json.length;i++){
        	   arr.push({"id" : json[i]._id,"technologyUpdate":json[i].technologyUpdate,"date":json[i].date,"comments":json[i].comments});
           }
    		 console.log(arr);
   		  //function to sort json array by date 
   			function sortFunction(a,b){  
   				var dateA = new Date(a.date).getTime();
   				var dateB = new Date(b.date).getTime();
   				return dateA < dateB ? 1 : -1;  
   			};
   			arr.sort(sortFunction);//sort the new javascript array 
   			console.log(arr);
   		
	   			for(var i=0;i<arr.length;i++){
					var div_data ="<div align='left' style='margin-left:50px'><li><b>"+arr[i].technologyUpdate.toUpperCase()+"</b></li></br><p data-toggle='modal' data-target='#"+arr[i].id+"'>Comment</p></div></br>" ;
						 $(".load_post").append(div_data);
						 if(arr[i].comments!='' || arr[i].comments!='undefined'){
							 createModel(arr[i].id.toUpperCase(),arr[i].technologyUpdate.toUpperCase(),arr[i].comments);
						 }
				}	
        }
    } 
}


function createModel(id,technologyUpdate,comments){
	$(".load_post").remove(id);
	var arr = []; 
	if(comments.length==0){
		var modelDiv="<input type='hidden' name='id' class='_id' value='"+id+"'/><div class='modal fade' id='"+id+"' role='dialog'> <div class='modal-dialog'> <div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h4 class='modal-title'>"+technologyUpdate.toUpperCase()+"</h4> </div><div class='commentBox'><textarea rows='2' cols='60' class='addComment"+id+"' id='addComment' placeholder='Add Comment...'></textarea></br></br><input type='button' name='button' id='"+id+"' value='Comment' onclick='addComment(this.id)' style='margin-left:300px'></div><div class='modal-body'> <p>No Comments To display</p></div></div></div></div>"	
		$(".load_post").append(modelDiv);
	}
	else{
		console.log(comments);
			for(i=0;i<comments.length;i++){
		 	   arr.push({"name":comments[i].name,"comment":comments[i].addComment,"date":comments[i].date});
		    }
			console.log(arr);	
			var modelDiv="<input type='hidden' name='id' class='_id' value='"+id+"'/><div class='modal fade' id='"+id+"' role='dialog'> <div class='modal-dialog'> <div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h4 class='modal-title'>"+technologyUpdate.toUpperCase()+"</h4> <div class='commentBox'><textarea rows='2' cols='60' class='addComment"+id+"' id='addComment' placeholder='Add Comment...'></textarea></br></br><input type='button' name='button' id='"+id+"' value='Comment' onclick='addComment(this.id)' style='margin-left:300px'></div></div><div class='modal-body" + id + "'></div><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>Close</button></div></div></div></div>"
			$(".load_post").append(modelDiv);
			for(var i=0;i<arr.length;i++){
						var modelpara= "<p align='left' style='margin-left:20px'><b>"+arr[i].name.toUpperCase()+"</b>        "+arr[i].comment.toUpperCase()+".</p>";
						$(".modal-body"+id+"").append(modelpara);
			}
	}
}