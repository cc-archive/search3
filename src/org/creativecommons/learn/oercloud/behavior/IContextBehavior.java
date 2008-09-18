package org.creativecommons.learn.oercloud.behavior;

import javax.xml.namespace.QName;

import org.openrdf.elmo.ElmoManager;

public interface IContextBehavior {

	public QName getQname();
	public String getUrl();
	public void setQName(QName qname);
	
	public ElmoManager getContextManager();
	
}
