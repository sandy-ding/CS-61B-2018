public class NBody {
	private static String bg = "images/starfield.jpg";

	public static void main(String[] args){
		// read args
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] bodies = readPlanets(filename);

		// draw bg
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		draw(bodies);

		// update time
		for (double t = 0; t <= T; t += dt) {
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for (int i = 0; i < bodies.length; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			draw(bodies);
			StdDraw.pause(10);
		}

		// print final status
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
					bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}

	public static void draw(Planet[] bodies) {
		StdDraw.clear();
		StdDraw.picture(0, 0, bg);

		// draw bodies
		for (Planet body: bodies) {
			body.draw();
		}
		StdDraw.show();
	}

	public static double readRadius(String fileName){
		In in = new In(fileName);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[numOfPlanets];

		for(int i = 0; i < numOfPlanets; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}			
		
		return planets;
	}
}