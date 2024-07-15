public class NBody {
    public static  double readRadius(String filename) {
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
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
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
        
    }

}