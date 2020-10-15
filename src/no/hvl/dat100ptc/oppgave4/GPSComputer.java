package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for (int i = 0; i < gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
		
		return distance;
	

		// TODO - SLUTT

	}
	

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		double [] elevations = new double [gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			elevations[i] = gpspoints[i].getElevation();
		}

		for(int i = 1; i < gpspoints.length; i++) {
			if(elevations[i-1] < elevations[i]) {
				elevation += elevations[i] - elevations[i-1];
			}
		}

		return elevation;

		// TODO - SLUTT

	}
	
	// beregn stigningsprosent på deler av ruten
		public double[] climbs() {
		
			//Hypotenusen. Meter mellom punkt, og høgdeforskjell mellom to punkt
	        //gpspoints[].getElevation();
	        //GPSUtils.distance(gpspoints[i], gpspoints[i+1]);

	        double[] stigningsprosent = new double[gpspoints.length];
	        double[] elevasjon = new double[gpspoints.length];
	        double[] lengde = new double[gpspoints.length-1];

	        for(int i = 0; i < gpspoints.length; i++) {
	            elevasjon[i] = gpspoints[i].getElevation();
	        }
	        for(int i = 0; i < gpspoints.length-1; i++) {
	            lengde[i] = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
	        }
	        for(int i = 0; i < gpspoints.length; i++) {
	            stigningsprosent[i] = (elevasjon[i]/lengde[i])*100;
	        }

	        return stigningsprosent;
		
		}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int time = 0;

        double[] timer = new double [gpspoints.length];
        for (int i = 0; i < gpspoints.length; i++) {
            timer[i] = gpspoints[i].getTime();
        }

        for(int i = 1; i < gpspoints.length; i++) {
            time += timer[i]-timer[i-1];
        }


        return time;
    	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double[] gjnSpeed = new double [gpspoints.length-1];
        for (int i = 0; i < gpspoints.length-1; i++) {
            gjnSpeed[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
        }

        return gjnSpeed;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;

        // TODO - START

        double[] maksFart = new double [gpspoints.length-1];
        maksFart = speeds();
        maxspeed = maksFart[0];

        for (int i = 0; i < maksFart.length; i++) {
            if (maxspeed < maksFart[i]) {
                maxspeed = maksFart[i];
            }
        }

        return maxspeed;

		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
		average = totalDistance() / totalTime()*3.6;
		
		return average;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)

		// TODO - START
		
		double met = 0;		
		double speedmph = speed * MS*0.62;
		
		// TODO - START
		
		if(speedmph<10) {
			met = 4.0;
		}
		else if (speedmph<12) {
			met = 6.0;
		}
		else if (speedmph<14) {
			met = 8.0;
		}
		else if (speedmph<16) {
			met = 10.0;
		}
		else if (speedmph<20) {
			met = 12.0;
		}
		else if (speedmph>20) {
			met = 16.0;
		}
		
		kcal = met*weight*(secs/3600.0);
		
		return kcal;
		
		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		totalkcal = kcal(weight, totalTime(), averageSpeed());
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println("Total Time	:" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance	:	" + Math.round(totalDistance()/1000)*100/100.0 + " km");
		System.out.println("Total elevation	:	" + Math.round(totalElevation()) +" m");
		System.out.println("Max speed	:	" + Math.round(maxSpeed()) + " km/t");
		System.out.println("Average speed	:	" + Math.round(averageSpeed()) + " km/t");
		System.out.println("Energy		:	" + Math.round(totalKcal(WEIGHT)) + " kcal");
		System.out.println("==============================================");
				
		// TODO - SLUTT
		
	}

}
