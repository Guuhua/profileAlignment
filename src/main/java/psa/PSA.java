package psa;

import index.Alphabet;

public class PSA {

    public static String[] SeqAlign(String A, String B) {
        double diff = getDiff(A.length(), B.length());
        if (diff > 0.4) {
            DP dp = new DP(A, B);
            return dp.getStrAlign();
        } else if (diff > 0.1) {
            Kband kd = new Kband(A, B);
            return  kd.getStrAlign();
        } else {
            FMAlign fmAlign = new FMAlign(A, B);
            return fmAlign.getStrAlign();
        }
    }

    public static String[] PrfAlign(String[] A, String[] B) {
        if (A.length == 1 && B.length == 1) {
            return SeqAlign(A[0], B[0]);
        }
        char[] alphabet = Alphabet.Counter(A);
        double diff = getDiff(A[0].length(), B[0].length());
        if (diff > 0.4) {
            multiDP mdp = new multiDP(A, B, alphabet);
            return mdp.getStrsAlign();
        } else if (diff > 0.1) {
            multiKband mkd = new multiKband(A, B, alphabet);
            return  mkd.getStrsAlign();
        } else {
            FastMultiAlign fma = new FastMultiAlign(A, B, alphabet, 0, 0);
            return fma.getStrsAlign();
        }
    }

    private static double getDiff(int l1, int l2) {
        return (double) Math.abs(l1 - l2) / Math.max(l1, l2);
    }

}
