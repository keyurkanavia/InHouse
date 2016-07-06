package com.oe.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.oe.services.db.DBServices;
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

	@Path("/findProfile")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String findProfile(
			@QueryParam("id") String id) {
		System.out.println("findProfile:Id:" + id);
		String result = ProfileDBServices.findProfile(id);
		return result;
	}
	
	@Path("/getAllProfiles")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllProfiles() {
		System.out.println("Get All Profiles");
		String result = ProfileDBServices.getAllProfiles();
		return result;
	}
	
	@Path("/addProfile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addProfile(
			String profileJson
			) {
		System.out.println("updateProfile:profileJson:" + profileJson);
		String result = ProfileDBServices.addProfile(profileJson);
		return result;
	}
	
	@Path("/getProfile")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfile(
			@QueryParam("email") String emailId,
			@QueryParam("password") String password) {
		System.out.println("get profile of user :" + emailId + ":password:" + password);
		String result = ProfileDBServices.getProfile(emailId, password);
		return result;
	}
	
	@Path("/getProfileUpdates")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfileUpdates() {
		System.out.println("Get all profile for home page");
		List<String> result = ProfileDBServices.getProfileUpdate();
		String res=result.toString();
		System.out.println(res);
		return res;
	}


}
