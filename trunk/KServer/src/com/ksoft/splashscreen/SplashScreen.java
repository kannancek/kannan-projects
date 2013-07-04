package com.ksoft.splashscreen;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public final class SplashScreen extends Frame {

	private final String imageId;
	private MediaTracker mediaTracker;
	private Image image;
	private static final ImageObserver NO_OBSERVER = null;
	private static final int IMAGE_ID = 0;

	public SplashScreen(String aImageId) {
		imageId = aImageId;
	}

	public void splash() {

		initImageAndTracker();
		setSize(image.getWidth(NO_OBSERVER), image.getHeight(NO_OBSERVER));
		center();

		mediaTracker.addImage(image, IMAGE_ID);
		try {
			mediaTracker.waitForID(IMAGE_ID);
		} catch (InterruptedException ex) {
			System.out.println("Cannot track image load.");
		}

		SplashWindow splashWindow = new SplashWindow(this, image);
	}

	private void initImageAndTracker() {
		mediaTracker = new MediaTracker(this);
		image = Toolkit.getDefaultToolkit().getImage(imageId);
	}

	private void center() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frame = getBounds();
		setLocation((screen.width - frame.width) / 2,
				(screen.height - frame.height) / 2);
	}

}
