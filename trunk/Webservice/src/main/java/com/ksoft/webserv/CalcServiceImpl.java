package com.ksoft.webserv;

public class CalcServiceImpl implements CalcService{

	@Override
	public int add(int x, int y) {
		int sum = 0;
		
		sum = x + y;
		
		return sum;
	}

}
