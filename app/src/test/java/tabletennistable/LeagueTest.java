package tabletennistable;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LeagueTest {
    League league;

    @BeforeEach
    public void setup() {
        //Given
        league = new League();
    }
    @Test
    public void testAddPlayer()
    {
        // When
        league.addPlayer("Bob");

        // Then
        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals(1, rows.size());
        List<String> firstRowPlayers = rows.get(0).getPlayers();
        Assert.assertEquals(1, firstRowPlayers.size());
        Assert.assertThat(firstRowPlayers, IsCollectionContaining.hasItem("Bob"));
    }

    @Test
    public void playerIsNotAdded_IfPlayerAlreadyExist() {
        league.addPlayer("Bob");

        Assert.assertThrows(IllegalArgumentException.class, () -> {
           league.addPlayer("Bob");
        });
    }

    @Test
    public void playerIsNotAdded_IfNameContainsInvalidChars() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            league.addPlayer("Bob12´^&");
        });
    }


}
