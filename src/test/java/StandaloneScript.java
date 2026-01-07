
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneScript {
    public static void main(String[] args) {
        System.out.println("This is a standalone Java application.");
    }

    @Test
    public void driverInitialization() {
        System.out.println("This is a sample test method.");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
        
        String itemName = "ZARA COAT 3";

        email.sendKeys("shailesh@abc.com");
        pass.sendKeys("Shai#111");

        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginButton.click();

        List <WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
        WebElement matchedItem = items.stream().filter(item->item.findElement(By.tagName("b")).getText().equals(itemName)).findFirst().orElse(null);
        matchedItem.findElement(By.xpath("//div[@class='card-body']//button[2]")).click();

        //Wait till item added to cart
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        WebElement cart = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
        cart.click();

        //Validate cart items
        List <WebElement> cartItems = driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted"));
       // WebElement we = cartItems.stream().filter(cartItem->cartItem.findElement(By.xpath("//div[@class='cartSection']/h3")).getText().equals(itemName)).findFirst().orElse(null);
        WebElement we =     cartItems.stream().filter(item->item.findElement(By.xpath("//div[@class='cartSection']/h3")).getText().equals(itemName)).findFirst().orElse(null);
        System.out.println(we.getText());
        assert we != null;
    }
}
