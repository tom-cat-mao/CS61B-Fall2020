public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readDouble();
        return in.readDouble();
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[N];

        for (int i = 0; i < N; i++) {
            bodies[i] = new Body(
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readString()
            );
        }
        return bodies;
    }

    public static void main(String[] args) {
        /* convert the first two command words into double
         * T: the total time
         * dt: the time interval
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        /* 2nd command line argument as a String named filename */
        String filename = args[2];

        /* read in the bodies and the univers radius
         * from the file descrbed by filename
         * using the former method
         */
        double Uni_Radius = readRadius(filename);
        Body[] Bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdAudio.play("audio/2001.mid");
        
        /* create an animation */
        double time = 0;
        while (time < T) {
            /* calculate the force in x,y
            * and then update the position
            */
            double[] xForces = new double[Bodies.length];
            double[] yForces = new double[Bodies.length];
            for (int i = 0; i < xForces.length; i++) {
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
            }
            
            for (int i = 0; i < Bodies.length; i++) {
                Bodies[i].update(dt, xForces[i], yForces[i]);
            }

            /* set the scale of the universe */
            StdDraw.setScale(-Uni_Radius, Uni_Radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            /* draw all the solar planets
            * in the universe
            */
            for (Body b : Bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            
            time += dt;
        }
        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", Uni_Radius);
        for (int i = 0; i < Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
            Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);   
        }


    }
}
