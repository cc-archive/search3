package org.creativecommons.learn.oercloud.support;

import javax.xml.namespace.QName;

import org.creativecommons.learn.oercloud.Feed;
import org.creativecommons.learn.oercloud.Resource;

public abstract class FeedImpl implements Feed {

	public Resource addResource(String URL) {

		Resource resource = this.getContextManager().designate(new QName(URL), Resource.class);
		resource.setAggregationSource(this);
		
		return resource;

	} // addFeed
	
} // FeedImpl
