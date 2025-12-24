package Funmatch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Fmlive
{
    public static void main(String[] args)
    {
        WebDriver driver =new ChromeDriver();
        driver.get("https://funmatch.com/");
        String a= driver.getTitle();
        System.out.println(a);

    }
}
