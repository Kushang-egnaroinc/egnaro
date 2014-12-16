package testing;

public class TestCasesForFeedBackControll {
   
	public static void testCase01(){
	//testCase 01
	    TestingVariable.setFeedBack("It is working in testcase 01");
        TestingVariable.setName("kushang");
	}

	public static void testCase02(){
    //testCase 02
	    TestingVariable.setFeedBack("It is Working in testcase 02");
	    TestingVariable.setName(null);
	}
	
	public static void testCase03(){
    //testCase 01
	    TestingVariable.setFeedBack(null);
        TestingVariable.setName("kushang");
	}
    
	public static void testCase04(){
	//testCase 04
        TestingVariable.setFeedBack(null);
        TestingVariable.setName(null);
	}

}
