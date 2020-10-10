package no.hvl.dat100ptc.oppgave3;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double [] latitudes = new double [gpspoints.length];
		
		for (int i=0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		  }
		
		return latitudes;

		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double [] longitudes = new double [gpspoints.length];
		
		for (int i=0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		  }
		
		return longitudes;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();

		// til radianer
		double la1 = Math.toRadians(latitude1);
		double la2 = Math.toRadians(latitude2);
		double lo1 = Math.toRadians(longitude1);
		double lo2 = Math.toRadians(longitude2);
		
		double lat = la2 - la1;
		double lon = lo2 - lo1;
		
		double a = Math.pow(Math.sin(lat/2),2) + Math.cos(la1) * Math.cos(la2) * Math.pow(Math.sin(lon/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = R * c; 
		
		return d;
		
		// TODO - SLUTT

		
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double speed;

		// TODO - START

		int time1 = gpspoint1.getTime();
		int time2 = gpspoint2.getTime();
		int secs = time2 - time1; 
		
		speed = ((distance(gpspoint1, gpspoint2)/secs)*3.6);
		
		return speed;
		
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		
		 
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
