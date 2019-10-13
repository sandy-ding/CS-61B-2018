public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet b){
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    public double calcDistance(Planet B){
        double distanceSquare = Math.pow(this.xxPos - B.xxPos, 2) + Math.pow(this.yyPos - B.yyPos, 2);
        return Math.sqrt(distanceSquare);
    }

    public double calcForceExertedBy(Planet B){
        double distanceSquare = Math.pow(this.calcDistance(B), 2);
        return G * this.mass * B.mass / distanceSquare;
    }

    public double calcForceExertedByX(Planet B){
        double d = calcDistance(B);
        double f = calcForceExertedBy(B);
        double fx = f * (B.xxPos - this.xxPos) / d;
        return fx;
    }

    public double calcForceExertedByY(Planet B){
        double d = calcDistance(B);
        double f = calcForceExertedBy(B);
        double fy = f * (B.yyPos - this.yyPos) / d;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] aB){
        double nfx = 0;
        for (Planet B : aB){
            if (!this.equals(B)){
                nfx += calcForceExertedByX(B);
            }
        }
        return nfx;
    }

    public double calcNetForceExertedByY(Planet[] aB){
        double nfy = 0;
        for (Planet B : aB){
            if (!this.equals(B)){
                nfy += calcForceExertedByY(B);
            }
        }
        return nfy;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}
