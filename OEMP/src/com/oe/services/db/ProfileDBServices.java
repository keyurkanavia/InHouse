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
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

/**
 * Rest API class using jax-rs for profile operations.
 * @author sachin.yadav
 *
 */
public class ProfileDBServices extends DBServices{
	
	public static MongoCollection<Document> profileColl;
	public static MongoCollection<Document> profileUpdateColl;
	
	
	static{
		profileColl = getCollection("profile");
		profileUpdateColl = getCollection("profile_update");
	}
	/*
	 * Update User Profile
	 */
	static ArrayList<BasicDBObject> listoFUpdatedColection = new ArrayList<BasicDBObject>();
	public static String updateProfile(String profileJson) {
		System.out.println("profileUpdate: profileJson:"+profileJson);
		Document result = null;
		String output = null;
		Document current = Document.parse(profileJson);
		String _id = current.getString("_id");
		if (_id != null) {
			result = profileColl.find(eq("_id", _id)).first();
		}
		listoFUpdatedColection.clear();
		if (result != null ) {
			if(!result.getString("fname").toString().equals(current.getString("fname").toString())){
				updateProfileJsonDocument("fname",current,result);
			}
			if(!result.getString("lname").toString().equals(current.getString("lname").toString())){
				updateProfileJsonDocument("lname",current,result);
			}
			if(!result.getString("mname").toString().equals(current.getString("mname").toString())){
				updateProfileJsonDocument("mname",current,result);
			}
			BasicDBObject document=new BasicDBObject();
			for(int i=0;i<listoFUpdatedColection.size();i++){
				document =listoFUpdatedColection.get(i);
				addUpdateProfile(document.toString());
				document=null;
			}
				
			profileColl.updateOne(eq("_id", _id), new Document("$set", current));
			output = "Updated";
		} 
		System.out.println("updateProfile:output:" + output);
		return output;
	}

	public static void updateProfileJsonDocument(String fieldName,Document current,Document result){
		BasicDBObject document= new BasicDBObject();
		document.put("fname",current.getString("fname"));
		document.put("lname",current.getString("lname"));
		document.put("profile_id",current.getString("_id"));
		document.put("updateType","lname");
		document.put("preValue",result.getString(fieldName));
		document.put("newValue",current.getString(fieldName));
		document.put("date",getCurrentDate());
		String name="document";
		listoFUpdatedColection.add(document);
	}
	
	/*
	 * Add Data to updateProfile Collection
	 */
	public static String addUpdateProfile(String profileJson) {
		System.out.println("addUpdateProfile: profileJson:"+profileJson);
		String output = null;
		Document current = Document.parse(profileJson);
		current.put("_id","PU"+getNextId("proupdateid","profile_update"));
		profileUpdateColl.insertOne(current);
		output = "Created update Profile";
		return profileJson;
	}
	
	/*
	 * Get All Update Profile Data
	 */
	public static List<String> getAllUpdateOfProfile() {
		List<Document> foundDocument = profileUpdateColl.find().into(new ArrayList<Document>());
		List<String> jsonArray = new ArrayList<String>();
		System.out.println(foundDocument.size());
		foundDocument.remove(0);
	
		for (Document document : foundDocument) {
			jsonArray.add(document.toJson());
		}
		return jsonArray;
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
}
