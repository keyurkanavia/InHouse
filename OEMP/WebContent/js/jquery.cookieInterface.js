function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}


function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    if (sPageURL != ''){
    	for (i = 0; i < sURLVariables.length; i++) {
    		sParameterName = sURLVariables[i].split('=');
    		
    		if (sParameterName[0] === sParam) {
    			return sParameterName[1] === undefined ? true : sParameterName[1];
    		}
    	}
    }else{
    	return null;
    }
};


function checkUserCookie() {
    var user=getCookie('username');
   if (user != "") {
        //alert("Welcome again " + user);
	   $("#sign").hide();
	   $("#profile").show();
        return user;
    } else {
       alert("Please Login before proceeding");
       $("#sign").show();
       $("#profile").hide();
       }
 }

function loginUser(){
	var email = $('#email_id').val();
	var password = $('#password').val();
	alert(email);
	  var hr = new XMLHttpRequest();
	  var url="/oemp/rest/profileUpdate/getProfile?email="+email;
	  	alert("URL:"+url);
	    hr.open("GET", url, true);
	    hr.setRequestHeader("Content-type", "application/json",true);
	    hr.send(email);
	   
	    hr.onreadystatechange = function() {
	        if(hr.readyState == 4 && hr.status == 200) {
	        	 var data = JSON.parse(hr.responseText);
	        	if(data!=null){
	        	 setCookie("username", data.fname, 30);
	        	 setCookie("id", data._id, 30);
	        	 location.reload();
	        	 }else{
	        		 alert("Invalid User");
	        	 }
	        }
	    };
}


function profileUrl(){
	var id=getCookie("id");
	var href="profile.jsp?id="+id;
	 $("#profile").attr("href",href);
}



