import java.rmi.RemoteException;

import com.ksoft.webserv.WebserviceStub;
import com.ksoft.webserv.WebserviceStub.Add;
import com.ksoft.webserv.WebserviceStub.AddResponse;


public class Client {
	
	
	public static void main(String[] args) throws RemoteException {
		
		WebserviceStub stub = new WebserviceStub("http://localhost/axis2/services/Webservice?wsdl");
		Add a = new Add();
		a.setX(2);
		a.setY(5);
		
	
		AddResponse res = stub.add(a);
		System.out.println("Sum = " + res.get_return());
	}
	

}
