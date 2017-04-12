package sample.nodecv;

import com.alibaba.fastjson.JSON;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeCVSampleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_case_1() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = "http://localhost:9900/opencv/dissimilarity";
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("image1", new File("./fixture/T-Shirt.jpg"), ContentType.MULTIPART_FORM_DATA, "T-Shirt.jpg");
		builder.addBinaryBody("image2", new File("./fixture/T-Shirt-logo.jpg"), ContentType.MULTIPART_FORM_DATA, "T-Shirt-logo.jpg");
		HttpEntity multipart = builder.build();
		httpPost.setEntity(multipart);
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(JSON.parseObject(EntityUtils.toString(response.getEntity())).get("dissimilarity"));
		client.close();
	}

	@After
	public void tearDown() throws Exception {
	}
}
