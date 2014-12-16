package testing;

public class TestingVariable {
	
	static String nameVar;
	static String feedBackVar;
	static String testEnableVar;
	
	public static String getTestEnableVar() {
		return testEnableVar;
	}
	public static void setTestEnableVar(String testEnableVar) {
		TestingVariable.testEnableVar = testEnableVar;
	}
	public static String getName() {
		return nameVar;
	}
	public static void setName(String nameVar) {
		TestingVariable.nameVar = nameVar;
	}
	public static String getFeedBack() {
		return feedBackVar;
	}
	public static void setFeedBack(String feedBackVar) {
		TestingVariable.feedBackVar = feedBackVar;
	}
		
	
}
