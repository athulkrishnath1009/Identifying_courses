package testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LanguageLearningPage;
import testBase.BaseClass;
import utilities.ExcelUtil;

public class TC_002_Language_Learning extends BaseClass {
   
    @Test(groups = {"regression"})
    public void selectingLanguages() throws IOException {
        try {
            logger.info("Starting test case: selectingLanguages");

            // Creating an instance of HomePage
            HomePage hp = new HomePage(driver);
            logger.info("HomePage object created");

            // Performing search operation
            hp.search(p.getProperty("searchElement2"));
            logger.info("Performed search for 'Language Learning'");

            // Creating an instance of LanguageLearningPage
            LanguageLearningPage llp = new LanguageLearningPage(driver);
            logger.info("LanguageLearningPage object created");
            
            // Generating a timestamp for the output file
            String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String filePath = System.getProperty("user.dir") + "\\OutputData\\Exceloutputfile_" + timeStamp + ".xlsx";
            
            // Defining headers for Excel output
            String[] headers = {"Language Number", "Language", "Level"};

            // Checking if any product is visible
            if (!llp.isVisibileProduct()) {
                logger.info("No visible product found, clicking 'Show More'");
                llp.clickShowMore();
                logger.info("'Show More' clicked");
                
                // Getting the count of languages
                int languageCount = llp.sizeOfLanguage();
                String[][] data = new String[languageCount][3];

                // Taking a screenshot of the languages learning page
                takeScreenshot("languages learning");
                logger.info("Screenshot taken for languages learning");

                logger.info("LANGUAGES:");
                System.out.println("LANGUAGES \n");

                // Looping through the languages and storing their details
                for (int i = 0; i < languageCount; i++) {
                    String language = llp.nameOfLanguage(i);
                    logger.info("Language {}: {}", i + 1, language);
                    System.out.println(language);
                    data[i][0] = String.valueOf(i + 1);
                    data[i][1] = language;
                }

                logger.info("LEVELS:");
                System.out.println("\n LEVELS \n");

                // Looping through the levels and storing their details
                for (int i = 0; i < 4; i++) {
                    String level = llp.getLevels(i);
                    logger.info("Level {}: {}", i + 1, level);
                    System.out.println(level);
                    data[i][2] = level;
                }
                // Writing the language details to an Excel file
                ExcelUtil.writeToExcel(filePath, "Language Learning", headers, data);
            } else {
                logger.info("Visible product found, clicking 'Language Button'");
                llp.clickLanguageButton();
                logger.info("'Language Button' clicked");
                
                // Getting the count of languages
                int languageCount = llp.sizeOfLanguage1();
                String[][] data = new String[languageCount][3];             

                logger.info("LANGUAGES:");
                System.out.println("LANGUAGES \n");

                // Looping through the languages and storing their details
                for (int i = 0; i < llp.sizeOfLanguage1(); i++) {
                    String language = llp.nameOfLanguage1(i);
                    logger.info("Language {}: {}", i + 1, language);
                    System.out.println(language);
                    data[i][0] = String.valueOf(i + 1);
                    data[i][1] = language;
                }

                // Applying filters and taking a screenshot
                llp.clickResult();
                logger.info("'Result' clicked");

                llp.clickDifficulty();
                logger.info("'Difficulty' clicked");

                takeScreenshot("languages learning");
                logger.info("Screenshot taken for languages learning");

                logger.info("LEVELS:");
                System.out.println("\n LEVELS \n");

                // Looping through the levels and storing their details
                for (int i = 0; i < 4; i++) {
                    String level = llp.getLevels1(i);
                    logger.info("Level {}: {}", i + 1, level);
                    data[i][2] = level;
                    System.out.println(level);
                }
                // Writing the language details to an Excel file
                ExcelUtil.writeToExcel(filePath, "Language Learning", headers, data);
            }

            // Asserting that search results are visible
            Assert.assertTrue(llp.isSearchResultsVisible(), "Search results are not visible");
            logger.info("Assertion passed: Search results are visible");
            logger.info("Test case: selectingLanguages completed");
        } catch (Exception e) {
            // Logging the error and failing the test case if an exception occurs
            logger.error("An error occurred during the test case execution: ", e);
            Assert.fail("Test case failed due to an exception: " + e.getMessage());
        }
    }
}
