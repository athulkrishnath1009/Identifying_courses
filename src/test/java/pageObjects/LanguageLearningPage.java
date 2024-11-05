package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LanguageLearningPage extends BasePage {
    
    // Constructor to initialize the WebDriver
    public LanguageLearningPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    // Locating web elements using @FindBy annotation
    
    @FindBy(xpath="//div[@data-testid='search-filter-group-Language']//span[contains(text(),'Show')]")  
    WebElement showMore;
    
    @FindBy(xpath="//div[@data-testid='search-filter-group-Language']//div[@class='css-1bmgd8']")   
    List<WebElement> listOfLanguages;
    
    @FindBy(xpath = "//div[@data-testid='search-filter-group-Level']//div[@class='css-1bmgd8']")
    List<WebElement> levels;
    
    @FindBy(xpath="//div[text()=\"Difficulty\"]")   
    WebElement difficulty;
    
    @FindBy(xpath="//button[@class='css-1bbqjkf']")   
    WebElement languageBtn;
    
    @FindBy(xpath = "//div[@class='cds-checkboxAndRadio-labelText']")
    List<WebElement> level1;
    
    @FindBy(xpath="//label[@class='cds-checkboxAndRadio-label']") 
    List<WebElement> languages;
    
    @FindBy(xpath="//div[text()='Product Type']")   
    WebElement productType;
    
    @FindBy(xpath="//span[text()='Show results']")   
    WebElement showResultBtn;
    
    @FindBy(xpath="//span[contains(text(),'Results for')]")   
    WebElement resultsElement;
    
    // Methods to perform actions on the web elements
    
    // Method to click on 'Show More' button
    public void clickShowMore() {
        showMore.click();
    }
    
    // Method to select a language from the list by index
    public void selectLanguage(int i) {
        listOfLanguages.get(i).click();
    }
    
    // Method to get the size of the language list
    public int sizeOfLanguage() {
        return listOfLanguages.size();
    }
    
    // Method to get the size of the languages list
    public int sizeOfLanguage1() {
        return languages.size();
    }
    
    // Method to get the name of a language by index
    public String nameOfLanguage(int i) {
        return listOfLanguages.get(i).getText();
    }
    
    // Method to get the name of a language from the languages list by index
    public String nameOfLanguage1(int i) {
        return languages.get(i).getText();
    }
    
    // Method to get the level by index
    public String getLevels(int i) {
        return levels.get(i).getText();
    }
    
    // Method to get the size of the levels list
    public int getLevelsSize() {
        return levels.size();
    }
    
    // Method to get the level from level1 list by index
    public String getLevels1(int i) {
        return level1.get(i).getText();
    }
    
    // Method to click on the 'Difficulty' checkbox
    public void clickDifficulty() {
        difficulty.click();
    }
    
    // Method to click on the 'Language' button
    public void clickLanguageButton() {
        languageBtn.click();
    }
    
    // Method to check if the 'Product Type' element is visible
    public Boolean isVisibileProduct() {
        try {
            return productType.isDisplayed();
        } catch(Exception e) {
            return false;
        }
    }
    
    // Method to click on the 'Show Results' button
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
