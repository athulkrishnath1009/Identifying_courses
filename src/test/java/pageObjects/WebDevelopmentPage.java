package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebDevelopmentPage extends BasePage {
    
    // Constructor to initialize the WebDriver
    public WebDevelopmentPage(WebDriver driver) {
        super(driver); 
    }
    
    // Locating web elements using @FindBy annotation
    
    @FindBy(xpath="//span[text()='English']")   // English checkbox
    WebElement englishCheckbox;
    
    @FindBy(xpath="//span[text()='Beginner']")   // Beginners checkbox
    WebElement beginnersCheckbox;
    
    @FindBy(xpath="//span[text()='Show results']")   // Show results button
    WebElement showResultBtn;
    
    @FindBy(xpath="//div[text()='Product Type']")   // Product Type section
    WebElement productType;
    
    @FindBy(xpath="//div[text()=\"Difficulty\"]")   // Difficulty section
    WebElement difficulty;
    
    @FindBy(xpath="//h3[@class='cds-CommonCard-title css-6ecy9b']") // Course titles
    List<WebElement> titles;
    
    @FindBy(xpath="//div[@aria-label='Rating']//span[@class=' css-6ecy9b']") // Course ratings
    List<WebElement> rating;
    
    @FindBy(xpath="//div[@class='cds-ProductCard-footer']//div[@class='cds-CommonCard-metadata']") // Course lengths
    List<WebElement> courseLength;
    
    @FindBy(xpath="//span[contains(text(),'Results for')]")   // Results element
    WebElement resultsElement;
    
    // Methods to perform actions on the web elements
    
    // Method to tick the English checkbox
    public void tickEnglishCheckBox() {
        englishCheckbox.click();
    }
    
    // Method to tick the Beginners checkbox
    public void tickBeginnersCheckBox() {
        beginnersCheckbox.click();
    }
    
    // Method to get the course title by index
    public String getCourseTitle(int i) {
        String courseTitle = titles.get(i).getText();
        return courseTitle; 
    }
    
    // Method to get the course rating by index
    public String getCourseRating(int i) {
        String courseRating = rating.get(i).getText();
        return courseRating; 
    }
    
    // Method to get the course length by index
    public String getCourseLength(int i) {
        String courseLength1 = courseLength.get(i).getText();
        return courseLength1; 
    }
    
    // Method to check if the Product Type section is visible
    public Boolean isVisibileProduct() {
        try {
            return productType.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Method to click on the Difficulty section
    public void clickDifficulty() {
        difficulty.click();
    }
    
    // Method to click on the Show Results button
    public void clickResult() {
        showResultBtn.click();
    }
    
    // Method to check if the search results are visible
    public boolean isSearchResultsVisible() {
        try {
            return resultsElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
