package com.snapdeal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.dto.UploadResult;

@Service
public interface ASNService {

	public UploadResult saveFile (MultipartFile file) throws Exception;
}
