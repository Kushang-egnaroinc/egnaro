package model;
/**
 * 
 * class to store values needed by  other Class
 * @see MongoDb
 * @see FeedBack
 * @see GeneralFeedBack
 *
 */
public class MongoDbValues {
	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 27017; 
	public static final String DEFAULT_DB = "DesignDb";
	public static final String FEEDBACK_COLL = "FeedBack";
	public static final String EMPLOYEE_COLL = "Employee";
	public static final String ID_COLUMN = "_id";
	public static final String PUSH = "$push";
	public static final String VISIBILITY_STATUS_COLUMMN = "visibilityStatus";
	public static final String SENDER_COLUMN = "sender";
	public static final String TESTIMONIALS_COLUMN = "testimonials";
	public static final String MESSAGE_COLUMN = "message";
	public static final String TIMESTAMP_COLUMN = "timestamp";
	public static final String NAME_COLUMN = "name";
    static String feedBackVar;
	static String nameVar;
	static Boolean testFlag=false;
}

