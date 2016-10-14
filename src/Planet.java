
public class Planet { //instance var created outside constructor and initialized inside.



	double myXPos;
	double myYPos;
	double myXVel; 
	double myYVel; 
	double myMass;
	public static final double G = 6.67E-11;
	String myFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		myXPos=xP;
		myYPos=yP;
		myXVel=xV; 
		myYVel=yV; 
		myMass=m;
		myFileName=img;


	} //constructor

	public Planet(Planet p) {
		myXPos=p.myXPos;
		myYPos=p.myYPos;
		myXVel=p.myXVel; 
		myYVel=p.myYVel; 
		myMass=p.myMass;
		myFileName=p.myFileName;

	} //constructor -- figure out why there are two.

	public double calcDistance(Planet p){
		return Math.sqrt(((this.myXPos - p.myXPos)*(this.myXPos - p.myXPos))+((this.myYPos - p.myYPos)*(this.myYPos - p.myYPos))); // distance formula

	}

	public double calcForceExertedBy(Planet p){ //returns a double describing the force exerted on this planet by the given planet (call calcDistance)	
		return ((G * p.myMass * this.myMass)) / (Math.pow(this.calcDistance(p), 2));
	}

	public double calcForceExertedByX(Planet p){ // returns the force exerted in the X direction


		return ((((G * p.myMass * this.myMass)) / (Math.pow(this.calcDistance(p), 2)))*((p.myXPos-this.myXPos)))/(this.calcDistance(p));


	}	

	public double calcForceExertedByY(Planet p){ // returns the force exerted in the Y direction

		return ((((G * p.myMass * this.myMass)) / (Math.pow(this.calcDistance(p), 2)))*((p.myYPos-this.myYPos)))/(this.calcDistance(p)); 
	}

	public double calcNetForceExertedByX(Planet allPlanets []){ //sum of the pairwise forces acting on the particle in that direction.
		//f==ma a==f/m
		double sum = 0; 
		for (int x =0; x<allPlanets.length; x++){
			if (! allPlanets[x].equals(this)) {
				sum += this.calcForceExertedByX(allPlanets[x]); 
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet []allPlanets){
		double sum = 0; 
		for (int x =0; x<allPlanets.length; x++){
			if (! allPlanets[x].equals(this)) {
				sum += this.calcForceExertedByY(allPlanets[x]); 
			}
		}
		return sum;
	}

	public void update(double dt, double fX, double fY){ //update this.myvel and mypos..etc
		//acc = force/mass
		//velo = dt*acc
		//pos = dt*vel

		double aNetX = fX/this.myMass; // acceleration describes the change in velocity per unit time
		double aNetY = fY/this.myMass;
		this.myXVel+=(aNetX*dt); //4
		this.myYVel+= (aNetY*dt); 
		this.myXPos += (this.myXVel*dt); //9
		this.myYPos += (this.myYVel*dt);
		//System.out.println(fX);System.out.println(dt);System.out.println(this.myXPos);


	}

	public void draw(){
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
