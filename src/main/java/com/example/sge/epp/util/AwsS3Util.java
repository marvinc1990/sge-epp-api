package com.example.sge.epp.util;

import com.amazonaws.SdkClientException;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Component
public class AwsS3Util {

    private static String ACCESSKEY;
    private static String SECRETKEY;
    private static String BUCKET;

    @Value("${aws.s3.accesskey}")
    public void setAccesskey(String accesskey) {
        ACCESSKEY = accesskey;
    }

    @Value("${aws.s3.secretkey}")
    public void setSecretkey(String secretkey) {
        SECRETKEY = secretkey;
    }

    @Value("${aws.s3.bucket}")
    public void setBucket(String bucket) {
        BUCKET = bucket;
    }

    public static void uploadFile(File file, String remotePath) {
        AmazonS3 s3Client = connect();
        s3Client.putObject(BUCKET, remotePath, file);
    }

    public static File downloadFile(String remotePath, String localPath) {
        File file = new File(localPath);
        try {
            AmazonS3 s3Client = connect();
            S3Object s3object = s3Client.getObject(BUCKET, remotePath);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file: " + e.getMessage());
        }
        return file;
    }

    public static void deleteFile(String remotePath) {
        try {
            AmazonS3 s3Client = connect();
            s3Client.deleteObject(BUCKET, remotePath);
        } catch (SdkClientException e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }

    public static AmazonS3 connect() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
