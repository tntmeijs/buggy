package buggy;

import buggy.exception.UserInterfaceException;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;

@Getter
public final class UserInterfaceContext {

    private final TerminalScreen screen;
    private final MultiWindowTextGUI handle;

    /**
     * Initialize all fields needed to create a new user interface
     */
    public UserInterfaceContext() {
        try {
            screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal());
        } catch (IOException e) {
            throw new UserInterfaceException("Unable to create a new terminal: " + e.getMessage());
        }

        handle = new MultiWindowTextGUI(screen);
    }

    /**
     * Wraps {@link Screen#startScreen()}
     */
    public void start() {
        try {
            screen.startScreen();
        } catch (IOException e) {
            throw new UserInterfaceException("Unable to start screen: " + e.getMessage());
        }
    }

    /**
     * Wraps {@link Screen#stopScreen()}
     */
    public void stop() {
        try {
            screen.stopScreen();
        } catch (IOException e) {
            throw new UserInterfaceException("Unable to stop screen: " + e.getMessage());
        }
    }

    /**
     * Add a window to the GUI system
     *
     * @param window      Window to add to the GUI system
     * @param waitOnClose Set this to true to wait for the window to close after adding the window to the GUI stack
     */
    public void addWindow(@NonNull final Window window, final boolean waitOnClose) {
        if (waitOnClose) {
            handle.addWindowAndWait(window);
        } else {
            handle.addWindow(window);
        }
    }

}
