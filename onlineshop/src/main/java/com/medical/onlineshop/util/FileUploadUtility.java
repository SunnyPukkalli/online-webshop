package com.medical.onlineshop.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = "D:\\spring\\online-medical-store\\onlineshop\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {

		// get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);

		// check all directories exists, if not create
		if (!new File(ABS_PATH).exists()) {
			// create
			new File(ABS_PATH).mkdirs();
		}

		// check all directories exists, if not create
		if (!new File(REAL_PATH).exists()) {
			// create
			new File(REAL_PATH).mkdirs();
		}
		
		try{
			//server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			
			//project dorectory upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			
		}catch(IOException ex){
			
		}
	}

}
