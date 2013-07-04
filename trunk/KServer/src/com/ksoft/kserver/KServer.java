package com.ksoft.kserver;

import java.io.File;
import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class KServer {

	private static KServer me = null;
	private Server server;

	private void KsoftServer() {

	}

	public static KServer getInstance() {

		if (null == me) {
			me = new KServer();
		}

		return me;

	}

	public void start() throws Exception {
		
		server = new Server(ServerConstants.PORT);

		URL warUrl = new File(ServerConstants.WAR_URL).toURI().toURL();
		String warUrlString = warUrl.toExternalForm();
		WebAppContext context = new WebAppContext(warUrlString, ServerConstants.CONTEXT);
		server.setHandler(context);
		//server.start();
		System.out.println("Server Started");
	}

	public void stop() throws Exception {
		server.stop();
		server.join();
		System.out.println("Server Stopped");
	}

	public boolean isStarted() {
		return server.isStarted();
	}

	public boolean isStopped() {
		return server.isStopped();
	}

}
