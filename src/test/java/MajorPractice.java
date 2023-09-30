import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MajorPractice {

    WebDriver driver;


    @BeforeMethod
    protected void runner() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*\", \"--window-size=1920,1080");
        chromeOptions.getBrowserVersion();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @Ignore
    void firstRow(){

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //Radio
        driver.findElement(By.cssSelector("input[value='radio1']")).click();

        //DynamicDrop
        driver.findElement(By.cssSelector(".inputs.ui-autocomplete-input")).sendKeys("Rus");

        List<WebElement> dynamicList = driver.findElements(By.cssSelector(".ui-menu-item"));

        for (WebElement option : dynamicList){
            String optionsTxt = option.getText();
            if (optionsTxt.contains("Russian Federation")) {
                option.click();
            }
        }

        //StaticDrop (Select)

        WebElement select = driver.findElement(By.id("dropdown-class-example"));

        Select options = new Select(select);
        options.selectByIndex(3);

        //Checkbox
        driver.findElement(By.xpath("//input[@name='checkBoxOption3']")).click();

        Assert.assertEquals(driver.findElement(By.id("autocomplete")).getText(), "Russian Federation");

    }

    @Test
    @Ignore
    protected void secondRowSwitch() throws Exception{

        //Alerts (Switch)

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("name")).sendKeys("Oleg");
        driver.findElement(By.id("alertbtn")).click();

        Thread.sleep(1000L);
        driver.switchTo().alert().accept();

        //New Tab (Switch)

        WebElement newTab = driver.findElement(By.id("openwindow"));
        String eventTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        newTab.sendKeys(eventTab);

        Set<String> window = driver.getWindowHandles();
        Iterator<String> iterator = window.iterator();

        String child = iterator.next();
        String parent = iterator.next();

        driver.switchTo().window(parent);
        System.out.println(driver.getTitle());

        driver.switchTo().window(child);

        //New Window (Switch) *********************************

    }

    @Test
    @Ignore
    protected void thirdRowTables(){

        //Tables

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement limited = driver.findElement(By.id("product"));

        List<WebElement> webElementList = limited.findElements(By.tagName("tr")).get(5).findElements(By.tagName("td"));

        for (int i = 0; i < webElementList.size(); i++){
            System.out.println(webElementList.get(i).getText());
        }

        //Table2

        List<WebElement> lim2 = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));

        int count = 0;

        for (int i = 0; i < lim2.size(); i++){

            count = count + Integer.parseInt(lim2.get(i).getText());

        }

        System.out.println(count);

    }

    @Test
    @Ignore
    protected void actionsTest(){

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Reload']"))).click().build().perform();

    }

    @Test
    @Ignore
    protected void iframeTest(){

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement iframe = driver.findElement(By.id("courses-iframe"));

        driver.switchTo().frame(iframe);

        System.out.println(driver.findElement(By.cssSelector(".theme-btn.register-btn")).getText());

    }

    @Test
    protected void webLinks() throws Exception{

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement limitedDr = driver.findElement(By.xpath("//tbody/tr/td[1]/ul[1]"));

        List<WebElement> linksList = limitedDr.findElements(By.tagName("a"));

        for (int i = 0; i < linksList.size(); i++){

            String event = Keys.chord(Keys.CONTROL, Keys.ENTER);
            linksList.get(i).sendKeys(event);
            Thread.sleep(2000L);

        }

        Set<String> win = driver.getWindowHandles();
        Iterator<String> iterator = win.iterator();

        while (iterator.hasNext()){
            driver.switchTo().window(iterator.next());
            System.out.println(driver.getTitle());
        }

    }


}
