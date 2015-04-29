package controller;

import java.rmi.RemoteException;

import model.MRMIClient;
import model.MRMIServer;
import view.VRMIClient;
import view.VRMIServer;

/*
 * Besoin de conserver la même architecture de package dans Le serveur comme le client
 * afin que les classes puissent être trouvé lors du Naming.lookup()
 */

/**
*
* DO NOT EDIT THIS FILE IT BELONG TO THE FRAMEWORK
* @author groupe1
*
*/
public class CRMIClient extends CRMI<MRMIClient, VRMIClient> {
	public CRMIClient() throws RemoteException {
		super();
		this.model = new MRMIClient();
		//this.view = new VRMIServer();
	}
}