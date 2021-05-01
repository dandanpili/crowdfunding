package com.atguigu.crowd.util;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;

import com.atguigu.crowd.constant.CrowdConstant;


/**
 * 众筹网通用工具方法类
 * @author 邵瑞琳
 *
 */
public class CrowdUtil {

	/**
	 * 专门负责上传文件到OSS服务器的工具方法
	 * @param endpoint			OSS参数
	 * @param accessKeyId		OSS参数
	 * @param accessKeySecret	OSS参数
	 * @param inputStream		要上传的文件的输入流
	 * @param bucketName		OSS参数
	 * @param bucketDomain		OSS参数
	 * @param originalName		要上传的文件的原始文件名
	 * @return	包含上传结果以及上传的文件在OSS上的访问路径
	 */
	public static ResultEntity<String> uploadFileToOss(
			String endpoint,
			String accessKeyId,
			String accessKeySecret,
			InputStream inputStream,
			String bucketName,
			String bucketDomain,
			String originalName) {
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String fileMainName = UUID.randomUUID().toString().replace("-", "");
		String extensionName = originalName.substring(originalName.lastIndexOf("."));
		String objectName = folderName + "/" + fileMainName + extensionName;
		try {
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
			ResponseMessage responseMessage = putObjectResult.getResponse();
			if(responseMessage == null) {
				String ossFileAccessPath = bucketDomain + "/" + objectName;
				return ResultEntity.successWithData(ossFileAccessPath);
			} else {
				int statusCode = responseMessage.getStatusCode();
				String errorMessage = responseMessage.getErrorResponseAsString();
				return ResultEntity.failed("当前响应状态码="+statusCode+" 错误消息="+errorMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		} finally {
			if(ossClient != null) {
				ossClient.shutdown();
			}
		}

	}

//	public static void main(String[] args) throws FileNotFoundException {
////		System.out.println("hello");
//		FileInputStream fileInputStream = new FileInputStream("E:\\IDEA-Git-Repository\\crowdfunding-network\\atcrowdfunding05-common-util\\QQHeadImage.jpg");
//		ResultEntity<String> resultEntity = uploadFileToOss("http://oss-cn-hangzhou.aliyuncs.com",
//				"LTAI5tCBDijahktTozG7zQcG",
//				"dd23nlezw3j4mJJTM4WKUD3rAVqx0f",
//				fileInputStream,
//				"shaoruilin01",
//				"http://shaoruilin01.oss-cn-hangzhou.aliyuncs.com",
//				"QQHeadImage.jpg"
//		);
//		System.out.println(resultEntity);
//	}


	public static ResultEntity<String> sendCodeByShortMessageTwo(
			String phone,
			String appcode,
			String sign,
			String skin,
			String host,
			String path
		){
	    String method = "POST";
		//生成验证码
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 4; i++) {
			str.append((int)(Math.random() * 10));
		}
		String param = str.toString();
//	    
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("phone", phone);
//	    querys.put("templateId", "TP18040314");
//	    querys.put("variable", param);
//	    Map<String, String> bodys = new HashMap<String, String>();
//
//
//	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
//	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//	    	System.out.println(response.toString());
//	    	//获取response的body
//	    	//System.out.println(EntityUtils.toString(response.getEntity()));
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }
		System.out.println(param);
	    return ResultEntity.successWithData(param);
	}
	
	
	/**
	 * 给远程第三方短信接口发送请求把验证码发送到用户手机上
	 * @param phone	接收验证码的手机号
	 * @param appcode	用来调用第三方短信API的AppCode
	 * @param sign	签名编号
	 * @param skin	模板编号
	 * @param host	短信接口调用的URL地址
	 * @param path	具体发送断行功能的地址
	 * @return
	 */
	public static ResultEntity<String> sendCodeByShortMessage(
				String phone,
				String appcode,
				String sign,
				String skin,
				String host,
				String path
			){
		//String host = "https://feginesms.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
        //String path = "/codeNotice";// 【2】后缀
        //String appcode = "4bac7ece03454e4f9b13410582036365"; // 【3】开通服务后 买家中心-查看AppCode
        //String sign = "1"; // 【4】请求参数，详见文档描述
        //String skin = "8"; // 【4】请求参数，详见文档描述
		
		//生成验证码
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < 4; i++) {
			str.append((int)(Math.random() * 10));
		}
		String param = str.toString();
        //String param = "shaoruilin"; // 【4】请求参数，详见文档描述
        //String phone = "15279381891"; // 【4】请求参数，详见文档描述
        String urlSend = host + path + "?sign=" + sign + "&skin=" + skin+ "&param=" + param+ "&phone=" + phone; // 【5】拼接请求链接
        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
                                                                                        // (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                //操作成功，把生成的验证码返回
                return ResultEntity.successWithData(param);
//                System.out.println("正常请求计费(其他均不计费)");
//                System.out.println("获取返回的json:");
//                System.out.print(json);
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    System.out.println("AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    System.out.println("请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    System.out.println("参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    System.out.println("套餐包次数用完 ");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                }
                return ResultEntity.failed(error);
            }

        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
	}
	/*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
	}
	/**
	 * 对明文字符串进行MD5加密
	 * @param source 传入的明文字符串
	 * @return 加密结果
	 */
	public static String md5(String source) {
		if(source == null || source.length() == 0) {
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		try {
			String algorithm = "md5";
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] input = source.getBytes();
			byte[] output = messageDigest.digest(input);
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum, output);
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断当前请求是否Ajax请求
	 * @param request
	 * @return
	 * 		true:当前请求是Ajax请求
	 * 		false:
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		//获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		//判断
		return (acceptHeader != null && acceptHeader.contains("application/json")) 
				||
			   (xRequestHeader != null && xRequestHeader.contains("XMLHttpRequest"));
	}
}
 