package testjpa.member;

import java.io.IOException;
import okhttp3.*;

public class HttpCaller {

	OkHttpClient client = new OkHttpClient()
			.newBuilder()
			.build();
	
	MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
	
	@SuppressWarnings("deprecation")
	RequestBody body = RequestBody.create(mediaType, "{\r\n  \"b_no\": [\r\n    \"5298600830\"\r\n  ]\r\n}");
	
	String run(String url) throws IOException {
		Request request = new Request.Builder()
			  .url(url)
			  .method("POST", body)
			  .addHeader("Content-Type", "application/json")
			  .build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public static void main(String[] args) throws IOException {
		String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=qiZ1LHi4vs9UHAPKDnqSXmpbF20WwAPnZd2RpfnCqRhbT06uvUepuDGUgTqdgb6cPGJB2OVnzHbzlH8EJpImng%3D%3D";
		String responseString = new HttpCaller().run(url);
		System.out.println(responseString);
	}
}