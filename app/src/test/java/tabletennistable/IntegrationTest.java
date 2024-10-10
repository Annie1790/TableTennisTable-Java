package tabletennistable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    App app;
    League league;
    LeagueRenderer leagueRenderer;
    FileService fileService;
    String name = "testname";

    @BeforeEach
    void setup() {
        league = new League();
        fileService = new FileService();
        leagueRenderer = new LeagueRenderer();
        app = new App(league, leagueRenderer, fileService);
    }

    @AfterEach
    void deleteFile() throws IOException {
        Path file = Paths.get("./testname");
        boolean result = Files.deleteIfExists(file);
    }
    @Test
    public void testPrintsEmptyGame() {
        Assertions.assertEquals("No players yet", app.sendCommand("print"));
    }
    
    @Test
    public void shouldPrintUnknownCommand() {
        final String command = "random command";

        Assertions.assertEquals("Unknown command: " + command, app.sendCommand(command));
    }

    @Test
    public void shouldSaveLeague() {
        String player = "testplayer";

        league.addPlayer(player);
        fileService.save(name, league);

        assertThat(league.getRows().size()).isEqualTo(1);
    }

    @Test
    public void shouldLoadLeague_IfLeagueIsAlreadySaved() {
        String player = "testplayer";

        league.addPlayer(player);
        fileService.save(name, league);

        assertThat(league)
                .usingRecursiveComparison()
                .isEqualTo(fileService.load(name));
    }

    @Test
    public void shouldThrowException_WhenLoading_AndLeagueIsNotSaved() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->league = fileService.load(name));
    }
    @Test
    void shouldCharlieWinGame() {
        String charlie = "Charlie";
        String bob = "Bob";
        String sam = "Sam";

        league.addPlayer(bob);
        league.addPlayer(sam);
        league.addPlayer(charlie);

        league.recordWin(charlie, bob);

        assertThat(league.getWinner()).isEqualTo(charlie);
    }
}
