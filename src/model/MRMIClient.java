package model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessControlException;

/**
*
* DO NOT EDIT THIS FILE IT BELONG TO THE FRAMEWORK
* @author groupe1
*
*/
public class MRMIClient extends UnicastRemoteObject implements MRMI, MRMIClientRemote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 70109437196450691L;

	/**
	 * 
	 * @throws RemoteException
	 */
	public MRMIClient() throws RemoteException {
		super();
		//ou super(0) ou UnicastRemoteObject.exportObject(this, 0); pas nécessaire car on étend déja UnicastRemoteObject 
		
		try {
			System.out.println("=================================================================");
			System.out.println("ModelRMIClient UID="+serialVersionUID+" : Lancement du client");
			
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}

			Remote r = Naming.lookup("rmi://127.0.0.1/testRMIserver");

			if (r instanceof MRMIServerRemote) {
				String s = ((MRMIServerRemote) r).getInfoServer();
				System.out.println("ModelRMIClient UID="+serialVersionUID+" : chaine renvoyee = " + s);
				
				MRMIServerRemote serv = (MRMIServerRemote) r;
				
				this.regOnServer(serv);
			}
			else { System.out.println("ModelRMIClient UID="+serialVersionUID+" : Instance incorrecte"); }
			System.out.println("ModelRMIClient UID="+serialVersionUID+" : Fin du client");
			System.out.println("=================================================================");
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (AccessControlException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getInfoClient() throws RemoteException {
		System.out.println("ModelRMIClient UID="+serialVersionUID+" Server call me !");
		return "Retour ModelRMIClient UID="+serialVersionUID;
	}

	/**
	 * 
	 * @param remoteServer
	 */
	public void regOnServer(MRMIServerRemote remoteServer) {
		try {
			remoteServer.regClient(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}