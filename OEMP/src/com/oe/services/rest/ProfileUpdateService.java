package com.oe.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.oe.services.db.ProfileDBServices;
/**
 * Rest API class using jax-rs for profile operations.
 * @author sachin.yadav
 *
 */
@Path("/profileUpdate")
public class ProfileUpdateService {
	@Path("/updateProfile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfile(
			String profileJson
			) {
		System.out.println("updateProfile:profileJson:" + profileJson);
		String result = ProfileDBServices.updateProfile(profileJson);
		return result;
	}
	
	
	@Path("/getProfileUpdates")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfileUpdates() {
		List<String> result = ProfileDBServices.getAllUpdateOfProfile();
		String res=result.toString();
		System.out.println(res);
		return res;
	}
	
	@Path("/findProfile")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String findProfile(
			@QueryParam("id") String id) {
		System.out.println("findProfile:Id:" + id);
		String result = ProfileDBServices.findProfile(id);
		return result;
	}
}