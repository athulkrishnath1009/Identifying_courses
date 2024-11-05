package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    
    // Constructor to initialize the WebDriver
    public HomePage(WebDriver driver) {
        super(driver); 
    }
    
    // Locating web elements using @FindBy annotation
    @FindBy(xpath="//input[@id='search-autocomplete-input']")
    WebElement searchBox;
    
    @FindBy(xpath="//span[contains(text(),'Businesses')]")
    WebElement forBusiness;
    
    // Methods to perform actions on the web elements
    
    // Method to perform a search action
    public void search(String str) {
        searchBox.sendKeys(str);
        searchBox.sendKeys(Keys.ENTER);
    }
    
    // Method to click on the 'For Business' section
    public void clickForEnterprise() {
        forBusiness.click();
    }
    
    // Method to get the title of the page
    public String title() {
        return driver.getTitle();
    }
}
