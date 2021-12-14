package facade.imp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import business.ProjectBusiness;
import entity.Project;
import facade.ProjectFacade;

public class ProjectImp extends UnicastRemoteObject implements ProjectFacade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 528073823339684241L;

	private ProjectBusiness projectBusiness;

	public ProjectImp() throws RemoteException {
		projectBusiness = new ProjectBusiness();
	}

	public boolean add(Project project) throws RemoteException {
		// TODO Auto-generated method stub
		return projectBusiness.addProject(project);
	}

	public Project getProject(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return projectBusiness.getProject(name);
	}

//	@Override
//	public boolean add(Project project) throws RemoteException {
//		// TODO Auto-generated method stub
//		return projectBusiness.addProject(project);
//	}
//
//	@Override
//	public Project getProject(String name) throws RemoteException {
//		// TODO Auto-generated method stub
//		return projectBusiness.getProject(name);
//	}

}
