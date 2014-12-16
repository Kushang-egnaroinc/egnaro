package model;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;


public class Controller {
	public static void main(String[] args) throws UnknownHostException {
	  	// TODO Auto-generated method stub

		BasicDBObject document = new BasicDBObject("MovieName", "InterSteller")
		.append("Ratting", "9.3");
	
		MongoDb.getCollection("localhost", 27017, "sample1", "sampleCollec").insert(document);
	
	}
	
/*	
  public DBObject display(String id) throws Exception
	{
		
		 	BasicDBObject doc = new BasicDBObject();
			doc.put("_id",id);
			DBCursor cursor=DBConnectionUtil.getDBconnection(EgnaroDBUtil.hostName, 
					EgnaroDBUtil.dbName, 
					EgnaroDBUtil.basicDetailsCollection).find(doc);
			DBObject record;
			record=cursor.next();
			return record;
  }
*/
}
