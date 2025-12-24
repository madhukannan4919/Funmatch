package Funmatch;

import org.openqa.selenium.By;
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

public class Casinoname {
    public static void main(String[] arg) throws InterruptedException {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://stage.funmatch.testingserverfm.com/mobile/casino");

        WebElement Auth = driver.findElement(By.cssSelector("input[name='access_login']"));
        Auth.sendKeys("1111");

        WebElement authButton = driver.findElement(By.cssSelector("button[type='submit']"));
        authButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("img.rem_lazy_loading_removed")));

        WebElement algElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.casino_sub_filter li[data-id='luckystreak'][data-tab='red_games_container'][data-table='cg']")));

        algElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.tile[data-provider='luckystreak'] img")));

        List<WebElement> gameImages = driver.findElements(By.cssSelector("a.tile[data-provider='luckystreak'] img"));

        System.out.println("luckystreak Provider Game Names:");

        for (WebElement img : gameImages) {
            String altText = img.getAttribute("alt");
            System.out.println("- " + altText);
        }

        System.out.println();
        int count = gameImages.size();
        System.out.println("Total games found: " + count);
       // driver.close();
    }
}

