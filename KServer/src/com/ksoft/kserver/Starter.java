package com.ksoft.kserver;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;



public class Starter {
	
	public static void main(String[] args) throws Exception {
		
		
		//UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BrowserPanel objBrowserPanel = null ;
				String url= null;
				try {
					init();
					if(null!=ServerConstants.START_PAGE && !"".equals(ServerConstants.START_PAGE)){
						url = "http://localhost:"+ServerConstants.PORT+ServerConstants.CONTEXT+"/"+ServerConstants.START_PAGE;
					}else{
						url = "http://localhost:"+ServerConstants.PORT+ServerConstants.CONTEXT;
					}
										
					//KServer.getInstance().start();
					
					//objBrowserPanel = new BrowserPanel(url);
					objBrowserPanel = new BrowserPanel("http://localhost/kbill-web/");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame frame = new JFrame(ServerConstants.APP_NAME);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(objBrowserPanel, BorderLayout.CENTER);
				//frame.setSize(800,600);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				
				frame.addWindowListener(new WindowAdapter(){
					  public void windowClosing(WindowEvent we){
						  try {
							KServer.getInstance().stop();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  }
					  });
			}
		});
		NativeInterface.runEventPump();
		
		
	}
	
	public static void init() throws JDOMException, IOException{
		
		Element xmlConfigElement = null;
		File file = new File("config/server-config.xml");
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		document = builder.build(file);
		
		xmlConfigElement = document.getRootElement();
		int port = Integer.parseInt(xmlConfigElement.getChild("PORT").getValue());
		ServerConstants.PORT=port;
		ServerConstants.APP_NAME=xmlConfigElement.getChild("APP_NAME").getValue();
		ServerConstants.CONTEXT=xmlConfigElement.getChild("CONTEXT").getValue();
		ServerConstants.WAR_URL=xmlConfigElement.getChild("WAR_URL").getValue();
		ServerConstants.START_PAGE=xmlConfigElement.getChild("START_PAGE").getValue();
		
		
	}

}
