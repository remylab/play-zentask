package views;

import static play.test.Helpers.fakeGlobal;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import play.test.FakeApplication;
import play.test.Helpers;

public class DummyTest {

    public static FakeApplication app;

    @Test
    public void test() throws Exception {
        
        // Create a new instance of the html unit driver
        WebDriver driver = new HtmlUnitDriver();

        app = Helpers.fakeApplication(fakeGlobal());
        Helpers.start(app);

        // And now use this to visit Google
        driver.get("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the
        // element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
    }
}
