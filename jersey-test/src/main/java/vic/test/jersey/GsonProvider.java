package vic.test.jersey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.common.base.Charsets;
import com.google.gson.Gson;

@Provider
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class GsonProvider<T> implements MessageBodyReader<T>, MessageBodyWriter<T> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] antns, MediaType mt) {
		return true;
	}
 
	@Override
	public T readFrom(Class<T> type, Type genericType,
			Annotation[] antns, MediaType mt,
			MultivaluedMap<String, String> mm, InputStream in)
			throws IOException, WebApplicationException {
		Gson g = new Gson();
		return g.fromJson(_convertStreamToString(in), type);
	}

	private String _convertStreamToString(InputStream inputStream) throws IOException {
		if (inputStream != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				inputStream.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	@Override
	public void writeTo(T t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream)
			throws IOException, WebApplicationException {
        Gson g = new Gson();
        OutputStreamWriter writer = new OutputStreamWriter(entityStream, Charsets.UTF_8);
        try {
            g.toJson(t, writer);
        } finally {
            writer.close();
        }
//		entityStream.write(g.toJson(t).getBytes("UTF-8"));
	}
 
	@Override
	public long getSize(T t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}
 
	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}
}
