import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class YandexMarketTest {
    public WebDriver driver;
    String priceFrom = "20000";
    String priceTo = "35000";

    @Test
    public void yandexMarketTest() throws InterruptedException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(option);
        driver.get("https://yandex.ru/");

        WebElement marketLink = driver.findElement(By.xpath("//div[contains(text(),'Маркет')]"));
        marketLink.click();
        Thread.sleep(5000);
        ArrayList<String> wl = new ArrayList<String>(driver.getWindowHandles());
        if (!wl.isEmpty()){
            driver.switchTo().window(wl.get(1));

        }

        WebElement categoryLink = driver.findElement(By.xpath("//span[contains(text(),'Компьютеры')]"));
        categoryLink.click();
        WebElement subCategoryLink = driver.findElement(By.xpath("//a[contains(text(),'Планшеты')]"));
        subCategoryLink.click();
        WebElement priceFromField = driver.findElement(By.id("glpricefrom"));
        priceFromField.sendKeys(priceFrom);
        Thread.sleep(5000);
        WebElement priceToField = driver.findElement(By.id("glpriceto"));
        priceToField.sendKeys(priceTo);
        Thread.sleep(5000);

        WebElement manufacturerCheckBox = driver.findElement(By.xpath("//body/div[2]/div[3]/div[3]/div[4]/aside[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/fieldset[1]/ul[1]/li[1]/div[1]/a[1]/label[1]/div[1]/span[1]"));
        if (!manufacturerCheckBox.isSelected()){
            manufacturerCheckBox.click();
        }
        Thread.sleep(5000);

        List<WebElement> productList = driver.findElements(By.cssSelector("[data-tid=\"ce80a508\"]"));
        String productTitle = productList.get(1).getText();
        WebElement searchField = driver.findElement(By.id("header-search"));
        searchField.sendKeys(productTitle);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        submitButton.click();
        System.out.println("searching");
        Thread.sleep(5000);

        String resultTitle = driver.findElements(By.cssSelector("[data-zone-name=\"title\"]")).get(0).getText();
        System.out.println(resultTitle);

        Assert.assertEquals(resultTitle, productTitle);
        driver.quit();
    }

}
