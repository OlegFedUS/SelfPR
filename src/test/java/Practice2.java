import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class Practice2 {

    WebDriver driver;
    @BeforeMethod
    protected void runner() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*\", \"--window-size=1920,1080");
        System.out.println(chromeOptions.getBrowserVersion());
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    @Ignore
    protected void tables(){

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        int price = 0;
        int disc = 0;

        List<WebElement> list = driver.findElements(By.cssSelector(".table.table-bordered td:nth-child(2)"));
        List<WebElement> list2 = driver.findElements(By.cssSelector(".table.table-bordered td:nth-child(3)"));

        for (int i = 0; i < list.size(); i++){

            price = price + Integer.parseInt(list.get(i).getText());

        }

        for (int i = 0; i < list2.size(); i++){

            disc = disc + Integer.parseInt(list2.get(i).getText());

        }

        System.out.println("Price: " + price + ", Discount: " + disc);

        //Req2

        driver.findElement(By.xpath("//span[text()='Veg/fruit name']")).click();

        WebElement limit = driver.findElement(By.cssSelector(".table.table-bordered"));

        System.out.println(limit.findElements(By.tagName("tr")).get(4).findElement(By.tagName("td")).getText());

    }

    @Test
    @Ignore
    protected void Links() throws Exception{

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement limit = driver.findElement(By.xpath("//tbody/tr/td[1]/ul[1]"));

        List<WebElement> links = limit.findElements(By.tagName("a"));

        for (int i = 0; i < links.size(); i++){

            String event = Keys.chord(Keys.CONTROL, Keys.ENTER);
            links.get(i).sendKeys(event);
            Thread.sleep(2000L);

        }

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();

        while (iterator.hasNext()){

            driver.switchTo().window(iterator.next());
            System.out.println(driver.getTitle());

        }

    }

    @Test
    protected void streams(){

        List<String> strings = new ArrayList<>();

        strings.add("Oleg");
        strings.add("Victor");
        strings.add("Valeria");
        strings.add("Vitaliy");
        strings.add("Olya");

        strings.stream().filter(s->s.startsWith("V")).sorted().map(s->s.toUpperCase()).forEach(s-> System.out.println(s));

    }

}
