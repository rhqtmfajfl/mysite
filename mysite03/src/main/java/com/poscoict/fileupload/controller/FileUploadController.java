package com.poscoict.fileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/form"})
	public String form() {
		return "form";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST) //form.jsp 에서 post형식으로 /upload로 인해 안에 값을 받아온다.
	public String upload(
			@RequestParam(value="email", required=true, defaultValue="") String email, //email이랑
			@RequestParam(value="upload-file") MultipartFile multipartFile,            // upload-filedmf form.jsp에서 받아온다.
			Model model) {
		
		System.out.println("email:" + email);
		System.out.println("multipartFile : " + multipartFile.toString());
		
		String url = fileUploadService.restore(multipartFile);  //FileUploadService에서 resotre을 사용한다. @Autowired를 사용해서 fileUploadService를 가지고 온다.
		model.addAttribute("url", url);
		
		return "result";
	}
}