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
    var usrFromURL = getUrlParameter('user_id');
    if(usrFromURL != '' && usrFromURL != null){
    	setCookie("username", usrFromURL, 30);
    	return usrFromURL;
    }else if (user != "") {
        alert("Welcome again " + user);
        return user;
    } else {
       user = prompt("Please enter your name:","");
       if (user != "" && user != null) {
           setCookie("username", user, 30);
       }
    }
}


