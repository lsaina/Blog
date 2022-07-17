package kr.co.member.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MemberController {
	
	//파일 업로드 메서드 (file객체, savePath 넣으면 filepath String으로 리턴) 
	public String fileUpload(MultipartFile file, String savePath) {
		//파일명이 기존 파일과 겹치는 경우 기존파일을 삭제하고 새 파일만 남는 현상이 생김(덮어쓰기)
		//파일명 중복처리
		String filename = file.getOriginalFilename();
		//test.txt -> test_1.txt
		//test.txt -> test_2.txt
		//업로드한 파일명이 test.txt인 경우 -> text			.txt 두 부분으로 분리
		String onlyFilename = filename.substring(0,filename.lastIndexOf("."));	//test
		String extention = filename.substring(filename.lastIndexOf("."));		//.txt
		//실제 업로드할 파일명을 저장할 변수
		String filepath = null;
		//파일명 중복 시 뒤에 붙일 숫자 변수
		int count = 0;
		while(true) {
			if(count == 0) {
				//반복 첫번째 회차에는 원본파일명을 그대로 적용
				filepath = onlyFilename+extention;	//test.txt
			}else {
				filepath = onlyFilename+"_"+count+extention;
			}
			File checkFile = new File(savePath+filepath);
			if(!checkFile.exists()) {
				break;
			}
			count++;
		}
		//파일명 중복검사가 끝난 시점 -> 해당파일 업로드 작업
		try {
			//중복처리가 끝난 파일명 (filepath)으로 파일을 업로드할 FileOutputStream객체 생성
			FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));
			//업로드 속도 증가를 위한 보조스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			//파일업로드
			byte[] bytes = file.getBytes();
			bos.write(bytes);
			bos.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}
	
	//파일 업로드 테스트 페이ㅣ로 이동
	@RequestMapping("/fileupload.do")
	public String fileupload() {
		return "main/fileupload";
	}
	
	//파일 업로드 테스트 페이지 보내기 버튼
	@RequestMapping("/uploadTest.do")
	public String uploadTest(MultipartFile upfile, HttpServletRequest request){
		//파일업로드 로직
		//1. 파일업로드 경로 설정
		//request.getSession().getServletContext().getRealPath() -> /webapp/폴더 경로
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/test/");
		//2. 실제로 저장될 파일명 변수, fileUpload method : 파일 업로드에서 반복되는 패턴을 따로 분리해둔 메서드
		String path = fileUpload(upfile, savePath); 
		//3. DB에 path값을 저장해서 후에 필요할 때 업로드 된 파일을 불러올 수 있다.
		// 현재 여기는 특별한 처리 안함
		return "redirect:/";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
