package com.ksoft.launcher;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import com.ksoft.splashscreen.SplashScreen;
import com.ksoft.splashscreen.SplashScreenCloser;
import com.ksoft.cpanel.MainForm;
import com.ksoft.kserver.Starter;

/**
 * Launch the application.
 * 
 * <P>
 * Perform tasks in this order :
 * <ul>
 * <li>promptly show a splash screen upon startup
 * <li>show the main screen
 * <li>remove the splash screen once the main screen is shown
 * </ul>
 * 
 * These tasks are performed in a thread-safe manner.
 */
public final class ApplicationLauncher {


	private static SplashScreen splashScreen;
	private static String SPLASH_IMAGE = "./resources/images/splashscreen.jpg";
	
	public static void main(String[] aArgs) throws Exception {

		/*
		 * Implementation Note:
		 * 
		 * Note that the launch thread of any GUI application is in effect an
		 * initial worker thread - it is not the event dispatch thread, where
		 * the bulk of processing takes place. Thus, once the launch thread
		 * realizes a window, then the launch thread should almost always
		 * manipulate such a window through EventQueue.invokeLater. (This is
		 * done for closing the splash screen, for example.)
		 */

		splashScreen = new SplashScreen(SPLASH_IMAGE);
		splashScreen.splash();		
		
		startApplication();
		
		EventQueue.invokeLater(new SplashScreenCloser());
	}

	/**
	 * Display the main window of the application to the user.
	 * @throws AWTException 
	 * @throws Exception 
	 */
	private static void startApplication() {
		
	}

	public static SplashScreen getSplashScreen() {
		return splashScreen;
	}

}