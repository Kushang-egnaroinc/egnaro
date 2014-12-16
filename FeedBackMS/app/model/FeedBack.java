package model;
import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


public class FeedBack {

	  String message;
	  Date timestamp;

	
	/**
	 * 
	 * Inserts the specified message and timeStamp into the FeedBack collection
	 * @param message
	 * @param timestamp
	 * @throws UnknownHostException
	 * 
	 */
	public void putDB(String message,Date timestamp) throws UnknownHostException {

		this.message=message;
		this.timestamp=timestamp;
		
		DBCollection collection = MongoDb.getCollection(MongoDbValues.FEEDBACK_COLL);
		BasicDBObject document = new BasicDBObject(MongoDbValues.MESSAGE_COLUMN, this.message)
		.append(MongoDbValues.TIMESTAMP_COLUMN, this.timestamp);
		collection.insert(document);

	}



}

