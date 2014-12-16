package model;
import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class Testimony extends GeneralFeedBack {
	private int receiverId;
	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public VisibilityStatus getVisibilityStatus() {
		return visibilityStatus;
	}

	public void setVisibilityStatus(VisibilityStatus visibilityStatus) {
		this.visibilityStatus = visibilityStatus;
	}

	private int senderId;
	private VisibilityStatus visibilityStatus;

	public int updateDB(String message, String name, Date timestamp,
			int receiverId, int senderId, VisibilityStatus visibilityStatus) throws UnknownHostException {
		
			this.name = name;
			this.message = message;
			this.timestamp = timestamp;
			this.receiverId = receiverId;
			this.senderId = senderId;
			this.visibilityStatus = visibilityStatus;
			DBCollection collection = MongoDb.getCollection(MongoDbValues.EMPLOYEE_COLL);

			BasicDBObject query = new BasicDBObject(MongoDbValues.ID_COLUMN, this.receiverId);
			BasicDBObject testimony = new BasicDBObject(MongoDbValues.NAME_COLUMN, this.name)
					.append(MongoDbValues.MESSAGE_COLUMN, this.message)
					.append(MongoDbValues.TIMESTAMP_COLUMN, this.timestamp)
					.append(MongoDbValues.SENDER_COLUMN,this.senderId)
					.append(MongoDbValues.VISIBILITY_STATUS_COLUMMN,this.visibilityStatus.toString());
			BasicDBObject update = new BasicDBObject(MongoDbValues.PUSH,
				new BasicDBObject(MongoDbValues.TESTIMONIALS_COLUMN, testimony));
			
			collection.update(query, update);
			

			return 1;
			}
	
}

