package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

//������
public class YxsjUtil {

	// �ֶ�����uuid
	public static String getUuid() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	// ��þ�ȷ�����ʱ�� string����
	public static String getDateSecond() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);

			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return "0000-00-00";
		}

	}

	// ��þ�ȷ�����ʱ�� Date����
	public static Date getDateSecond_string() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);

			return secondDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return secondDate;
		}

	}

	public static String getDateSecond_2() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date secondDate = new Date();
		String date = formatter.format(secondDate);
		try {
			secondDate = formatter.parse(date);
			System.out.println(date);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return "0000-00-00";
		}

	}
}
