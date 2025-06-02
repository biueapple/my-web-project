package com.example.mysite.user;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Upload {
	
	public String fileUpload(String savePath, MultipartFile file)
	{
		String path = null;
		if (!file.isEmpty()) {

			String originalFilename = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String savedFilename = uuid + "_" + originalFilename;
			File uploadDir = new File(savePath);

			path = savePath + savedFilename;
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 폴더가 없으면 자동 생성
			}

			File dest = new File(savePath + savedFilename);

			try {
				file.transferTo((dest));
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	
	

}
