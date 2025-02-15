### UI Test Scenarios for qa-automation-coding-challenge

This README file outlines the test scenarios for testing the qa-automation-coding-challenge application's user interface.\
The application consists of a header, a search form, and a search result section.

---

## Brief Description of the why and how

- All tests below when execute together meet the "Acceptance Criteria",\
but at the automation level.
- Why I only automated the tests "End2End(Smoke) Tests - High Priority"?\
Because those tests are the highest priority since they reproduce the users real behavior.
- Why did I choose to use selenium instead of cypress?\
Because cypress can't work with multiple tabs, selenium can.
- The tests show 4 implemented tests because of the outline scenario, but in reality, there are only 3 tests.
- This file works as the 'challenge' project readme file.
- What could get better?\
The chromedriver, both chromedrivers being used (macos and windows) are for a specific chrome version, adding a docker-compose file to this project would avoid the chromedriver version problem.
---

## How to set up and run the automated tests

- Java 11 version.
- Maven.
- Chrome browser (if macos version 115, if windows version 114).
- Clone the repository.
- Open the terminal and:
  - Download the dependencies.\
  `yarn`
  - Start the project.\
  `yarn start`
  - Go to the folder 'challenge'\
  `/challenge`
  - Inside the 'challenge' folder run the command:\
  `mvn clean test allure:report`
- Reports are generated in the folder:\
`/challenge/target/site/index.html`

---

## Test Scenarios

## UI(Sanity) Test Suite - Low Priority

```
Scenario: Verify title in header
Given I open the application
Then the app title in the header matches the expected title
```

```
Scenario: Verify search form
Given I open the application
Then the search form elements are displayed
```

## End2End(Smoke) Tests - High Priority

```
Scenario Outline: Verify valid search term
Given I open the application
When I <action> the <username>
Then the repo list is displayed

Examples:
|  action  |   username  |
|  click   |   torvalds  |
|  enter   |   torvalds  |
```

```
Scenario: Verify Basic repo search results
Given I open the application
When I perform a search with a valid repo name "torvalds"
Then the repo information name and description is displayed for each found repo
```

```
Scenario: Verify Search and open repo link
Given I open the application
When I perform a search with a valid repo name "torvalds"
Then the repo links opens the repo page in a new tab
```

## End2End(Smoke) Tests - Low Priority

```
Scenario Outline: Verify Search short time info message displayed
Given I open the application
When I perform a search a repo username <username>
Then the short message <shortmessage> is displayed
Examples:
|                  username                     |       shortmessage      |
|                  torvalds                     |         Success!        |
|                 torvaldsss                    |  Github user not found  |
|  {an username with more then 4127 caracters}  |   Something went wrong  |
```
