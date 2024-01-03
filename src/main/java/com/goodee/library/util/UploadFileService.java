package com.goodee.library.util;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileService.class);
	
	public String upload(MultipartFile file) {
		boolean result = false;
		// 파일 저장
		String fileOriName = file.getOriginalFilename();
		String fileExtension =
				fileOriName.substring(fileOriName.lastIndexOf("."),fileOriName.length());
		String uploadDir = "C:\\library\\upload\\";
		
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File saveFile = new File(uploadDir+"\\"+uniqueName+fileExtension);
		
		if(!saveFile.exists())
			saveFile.mkdirs();
		
		try {
			file.transferTo(saveFile);
			result = true;
		} catch(Exception e) {
			logger.error(e.toString());
		}
		
		if(result) {
			logger.info("[UploadFileService] 파일 업로드 성공!!");
			return uniqueName+fileExtension;
		} else {
			logger.info("[UploadFileService] 파일 업로드 실패");
		}
		return null;
	}
}
