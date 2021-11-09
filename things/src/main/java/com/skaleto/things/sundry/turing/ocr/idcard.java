package com.skaleto.things.sundry.turing.ocr;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
/**
 *身份证识别 WebAPI 接口调用示例 接口文档（必看）：https://doc.xfyun.cn/rest_api/%E8%BA%AB%E4%BB%BD%E8%AF%81%E8%AF%86%E5%88%AB.html
 *webapi OCR服务参考帖子（必看）：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=39111&highlight=OCR
 *(Very Important)创建完webapi应用添加身份证识别之后一定要设置ip白名单，找到控制台--我的应用--设置ip白名单，如何设置参考：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=41891
 *图片属性：仅支持jpg格式，推荐 jpg 文件设置为：尺寸 1024×768，图像质量 75 以上，位深度 24。base64位编码之后大小不超过4M
 *错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
 *OCR错误码400开头请在接口文档底部查看
 */
public class idcard {
	   // OCR webapi 接口地址
		private static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/idcard";
		// 应用APPID（必须为webapi类型应用，并开通身份证识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481）
		private static final String APPID = "5e79c99f";
		// 接口密钥（webapi类型应用开通身份证识别服务后，控制台--我的应用---身份证识别---相应服务的apikey）
		private static final String API_KEY = "c524fbbf53d0cbe9d20561d9c2a9138a";
		// 引擎类型
		private static final String ENGINE_TYPE = "idcard";
		// 是否返回头像图片		
		private static final String HEAD_PORTRAIT = "1";
		// 是否返回切片图
		//private static final String CROP_IMAGE = "0";

		// 是否返回身份证号码区域截图
		private static final String ID_NUMBER_IMAGE = "1";
		
		// 图片地址
		private static final String AUDIO_PATH = "/Users/yaoyibin/Documents/工业图聆/test.jpg";
		/**
		 * OCR WebAPI 调用示例程序
		 * 
		 * @param args
		 * @throws IOException
		 */
		public static void main(String[] args) throws IOException {
			Map<String, String> header = buildHttpHeader();
			byte[] imageByteArray = FileUtil.read(AUDIO_PATH);
			String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
			String result = HttpUtil.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
			System.out.println("WEB card 接口调用结果：" + result);//错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
		}

		/**
		 * 组装http请求头
		 */
		private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
			String curTime = System.currentTimeMillis() / 1000L + "";
			String param = "{\"engine_type\":\"" + ENGINE_TYPE + "\",\"head_portrait\":\"" + HEAD_PORTRAIT + "\",\"id_number_image\":\"" + ID_NUMBER_IMAGE +"\"}";
			String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", paramBase64);
			header.put("X-CurTime", curTime);
			header.put("X-CheckSum", checkSum);
			header.put("X-Appid", APPID);
			return header;
		}
}