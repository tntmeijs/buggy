package buggy;

import lombok.Setter;

import java.io.File;
import java.util.Optional;
import java.util.function.Consumer;

public final class ApplicationContext {

    private File selectedProjectDirectory;

    @Setter
    private Consumer<File> onUpdateSelectedProjectDirectory;

    /**
     * Get the selected project directory
     *
     * @return Selected project directory, or empty if no project directory has been selected
     */
    public Optional<File> getSelectedProjectDirectory() {
        return Optional.ofNullable(selectedProjectDirectory);
    }

    /**
     * Set the selected project directory and invoke {@link ApplicationContext#onUpdateSelectedProjectDirectory}
     *
     * @param selectedProjectDirectory Selected project directory
     */
    public void setSelectedProjectDirectory(final File selectedProjectDirectory) {
        this.selectedProjectDirectory = selectedProjectDirectory;
        onUpdateSelectedProjectDirectory.accept(selectedProjectDirectory);
    }

}
