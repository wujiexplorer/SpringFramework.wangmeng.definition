package com.wangmeng.util;

import com.iflytek.cloud.speech.*;

/**
 * 讯飞转换PCM
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年3月6日
 */
public class XunfeiConvertUtil {
	public static Boolean flag = true;
	// 合成监听器
	public final static SynthesizeToUriListener mSynListener = new SynthesizeToUriListener() {
		//progress为合成进度0~100
		@Override
		public void onBufferProgress(int progress) {
			
		} 
		//会话合成完成回调接口 URI为合成保存地址，error为错误信息，为null时表示合成会话成功
		@Override
		public void onSynthesizeCompleted(String uri, SpeechError error) {
			if(error==null){
				System.out.println("生成路径地址:"+uri);
			}else{
				System.out.println(error);
			}
			flag = true;
		}
		@Override
		public void onEvent(int arg0, int arg1, int arg2, int arg3,
				Object arg4, Object arg5) {
			
		}
	};
    public static void convert(String message,String path){
    	try {
    		//System.out.println(message);
    		//更换为自己 申请的APPID
			SpeechUtility.createUtility(SpeechConstant.APPID + "=5cd40c3d");
			//Setting.setShowLog(true);

			// 1.创建SpeechSynthesizer对象
			SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
			// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
			mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
			mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
			mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
			// 3.开始合成
			mTts.setParameter("LIB_NAME_64", "D:\\code\\xufei_msc\\src\\main\\webapp\\msc64.dll");
			mTts.synthesizeToUri(message,path,mSynListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		convert("wangmeng","D:/");
	}
}
