package buggy.menu;

import buggy.UserInterfaceContext;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import lombok.NonNull;

import java.util.List;

public final class HelpMenu extends Menu {

    /**
     * Create a new help menu
     *
     * @param userInterfaceContext User interface context used to render the application
     */
    public HelpMenu(@NonNull final UserInterfaceContext userInterfaceContext) {
        super("Help");
        add(aboutMenuItem(userInterfaceContext.getHandle()));
    }

    /**
     * Create a new menu item that displays a window with information about the project
     *
     * @param handle Handle to the {@link MultiWindowTextGUI} that displays the GUI
     * @return Menu item instance
     */
    private MenuItem aboutMenuItem(@NonNull final MultiWindowTextGUI handle) {
        return new MenuItem("About...", () -> {
            final var window = new BasicWindow("Buggy - an educational JVM debugger");
            window.setHints(List.of(Window.Hint.CENTERED));

            final var root = new Panel();
            root.addComponent(new EmptySpace());
            root.addComponent(new Label("Author:\t\t\tTahar Meijs"));
            root.addComponent(new Label("License:\t\tMIT"));
            root.addComponent(new Label("Source code:\thttps://github.com/tntmeijs/buggy"));
            root.addComponent(new EmptySpace());
            root.addComponent(new Button("Close", window::close));

            window.setComponent(root);

            handle.addWindowAndWait(window);
        });
    }

}
