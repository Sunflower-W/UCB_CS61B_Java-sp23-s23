package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
//            b.phase0("Figure this out. I wonder where the phases are defined...");
            b.phase0("39291226");
        }
        if (phase >= 1) {
//            b.phase1(null); // Figure this out too
            b.phase1(IntList.of(0, 9, 3, 0, 8)); // Figure this out too
        }
        if (phase >= 2) {
//            b.phase2("Figure this out. I wonder where the phases are defined...");
            String password = ""; // You'll need constructed a sufficiently `String` so that 1337 is a valid index.
            for(int i = 0; i < 1338; i++) {
                if (i != 1337) {
                    password = password.concat(i + " ");
                }
                else {
                    password = password.concat("-81201430");
                }
            }
            b.phase2(password); // You don't necessarily need to construct the passwprd
        }
    }
}
