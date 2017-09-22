package cn.itcast.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestSorl {
	
	@Autowired
	private SolrServer solrServer;
	
	@Test
	public void testSolrSpring() throws Exception{
		
		//保存
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", "4");
		doc.setField("name", "范冰冰");
		solrServer.add(doc);
		solrServer.commit();
	}

}
