package frames;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class FramesPracticeTest {

    Playwright playwright;
    Page page;
    Browser browser;

    @Test
    public void testFrameLocator(){

        String header = page.frameLocator("//iframe[@id='singleframe']")
                .locator("h5")
                .textContent();
        System.out.println(header);
        page.frameLocator("//iframe[@id='singleframe']")
                .locator("input")
                .fill("Entered!");
        assertThat(page.frameLocator("//iframe[@id='singleframe']")
                .locator("input"))
                .hasValue("Entered!");

    }

    @Test
    public void testFrame(){
//        page.reload();
        String header = page.frame("SingleFrame")
                .locator("h5")
                .textContent();
        System.out.println(header);
        page.frame("SingleFrame")
                .locator("input")
                .fill("Entered!");
        assertThat(page.frameLocator("SingleFrame")
                .locator("input"))
                .hasValue("Entered!");
    }



    @BeforeMethod
    public void beforeMethod(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.navigate("https://demo.automationtesting.in/Frames.html");
    }

    @AfterMethod
    public void afterMethod(){
        playwright.close();
    }
}
