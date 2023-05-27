package ApiProjectHighLevel;



import com.aventstack.extentreports.Status;
import listeners.Listeners;
import org.apache.log4j.PropertyConfigurator;

import java.util.logging.Logger;



public class Log {

    static {
        init();
    }
    private  static Logger Log = Logger.getLogger(Log.class.getName());

    private  static  void  init()
    {
        PropertyConfigurator.configure(System.getProperty("user.dir").toString() + "/src/main/resources/log4j.properties");
    }

    /** this is to print log for the beginning of the test case */
    public static void startTestCase(String sTestCaseName){
        Log.info("starting execution of test " + sTestCaseName );
        Listeners.extestTest.get().info("starting execution of test <b>" + sTestCaseName +"</b>");
    }

    /** this is to print log for the ending of the test case */
    public static void endTestCase(String sTestCaseName){
        Log.info("Testcase execution of finished.");
    }

    /**
     * this method is used to print message on console
     * @param message -  string log to print on console
     */
    public static void info(String message){
        Log.info(message);
    }

    /**
     * this method is used to print message on console & on extent report
     * @param message - string log to print on console & extent report
     */
    public static void info1(String message){
        info(message);
        Listeners.extestTest.get().log(Status.PASS, message);
        //Listeners.extestTest.get().log(Status.PASS, message);
    }

}
