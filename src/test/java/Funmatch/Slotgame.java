package Funmatch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slotgame
{
    public static void main(String[] args) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://stage.funmatch.testingserverfm.com/mobile/slots");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement betcodeclick = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("span.load_more_game[data-id='pgsoft']")
        ));
        betcodeclick.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        int previousSize = 0;
        int attempts = 0;

        while (true) {
            // Get current game list
            List<WebElement> gamelist = driver.findElements(
                    By.cssSelector("span[data-provider='pgsoft'] > img"));

            // Scroll to bottom of page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Wait a bit for more content to load
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Get new game list size
            int currentSize = gamelist.size();

            // Break loop if no new games are loaded
            if (currentSize == previousSize) {
                attempts++;
                if (attempts >= 2) break; // double check that no more are loading
            } else {
                attempts = 0; // reset attempts
                previousSize = currentSize;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-provider='pgsoft'] > img")));

        List<WebElement> gamelist = driver.findElements(By.cssSelector("span[data-provider='pgsoft'] > img"));
        System.out.println("list of game under this provider ");

        for (WebElement img : gamelist) {
            String altText = img.getAttribute("alt");
            System.out.println("- " + altText);
        }
        System.out.println();

        int count= gamelist.size();
        System.out.println("Total games found " +count);


    }
}
