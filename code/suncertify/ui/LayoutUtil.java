/*
 * @(#)LayoutUtil.java 1.0 2 Mar 2010
 * 
 * Candidate: Christopher Hatton 
 * Prometric ID: SR6265633 
 * Candidate ID: 657115
 * 
 * Sun Certified Developer for Java 2 Platform, Standard Edition Programming
 * Assignment (GB-CX-310-252A)
 * 
 * This class is part of the Programming Assignment of the Sun Certified
 * Developer for Java 2 Platform, Standard Edition certification program, must
 * not be used out of this context and must be used exclusively by Oracle and
 * Sun Microsystems, Inc.
 */



package suncertify.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import java.awt.Insets;

import javax.swing.AbstractButton;

/**
 * Utility class to make it a little easier when working with GridBagLayout.
 * 
 * @author Chris Hatton
 * 
 */
class LayoutUtil {
	
	private LayoutUtil() {
		super();
	}

	/**
	 * Creates and returns a new <code>Insets</code> object with the specified
	 * top, left, bottom, and right insets.
	 * 
	 * @param top
	 *            the inset from the top.
	 * @param left
	 *            the inset from the left.
	 * @param bottom
	 *            the inset from the bottom.
	 * @param right
	 *            the inset from the right.
	 */
	static Insets getInsets(int top, int left, int bottom, int right) {
		Insets insets = new Insets(top, left, bottom, right);

		return insets;
	}

	/**
	 * Returns a Builder for easier construction of
	 * <code>GridBagConstraints</code>.
	 * 
	 * @return <code>GridBagConstraints</code> Builder
	 */
	static LayoutUtil.GridBagConstraintsBuilder getBuilder() {
		return new LayoutUtil.GridBagConstraintsBuilder();
	}

	/*
	 * Builder class for easier construction of GridBagConstraints. The default
	 * values set are the same default values as used by GridBagConstraints.
	 */
	static class GridBagConstraintsBuilder {

		private int gridx;

		private int gridy;

		private int gridwidth;

		private int gridheight;

		private int fill;

		private int ipadx;

		private int ipady;

		private Insets insets;

		private int anchor;

		private double weightx;

		private double weighty;

		{
			gridx = GridBagConstraints.RELATIVE;
			gridy = GridBagConstraints.RELATIVE;
			gridwidth = 1;
			gridheight = 1;
			fill = GridBagConstraints.NONE;
			ipadx = 0;
			ipady = 0;
			insets = new Insets(0, 0, 0, 0);
			anchor = GridBagConstraints.CENTER;
			weightx = 0;
			weighty = 0;
		}

		GridBagConstraintsBuilder gridX(int gridx) {
			this.gridx = gridx;

			return this;
		}

		GridBagConstraintsBuilder gridY(int gridy) {
			this.gridy = gridy;

			return this;
		}

		GridBagConstraintsBuilder gridwidth(int gridWidth) {
			this.gridwidth = gridWidth;

			return this;
		}

		GridBagConstraintsBuilder gridheight(int gridHeight) {
			this.gridheight = gridHeight;

			return this;
		}

		GridBagConstraintsBuilder fill(int fill) {
			this.fill = fill;

			return this;
		}

		GridBagConstraintsBuilder ipadx(int ipadx) {
			this.ipadx = ipadx;

			return this;
		}

		GridBagConstraintsBuilder ipady(int ipady) {
			this.ipady = ipady;

			return this;
		}

		GridBagConstraintsBuilder insets(Insets insets) {
			this.insets = insets;

			return this;
		}

		GridBagConstraintsBuilder anchor(int anchor) {
			this.anchor = anchor;

			return this;
		}

		GridBagConstraintsBuilder weightx(double weightx) {
			this.weightx = weightx;

			return this;
		}

		GridBagConstraintsBuilder weighty(double weighty) {
			this.weighty = weighty;

			return this;
		}

		GridBagConstraints build() {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = gridwidth;
			c.gridheight = gridheight;
			c.fill = fill;
			c.ipadx = ipadx;
			c.ipady = ipady;
			c.insets = insets;
			c.anchor = anchor;
			c.weightx = weightx;
			c.weighty = weighty;

			return c;
		}
	}

	static void equalizeButtonSizes(AbstractButton... buttons) {
		int maxWidth = Integer.MIN_VALUE;
		int maxHeight = Integer.MIN_VALUE;

		for (AbstractButton button : buttons) {
			if (button.getPreferredSize().width > maxWidth) {
				maxWidth = button.getPreferredSize().width;
			}

			if (button.getPreferredSize().height > maxHeight) {
				maxHeight = button.getPreferredSize().height;
			}
		}

		Dimension maxPreferedDimension = new Dimension(maxWidth, maxHeight);

		for (AbstractButton button : buttons) {
			button.setPreferredSize(maxPreferedDimension);
		}
	}
}
