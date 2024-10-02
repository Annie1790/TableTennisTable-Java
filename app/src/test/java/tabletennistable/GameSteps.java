package tabletennistable;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GameSteps {
    // Current app instance
    private App app;

    // Last response
    private String response;

    @Given("A game is initialized and a league is created")
    public void aGameIsInitializedAndALeagueIsCreated() {
        League league = new League();
        LeagueRenderer leagueRenderer = new LeagueRenderer();
        FileService fileService = new FileService();
        app = new App(league, leagueRenderer, fileService);
    }

    @And("A player {string} is added")
    public void aPlayerBobIsAdded(String name) {
        final String command = "add player " + name;

        app.sendCommand(command);
    }

    @When("{string} won a round against {string}")
    public void charlieWonRound(String winner, String loser) {
        app.sendCommand(String.format("record win %s %s", winner, loser));
    }

    @Given("the league has no players")
    public void givenTheLeagueHasNoPlayers()
    {
        // Nothing to do - the default league starts with no players
    }

    @And("Print the league")
    public void whenIPrintTheLeague()
    {
        response = app.sendCommand("print");
    }

    @Then("I should see {string}")
    public void iShouldSeeString(String expected)
    {
        Assert.assertEquals(expected, response);
    }

    @Then("{string} is the winner")
    public void charlieIsTheWinner(String name) {
         response = app.sendCommand("winner");

         Assert.assertEquals(name, response);
    }
}
