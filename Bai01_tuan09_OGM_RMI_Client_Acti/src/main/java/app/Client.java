package app;

import java.rmi.Naming;

import entity.Project;
import facade.ProjectFacade;

public class Client {
	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.setProperty("java.security.policy", "myrmi/myrmi.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			ProjectFacade projectFacade = (ProjectFacade) Naming.lookup("project");

			Project p = projectFacade.getProject("p1");
			System.out.println("client\n-------------");
			System.out.println(p);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
