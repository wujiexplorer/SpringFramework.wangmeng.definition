package com.lx.benefits.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ImageCheckCodeUtil {
	private static char[] chs = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy0123456789".toCharArray();
	private static final int NUMBER_OF_CHS = 4;
	private static final int IMG_WIDTH = 65;
	private static final int IMG_HEIGHT = 25;
	private static Random r = new Random();
	private static final String IMAGE_CODE_CACHE = "imageCode_%s_%s";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String getImageChcekCode(String codeKey, OutputStream outputStream) throws IOException {
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB); // 实例化BufferedImage
		Graphics g = image.getGraphics();
		Color c = new Color(200, 200, 255); // 验证码图片的背景颜色
		g.setColor(c);
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // 图片的边框
		StringBuilder sb = new StringBuilder(); // 用于保存验证码字符串
		int index; // 数组的下标
		for (int i = 0; i < NUMBER_OF_CHS; i++) {
			index = r.nextInt(chs.length); // 随机一个下标
			g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150))); // 随机一个颜色
			Font font = new Font("Fixedsys", Font.BOLD, 18);
			g.setFont(font);
			g.drawString(chs[index] + "", 15 * i + 3, 18); // 画出字符
			sb.append(chs[index]); // 验证码字符串
		}
		g.setFont(null);
		for (int i = 0; i < 5; i++) {// 画5条干扰线
			int x1 = r.nextInt(IMG_WIDTH);
			int x2 = r.nextInt(IMG_WIDTH);
			int y1 = r.nextInt(IMG_HEIGHT);
			int y2 = r.nextInt(IMG_HEIGHT);
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawLine(x1, y1, x2, y2);
		}
		ImageIO.write(image, "jpg", outputStream);
		String imageCode = sb.toString();
		stringRedisTemplate.opsForValue().set(String.format(IMAGE_CODE_CACHE, codeKey, imageCode.toLowerCase()), "1", 5, TimeUnit.MINUTES);
		return imageCode;
	}

	public boolean checkImageCode(String codeKey, String imageCode) {
		if (codeKey == null || StringUtils.isEmpty(imageCode) || imageCode.length() != NUMBER_OF_CHS) {
			return false;
		}
		return stringRedisTemplate.delete(String.format(IMAGE_CODE_CACHE, codeKey, imageCode.toLowerCase()));
	}

}
