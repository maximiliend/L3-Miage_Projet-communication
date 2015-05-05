package core.pcore;

import java.rmi.RemoteException;

import onodes.RMI.Server.*;
import onodes.RMI.Client.*;
import pnodes.monAppClient.ControllerMonAppClient;
import pnodes.monAppServer.ControllerMonAppServer;


/**
*
* DO NOT EDIT THIS FILE IT BELONG TO THE FRAMEWORK
* @author groupe1
*
*/
public final class CoreApp {
	
	/**
	 * Main code of the application
	 * DevOps Write some code here
	 * @param args
	 */
	public static void mainApp(String[] args) {
		try {
			ControllerMonAppServer testserver = new ControllerMonAppServer();

			Thread.sleep(100);

			ControllerMonAppClient testclient = new ControllerMonAppClient();
			testclient.startTEST();
			
			testserver.startTESTAll();
			
			System.out.println((String) testserver.startTESTOne(0));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//ControllerRMIServer<ModelRMIServer<ModelRMIServerRemote<ModelRMIClientRemote>, ModelRMIClientRemote>, ViewRMIServer> test = new ControllerRMIServer<ModelRMIServer, ViewRMIServer>(new ModelRMIServer<ModelRMIServerRemote, ModelRMIClientRemote>, new ViewRMIServer())
	}

	/*
	 * Main functions of the application
	 * DevOps Write some functions here
	 */
	
}
