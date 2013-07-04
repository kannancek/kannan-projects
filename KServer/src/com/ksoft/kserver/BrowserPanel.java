package com.ksoft.kserver;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;


public class BrowserPanel extends JPanel {

	
	public BrowserPanel(String url) {
		
		super(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser();

		webBrowser.setButtonBarVisible(false);
		webBrowser.setLocationBarVisible(false);
		webBrowser.setMenuBarVisible(false);
		webBrowser.setStatusBarVisible(false);
		
		webBrowser.navigate(url);

		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);

		addCallbackListener(webBrowser);
	}

	/**
	 * establish web browser callback
	 * 
	 * @param webBrowser
	 */
	private void addCallbackListener(final JWebBrowser webBrowser) {
		webBrowser.addWebBrowserListener(new WebBrowserAdapter() {

			@Override
			public void commandReceived(WebBrowserCommandEvent e) {
//				6: just show that it works
//				JOptionPane.showConfirmDialog(Frame.getFrames()[0], e.getCommand());

//				7: returning the choice back to the app
				JFileChooser openFile = new JFileChooser();
				int ret = openFile.showOpenDialog(Frame.getFrames()[0]);
				if(ret == JFileChooser.APPROVE_OPTION) {
					String file = openFile.getSelectedFile().toString();
					file = file.replace("\\", "\\\\");
					webBrowser.executeJavascript("triggerOpenFile('"+file+"')");
				}
			}
			
			
		});
	}

	

}
