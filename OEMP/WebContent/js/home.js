/**
 * JS for home.jsp 
 * @author sachin.yadav
 *
 */


$(document).ready(function(){
			$('.less').hide();
			ajaxViewProfileUpdate();
		});
		function ajaxViewProfileUpdate(){
			var hr = new XMLHttpRequest();
		    hr.open("GET", "/oemp/rest/profileUpdate/getProfileUpdates", true);
		    hr.setRequestHeader("Content-type", "application/json",true);
		    hr.send();
		   
		    hr.onreadystatechange = function() {
		        if(hr.readyState == 4 && hr.status == 200) {
		        	//console.log(hr.responseText);
		        	var json = $.parseJSON(hr.responseText);
		        	var arr = []; 
		        		for(i=0;i<json.length;i++){
		            	   arr.push({"id" : json[i]._id,"fname":json[i].fname,"lname":json[i].lname,"profile_id":json[i].profile_id,"updateType":json[i].updateType,"preValue": json[i].preValue,"newValue": json[i].newValue,"date": json[i].date});
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
		        			for(var i=0;i<3;i++){
		        				var div_data ="<div style='margin-left:-300px'><dl><dt><li><a href='profile.jsp?id="+arr[i].profile_id+"'>"+arr[i].fname.toUpperCase()+" "+arr[i].lname.toUpperCase()+"&nbsp</a>Updated  &nbsp&nbsp&nbsp"+arr[i].updateType+"</li></dt><dd style='margin-left:150px'>Updated&nbsp&nbsp"+arr[i].updateType+"&nbsp&nbspFrom&nbsp&nbsp"+arr[i].preValue+"&nbsp&nbspTo&nbsp&nbsp"+arr[i].newValue+"</dd></dl></div>" ;
		        					 $(".profile_update").append(div_data);
		        				}
		        			$(".profile_more").click(function(){
		        				  $(".more").hide();
		        				  $('.less').show();
		        				  for(i=3;i<arr.length;i++){
		        					  var div_data ="<div style='margin-left:-300px'><dl><dt><li><a href='profile.jsp?id="+arr[i].profile_id+"'>"+arr[i].fname.toUpperCase()+" "+arr[i].lname.toUpperCase()+"&nbsp</a>Updated  &nbsp&nbsp&nbsp"+arr[i].updateType+"</li></dt><dd style='margin-left:150px'>Updated&nbsp&nbsp"+arr[i].updateType+"&nbsp&nbspFrom&nbsp&nbsp"+arr[i].preValue+"&nbsp&nbspTo&nbsp&nbsp"+arr[i].newValue+"</dd></dl></div>" ;
		        					  $(".profile_update").append(div_data);
		        				  }
		        			});
		        			 $(".profile_less").click(function(){
		        					$('.profile_update div').remove();
		        					  $('.less').hide();
		        					  	for(i=0;i<3;i++){
		        					  		var div_data ="<div style='margin-left:-300px'><dl><dt><li><a href='profile.jsp?id="+arr[i].profile_id+"'>"+arr[i].fname.toUpperCase()+" "+arr[i].lname.toUpperCase()+"&nbsp</a>Updated  &nbsp&nbsp&nbsp"+arr[i].updateType+"</li></dt><dd style='margin-left:150px'>Updated&nbsp&nbsp"+arr[i].updateType+"&nbsp&nbspFrom&nbsp&nbsp"+arr[i].preValue+"&nbsp&nbspTo&nbsp&nbsp"+arr[i].newValue+"</dd></dl></div>" ;
				        					 $(".profile_update").append(div_data);
				        				}
		        						$('.more').show();
		        			   });
		        }
			}
		}