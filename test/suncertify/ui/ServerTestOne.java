/* 
* @(#)ServerTestOne.java    1.0    28 Feb 2010 
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

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import junit.framework.TestCase;
import suncertify.common.SearchCriteriaDTO;
import suncertify.server.ServiceFactory;
import suncertify.service.ReservationService;

/**
 * @author Chris Hatton
 *
 */
public class ServerTestOne extends TestCase {

	/**
	 * @param name
	 */
	public ServerTestOne(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testServer() throws Exception {
		System.out.println("testing the server");
	    String s =java.net.InetAddress.getLocalHost().getHostAddress();
	    System.out.println(s);
//		String hostname = "192.168.2.3";
//		String port = "1099";
//		String url = "rmi://" + hostname + ":" + port;
	    Registry registry = LocateRegistry.getRegistry("127.0.0.1", 51000);
	    ServiceFactory<ReservationService> stub =
	    	(ServiceFactory<ReservationService>) registry.lookup("ReservationService");
	    ReservationService rs = stub.getService();
	    List i = rs.searchForRoom(new SearchCriteriaDTO());

	    System.out.println(i);
	    System.out.println("list size = " + i.size());
		System.out.println("tested");
		
	}

}
