import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

	public static double readRadius(String fname){
		File file = new File(fname);

		try {
			Scanner sc = new Scanner(file);
			sc.nextDouble();
			return sc.nextDouble();
		}


		catch (FileNotFoundException e) { 
			e.printStackTrace();
			return 0; //fix this
		}


	}

	public static Planet[] readPlanets(String fname){ // given a file name, return an array of Planets corresponding to the planets in the file,
		File file = new File(fname);
		try{
			Scanner sc = new Scanner(file);
			Planet [] size = new Planet [sc.nextInt()]; //should be first number in file
			sc.nextDouble();
			int counter=0;
			int x =0;
			while (x<size.length){
				double xPosition = sc.nextDouble();
				double yPosition = sc.nextDouble();
				double xVel = sc.nextDouble();
				double yVel = sc.nextDouble();
				double Mass = sc.nextDouble();
				String Name = sc.next();

				size[counter]=new Planet(xPosition, yPosition,xVel,yVel,Mass,Name);
				counter++;
				x++;

			}
			
			return size;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("found error");
			Planet [] num = new Planet [1];
			return num; 
		}
	}

	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";


		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Planet[] planets = readPlanets(pfile);
		double radius = readRadius(pfile);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
		for(int p = 0; p< planets.length;p++){
			planets[p].draw();
		}
		int time = 0;
		while ( time <T){ 
			double [] xForces = new double [planets.length]; 
			double [] yForces = new double [planets.length];
			
			int parseForce = 0;
			while(parseForce<xForces.length){
				xForces[parseForce]=planets[parseForce].calcNetForceExertedByX(planets); //creating new arrays for each timestep
				yForces[parseForce]=planets[parseForce].calcNetForceExertedByY(planets);
				parseForce++;
			}


			int r=0; 
			while (r<xForces.length){
				planets[r].update(dt, xForces[r], yForces[r]);
				r++;
			}
			
			StdDraw.picture(0.0, 0.0, "images/starfield.jpg");


			int x = 0;
			while(x<planets.length){
				planets[x].draw();
				x++;
			}
			StdDraw.show(10); //smoooth visuals
			time+=dt;
		}



		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].myXPos, planets[i].myYPos, 
					planets[i].myXVel, planets[i].myYVel, 
					planets[i].myMass, planets[i].myFileName);	
		}
		NBody.readPlanets("./data/planets.txt");
	}
}
