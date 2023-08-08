@regression-high-priority
Feature: qa-automate-coding-challenge - Github Repo Search
  As a web developer school teacher,
  Adam wishes to maintain an easy overview of the progress of his students,
  So he can get a simple list of repos owned by a given student, and see the most basic details of each repo.
  He also wants to be able to easily navigate to a specific repo, in case he needs to dive deeper.

  Scenario Outline: Verify valid search term
    Given I open the application
    When I "<action>" the "<username>"
    Then the repo list is displayed

    Examples:
      |  action  |   username  |
      |  click   |   torvalds  |
      |  enter   |   torvalds  |

  Scenario: Verify Basic repo search results
    Given I open the application
    When I perform a search with a valid repo name "torvalds"
    Then the repo information name and description is displayed for each found repo

  Scenario: Verify Search and open repo link
    Given I open the application
    When I perform a search with a valid repo name "torvalds"
    Then the repo links opens the repo page in a new tab