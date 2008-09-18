package org.creativecommons.learn.oercloud;

import java.util.Set;

import org.openrdf.elmo.annotations.rdf;

@rdf(OERCLOUD.Resource)
public interface Resource {

    @rdf(OERCLOUD.aggregationSource)
    public Feed getAggregationSource();
    public void setAggregationSource(Feed aggregationSource);

	@rdf("http://purl.org/dc/terms/title")
	public String getTitle();
	public void setTitle(String title);
	
	@rdf("http://purl.org/dc/terms/subject")
	public Set<String>getSubjects();
	public void setSubjects(Set<String> subjects);
}
