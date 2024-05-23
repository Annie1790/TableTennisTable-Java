package tabletennistable;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LeagueTest {
    private final String BOB = "Bob";
    private final String CHARLOTTE = "Charlotte";
    private final String MICHELLE = "Michelle";
    private League league;
    private List<LeagueRow> rows;

    @BeforeEach
    public void setup() {
        //Given
        rows = new ArrayList<LeagueRow>();
        league = new League(rows);
    }

    @Test
    public void testAddPlayer() {
        // When
        league.addPlayer(BOB);

        // Then
        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals(1, rows.size());
        List<String> firstRowPlayers = rows.get(0).getPlayers();
        Assert.assertEquals(1, firstRowPlayers.size());
        Assert.assertThat(firstRowPlayers, IsCollectionContaining.hasItem("Bob"));
    }

    @Test
    public void playerIsNotAdded_IfPlayerAlreadyExist() {
        league.addPlayer(BOB);

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            league.addPlayer(BOB);
        });
    }

    @Test
    public void playerIsNotAdded_IfNameContainsInvalidChars() {
        final String BobWithCharacters = "Bob123/?";

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            league.addPlayer(BobWithCharacters);
        });
    }

    @Test
    public void getWinnerReturnsPlayer() {
        league.addPlayer(BOB);

        Assert.assertTrue(league.getWinner().equals(BOB));
    }

    @Test
    public void getWinnerWillReturnTopPlayer() {

        league.addPlayer(BOB);
        league.addPlayer(CHARLOTTE);
        league.addPlayer(MICHELLE);

        league.recordWin(CHARLOTTE, BOB);
        league.recordWin(MICHELLE, CHARLOTTE);
        league.recordWin(BOB, MICHELLE);

        Assert.assertTrue(league.getWinner().equals(BOB));
    }

    @Test
    public void getWinnerWillReturnNull_IfNoPlayersArePresent() {
        Assert.assertNull(league.getWinner());
    }

    @Test
    public void recordWinFail_IfWinnerIsAboveLoser() {
        league.addPlayer(BOB);
        league.addPlayer(CHARLOTTE);
        league.addPlayer(MICHELLE);

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            league.recordWin(BOB, MICHELLE);
        });
    }

}
