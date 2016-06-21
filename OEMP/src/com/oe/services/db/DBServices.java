package com.oe.services.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

/**
 * Mongo DB connecter class to query/update items.
 * @author keyur.kanavia
 *
 */
public class DBServices {

	private static final String connectionURL = "mongodb://oeis-d42.objectedge.com:27017";
	
	public static void main (String [] args) {
		MongoClientURI mcUri = new MongoClientURI(
				connectionURL,
				MongoClientOptions.builder().cursorFinalizerEnabled(false));
		MongoClient client = new MongoClient(mcUri);
		MongoDatabase database = client.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("employees");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		for (Document document : foundDocument) {
			System.out.println("Document: " + document);
		}
	}
	
	public static MongoDatabase getSourceDB() {
		MongoClientURI mcUri = new MongoClientURI(
				connectionURL,
				MongoClientOptions.builder().cursorFinalizerEnabled(false));
		MongoClient client = new MongoClient(mcUri);
		MongoDatabase database = client.getDatabase("test");
		return database;
	}

	public static MongoCollection<Document> getCollection(String collectionName) {
	
		MongoDatabase db = getSourceDB();
		System.out.println("Connected to DB");
		MongoCollection<Document> coll = db.getCollection(collectionName);
		if (coll == null) {
			throw new RuntimeException("mongo db connect error");
		}
		System.out.println("Connected to "+collectionName+" collection");
		return coll;
	}
	
	public static String findEmployee(String emp_id, String email_id) {
		System.out.println("findEmployee: emp_id:"+emp_id + ":email_id:" + email_id);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll = getCollection("employees");
		if (email_id != null) {
			result = coll.find(eq("email", email_id)).first();
		}
		if (result == null && emp_id != null) {
			result = coll.find(eq("emp_id", emp_id)).first();
		}
		if(result == null) {
			output = "{name : Not Found}";
		} else {
			output = result.toJson();
		}
		 
		System.out.println("findEmployee:result:" + output);
		return output;
	}
	
	public static String addEmployee(String employeeJson) throws Exception {
		System.out.println("addEmployee: employeeJson:"+employeeJson);
		Document result = null;
		String output = null;
		MongoCollection<Document> coll =  getCollection("employees");
		Document current = Document.parse(employeeJson);
		String email_id = current.getString("email");
		if (email_id != null) {
			result = coll.find(eq("email", email_id)).first();
		}
		if (result != null ) {
			coll.updateOne(eq("email", email_id), new Document("$set", current));
			output = "Updated";
		} else {
			current.put("_id",ProfileDBServices.getNextId("user_id","employees"));
			
			coll.insertOne(current);
			output = "Created";
		}
		System.out.println("addEmployee:output:" + output);
		return output;
	}

	public static List<String> findAllEmployee() {
		MongoCollection<Document> coll = getCollection("employees");
		List<Document> foundDocument = coll.find().into(new ArrayList<Document>());
		List<String> jsonArray = new ArrayList<String>();
		for (Document document : foundDocument) {
			jsonArray.add(document.toJson());
		}
		return jsonArray;
	}
	
}
