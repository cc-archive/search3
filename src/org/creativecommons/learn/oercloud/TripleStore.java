/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.creativecommons.learn.oercloud;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.creativecommons.learn.oercloud.behavior.ContextBehaviorFactory;
import org.creativecommons.learn.oercloud.support.CuratorImpl;
import org.creativecommons.learn.oercloud.support.FeedImpl;
import org.openrdf.elmo.ElmoManager;
import org.openrdf.elmo.ElmoModule;
import org.openrdf.elmo.sesame.SesameManagerFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

/**
 *
 * A Repository wrapper that loads and initializes our Elmo bindings.
 * 
 * @author nathan
 */
public class TripleStore {

	private Repository r;

    private ElmoModule elmo;
    private SesameManagerFactory factory = null;

    public TripleStore() throws RepositoryException {

        // initialize the repository connection
        String sesameServer = "http://localhost:8080/openrdf-sesame";
        String repositoryID = "oercloud";

        r = new HTTPRepository(sesameServer, repositoryID);
        r.initialize();
        
        // initialize Elmo
        elmo = this.createModule();
    }

    /**
     * Create and initialize an ElmoModule with interesting roles.
     * The returned module can then be coped to a context, etc.
     * 
     * @return ElmoModule
     */
    protected ElmoModule createModule() {
        
        ElmoModule result = new ElmoModule();

        addModuleRegistrations(result);
        
        return result;
    } // createModule

	public void addModuleRegistrations(ElmoModule module) {
		
		// core Roles
		module.addRole(Curator.class);
        module.addRole(Feed.class);
        module.addRole(Resource.class);
        
        // Behavior classes
        module.addRole(CuratorImpl.class);
        module.addRole(FeedImpl.class);
        
        module.addFactory(ContextBehaviorFactory.class, OERCLOUD.Curator);
        module.addFactory(ContextBehaviorFactory.class, OERCLOUD.Feed);
	}

    public Repository getRepository() {
		return r;
	}

    public RepositoryConnection getConnection() {
        try {
            return this.r.getConnection();
        } catch (RepositoryException ex) {
            Logger.getLogger(TripleStore.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    } // getConnection

    public ElmoManager getElmoManager() {
    	
        return this.getManagerFactory().createElmoManager();
        
    }
    
    public ElmoManager getElmoManager(ElmoModule module) {
    	return this.getManagerFactory(module).createElmoManager();
    }

	public SesameManagerFactory getManagerFactory() {
		
		if (this.factory == null) {
			// create a new factory
	        factory = new SesameManagerFactory(elmo, r);
		}
		
		return factory;
	}

	public SesameManagerFactory getManagerFactory(ElmoModule module) {
		
        return new SesameManagerFactory(module, this.getRepository());

	}
    
} // TripleStore
