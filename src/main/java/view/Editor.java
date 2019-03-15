package writxtr.view;

public interface Editor {
    void init();
    void clearText();
    void resetUnsavedChangesIndicator();
    boolean hasUnsavedChanges();
}
