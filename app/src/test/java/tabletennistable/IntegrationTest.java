package tabletennistable;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class IntegrationTest {

    App app;

    @BeforeEach
    void setup() {
        app = new App(new League(), new LeagueRenderer(), new FileService());

    }
    @Test
    public void testPrintsEmptyGame() {
        Assertions.assertEquals("No players yet", app.sendCommand("print"));
    }

    @Test
    void shouldExitGame_IfUserNoLongerPlays() throws FileNotFoundException {
        // https://stackoverflow.com/questions/36349827/testing-main-method-by-junit
        final String COMMAND = "quit";
        boolean isGameActive = true;
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File("tabletennistable/Main.java"));
        System.setIn(fips);
        Main.main(args);
        System.setIn(original);


    }

    @Test
    void shouldPlayer1Win() {

    }

    @Test
    void shouldPlayer1Lose() {

    }
}
