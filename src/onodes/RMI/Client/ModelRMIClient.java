package onodes.RMI.Client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessControlException;

import onodes.Controller;
import onodes.RMI.ModelRMI;
import onodes.RMI.Server.ModelRMIServerRemote;

/**
 *
 * DO NOT EDIT THIS FILE IT BELONG TO THE FRAMEWORK
 * 
 * @author groupe1
 *
 */
public class ModelRMIClient<C extends Controller> extends
		UnicastRemoteObject implements ModelRMI, ModelRMIClientRemote {

	protected ModelRMIServerRemote modelRMIServerR;
	private C controllerAppClient;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 70109437196450691L;

	/**
	 * 
	 * @throws RemoteException
	 */
	public ModelRMIClient(C controllerAppClient) throws RemoteException {
		super();
		this.controllerAppClient=controllerAppClient;
		launchClient("127.0.0.1");
	}

	/**
	 * 
	 * @param ip
	 * @throws RemoteException
	 */
	public ModelRMIClient(C controllerAppClient, String ip) throws RemoteException {
		super();
		// TODO Match regexp of ip addr
		this.controllerAppClient=controllerAppClient;
		launchClient(ip);
	}

	/**
	 * 
	 * @param ip
	 */
	private void launchClient(String ip) {
		try {
			System.out
					.println("=================================================================");
			System.out.println("ModelRMIClient UID=" + serialVersionUID
					+ " : Lancement du client");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
System.out.println("launch client ip : "+ip);
			Remote r = Naming.lookup("rmi://" + ip + "/testRMIserver");

			if (r instanceof ModelRMIServerRemote) {
				String s = ((ModelRMIServerRemote) r).getInfoServer();
				System.out.println("ModelRMIClient UID=" + serialVersionUID
						+ " : chaine renvoyee = " + s);

				this.modelRMIServerR = (ModelRMIServerRemote) r;
		
			} else {
				System.out.println("ModelRMIClient UID=" + serialVersionUID
						+ " : Instance incorrecte");
			}

			this.modelRMIServerR.registerClient(this);
			
			System.out.println("ModelRMIClient UID=" + serialVersionUID
					+ " : Client Lancé");
			System.out
					.println("=================================================================");
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
		return "Retour ModelRMIClient UID=" + serialVersionUID;
	}
	
	public Object invokeMethodOnControllerAppServer(String methodName, Class[] args, Object[] oArgs) {
		Object ret=null;
		try {
			ret=modelRMIServerR.invokeMethodOnControllerAppServer(methodName, args, oArgs);
		} catch (RemoteException e) {
			System.err.println("Remote Exception when RMI Client call method "+methodName+" on modelRMIServerRemote");
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Object invokeMethodOnControllerAppClient(String methodName,
			Class[] cArgs, Object[] oArgs) throws RemoteException {

		Object ret = null;

		Method method = null;

		try {
			method = controllerAppClient.getClass().getMethod(methodName, cArgs);

			if (method ==null) {
				System.out.println("erreur method");
			}
			if (controllerAppClient==null) {
				System.out.println("erreur controller");
			}
			
			if (method == null) {
				System.err
				.println("Attention la methode "
						+ methodName
						+ " avec les parametres "
						+ oArgs
						+ " n'existe pas sur le Controlleur de l'application Client");
			} else {
				ret = method.invoke(controllerAppClient, oArgs);	
			}
		} catch (NoSuchMethodException e) {
			e.getMessage();
		} catch (SecurityException e) {
			e.getMessage();
		} catch (IllegalAccessException e) {
			e.getMessage();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		} catch (InvocationTargetException e) {
			e.getMessage();
		}

		return ret;
	}
}