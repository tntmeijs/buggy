package buggy.menu;

import buggy.ApplicationContext;
import buggy.UserInterfaceContext;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.dialogs.DirectoryDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import lombok.NonNull;

import java.io.File;

public class FileMenu extends Menu {

    /**
     * Create a new file menu
     *
     * @param userInterfaceContext User interface context used to render the application
     * @param applicationContext   Application context used to store application state and settings
     */
    public FileMenu(@NonNull final UserInterfaceContext userInterfaceContext, @NonNull final ApplicationContext applicationContext) {
        super("File");

        add(openMenuItem(userInterfaceContext.getHandle(), applicationContext));
        add(exitMenuItem(userInterfaceContext.getHandle()));
    }

    /**
     * Display a new menu item that allows the user to select a project root directory
     *
     * @param handle             Handle to the {@link MultiWindowTextGUI} that displays the GUI
     * @param applicationContext Application context used to store application state and settings
     * @return Menu item instance
     */
    private MenuItem openMenuItem(@NonNull final MultiWindowTextGUI handle, @NonNull final ApplicationContext applicationContext) {
        return new MenuItem("Open...", () -> {
            File selectedProjectDirectory = null;

            // Allow the user to select a directory and confirm it using a dialog prompt
            do {
                selectedProjectDirectory = new DirectoryDialogBuilder()
                        .setTitle("Open project folder")
                        .setActionLabel("Open project")
                        .setSelectedDirectory(selectedProjectDirectory)
                        .build()
                        .showDialog(handle);
            } while (!MessageDialogButton.Yes.equals(MessageDialog.showMessageDialog(
                    handle,
                    null,
                    "Select this directory?",
                    MessageDialogButton.No,
                    MessageDialogButton.Yes)));

            applicationContext.setSelectedProjectDirectory(selectedProjectDirectory);
        });
    }

    /**
     * Create a new menu item that allows the user to exit the application
     *
     * @param handle Handle to the {@link MultiWindowTextGUI} that displays the GUI
     * @return Menu item instance
     */
    private MenuItem exitMenuItem(@NonNull final MultiWindowTextGUI handle) {
        return new MenuItem("Exit", () -> {
            final var choice = MessageDialog.showMessageDialog(
                    handle,
                    null,
                    "Do you really want to quit?",
                    MessageDialogButton.No,
                    MessageDialogButton.Yes);

            if (MessageDialogButton.Yes.equals(choice)) {
                System.exit(0);
            }
        });
    }

}
