package model;
import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;


public class GeneralFeedBack extends FeedBack{
	/**
	 * 
	 */
    String name;
	/**
	 * Inserts the specified name,message and timeStamp into the FeedBack collection
	 * @param name name of the person giving feedBack
	 * @param message the feedback itself
	 * @param timestamp the date in which the feedback was given
	 */
	public void putDB(String name,String msg,Date timestamp) throws UnknownHostException {

		this.name=name;
		this.message=msg;
		this.timestamp=timestamp;
		DBCollection collection = MongoDb.getCollection(MongoDbValues.FEEDBACK_COLL);
		BasicDBObject document = new BasicDBObject(MongoDbValues.NAME_COLUMN, this.name)
		.append(MongoDbValues.MESSAGE_COLUMN, this.message)
		.append(MongoDbValues.TIMESTAMP_COLUMN, this.timestamp);
		collection.insert(document);

	}
	
}

