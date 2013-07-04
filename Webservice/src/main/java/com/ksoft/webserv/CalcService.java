package com.ksoft.webserv;

import java.rmi.Remote;

public interface CalcService extends Remote{
	
	public int add(int x, int y);

}
