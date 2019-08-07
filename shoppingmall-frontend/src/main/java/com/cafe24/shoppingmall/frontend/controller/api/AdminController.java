package com.cafe24.shoppingmall.frontend.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shoppingmall.frontend.dto.JSONResult2;
import com.cafe24.shoppingmall.frontend.service.FileuploadService;

@Controller("adminAPIController")
@RequestMapping("/admin/api")
public class AdminController {
	
	@Autowired
	private FileuploadService fileuploadService;

	@ResponseBody
	@RequestMapping(value = "/insertImg", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	public String insertImg(@RequestParam("shopImg") MultipartFile multipartFile) {
		String url = fileuploadService.restore(multipartFile);
		
		System.out.println("============================");
		System.out.println(url);
		System.out.println("============================");
		
		// url = /image/2019772332929594.jpg
		// http://localhost:8080/shoppingmall-frontend/assets/image/2019772332929594.jpg
		return url;
	}
	
}
