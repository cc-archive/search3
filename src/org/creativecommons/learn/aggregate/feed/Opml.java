package org.creativecommons.learn.aggregate.feed;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import org.creativecommons.learn.oercloud.Feed;
import org.creativecommons.learn.oercloud.store.TripleStore;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * 
 * @author nathan
 */
public class Opml {

	public void poll(Feed feed) {
		try {

			// load the OPML feed as a JDOM document
			Document opml = new SAXBuilder().build(new URL(feed.getUrl()));

			// iterate over outline nodes
			List<Element> nodes = XPath.selectNodes(opml, "//outline");

			for (Element n : nodes) {

				// check if this feed already exists
				if (TripleStore.get().exists(feed.getUrl(), Feed.class,
						n.getAttributeValue("xmlUrl")))
					continue;

				// nope...
				Feed node_feed = null;

				if (n.getAttributeValue("type").equals("include")) {
					// explicit inclusion
					node_feed = TripleStore.get().getElmoManager(
							new QName(feed.getUrl())).designate(
							new QName(n.getAttributeValue("url")), Feed.class);
					node_feed.setType("opml");

				} else {
					// assume it's a feed... which to us is anything else
					node_feed = TripleStore.get().getElmoManager(
							new QName(feed.getUrl())).designate(
							new QName(n.getAttributeValue("xmlUrl")),
							Feed.class);
					node_feed.setType(n.getAttributeValue("type"));
				}

				// XXX poll here?
			}

		} catch (JDOMException ex) {
			Logger.getLogger(Opml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Opml.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
