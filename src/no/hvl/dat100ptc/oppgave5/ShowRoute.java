package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minLon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double startPunkty = -(gpspoints[0].getLatitude()-minLat)*(ystep())+ybase;
		double startPunktx = (gpspoints[0].getLongitude()-minLon)*xstep()+MARGIN;
		
		for(int i = 0; i < gpspoints.length; i++) {
			double xMinDiff = gpspoints[i].getLongitude() - minLon;
			double yMinDiff = gpspoints[i].getLatitude() - minLat;
			
			double ykor = (-yMinDiff*ystep()) + ybase;
			double xkor = (xMinDiff*xstep() + MARGIN);
			
			int convy = (int)ykor;
			int convx = (int)xkor;
			
			System.out.println(convy + " " + convx);
			
			setColor(255,0,0);
			fillCircle(convx, convy, 2);
	
			drawLine((int)startPunktx, (int)startPunkty, convx, convy);
			startPunkty = convy;
			startPunktx = convx;
		}
        
        
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		double WEIGHT = 80.0;
		
		String[] str = {
		"Total Time         :" + GPSUtils.formatTime(gpscomputer.totalTime()),
		"Total distance     :  " + Math.round(gpscomputer.totalDistance()/1000)*100/100.0 + " km", 
		"Total elevation    :  " + Math.round(gpscomputer.totalElevation()) +" m",
		"Max speed          :  " + Math.round(gpscomputer.maxSpeed()) + " km/t",
		"Average speed      :  " + Math.round(gpscomputer.averageSpeed()) + " km/t",
		"Energy             :  " + Math.round(gpscomputer.totalKcal(WEIGHT)) + " kcal"
		};
	
		
		for (int i=0; i < str.length; i++) {
			drawString(str[i], MARGIN, MARGIN+20*i);
		}
		
		// TODO - SLUTT;
	}

}
