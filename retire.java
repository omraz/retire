/*
 *  Version Date    Note
 *  0.0.1   06Nov20 Initial version
 *
 */

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class retire {

	static void Help()	{
		System.out.println("Usage: retire { premature } { [ --num | -n ] } { [ --help | -h | /h | /? ] }");
		System.out.println("  premature\tset retire date to premature instead of regular; shorts allowed");
		System.out.println("  --num \tdisplay months as numbers");
		System.out.println("  --help\tthis help");
	}

	public static void main(String[] argv) {

		boolean premature = false;
		boolean numbers = false;
		boolean help = false;

		System.out.println("retire version 0.0.1 (19.12.2019)\t(c) OM");

		for(String arg : argv)	{
			if ( new String("premature").startsWith(arg.toLowerCase()) )
				premature = true;
			if ( new String("-h").equals(arg.toLowerCase()) ||
			     new String("--help").equals(arg.toLowerCase()) ||
			     new String("/h").equals(arg.toLowerCase()) ||
			     new String("/?").equals(arg.toLowerCase()) )
				help = true;
			if ( new String("-n").equals(arg.toLowerCase()) ||
			     new String("--num").equals(arg.toLowerCase()) )
				numbers = true;
		}

		if ( help )	{
			Help();
			return;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat fmtMonth = new SimpleDateFormat("MMM");
		Calendar retire = Calendar.getInstance();
		if ( premature )	{
			retire.set(2021, 6, 31);	//	month is zero based: 0 - January
			System.out.print("premature ");
		}
		else	{
			retire.set(2025, 10, 30);
			System.out.print("regular ");
		}
		System.out.println("retire date: " + formatter.format(retire.getTime()));

		Calendar today = Calendar.getInstance();
		int days = 0;
		int weeks = 0;
		int wdays = 0;
		int dayofweek;
		int month = today.get(Calendar.MONTH);
		int year = today.get(Calendar.YEAR);
		System.out.print("\n" + year + "  ");
		for (int m = 0; m < month; m++)
			System.out.print("    ");
		if ( numbers )
			System.out.printf("  %02d", month + 1);	//	month is zero based
		else
			System.out.printf(" " + fmtMonth.format(today.getTime()));	//	month is zero based
		//if ( month == Calendar.DECEMBER )
		//	System.out.println();
		do	{
			today.add(Calendar.DAY_OF_MONTH, 1);

			days++;

			dayofweek = today.get(Calendar.DAY_OF_WEEK);
			if ( dayofweek == Calendar.MONDAY ||
				dayofweek == Calendar.TUESDAY ||
				dayofweek == Calendar.WEDNESDAY ||
				dayofweek == Calendar.THURSDAY ||
				dayofweek == Calendar.FRIDAY )
				wdays++;

			if ( dayofweek == Calendar.SUNDAY )
				weeks++;

			if ( month != today.get(Calendar.MONTH) )	{
				month = today.get(Calendar.MONTH);
				if ( month == Calendar.JANUARY )
					System.out.print("\n" + today.get(Calendar.YEAR) + "  ");
				if ( numbers )
					System.out.printf("  %02d", month + 1);	//	month is zero based
				else
					System.out.printf(" " + fmtMonth.format(today.getTime()));	//	month is zero based
				//if ( month == Calendar.DECEMBER )
				//	System.out.println();
			}	

		} while (today.getTime().compareTo(retire.getTime()) < 0);
		System.out.println("\n\nremaining days: " + days + ", working days: " + wdays + ", weeks: " + weeks);
	}
}
