import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZalupaTarasa {

    protected WebDriver driver;
    @BeforeMethod
    protected void runner(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*\", \"--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    protected String getValue(WebElement s){
        return s.findElement(By.xpath("following-sibling::td[1]")).getText();
    }

    @Test
    @Ignore
    protected void streams(){

        List<String> strings = new ArrayList<>();

        strings.add("Oleg");
        strings.add("Victor");
        strings.add("Valeria");
        strings.add("Vitaliy");
        strings.add("Olya");

        //strings.stream().filter(s->s.startsWith("O")).sorted().map(s->s.toUpperCase()).forEach(s-> System.out.println(s));

        List<String> text = Stream.of("Oleg", "Victor", "Valeria", "Vitaliy", "Olya")
                .filter(s->s.startsWith("V"))
                .sorted()
                .map(s->s.toUpperCase())
                .collect(Collectors.toList());


        String[] arr = {"o", "z", "c", "h"};
        String[] arr2 = {"l", "a", "e", "g"};

        List<String> nums = Arrays.asList(arr);
        List<String> nums2 = Arrays.asList(arr2);

        List<String> total = Stream.concat(nums.stream(), nums2.stream()).sorted().collect(Collectors.toList());

        System.out.println(total);

        Integer[] x = {1,0,1,0,2,1,2,2,0,3,1,4,0,1,5,4};

        List<Integer> xSort = Arrays.asList(x);

        List<Integer> xTotal = xSort.stream().distinct().sorted().collect(Collectors.toList());

        System.out.println(xTotal);

    }

    @Test
    @Ignore
    protected void tableStream(){

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        driver.findElement(By.xpath("//span[text()='Veg/fruit name']")).click();

        List<String> number;

        do {

            List<WebElement> itemNames = driver.findElements(By.xpath("//tr/td[1]"));

            number = itemNames.stream()
                    .filter(s -> s.getText().equalsIgnoreCase("wheat"))
                    .map(s -> getValue(s))
                    .collect(Collectors.toList());

            if (number.size() < 1) {
                driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
            }
        } while (number.size() < 1);

        Assert.assertEquals(number.get(0), "671");
    }

    @Test
    @Ignore
    protected void js(){

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");

    }

    @Test
    @Ignore
    protected void screen(){

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        //File screen =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screen, new File("d:\\screenTest.png"));

    }

    @Test
    protected void statusCode() throws Exception{

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement links = driver.findElement(By.id("gf-BIG"));
        List<WebElement> listHTTP = links.findElements(By.tagName("a"));

        int statusCode = 0;
        List<Integer> codes = new ArrayList<>();


        for (int i = 0; i < listHTTP.size(); i++){

            String attrib = listHTTP.get(i).getAttribute("href");

            HttpURLConnection sd = (HttpURLConnection) new URL(attrib).openConnection();
            sd.setRequestMethod("HEAD");
            sd.connect();
            statusCode = sd.getResponseCode();
            codes.add(statusCode);

        }

        List<Integer> errorCode = new ArrayList<>();

        for (Integer code : codes) {
            if (code > 400) {
                errorCode.add(code);
            }
        }

        System.out.println(errorCode.get(1));

    }

}
