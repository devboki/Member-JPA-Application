package com.doctor.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.MemberDto;
import com.doctor.domain.Bno;
import com.doctor.domain.ResultDto;
import com.doctor.domain.ResultFindMember;
import com.doctor.service.BuisnessService;
import com.doctor.service.DoctorService;
import com.doctor.service.MemberService;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorServiceTest {

	@Autowired DoctorService doctorService;
	@Autowired MemberService memberService;
	
	@PersistenceContext EntityManager em;
	
	@Test
	public void 의사_단건조회_Dto() {
		Optional<Doctor> findDoctor0 = doctorService.findDoctorIdOptional("doctor0");
		Optional<Doctor> findDoctor1 = doctorService.findDoctorIdOptional("doctor1");
		System.out.println("findDoctor0 = " + findDoctor0);
		System.out.println("findDoctor1 = " + findDoctor1);
		
		Optional<DoctorDto> findDoctorDto0 = findDoctor0.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		Optional<DoctorDto> findDoctorDto1= findDoctor1.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		System.out.println("findDoctorDto0 = " + findDoctorDto0);
		System.out.println("findDoctorDto1 = " + findDoctorDto1);
					
		Optional<DoctorDto> findDoctorDto3 = doctorService.findDoctorIdOptional("doctor3")
													.map(d -> new DoctorDto(d.getId(), d.getPassword(), d.getPhoneNumber(), d.getBuisnessNumber()));
		System.out.println("findDoctorDto3 = " + findDoctorDto3);
	}
	
	@Test
	@Rollback(false)
	public void 의사삭제() {
		doctorService.delete("doctor1");
	}
	
	@Test
	public void 환자조회_fetch_join_Dto() {
		List<MemberDto> doctor3Members = doctorService.findMemberDto("doctor3", "Aaaa1234!");
		System.out.println(doctor3Members);
	}
	
	/* list 출력 이슈 : 수정중 */
	@Test
	public void 환자조회_fetch_join_Result() {
		List<ResultFindMember> result = doctorService.findMemberResult("doctor3", "Aaaa1234!");
		System.out.println(result);
	}

	@Test
	public void 환자조회_paging_Dto() {
		
		PageRequest pageRequest = PageRequest.of(0, 3); //doctor3인 member 첫 페이지는 0, 한 페이지에 3개 보이도록
		Page<MemberDto> result = doctorService.findAllMember("doctor3", pageRequest);  
		 			
	    System.out.println(result.getTotalPages()); 	//총 데이터 5개를 3개씩 : 2페이지
	    System.out.println(result.getTotalElements());  //총 데이터 5개
	    System.out.println(result.nextPageable());		//Page request [number: 1, size 3]

	    List<MemberDto> content = result.getContent();
		System.out.println(content.size());				//조회된 데이터 : 3개
		System.out.println(content.stream().collect(Collectors.toList()));
	}
	
	@Test
	@Rollback(false)
	public void 의사등록() {
		Doctor doctor = Doctor.builder()
				.id("TESTdoctor")
				.password("AAaa123!")
				.phoneNumber("010-1234-5678")
				.buisnessNumber("123-12-12345")
				.build();
		
		String doctorId = doctorService.join(doctor);
		List<DoctorDto> joinDoctor = doctorService.findDoctorDto(doctorId);
		System.out.println("joinDoctor = " + joinDoctor);
	}
	
	@Test
	@Rollback(false)
	public void 의사수정() {

		/* Doctor : .changeDoctor() */
		Doctor findDoctor3 = doctorService.findDoctorOneId("doctor3");
		findDoctor3.changeDoctor("Abc1234!", "010-4560-7890");
		List<DoctorDto> updateDoctor3 = doctorService.findDoctorDto(findDoctor3.getId());
		System.out.println("updateDoctor3 = " + updateDoctor3);
				
		/* Service : .update() */
		Doctor findDoctor2 = doctorService.findDoctorOneId("doctor2");
		doctorService.update(findDoctor2.getId(), "Ooo0000!", "010-7777-7777");
		List<DoctorDto> updateDoctor2 = doctorService.findDoctorDto(findDoctor2.getId());
		System.out.println("updateDoctor2 = " + updateDoctor2);
	}
	
	@Autowired BuisnessService buisnessService;
	
	@Test
	public void openApi() throws IOException {
		Bno bno = new Bno();
		bno.setBNo("5298600830");

		OkHttpClient client = new OkHttpClient()
				.newBuilder()
				.build();
		
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		
		String json = bno.getBNo();
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
			System.out.println("jsonResult = " + jsonResult);
			System.out.println("status_code = " + jsonResult.get("status_code"));
			
			JSONArray dataArray = jsonResult.getJSONArray("data");
			System.out.println("dataArray = " + dataArray.get(0));
			
			for(int i=0; i<dataArray.length(); i++) {
				JSONObject dataObj = dataArray.getJSONObject(i);
				String bNo = dataObj.getString("b_no");
				String taxType = dataObj.getString("tax_type");
				ResultDto resultDTO = new ResultDto(bNo, taxType);
				System.out.println("resultDTO = " + resultDTO);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void openApiDto() throws IOException {

		Bno bnoNO = new Bno();
		bnoNO.setBNo("000000000011");
		
		ResultDto dtoNO = buisnessService.check(bnoNO);
		String error = "국세청에 등록되지 않은 사업자등록번호입니다.";
		if(dtoNO.getTaxType().equals(error)) {
			System.out.println("사업자 번호를 확인해주세요.");
		}
	}
	
	@Test
	public void sendSms() {
		String api_key = "NCSUABB5JPDXCCE8";
        String api_secret = "3EWZMGR1KROFDELHRGF2CYGWQ5LZNVYV";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", "01031338916");
        params.put("from", "01031338916");
        params.put("type", "SMS");
        params.put("text", "문자 내용");
        params.put("app_version", "test app 1.2");

        try {
            org.json.simple.JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
	}
}
