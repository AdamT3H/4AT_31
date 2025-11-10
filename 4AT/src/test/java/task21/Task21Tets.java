package task21;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import java.io.File;

public class Task21Tets {
    @Test
    void calcTest() throws FindFailed {
        Screen screen = new Screen();
        new Screen().capture().save(".", "capture_" + System.currentTimeMillis() + ".png");

        Pattern pattern = new Pattern(
                new File("src/main/resources/pattern/search_input.png")
                        .getAbsolutePath());
        screen.find(pattern).click();

        screen.type("calc");

        screen.type(Key.ENTER);

    }
}
