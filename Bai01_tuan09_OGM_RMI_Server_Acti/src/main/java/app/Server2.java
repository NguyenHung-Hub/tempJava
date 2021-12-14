package app;

import java.net.MalformedURLException;
import java.rmi.MarshalledObject;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.util.Properties;

import entity.Employee;
import facade.imp.ProjectImp;

public class Server2 {
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Properties props = new Properties();
			props.put("java.security.policy", "myrmi/myrmi.policy");

			ActivationGroupDesc exampleGroup = new ActivationGroupDesc(props, null);

			ActivationGroupID agi;
			agi = ActivationGroup.getSystem().registerGroup(exampleGroup);
			

			ActivationDesc desc = new ActivationDesc(agi, ProjectImp.class.getName() , null, null);
			ActivationDesc desc2 = new ActivationDesc(agi, Employee.class.getName() , null, null);

			Remote remote = Activatable.register(desc);
			Remote remote2 = Activatable.register(desc2);
			
			Naming.rebind("project", remote);
			Naming.rebind("project", remote);

			System.exit(0);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ActivationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		

	}
}
