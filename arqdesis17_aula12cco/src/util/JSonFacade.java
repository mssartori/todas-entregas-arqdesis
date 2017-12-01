package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import model.Extrato;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class JSonFacade {
	public static StringBuilder montaJSon(HttpServletRequest request)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		return sb;
	}
	public static String listToJSon(ArrayList<Extrato> lista) {
		JSONArray vetor = new JSONArray();
		for (Extrato to : lista) {
			JSONObject object = new JSONObject();
			try {
				object.put("id", to.getId());
				object.put("extrato", to.getTransacoes());
				object.put("periodo", to.getData());
				vetor.put(object);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return vetor.toString();
	}
	public static Extrato jSonToCliente(String json) throws IOException{
		try{
			JSONObject registro = new JSONObject(json);
			int id = registro.getInt("id");
			String extrato = registro.getString("transacoes");
			String periodo = registro.getString("periodo");
			Extrato extrato = new Extrato();
			extrato.setId(id);
			extrato.setTransacoes(extrato);
			extrato.setData(periodo);
			return extrato;
		} catch(JSONException jsone){
			jsone.printStackTrace();
			throw new IOException(jsone);
		}
	}
	public static String extratoToJSon(Extrato extrato) throws IOException {
		JSONObject object = new JSONObject();
		try {
			object.put("id", extrato.getId());
			object.put("extrato", extrato.getTransacoes());
			object.put("periodo", extrato.getData());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}
	public static String errorToJSon(Exception e) {
		JSONObject object = new JSONObject();
		try {
			object.put("error", e.toString());
		} catch (JSONException e1) {
			e.printStackTrace();
		}
		return object.toString();
	}
}