/**
 * JS for profile.jsp 
 * @author sachin.yadav
 *
 */

$(document).ready(function(){
			var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;
			ajaxViewEmp(sPageURL);
			$(".edit").hide();
			$(".save").hide();
			$(".edit").click(function(){
				
			
				createInput("fname1","fname");
				createInput("mname1","mname");
				createInput("lname1","lname");
				createInput("email1","email");
				createInput("cellphone1","cellphone");
				createInput("skype_id1","skype_id");
				var  genderText=$(".gender").text();
				$(".gender").text("");
				if(genderText =='male'){
					$(".gender").append("<input type='radio' name='gender' value='male' class='gender1' checked/>Male");
					$(".gender").append("<input type='radio' name='gender' value='female' class='gender1' />Female");
				}
				else{
					$(".gender").append("<input type='radio' name='gender' value='male' class='gender1' />Male");
					$(".gender").append("<input type='radio' name='gender' value='female' class='gender1' checked/>Female");
				}
				var  dob=$(".dob").text();
				$(".dob").text("");
				$(".dob").append("<input type='date'  name='dob1' value='"+dob+"' class='dob1'/>");
				createInput("country1","country");
				createInput("interest1","interest");
				createInput("language1","language");
				

				$(".edit").hide();
				$(".save").show();
			});
		});
		
			function createInput(newclassName,oldClassName){
				var classText=$("."+oldClassName).text();
				$("."+oldClassName).text("");
				$("."+oldClassName).append("<input type='text' name='"+newclassName+"' value='"+classText+"' class='"+newclassName+"'/>");
			}
				function ajaxViewEmp(id){
					var hr = new XMLHttpRequest();
				    hr.open("GET", "/oemp/rest/profileUpdate/findProfile?"+id, true);
				    hr.setRequestHeader("Content-type", "application/json",true);
				    hr.send(id);
				   
				    hr.onreadystatechange = function() {
				        if(hr.readyState == 4 && hr.status == 200) {
				        	 var data = JSON.parse(hr.responseText);
				        	    //alert ('data:'+ data);
				   				$("._id").val(data._id);
				        		$(".name").text(data.fname+" "+data.lname);
				         		$(".fname").text(data.fname);
				         		$(".mname").text(data.mname);	
				         		$(".lname").text(data.lname);
				         		$(".email").text(data.email);
				         		$(".cellphone").text(data.cellphone);
				         		$(".skype_id").text(data.skype_id);
				         		$(".gender").text(data.gender);
				         		$(".dob").text(data.dob);
				         		$(".country").text(data.country);
				         		$(".city").text(data.city);
				         		$(".interest").text(data.interest);
				         		$(".language").text(data.language);
				         		
				         		$(".edit").show();
				        }
				    };
				}  
			
				function ajaxUpdateProfile() {
				//	alert("Add called");
					//alert($("._id").val());
				    $.ajax({
				        url: "/oemp/rest/profileUpdate/updateProfile",
				        type: 'post',
				        dataType: 'json',
				        contentType: 'application/json',
				        data: JSON.stringify({_id:$("._id").val(),
				        	fname:$(".fname1").val(),
				        	mname:$(".mname1").val(),
				        	lname:$(".lname1").val(),
				        	email:$(".email1").val(),
				        	cellphone:$(".cellphone1").val(),
				        	skype_id:$(".skype_id1").val(),
				        	gender:$(".gender1:checked").val()
					}),
					success : hideTextFieldAfterEdit()
				    });
				}
			
			function hideTextFieldAfterEdit(){
				$(".edit").show();
				$(".save").hide();
				hideTextField("fname1","fname");
				hideTextField("mname1","mname");
				hideTextField("lname1","lname");
				hideTextField("email1","email");
				hideTextField("cellphone1","cellphone");
				hideTextField("skype_id1","skype_id");
				hideTextField("dob1","dob");
				hideTextField("country1","country");
				hideTextField("interest1","interest");
				hideTextField("language1","language");
			}
			function hideTextField(newclassName,oldClassName){
				var classVal=$("."+newclassName).val();
				$("."+newclassName).remove();
				$("."+oldClassName).text(classVal);
			}