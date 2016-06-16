var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
var projJSON;
function init(){
	console.log("init");
	refreshPageContent(true);
	console.log("projJSON from init" + projJSON);
};

function refreshPageContent(renderAll){
	var proj_id = getUrlParameter('proj_id');
	 var projectId = '';
	    if (proj_id == '') {
	    	alert("Project Id passed is empty");
	    } else {
	    	projectId = 'proj_Id=' + proj_id;
	    }
	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/project/getProjectData?"+projectId, true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send(projectId);
   
    hr.onreadystatechange = function() {
        if(hr.readyState == 4 && hr.status == 200) {
        	 var data = JSON.parse(hr.responseText);
        	 projJSON = data;
        	 populateDescription();
        	 
        	 if(renderAll == true){
        		populatePosts();
        	 	populateTeam();
        	 }
        }
    };
}

function populatePosts(){
	var posts = projJSON.posts;
	console.log(projJSON);
	for(i=0;i<posts.length;i++){
		console.log("posts items",posts[i].text);
		addComment(posts[i].text);
	}
}

function populateDescription(){
	console.log("projJSON" + projJSON); 	
	$("#description").empty();
	    $("#description").append(projJSON.proj_desc);
}


function updateDescription() {
	var projId =  getUrlParameter('proj_id');
    $.ajax({
        url: "/oemp/rest/project/updateProjDesc",
        async : false,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({name:projId,proj_id:projId,proj_desc:$("#descEditable").val()}),
    });
    console.log("Before:populateDescription called after updating");
    populateDescription(projId);
    console.log("After:populateDescription called after updating");
    $('#editDescModal').css('display','none');
    refreshPageContent(false);
}

function populateTeam(){
	console.log("populateTeam");
	var members = new Array('images/mockImages/darth-vader1_opt.jpg','images/mockImages/character1_opt.jpg','images/mockImages/darth-vader1_opt.jpg','images/mockImages/character1_opt.jpg','images/mockImages/darth-vader1_opt.jpg','images/mockImages/character1_opt.jpg','images/mockImages/character1_opt.jpg');
	var team = document.getElementById('team');
	
	for(i=0;i<members.length;i++){
		var aLink = document.createElement('a');
		var image = document.createElement('img');
		
		image.setAttribute("src",members[i]);
		image.setAttribute("id","member");
		aLink.setAttribute("id","member"+i)
		aLink.setAttribute("href","http://www.google.com");
		aLink.appendChild(image);
		team.appendChild(aLink);
	}
}


var ev = [{
							id : 1,
							name : "Release P1.1",
							on : new Date(2015,2,15)
						},{
							id : 2,
							name : "Release P2.1",
							on : new Date(2015,4,17)
						},{
							id : 3,
							name : "Release P2.2.1",
							on : new Date(2015,5,30)
						},{
							id : 4,
							name : "Release P2.2.1",
							on : new Date(2015,7,5)
						},{
							id : 5,
							name : "Trip to Australia",
							on : new Date(2015,5,5)
						},{
							id : 6,
							name : "Trip to New Zealand",
							on : new Date(2015,5,30)
						},{
							id : 7,
							name : "Awesome new year",
							on : new Date(2015,0,1)
						},{
							id : 8,
							name : "Will go to Moon",
							on : new Date(2015,6,10)
						},{
							id : 9,
							name : "Will go to Mars",
							on : new Date(2016,6,10)
						},{
							id : 10,
							name : "No idea about this date",
							on : new Date(2016,6,10)
						},{
							id : 11,
							name : "Last Release",
							on : new Date(2016,6,10)
						}]
			var tl = $('#myTimeline').jqtimeline({
							events : ev,
							numYears:2,
							startYear:2015,
							click:function(e,event){
								alert(event.name);
							}
						});

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  
$(".act").click(function(){
    
    
    var val = $(this).text();

if (val == "More") {
    $("#description").css('height', 'auto');
    $(this).text("Less");
} else {
    $("#description").css('height', '20em');
    $(this).text("More");
}
    return false;
    
    });
var shifted = false;
$("#comment").keypress(function(e) {
       var ev = e || window.event;
       var key = ev.keyCode || ev.which;
	    var new_div = $('<div/>');
       new_div.hide();
       new_div.css('color', 'darkgreen');
	   
	   if(key=='13' && shifted == false){
			var text = $('#comment').val();	
			var testText = text.replace('\n', ' ');
			var eachLine = testText.trim();
			if(eachLine != ''){
				addComment(text);
				$('#comment').val('');
			}
			$('body').append(new_div);
				new_div.fadeIn();
	   }else{
		//new_div.html('key code ' + key + ' was pressed!');      
      // $('body').append(new_div);
      // new_div.fadeIn();
	   }
   });
 
 $(document).keydown(function (e) {
    if (e.keyCode == 16) {
        //alert(e.which + " or Shift was pressed down");
		shifted= true;
    }
});
$(document).keyup(function (e) {
    if (e.keyCode == 16) {
        //alert(e.which + " or Shift was pressed up");
    }
	shifted = false;
});
function addComment(commentText){
	//alert("text = " + commentText);
	var new_comment = $('#mainAreaItem').clone();
	new_comment.find('#description').empty();
	new_comment.find('#description').append(commentText);
	new_comment.find('.act').remove();
	new_comment.find('#description').css('height', 'auto');
	new_comment.prependTo('#dashboard');
	
	//checkForURL(new_comment);
};

function checkForURL(new_comment){
	var $words = new_comment.find('#description').text().split(' ');
	for (i in $words) {
    if ($words[i].indexOf('http://') == 0 || $words[i].indexOf('https://') == 0) {
		var word = $words[i];
		if($words[i].indexOf('youtube')> -1){
			//alert("youtube URL found "+$words[i]);
			 var id = (word.split('?v='))[1];
			 $words[i] = '<img src="http://img.youtube.com/vi/'+id+'/0.jpg"';
		}
        $words[i] = '<a href="' + word + '">' + $words[i] + '</a>';
    }
}
new_comment.find('#description').empty();
new_comment.find('#description').append($words.join(' '));
};

$(document).ready(function() {
// Get the modal
var modal = $('#allTeamModal');
var editDescModal = $('#editDescModal');

// Get the button that opens the modal
var btn = document.getElementById("allTeam");
var editDescBtn = document.getElementById("editDesc");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var editDescSpan = document.getElementsByClassName("close")[1];
// When the user clicks the button, open the modal 
btn.onclick = function() {
    
	var allTeam = $('#team').clone();
	allTeam.find('#showTeamModal').remove();
	allTeam.find('#allTeam').remove();
	modal.find('.modal-content').find('#team').remove();
	
	modal.find('.modal-content').append(allTeam);
	modal.find('.modal-content').find('#team').css('width','85%');
	modal.find('.modal-content').find('#team').css('height','auto');
	modal.css('display', 'block');
	//$( "#allTeamModal" ).dialog();
}


editDescBtn.onclick = function(){
	var text = $('#description').text();
	var textArea = $('#descEditable')
	//textArea.setAttribute("id","descEditable");
	//textArea.append(text);
	textArea.val(text);
	editDescModal.css('display', 'block');
}

editDescSpan.onclick = function(){
	editDescModal.css('display','none');
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.css('display','none');
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.css('display','none');
    }
    if( event.target == editDescModal){
    	editDescModal.css('display','none');
    }
}

})
