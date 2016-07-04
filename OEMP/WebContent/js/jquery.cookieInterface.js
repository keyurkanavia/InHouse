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
        return user;
    } else {
       alert("Please Login before proceeding");
       }
 }

function loginUser(){
	var email = $('#email_id').val();
	var password = $('#password').val();
	
	if (email != "" && email != null) {
        setCookie("username", email, 30);
        setCookie("password", password, 30);
        //getProfile(user,"xyz");
    }
}


function getProfile(email,password){
	var profile = null;
	var projectId = '';
    if (email == '') {
    	alert("email Id passed is empty");
    } else {
    	emailID = 'email=' + email;
    }
	
	var hr = new XMLHttpRequest();
    hr.open("GET", "/oemp/rest/profileUpdate/getProfile?"+emailID, true);
    hr.setRequestHeader("Content-type", "application/json",true);
    hr.send(emailID);
   
    hr.onreadystatechange = function() {
        if(hr.readyState == 4 && hr.status == 200) {
        	 var profile = JSON.parse(hr.responseText);
        	 console.log("profile = "+profile)
        }
    };
	
	return profile;
	
}


