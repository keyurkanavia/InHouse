/**
 * 
 */
package com.oe.services.db;

import static com.mongodb.client.model.Filters.eq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

/**
 * Rest API class using jax-rs for profile operations.
 * @author sachin.yadav
 *
 */
public class ProfileDBServices extends DBServices{
	
	public static MongoCollection<Document> profileColl;
	
	
	static{
		profileColl = getCollection("profile");
	}
	/*
	 * Update User Profile
	 */
	public static String updateProfile(String profileJson) {
		System.out.println("profileUpdate: profileJson:"+profileJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(profileJson);
		current.put("lastModifiedDate", getCurrentDate());
		String _id = current.getString("_id");
		if (_id != null) {
			result = profileColl.find(eq("_id", _id)).first();
		}
		if (result != null ) {
			
			profileColl.updateOne(eq("_id", _id), new Document("$set", current));
			output = "Updated";
		} 
		System.out.println("updateProfile:output:" + output);
		return output;
	}
	/**
	 * add profile to collection
	 * 
	 * @param profileJson
	 * @return output
	 */
	
	public static String addProfile(String profileJson) {
		System.out.println("addEmployee: profileJson:"+profileJson);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll =  getCollection("profile");
		Document current = Document.parse(profileJson);
		
		String email_id = current.getString("email");
		if (email_id != null) {
			result = coll.find(eq("email", email_id)).first();
		}
		if (result != null ) {
			output="Email ALready Exists";
		} else {
			current.put("_id","PR"+getNextId("profileid","profile"));
			coll.insertOne(current);
			output = "Created";
		}
		System.out.println("addProfile:output:" + output);
		return output;
	}
	/*
	 * Get Profile Date By ID
	 */
	public static String findProfile(String id) {
		System.out.println("findProfile:id:" + id);
		Document result = null;
		String output = null;
		if (id != null) {
			result = profileColl.find(eq("_id", id)).first();
		}
		if(result == null) {
			output = "{name : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("findProfile:result:" + output);
		return output;
	}
	
	/*
	 * Auto increment _id
	 */
	public static String getNextId(String seq_name,String collectionName) {
	    String sequence_collection = collectionName; // the name of the sequence collection
	    String sequence_field = "seq"; // the name of the field which holds the sequence
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    // Now connect to your databases
	    DB db = mongoClient.getDB("test");
	    DBCollection seq = db.getCollection(sequence_collection); // get the collection (this will create it if needed)

	    // this object represents your "query", its analogous to a WHERE clause in SQL
	    DBObject query = new BasicDBObject();
	    query.put("_id", seq_name); // where _id = the input sequence name

	    // this object represents the "update" or the SET blah=blah in SQL
	    DBObject change = new BasicDBObject(sequence_field, 1);
	    DBObject update = new BasicDBObject("$inc", change); // the $inc here is a mongodb command for increment

	    // Atomically updates the sequence field and returns the value for you
	    DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
	    return res.get(sequence_field).toString();
	}
	
	/*
	 * Conevert Date to MMM dd YYYY HH:mm:ss aaa Format
	 */
	public static String getCurrentDate(){
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("MMM dd YYYY HH:mm:ss aaa");
		return ft.format(dNow);
	}
	
	/*
	 * Method returns all the profiles
	 */
	public static String getAllProfiles(){
		Document result = null;
		String output = null;
		FindIterable<Document> iterable = profileColl.find();
		final List<Document> profileList = new ArrayList<Document>();
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		        profileList.add(new Document("id",document.get("_id")).append("fname", document.get("fname")).append("lname", document.get("lname")).append("country", document.get("country")));
		    }
		});
		result = new Document("profiles",profileList);
		if(result == null) {
			output = "{name : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("findProfile:result:" + output);
		return output;
	}
	
	

	/*
	 * Get all profiles for Home page
	 */
	public static List<String> getProfileUpdate(){
			MongoCollection<Document> coll = getCollection("profile");
			List<Document> foundDocument = coll.find().into(new ArrayList<Document>());
			List<String> jsonArray = new ArrayList<String>();
			for (Document document : foundDocument) {
				jsonArray.add(document.toJson());
			}
			return jsonArray;
	}
	

	public static String getProfile(String emailId, String password) {
		System.out.println("findEmployee: emailId:"+emailId + ":password:" + password);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll = getCollection("profile");
		if (emailId != null) {
			result = coll.find(eq("email", emailId)).first();
		}

		if(result == null) {
			output = "{name : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("findEmployee:result:" + output);
		return output;
	}
}
