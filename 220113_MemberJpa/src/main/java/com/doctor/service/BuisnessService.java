package com.doctor.service;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.doctor.domain.Bno;
import com.doctor.domain.ResultDto;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@RequiredArgsConstructor
public class BuisnessService {

	public ResultDto check(Bno bNo) throws IOException {
		
		OkHttpClient client = new OkHttpClient()
				.newBuilder()
				.build();
		
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		
		String json = bNo.getBNo();
		String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=qiZ1LHi4vs9UHAPKDnqSXmpbF20WwAPnZd2RpfnCqRhbT06uvUepuDGUgTqdgb6cPGJB2OVnzHbzlH8EJpImng%3D%3D";
		String jsonStart = "{\r\n  \"b_no\": [\r\n    \"";
		String jsonEnd = "\"\r\n  ]\r\n}";
		
		RequestBody body = RequestBody.create(mediaType, jsonStart + json + jsonEnd);
		
		Request request = new Request.Builder()
				  .url(url)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
		
		Response response = client.newCall(request).execute();
		
		String result = response.body().string();
		
		try {
			JSONObject jsonResult = new JSONObject(result);
			JSONArray dataArray = jsonResult.getJSONArray("data");
			
			for(int i=0; i<dataArray.length(); i++) {
				JSONObject dataObj = dataArray.getJSONObject(i);
				String dataBno = dataObj.getString("b_no");
				String dataTaxType = dataObj.getString("tax_type");
				ResultDto resultDTO = new ResultDto(dataBno, dataTaxType);
				//System.out.println(resultDTO.getBNo() + " : " + resultDTO.getTaxType());
				return resultDTO;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResultDto();
	}
}