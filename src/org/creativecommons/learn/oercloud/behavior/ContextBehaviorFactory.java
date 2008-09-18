package org.creativecommons.learn.oercloud.behavior;

import org.creativecommons.learn.oercloud.Curator;
import org.creativecommons.learn.oercloud.Feed;
import org.openrdf.elmo.Entity;
import org.openrdf.elmo.annotations.factory;

@factory
public class ContextBehaviorFactory {

	@factory
	public IContextBehavior createContextBehavior(Curator curator) {
		ContextBehavior result = new ContextBehavior();
		result.setQName(((Entity)curator).getQName());
		
		return result;
	}
	
	@factory
	public IContextBehavior createContextBehavior(Feed feed) {
		ContextBehavior result = new ContextBehavior();
		result.setQName(((Entity)feed).getQName());
		
		return result;
	}
	
	
}
