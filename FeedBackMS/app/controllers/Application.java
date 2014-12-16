package controllers;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import play.Logger;
import model.LoggerVariable;
import model.FeedBack;
import model.GeneralFeedBack;
import model.MongoDb;
import model.MongoDbValues;
import model.ControllerValue;
import model.Testimony;
import model.VisibilityStatus;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import testing.TestingVariable;
import views.html.index;
import views.html.testimonyHomePage;
import views.html.testimonyThankYou;
import views.html.thankYou;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Application extends Controller {

/**
 * This function forward the http respose to index.scala.html
 * @return Result
 */
  public static Result index()
 {
	 return ok(index.render(null));
 }
  
  public static Result callTestimonyHomePage() {
	  
	  	Logger.of("FMS").debug("Entered into index()");
	  	Logger.of("FMS").debug("Exiting from index()");
	  	return ok(testimonyHomePage.render("HomePage"));
}


  public static Result updateDB() throws Exception{
	  Logger.of("FMS").debug("Entering into updateDB");
	  
	  DynamicForm bindedForm = form().bindFromRequest();
	  
	  
	  String name = bindedForm.get(MongoDbValues.NAME_COLUMN);
	  String message = bindedForm.get(MongoDbValues.MESSAGE_COLUMN);
	  
	  Logger.of("FMS").info("Parameters has been passed");
	  Testimony testimony = new Testimony();
	  
	  //Passing message and name which are taken from the html page.
	  //TODO DATE, RECIVER ID, SENDER ID AND VISIBILITY PART WILL BE DONE AFTER INTEGRATION
	  Logger.of("FMS").info("Calling updateDB()");
	  Application.updateDB(testimony, name, message, new Date(), 123, 111, VisibilityStatus.none);
	  
	  //Calling function to store the data in database.
	  Logger.of("FMS").info("Calling storeTestimony()");
	  Application.storeTestimony(message, testimony);
	  
	  //Passing control to Thank you page
	  Logger.of("FMS").info("Control forwarding to Thank you page");
	  return ok(testimonyThankYou.render("Thank You"));
  }
  
  //Function for updating testimonials
  public static void updateDB(Testimony testimony, String message, String name, Date timestamp, int receiverId, int senderId, VisibilityStatus visibilityStatus) throws Exception
  {
	  testimony.updateDB(name, message, timestamp, receiverId, senderId, visibilityStatus);
  }
  
  //function for storing the testimony in database
  public static void storeTestimony(String message, Testimony testimony) throws UnknownHostException
  {
	  Logger.of("FMS").debug("Entering into storeTestimony()");
	   //Saving message and date in database
	  
	  Logger.of("FMS").info("Entering data into database");
       testimony.putDB(message, new Date());
       
       //Function call to view message
       Logger.of("FMS").info("Calling function view()");
       Application.View();
       Logger.of("FMS").debug("Exiting from the storeTestimony()");
  }
  
  //function to view the messages
  public static void View() throws UnknownHostException
  {
	  	Logger.of("FMS").debug("Entered into View()");
	  	DBCollection DB = MongoDb.getCollection(MongoDbValues.FEEDBACK_COLL);
        DBCursor cursor = DB.find();
       // Logger.of("FMS").debug("-----------------------------------------------");
    	//Logger.of("FMS").debug("History of the messages entered by the users");
        while(cursor.hasNext())
         {
        	Logger.of("FMS").debug("Message entered by user:- "+cursor.next().get(MongoDbValues.MESSAGE_COLUMN));
         }
        Logger.of("FMS").debug("Exiting from View()");
  }

  
 /**
  * This function retrieves feedBack from index.scala.html form and assign to global 
  * variable feedBack   
  * @return void
  * @throws UnknownHostException
  * @throws ParseException
  * @throws NullPointerException
  */
 
 public static void retrieveParameter() throws NullPointerException{

	 Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+"for User"+ControllerValue.getUIDVar()+"  "+LoggerVariable.enterSyntax+" retrieving parameter from Form @"+ new Date());
	 
	  
	 if(ControllerValue.getTestFlag() == true){
		    Logger.of(LoggerVariable.fileName).debug(LoggerVariable.User+"for User-"+ControllerValue.getUIDVar()+"Testing is enable  @"+new Date());
		    ControllerValue.setFeedBackVar(TestingVariable.getFeedBack());
	        ControllerValue.setNameVar(TestingVariable.getName());
	     }else{
	    	DynamicForm bindedForm = form().bindFromRequest();
	    	ControllerValue.setFeedBackVar(bindedForm.get("feedBack"));
	        ControllerValue.setNameVar(bindedForm.get("name"));
	 	    
	 	    if(ControllerValue.getNameVar()==null)ControllerValue.setNameVar("LMS");
	     }
	 Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.existSuntax +" retrieving Parameter@"+new Date());
 }
 
 /**
  * This function stores the feedback in to the database and return thankYou page
  * @return Result
  * @throws UnknownHostException
  * @throws ParseException
  * @throws NullPointerException
  */
 
 public static Result feedBackControl() throws UnknownHostException, ParseException,NullPointerException 
    {
	   
	    Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+" "+ControllerValue.getUIDVar()+"  "+LoggerVariable.enterSyntax+" retrieving parameter from Form @"+ new Date());
	 
	    FeedBack  feedBack= new FeedBack(); 
	    GeneralFeedBack generalFeedBack = new GeneralFeedBack();
	  	
	    ControllerValue.setTestFlag(false);
	    Application.retrieveParameter();
		 
	    
	     if(ControllerValue.getFeedBackVar() != null)
	     {
	    	if(ControllerValue.getNameVar().equals("Anonymous")){
	    	     Logger.of("FMS").info(LoggerVariable.User+" has selected Anonymous @ "+new Date());
	    		 Application.storeFeedBack(ControllerValue.getFeedBackVar(),feedBack);
		   }else{
			     Logger.of("FMS").info(LoggerVariable.User+" has selected general @ "+new Date());
			     Application.storeGeneralFeedBack(generalFeedBack,ControllerValue.getNameVar(),ControllerValue.getFeedBackVar() ,new Date());
    	   }
   	    }
	     
		 Logger.of(LoggerVariable.fileName).debug(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.existSuntax +" retrieving Parameter@"+new Date());
		 return ok(thankYou.render(""));
    }
  
 /**
   * This function stores feedback in FeedBack database  
   * @param messageVar
   * @param feedBack
   * @throws UnknownHostException
   */
  public static void storeFeedBack(String messageVar,FeedBack feedBack) throws UnknownHostException
  {
	 // Logger.;
	  Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.enterSyntax+" retrieving parameter from Form @"+ new Date());
	  feedBack.putDB(messageVar,new Date());
	  Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.existSuntax +" retrieving Parameter@"+new Date());
  }
  
  /**
   * This function store feedback in feedback database
   * @param generalFeedBack
   * @param nameVar
   * @param messageVar
   * @param timeStampVar
   * @throws UnknownHostException
   */
  public static void storeGeneralFeedBack(GeneralFeedBack generalFeedBack,String nameVar,String messageVar,Date timeStampVar) throws UnknownHostException
  {
	  Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.enterSyntax+" retrieving parameter from Form @"+ new Date());
	  generalFeedBack.putDB(nameVar,messageVar, timeStampVar);
	  Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.existSuntax +" retrieving Parameter@"+new Date());

  }
  /**
   * This function displays all the documents of the collection feedBack to display.scala.html
   * @return Result
   * @throws UnknownHostException
   */
  public static Result display() throws UnknownHostException
  {
	  Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.enterSyntax+" retrieving parameter from Form @"+ new Date());
      DBCursor cursor = MongoDb.getCollection(MongoDbValues.FEEDBACK_COLL).find();
      DBObject record;
      //TODO:  kushangP	filtering
      String[] displayArray =new String[cursor.count()];
      int count=0;
      while(cursor.hasNext())
       {
      	record = cursor.next();
      	if((record.get(MongoDbValues.MESSAGE_COLUMN)) == ""){
                
      	}else{
      		
              displayArray[count]  = record.get(MongoDbValues.MESSAGE_COLUMN).toString();  
      	   }
       	  count++;
       } 
  	    List<String> strings = Arrays.asList(displayArray);
		 
  	   Logger.of(LoggerVariable.fileName).info(LoggerVariable.User+ControllerValue.getUIDVar()+"  "+LoggerVariable.existSuntax +" retrieving Parameter@"+new Date());
	   return ok(views.html.display.render(count,count,strings));
  }
  
}