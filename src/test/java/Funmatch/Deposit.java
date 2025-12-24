package Funmatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Deposit {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");


        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);


        driver.get("https://develop.funmatch.testingserverfm.com/mobile/login");

      //  WebElement Auth = driver.findElement(By.cssSelector("input[name='access_login']"));
     //   Auth.sendKeys("1111");

      //  WebElement authButton = driver.findElement(By.cssSelector("button[type='submit']"));
     //   authButton.click();

        WebElement usernameField = driver.findElement(By.cssSelector(".fm-input_field.fm-usr_name.loginUsername.fm_emj_is"));
        WebElement userPassword = driver.findElement(By.cssSelector(".fm-input_field.fm-password_r.loginPassword"));


        usernameField.sendKeys("test4900");
        userPassword.sendKeys("Test@123");

        WebElement loginBtn = driver.findElement(By.cssSelector(".cls_loginBtn_sme"));

        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement elementByCSS = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fm_dep_text_hs"))
        );
        elementByCSS.click();

        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='depositinstantamount']"))
        );
        amountField.clear();
        amountField.sendKeys("200000");

        WebElement proceedToPayButton = driver.findElement(By.cssSelector(".depo_poceed_to_pay.clsPrcedPay"));
        proceedToPayButton.click();

        String originalWindow = driver.getWindowHandle();

        wait.until(driver1 -> driver1.getWindowHandles().size() > 1);

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        WebElement successButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='success']"))
        );
        successButton.click();


        driver.switchTo().window(originalWindow);

        WebElement okayButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick=\"location.href='https://stage.funmatch.testingserverfm.com/mobile/myAccount'\"]"))
        );
        okayButton.click();


//        driver.quit(); // Close browser
    }
}
