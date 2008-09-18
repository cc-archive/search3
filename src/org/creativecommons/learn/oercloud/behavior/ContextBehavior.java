package org.creativecommons.learn.oercloud.behavior;

import javax.xml.namespace.QName;

import org.creativecommons.learn.oercloud.TripleStore;
import org.openrdf.elmo.ElmoManager;
import org.openrdf.elmo.ElmoModule;
import org.openrdf.repository.RepositoryException;

public class ContextBehavior implements IContextBehavior {

	private QName qname;
	
	public QName getQname() {
		return qname;
	}

	public void setQName(QName base_qname) {
		this.qname = base_qname;
	}

	@Override
	public ElmoManager getContextManager() {
		TripleStore store;
		try {
			store = new TripleStore();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		ElmoModule module = new ElmoModule();
		module.setGraph(this.getQname());
		
		store.addModuleRegistrations(module);
		
		return store.getManagerFactory(module).createElmoManager();
		
	}

}
