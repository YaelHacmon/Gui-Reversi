package reversiapp;


import FXMLReversi.RevesiGameController;

public class GUIAdapter {
	//The Singleton instance
	private static GUIAdapter instance = null;

	private RevesiGameController controller_;
	private boolean isGUI = false;

	private GUIAdapter() {}

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
		isGUI = gui;
	}

	public void setController(RevesiGameController controller) {
		controller_ = controller;
	}


	public void draw(Board b) {
		if (isGUI && controller_ != null) {
			controller_.redraw(b);
		}
	}

	public void changeCurrPlayer(Player p) {
		if (isGUI && controller_ != null) {
			controller_.changeCurrentPlayer(p.toString());
		}
	}

	public void changeWhitePlayerScore(int score) {
		if (isGUI && controller_ != null) {
			controller_.changeWhitePlayerScore(Integer.toString(score));
		}
	}

	public void changeBlackPlayerScore(int score) {
		if (isGUI && controller_ != null) {
			controller_.changeBlackPlayerScore(Integer.toString(score));
		}
	}


}
