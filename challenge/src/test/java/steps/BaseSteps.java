package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import setup.MyWebDriver;

public class BaseSteps {

    @Before
    public static void setup(){
        System.out.println("Before class");
    }

    @After
    public static void teardown(){
        System.out.println("After class");
        MyWebDriver.tearDown();
    }

}
