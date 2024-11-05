package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ForEnterprisePage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_FormFilling extends BaseClass {

    @Test(dataProvider = "formData", dataProviderClass = DataProviders.class, groups = {"regression"})
    public void formFilling(String fname, String lname, String email, String phone, String title, String learnings, String country, String needs) {
        try {
            logger.info("Starting test case: formFilling");

            // Creating an instance of HomePage
            HomePage hp = new HomePage(driver);
            logger.info("HomePage object created");

            // Clicking on 'For Enterprise' link
            hp.clickForEnterprise();
            logger.info("Clicked on 'For Enterprise'");

            // Creating an instance of ForEnterprisePage
            ForEnterprisePage ep = new ForEnterprisePage(driver);
            logger.info("ForEnterprisePage object created");

            // Filling out the form with provided data
            ep.setFirstName(fname);
            logger.info("Set first name: {}", fname);

            ep.setLastName(lname);
            logger.info("Set last name: {}", lname);

            ep.setEmail(email);
            logger.info("Set email: {}", email);

            ep.setPhone(phone);
            logger.info("Set phone: {}", phone);

            ep.setTitle(title);
            logger.info("Set title: {}", title);

            ep.setNoOfLearners(learnings);
            logger.info("Set number of learners: {}", learnings);

            ep.setCountry(country);
            logger.info("Set country: {}", country);

            ep.setNeeds(needs);
            logger.info("Set needs: {}", needs);

            // Submitting the form
            ep.submitForm();
            logger.info("Form submitted");

            // Taking a screenshot of the form
            takeScreenshot("form filling");
            logger.info("Screenshot taken for form filling");

            // Retrieving and logging the error message
            String errorMsg = ep.getErrorMsg();
            logger.info("Error message: {}", errorMsg);
            System.out.println(errorMsg);

            // Adding assert statements to verify the error message
            Assert.assertTrue(errorMsg.contains("Must be valid email.\nexample@yourdomain.com"), "Error message does not contain expected text");
            logger.info("Assertion passed: Error message contains expected text");

        } catch (Exception e) {
            // Logging the error and failing the test case if an exception occurs
            logger.error("An error occurred during the form filling process: ", e);
            Assert.fail("Test case failed due to an exception: " + e.getMessage());
        }
    }
}
