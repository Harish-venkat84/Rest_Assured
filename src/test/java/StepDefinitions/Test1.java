package StepDefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

	public static String timeData() {

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();

		String format = formatter.format(date);

		String[] split = format.split(":");

		String newNum = "";
		for (String w : split)

		{

			newNum = newNum + w;

		}

		System.out.println("String time :" + newNum);

		int parseInt = Integer.parseInt(newNum);

		System.out.println("Int time :" + parseInt);
		System.out.println(formatter.format(date));

		newNum = "1612581256";
		return newNum;

	}

	public static void main(String[] args) {

	}

}
