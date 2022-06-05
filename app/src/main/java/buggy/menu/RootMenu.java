package buggy.menu;

import buggy.ApplicationContext;
import buggy.UserInterfaceContext;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import lombok.NonNull;

public final class RootMenu extends MenuBar {

    public RootMenu(@NonNull final UserInterfaceContext userInterfaceContext, @NonNull final ApplicationContext applicationContext) {
        add(new FileMenu(userInterfaceContext, applicationContext));
        add(new HelpMenu(userInterfaceContext));
    }

}
