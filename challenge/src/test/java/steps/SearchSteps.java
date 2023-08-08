package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private SearchPage searchPage = new SearchPage();

    @Given("I open the application")
    public void iOpenTheApplication() {
        searchPage.visit("http://localhost:3000/");
    }

    @When("I {string} the {string}")
    public void iActionTheUsername(String action, String username) {
        searchPage.search(username);
        switch (action) {
            case "click":
                searchPage.clickSearchButton();
                break;
            case "enter":
                searchPage.clickEnter();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

    @Then("the repo list is displayed")
    public void theRepoListIsDisplayed() {
        assertTrue(searchPage.isRepoListDisplayed());
    }

    @When("I perform a search with a valid repo name {string}")
    public void iPerformASearchWithAValidRepoName(String repoUserName) {
        searchPage.search(repoUserName);
        searchPage.clickSearchButton();
    }

    @Then("the repo information name and description is displayed for each found repo")
    public void theRepoInformationNameAndDescriptionIsDisplayedForEachFoundRepo() {
        assertTrue(searchPage.isRepoListIndividualElementsDisplayed());
    }

    @Then("the repo links opens the repo page in a new tab")
    public void theRepoLinksOpensTheRepoPageInANewTab() {
        assertTrue(searchPage.clickReposLinks());
    }

}
