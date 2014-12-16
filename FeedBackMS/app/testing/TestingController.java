package testing;

import controllers.routes;
import play.data.DynamicForm;
import play.mvc.*;


public class TestingController extends Controller{

	public static Result test()
	   {
	
        DynamicForm bindedForm = form().bindFromRequest();
	    
	    String nameVar = bindedForm.get("chkname");
	    String feedBackVar = bindedForm.get("feedBack");
	
	    if(nameVar.equals("true")){
             nameVar = "Anonymous";
	    }else{
	         nameVar ="comesFromLMS";
	    }
	 
	    TestingVariable.nameVar = nameVar;
	    TestingVariable.feedBackVar = feedBackVar;
	    TestingVariable.testEnableVar = "true";
	    
	    System.out.println("I am here");
	    
	    return redirect(routes.Application.feedBackControl());
	   }

	
	
}
