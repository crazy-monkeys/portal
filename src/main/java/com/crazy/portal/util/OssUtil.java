package com.crazy.portal.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @ClassName: OssUtil
 * @Author: God Man Qiu~
 * @Date: 2018/12/12 20:44
 */
public class OssUtil {

    private static String END_POINT = "oss-cn-shanghai.aliyuncs.com";
    private static String ACCESS_KEY_ID = "LTAIkJ8NAuB9btzq";
    private static String ACCESS_KEY_SECRET = "Td0`Da4YyD6iv5UCnKUbVEv0I9XGiRO";
    private static String BUCKET_NAME = "multilink";

    private static OSSClient openClient(){
        return new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    private static void closeClient(OSSClient ossClient){
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /*上传图片*/
    public static void uploadFile(String objectName, String filePath){
        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        /*String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));*/

        // 上传文件流。
        /*InputStream inputStream = new FileInputStream("<yourlocalFile>");
        ossClient.putObject("<yourBucketName>", "<yourObjectName>", inputStream);*/

        OSSClient ossClient = openClient();
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        File file = new File(filePath);
        ossClient.putObject(BUCKET_NAME, objectName, file);
        closeClient(ossClient);

    }

    /*下载文件
    * objectName：服务器上的文件名
    * filePath：本地存放路径(c：objectName.type)
    * */
    public static void downloadFile(String objectName, String filePath){
        OSSClient ossClient = openClient();
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.getObject(new GetObjectRequest(BUCKET_NAME, objectName), new File(filePath));
        closeClient(ossClient);
    }

    /*获取文件访问URL 有时效性
     *设置URL过期时间为1小时。
    * Date expiration = new Date(new Date().getTime() + 3600 * 1000);
    * */
    public static URL getObjectUrl(String objectName, Date expiration){
        OSSClient ossClient = openClient();
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(BUCKET_NAME, objectName, expiration);
        closeClient(ossClient);
        return url;
    }

    /*获取文件访问URL 无时效性
    * */
    public static URL getObjectUrl(String objectName){
        OSSClient ossClient = openClient();
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(BUCKET_NAME, objectName);
        request.setMethod(HttpMethod.GET);
        URL url = ossClient.generatePresignedUrl(request);
        closeClient(ossClient);
        return url;
    }
}
