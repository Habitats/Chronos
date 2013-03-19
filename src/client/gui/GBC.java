package client.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Helper class for gridBagLayout
 * 
 * @author anon
 * 
 */
public class GBC extends GridBagConstraints {

	public enum Align {
		LEFT, RIGHT, MID, TIGHT, BOTTOM, LEFT_BOTTOM, MID_BOTTOM, RIGHT_BOTTOM, ALONE, FULL_WIDTH, FULL_WIDTH_BOTTOM, NONE, TOP_AND_BOTTOM, NOT_RIGHT, NOT_LEFT, NOT_BOTTOM, NOT_TOP;
	}

	public GBC(int gridx, int gridy, Align align) {
		this.gridx = gridx;
		this.gridy = gridy;

		// for even borders
		int b = 5;
		if (align != null) {
			if (align == Align.RIGHT)
				setInsets(b, b, 0, b);
			else if (align == Align.MID || align == Align.LEFT)
				setInsets(b, b, 0, 0);
			else if (align == Align.FULL_WIDTH)
				setInsets(b, b, 0, b);
			else if (align == Align.MID_BOTTOM || align == Align.LEFT_BOTTOM)
				setInsets(b, b, b, 0);
			else if (align == Align.RIGHT_BOTTOM)
				setInsets(b, b, b, b);
			else if (align == Align.FULL_WIDTH_BOTTOM)
				setInsets(b, b, b, b);
			else if (align == Align.NONE)
				setInsets(0, 0, 0, 0);
			else if (align == Align.TOP_AND_BOTTOM)
				setInsets(b, 0, b, 0);
			else if (align == Align.NOT_RIGHT)
				setInsets(b, b, b, 0);
			else if (align == Align.NOT_LEFT)
				setInsets(b, 0, b, b);
			else if (align == Align.NOT_BOTTOM)
				setInsets(b, b, 0, b);
			else if (align == Align.NOT_TOP)
				setInsets(0, b, b, b);
		}
		setFill(BOTH);
	}

	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
		setFill(BOTH);
		int b = 5;
		setInsets(b, b, b, b);
	}

	// how many grids shall it span
	public GBC setSpan(int gridwidth, int gridheight) {
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
		return this;
	}

	public GBC setAnchor(int value) {
		this.anchor = value;
		return this;
	}

	/*
	 * Fill area if the component doesn't match the available space given
	 * HORIZONTAL = fill horizontally VERTICAL = fill vertically BOTH = guess
	 */
	public GBC setFill(int fill) { // NO_UCD
		this.fill = fill;
		return this;
	}

	// this one's complicated, check docs lol
	public GBC setWeight(double weightx, double weighty) { // NO_UCD
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	// external padding
	public GBC setInsets(int top, int left, int bottom, int right) { // NO_UCD
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}

	// internal padding
	public GBC setIpad(int ipadx, int ipady) { // NO_UCD
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
