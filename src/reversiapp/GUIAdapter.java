package reversiapp;

import FXMLReversi.RevesiGameController;

public class GUIAdapter {
    // The Singleton instance
    private static GUIAdapter instance = null;

    private RevesiGameController controller_;

    private boolean isGUI = false;

    private GUIAdapter() {
    }

    public static GUIAdapter getInstance() {
        if (instance == null) {
            instance = new GUIAdapter();
        }
        return instance;
    }

    public static GUIAdapter getInstance(boolean gui) {
        if (instance == null) {
            instance = new GUIAdapter();
        }
        instance.isGUI = gui;
        return instance;
    }

    public void setGUI(boolean gui) {
        this.isGUI = gui;
    }

    public void setController(RevesiGameController controller) {
        this.controller_ = controller;
    }

    public void draw(Board b) {
        if (this.isGUI && this.controller_ != null) {
            this.controller_.redraw(b);
        }
    }

    public void changeCurrPlayer(Player p) {
        if (this.isGUI && this.controller_ != null) {
            this.controller_.changeCurrentPlayer(p.toString());
        }
    }

    public void changeWhitePlayerScore(int score) {
        if (this.isGUI && this.controller_ != null) {
            this.controller_.changeWhitePlayerScore(Integer.toString(score));
        }
    }

    public void changeBlackPlayerScore(int score) {
        if (this.isGUI && this.controller_ != null) {
            this.controller_.changeBlackPlayerScore(Integer.toString(score));
        }
    }

}
