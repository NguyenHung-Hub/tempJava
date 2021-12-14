package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import facade.ProjectFacade;
import facade.imp.ProjectImp;

public class Server {
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "myrmi/myrmi.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {

			LocateRegistry.createRegistry(1099);
			ProjectFacade projectFacade = new ProjectImp();
			Naming.bind("rmi://localhost:1099/project", projectFacade);
			System.out.println("server start ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
