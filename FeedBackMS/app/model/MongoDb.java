package model;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;


public class MongoDb {

	/**
	 *Returns a collection from localhost "DesignDB" DataBase
	 *@param collectionName name of the collection you want to work with
	 *@return DBCollection
	 */
	public static DBCollection getCollection(String collectionName)throws UnknownHostException {
		return getCollection(MongoDbValues.DEFAULT_HOST,MongoDbValues.DEFAULT_PORT,MongoDbValues.DEFAULT_DB,collectionName);
	}
	/**
	 *Returns a collection from localhost from the specified Database 
	 *@param dbName name of the DataBase in which the collection resides
	 *@param collectionName name of the collection you want to work with
	 *@return DBCollection
	 */
	public static DBCollection getCollection(String dbName,String collectionName)throws UnknownHostException {
		return getCollection(MongoDbValues.DEFAULT_HOST,MongoDbValues.DEFAULT_PORT,dbName,collectionName);
	}
	/**
	 *Returns a collection from specified host , port_no and Database 
	 *@param host name of the host mongod is running
	 *@param port portno where the mongod is running in the specified host
	 *@param dbName name of the DataBase in which the collection resides
	 *@param collectionName name of the collection you want to work with
	 *@return DBCollection
	 */
	public static DBCollection getCollection(String host,int port,String dbName,String collectionName)throws UnknownHostException {
		MongoClient mongoClient = new MongoClient( host , port );
		DB db = mongoClient.getDB(dbName);
		mongoClient.setWriteConcern(WriteConcern.JOURNALED);
		DBCollection collection = db.getCollection(collectionName);
		return collection;
	}

}