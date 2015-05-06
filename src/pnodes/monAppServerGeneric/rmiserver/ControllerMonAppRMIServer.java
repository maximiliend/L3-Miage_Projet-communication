package pnodes.monAppServerGeneric.rmiserver;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.Enumeration;

import onodes.RMI.ServerGeneric.ControllerRMIServerGeneric;

/**
 *
 * DO NOT EDIT THIS FILE IT BELONG TO THE FRAMEWORK
 * 
 * @author groupe1
 *
 */
public class ControllerMonAppRMIServer extends
		ControllerRMIServerGeneric<ModelMonAppRMIServer, ViewMonAppRMIServer> {

	/**
	 * @throws RemoteException
	 */
	public ControllerMonAppRMIServer() throws RemoteException {
		this.model = new ModelMonAppRMIServer(getFirstNonLocalAdress());
		this.view = new ViewMonAppRMIServer();
	}

	/**
	 * @return
	 */
	private String getFirstNonLocalAdress() {
		String ret = null;
	    Enumeration en;
		try {
			en = NetworkInterface.getNetworkInterfaces();
		    while (en.hasMoreElements()) {
		        NetworkInterface i = (NetworkInterface) en.nextElement();
		        for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
		            InetAddress addr = (InetAddress) en2.nextElement();
		            if (!addr.isLoopbackAddress()) {
		                if (addr instanceof Inet4Address) {
		                    ret=addr.getHostAddress();
		                }
		            }
		        }
		    }
		} catch (SocketException e) {
			e.getMessage();
		}
	    return ret;
	}
	
	/**
	 * @param model
	 * @param view
	 * @throws RemoteException
	 */
	public ControllerMonAppRMIServer(ModelMonAppRMIServer model,
			ViewMonAppRMIServer view) throws RemoteException {
		super(model, view);
	}
}
