package iti.note.test.client;

import static org.junit.Assert.*;
import iti.note.model.Taccuino;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class TaccuinoClientTest {

	@Test
	public void test() {
		DefaultClientConfig cc = new DefaultClientConfig();
		cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);

		Client client = Client.create(cc);

		WebResource r = client
				.resource("http://localhost:8080/iti-note-model-service/taccuino");

		Taccuino t = r.type(MediaType.APPLICATION_JSON).post(Taccuino.class,
				new Taccuino("Taccuino by Jersey"));

		assertNotNull(t.getId());

		List<Taccuino> tt = r.get(new GenericType<List<Taccuino>>() {
		});

		assertFalse(tt.isEmpty());
	}

}
