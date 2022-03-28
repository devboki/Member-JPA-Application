package com.doctor.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.doctor.domain.Bno;
import com.doctor.domain.Pno;
import com.doctor.domain.ResultDto;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class BuisnessService {

	/* 사업자 번호 인증 */
	@SuppressWarnings("unused")
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

	/* 휴대폰 인증 */
	public String phoneNumberCheck(String pNo) throws CoolsmsException {
		String api_key = "NCSUABB5JPDXCCE8";
        String api_secret = "3EWZMGR1KROFDELHRGF2CYGWQ5LZNVYV";
        Message coolsms = new Message(api_key, api_secret);
        
        Random rand = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
        	String ran = Integer.toString(rand.nextInt(10));
        	numStr+=ran;
        }
        
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", pNo);
        params.put("from", "01031338916");
        params.put("type", "sms");
        params.put("text", "인증 번호는 [" + numStr + "] 입니다.");
        params.put("app_version", "doctor app 0.0");
       
        coolsms.send(params);
        
        return numStr;
	}

}