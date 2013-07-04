package com.ksoft.splashscreen;

import com.ksoft.launcher.ApplicationLauncher;

public final class SplashScreenCloser implements Runnable {

	/**
	 * Removes the splash screen.
	 * 
	 * Invoke this <tt>Runnable</tt> using <tt>EventQueue.invokeLater</tt>,
	 * in order to remove the splash screen in a thread-safe manner.
	 */
	public void run() {
		ApplicationLauncher.getSplashScreen().dispose();
	}
}
