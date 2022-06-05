package buggy;

import buggy.menu.RootMenu;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import java.util.List;

public class App {

    public static void main(String[] args) {

        final var applicationContext = new ApplicationContext();
        final var userInterfaceContext = new UserInterfaceContext();
        userInterfaceContext.start();

        final var label = new Label("No project selected");
        applicationContext.setOnUpdateSelectedProjectDirectory(projectDirectory -> label.setText(projectDirectory.getAbsolutePath()));

        final var root = new Panel();
        root.addComponent(new RootMenu(userInterfaceContext, applicationContext));
        root.addComponent(label);

        final var window = new BasicWindow("Window");
        window.setComponent(root);
        window.setHints(List.of(Window.Hint.FULL_SCREEN, Window.Hint.NO_DECORATIONS));

        userInterfaceContext.addWindow(window, true);
        userInterfaceContext.stop();
    }

}
