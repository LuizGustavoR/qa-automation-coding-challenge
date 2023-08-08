package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.MyWebDriver;

import java.util.List;

public class SearchPage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(className = "submit")
    private WebElement searchButton;

    @FindBy(className = "repo-list-container")
    private WebElement repoListContainer;

    public SearchPage() {
        PageFactory.initElements(MyWebDriver.getInstance(), this);
    }

    public void visit(String pageUrl) {
        MyWebDriver.getInstance().get(pageUrl);
    }

    public void search(String username) {
        usernameField.sendKeys(username);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickEnter() {
        searchButton.sendKeys(Keys.ENTER);
    }

    public boolean isRepoListDisplayed() {
        return repoListContainer.isDisplayed();
    }

    public boolean isRepoListIndividualElementsDisplayed() {
        boolean isDisplayed = false;
        for(WebElement webElement : getRepoList()){
            boolean isDisplayedEl1 = webElement.findElement(By.tagName("a")).isDisplayed();
            boolean isDisplayedEl2 = webElement.findElement(By.className("repo-description")).isDisplayed();
            isDisplayed = isDisplayedEl1 && isDisplayedEl2;
            if (!isDisplayed) break;
        }
        return isDisplayed;
    }

    public boolean clickReposLinks() {
        boolean isUrlEqual = false;
        for (WebElement webElement : getRepoList()) {
            WebElement element = webElement.findElement(By.tagName("a"));
            String repoUrl = element.getAttribute("href");
            element.click();

            // get all tabs
            List<String> windowHandlesList = List.copyOf(MyWebDriver.getInstance().getWindowHandles());
            String currentWindowHandle = MyWebDriver.getInstance().getWindowHandle();
            int tabPosition = windowHandlesList.indexOf(currentWindowHandle) + 1;
            // change the webdriver context to get the url of the new tab
            String currentUrl = MyWebDriver.getInstance().switchTo().window(windowHandlesList.get(tabPosition)).getCurrentUrl();

            // Compare urls
            isUrlEqual = repoUrl.equals(currentUrl);
            if (!isUrlEqual){
                break;
            }else{
                // Close tab and change the webdriver context to the first tab
                MyWebDriver.getInstance().close();
                MyWebDriver.getInstance().switchTo().window(windowHandlesList.get(0));
            }
        }
        return isUrlEqual;
    }

    // Methods to work with the repo list and its elements

    private List<WebElement> getRepoList() {
        return MyWebDriver.getInstance().findElements(By.cssSelector("li.repo-row"));
    }

}
