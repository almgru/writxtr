package writxtr.view;

public interface View {

    /**
     * Initialize the view
     */
    void init();

    /**
     * Set observer to be notified when the user selects a font in the font
     * selector.
     */
	void setFontSelectedObserver(FontSelectedObserver observer);

    MenuView getMenu();
    EditorView getEditor();
    ToolbarView getToolbar();
    FontSelectorView getFontSelector();

    /**
     * Show a message to the user.
     */

    void showMessage(String text);

    /**
     * Open the font selector to let the user choose to change font.
     */
    void openFontSelector();

    /**
     * Change the title of the main view.
     */
    void changeTitle(String fileName);
}

