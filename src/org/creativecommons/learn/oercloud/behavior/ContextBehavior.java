package org.creativecommons.learn.oercloud.behavior;

import javax.xml.namespace.QName;

import org.creativecommons.learn.oercloud.TripleStore;
import org.openrdf.elmo.ElmoManager;

public class ContextBehavior implements IContextBehavior {

	private QName qname;
	
	public String getUrl() {
		return this.get_QName().getNamespaceURI() + this.get_QName().getLocalPart();
	}
	
	public QName get_QName() {
		return qname;
	}

	public void setQName(QName base_qname) {
		this.qname = base_qname;
	}

	@Override
	public ElmoManager getContextManager() {
		
		return TripleStore.get().getElmoManager(this.get_QName());

	}

}
