package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForEnterprisePage extends BasePage {

    // Constructor to initialize the WebDriver
    public ForEnterprisePage(WebDriver driver) {
        super(driver);
    }
    
    // Locating web elements using @FindBy annotation
    @FindBy(id="FirstName")
    WebElement firstName;
    
    @FindBy(id="LastName")
    WebElement lastName;
    
    @FindBy(id="Email")
    WebElement email;
    
    @FindBy(id="Phone")
    WebElement phone;
    
    @FindBy(id="Title")
    WebElement jobTitle;
    
    @FindBy(id="Self_reported_employees_to_buy_for__c")
    WebElement noOfLearners;
    
    @FindBy(id="Country")
    WebElement country;
    
    @FindBy(id="State")
    WebElement state;
    
    @FindBy(id="What_the_lead_asked_for_on_the_website__c")
    WebElement needs;
    
    @FindBy(xpath="//button[normalize-space()='Submit']")
    WebElement submitButton;
    
    @FindBy(id="ValidMsgEmail")
    WebElement errorMsg;
    
    // Methods to perform actions on the web elements
    
    // Method to set the first name
    public void setFirstName(String fname) {
        firstName.sendKeys(fname);
    }
    
    // Method to set the last name
    public void setLastName(String lname) {
        lastName.sendKeys(lname);
    }
    
    // Method to set the email
    public void setEmail(String email1) {
        email.sendKeys(email1);
    }
    
    // Method to set the phone number
    public void setPhone(String phone1) {
        phone.sendKeys(phone1);
    }
    
    // Method to set the job title
    public void setTitle(String title) {
        jobTitle.sendKeys(title);
    }
    
    // Method to set the number of learners
    public void setNoOfLearners(String no_of_learners) {
        noOfLearners.sendKeys(no_of_learners);
    }
    
    // Method to set the country
    public void setCountry(String country1) {
        country.sendKeys(country1);
    }
    
    // Method to set the state
    public void setState(String state) {
        this.state.sendKeys(state);
    }
    
    // Method to set the needs
    public void setNeeds(String need) {
        needs.sendKeys(need);
    }
    
    // Method to submit the form
    public void submitForm() {
        submitButton.click();
    }
    
    // Method to get the error message
    public String getErrorMsg() {
        return errorMsg.getText();
    }
}
