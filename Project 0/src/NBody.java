public class NBody {
    public static double readRadius(String filename){
        In file = new In(filename);
        int numberPlanet = file.readInt();
        return file.readDouble();
    }
    public static Body[] readBodies(String filename){
        In file = new In(filename);
        int numberPlanet = file.readInt();
        file.readDouble();
        Body[] planet = new Body[numberPlanet];
        int counting = 0;
        while (counting != numberPlanet){
            double xP = file.readDouble();
            double yP = file.readDouble();
            double xV = file.readDouble();
            double yV = file.readDouble();
            double m = file.readDouble();
            String img = file.readString();
            planet[counting] = new Body(xP, yP, xV, yV, m, img);
            counting += 1;
        }
        return planet;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] planets = NBody.readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        String background = "images/starfield.jpg";
        StdDraw.picture(0, 0, background);
        for (Body x : planets){
            x.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i += 1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int k = 0; k < planets.length; k += 1){
                planets[k].update(dt, xForces[k], yForces[k]);
            }
            StdDraw.picture(0, 0, background);
            for (Body x : planets){
                x.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e", radius);
        for (int i = 0; i < planets.length; i += 1){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFilename);
        }
    }

}
