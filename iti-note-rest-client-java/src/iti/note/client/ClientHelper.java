package iti.note.client;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientHelper {

	private final static Client client;

	static {
		DefaultClientConfig cc = new DefaultClientConfig();
		cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(cc);
	}

	public static <T> List<T> retrieve(String path) {
		WebResource r = client.resource(path);
		return r.get(new GenericType<List<T>>() {
		});
	}

	public static <T> T create(String path, T t) {
		WebResource r = client.resource(path);
		return (T) r.type(MediaType.APPLICATION_JSON).post(t.getClass(), t);
	}

}
