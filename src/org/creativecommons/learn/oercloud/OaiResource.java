package org.creativecommons.learn.oercloud;

import java.util.Set;

import org.openrdf.elmo.annotations.rdf;

@rdf(OERCLOUD.OaiResource)
public interface OaiResource {

	@rdf("http://purl.org/dc/terms/subject")
	public Set<String> getSubjects();
	public void setSubjects(Set<String> subjects);
	
}
