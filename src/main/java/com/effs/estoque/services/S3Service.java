package com.effs.estoque.services;

import java.io.File;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * @author eduardosatyra
 *
 */

@Service
public class S3Service {

	private Logger log = org.slf4j.LoggerFactory.getLogger(S3Service.class);	
	@Autowired
	private AmazonS3 s3client;	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			log.info("iniciando upload...");
			s3client.putObject(new PutObjectRequest(bucketName, "teste.png", file));
			log.info("upload efetuado com sucesso!");
		} catch (AmazonServiceException e) {
			log.info("AmazonServiceException: " + e.getMessage());
			log.info("status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			log.info("AmazonClientException: " + e.getMessage());
		}
	}
}
