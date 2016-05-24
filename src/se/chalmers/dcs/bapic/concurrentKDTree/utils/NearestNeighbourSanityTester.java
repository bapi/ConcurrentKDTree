///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package se.chalmers.dcs.bapic.concurrentKDTree.utils;
//
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import se.chalmers.dcs.bapic.concurrentKDTree.KDTrees.*;
//
///**
// *
// * @author walulya
// */
//public class NearestNeighbourSanityTester<T> {
//
//    private boolean linearizable;
//    private KDTreeADT<T> kdt;
//
////
////    public enum TSTDATASET {
////
////        CUBE,
////        CLUSTER,
////        TIGER,
////    }
////
////    public enum TSTIDX {
////
////        KD_LEVY,
////        KD_SAVA,
////        KDLFTREE,
////    }
////    public static class TestStats implements Serializable, Cloneable {
////
////        public TestStats(TSTDATASET test, TSTIDX index, int N, int DIM, int DEPTH, boolean isRangeData,
////                double param1) {
////            this(test, index, N, DIM, DEPTH, isRangeData, param1, 0);
////        }
////
////        public TestStats(TSTDATASET test, TSTIDX index, int N, int DIM, int DEPTH, boolean isRangeData,
////                double param1, double param2) {
////            this.statNEntries = N;
////            this.statNDims = DIM;
////            this.statNBits = DEPTH;
////            this.INDEX = index;
////            this.TEST = test;
////            this.SEED = "" + SEED;
////            this.isRangeData = isRangeData;
////            this.param1 = param1;
////            this.param2 = param2;
////        }
////        public TSTIDX INDEX;
////        public final TSTDATASET TEST;
////        String SEED;
////        final double param1;
////        double param2;
////        final boolean isRangeData;
////
////        int statNnodes;
////        int statNBits;
////        final int statNDims;
////        int statNEntries;
////
////        private Throwable exception = null;
////
////        public void setFailed(Throwable t) {
////            SEED = SEED + "-F";
////            exception = t;
////        }
////
////        @Override
////        protected Object clone() throws CloneNotSupportedException {
////            return super.clone();
////        }
////
////        public TestStats cloneStats() {
////            try {
////                return (TestStats) clone();
////            }
////            catch (CloneNotSupportedException e) {
////                throw new RuntimeException(e);
////            }
////        }
////
////        public void setN(int N) {
////            statNEntries = N;
////        }
////
////        public int getN() {
////            return statNEntries;
////        }
////    }
//    private static void test(KDTreeADT kdt, int repeat, int DIM, int DEPTH, int N, double param1, int numQuery) {
//        for (int i = 0; i < repeat; i ++) {
//            test(kdt, DIM, DEPTH, N, i, false, param1, numQuery);
//        }
//    }
//
//    private static void test(KDTreeADT kdt, int DIM, int DEPTH, int N, long SEED,
//            boolean isRangeData, double param1, int numQuery) {
//        TestStats s0 = new TestStats(TEST, INDEX, N, DIM, DEPTH, isRangeData, param1);
//        //We use two different TestStats instances here, because the 2nd is returned from RMI.
//        TestStats s = runTest(s0, SEED, numQuery);
//
//    }
//
////    static TestStats runTest(TestStats stats, long SEED, int numQuery) {
////        return testWarm(stats, SEED, numQuery);
////    }
//    private static TestStats testWarm(TestStats stats, long SEED, int numQuery) {
//        //clean-up
//        long memTree = Tools.getMemUsed();
//        Tools.cleanMem(stats.getN(), memTree);
//
//        System.out.println("\u001B[34m" + stats.getN() + " Testrunner: starting task: " + new Date());
//        TestStats s = null;
//        Execution_Main testExec = new Execution_Main(stats, SEED, numQuery);
//        try {
//            s = testExec.run(stats, SEED);
//        }
//        catch (Throwable t) {
//            System.err.println("Failed " + t.toString());
//            t.printStackTrace();
//
//            s = testExec.getTestStats();
//            //s.setFailed(t);
//        }
//        System.out.println("\u001B[35m" + "Testrunner: Task finished: " + new Date());
//        return s;
//    }
//
//    public static class Execution_Main {
//
////        private final TestStats S;
//        private class NeigbourR {
//
//            double[][] queryKeys;
//            ArrayList<double[]> neighArrayList;
//
//            public NeigbourR(double[][] queryKeys_, ArrayList<double[]> neighArrayList_) {
//                this.queryKeys = queryKeys_;
//                this.neighArrayList = neighArrayList_;
//            }
//
//        }
//
//        private Random R;
//
//        private static int N_RANGE_QUERY; //number of range queries
//
//        private double[] data;
//        private KDTreeADT tree;
//
//        /**
//         * Edge length of the populated data area.
//         */
//        //private final double LEN = (1L<<31)-1;
//        //private final double LEN = 1000.0;
//        private final double LEN = 1.0;
//
////        private static final boolean DEBUG = true;
////        private final int DEPTH;
//        private final int DIM;
//        private final int N;
//
////        private TSTIDX INDEX;
////        private TSTDATASET TEST;
//        public Execution_Main(TestStats S, long SEED, int numQuery) {
//            this.S = S;
//            this.TEST = S.TEST;
//            this.INDEX = S.INDEX;
//            this.DIM = S.statNDims;
//            this.DEPTH = S.statNBits;
//            this.N = S.statNEntries;
//            this.R = new Random(SEED);
//            this.N_RANGE_QUERY = numQuery;
//            //this.IS_POINT_DATA = !S.isRangeData;
//        }
//
//        private void load(TSTDATASET TEST, TSTIDX INDEX, int N, int DIM, int DEPTH) {
//            log(time() + " generating data ..." + N);
//            long t1g = System.currentTimeMillis();
//
//            switch (TEST) {
//                case CUBE:
//                    data = generatePointCube(S);
//                    break;
//                case CLUSTER:
//                    break;
//                case TIGER:
//                    break;
//            }
//
//            long memTree = Tools.getMemUsed();
//            Tools.cleanMem(N, memTree);
//            log(time() + " loading index ..." + INDEX);
//
//            memTree = Tools.getMemUsed();
//            long t1 = System.currentTimeMillis();
//            createPointTree(S);
//
//            int loadedItems = treeLoad(data, DIM);
//
//            long t2 = System.currentTimeMillis();
//            log(INDEX + " loading finished in: " + (t2 - t1) + " [ms]");
//            Tools.cleanMem(loadedItems, memTree);
//
//            log("loaded objects: " + loadedItems + " " + data[0]);
//        }
//
//        private int treeLoad(double[] data, int idxDim) {
//
//            int itemsAdded = 0;
//            for (int i = 0; i < N; i ++) {
//                double[] key = new double[idxDim];
//                for (int d = 0; d < idxDim; d ++) {
//                    key[d] = data[i * idxDim + d];
//                }
//                try {
//                    if (tree.add(key, key)) {
//                        itemsAdded ++;
//                    }
//                }
//                catch (DimensionLimitException ex) {
//                    Logger.getLogger(NearestNeighbourSanityTester.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            return itemsAdded;
//        }
//
//        public double[] generatePointCube(TestStats S) {
//            log("Running TestPointCube Generate: TestSkewed");
//            double[] data = new double[S.statNDims * S.statNEntries];
//
//            double lcl = LEN / Math.pow(LEN, S.param1);
//            for (int i = 0; i < N; i ++) {
//                int pos = S.statNDims * i;
//                for (int d = 0; d < S.statNDims; d ++) {
//                    data[pos + d] = R.nextDouble();
//                    if (d == 1) {
//                        data[pos + d] = LEN * lcl * Math.pow(data[pos + d], S.param1);
//                    }
//                    else {
//                        data[pos + d] *= LEN;
//                    }
//                }
//            }
//            return data;
//        }
//
//        private void createPointTree(TestStats S) {
//            tree = new KDTreeLevyWrapper(S.statNDims);
//            if (tree == null) {
//                throw new UnsupportedOperationException("No Set ADT chosen!!!!!!.");
//            }
//
//        }
//
//        private NeigbourR repeatNearestNeighbour(int repeat) {
//
//            log("N=" + N);
//            log("\u001B[32m" + time() + " NearestNeigbour Search ... repeat = " + repeat);
//            long t1 = System.currentTimeMillis();
//            double[][] queryKeys = preparePointQuery(repeat);
//            ArrayList<double[]> neighArrayList = new ArrayList();
//            int n = 1;
//            int i = 0;
//            for (double[] q : queryKeys) {
//                try {
//
//                    double[] neigh = (double[]) tree.nearest(q);
//                    if (neigh == null) {
//                        System.err.println("");
//                    }
//                    // System.out.println("Searchkey " + Arrays.toString(q) + "\tNeigh " + Arrays.toString(neigh));
//                    neighArrayList.add(neigh);
//                }
//                catch (KeySizeException ex) {
//                    Logger.getLogger(NearestNeighbourSanityTester.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                i ++;
//            }
//            long t2 = System.currentTimeMillis();
//            log(tree.getClass().toString() + " Query time: " + (t2 - t1) + " ms -> " + (t2 - t1) / (double) repeat + " ms/q -> "
//                + (t2 - t1) * 1000 * 1000 / (double) n + " ns/q/r  (n=" + n + ") Neighbours=" + neighArrayList.size());
//            return new NeigbourR(queryKeys, neighArrayList);
//        }
//
//        double[][] preparePointQuery(int repeat) {
//            double[][] qA;
//            // this.R = new Random(3);
//            qA = new double[repeat][];
//            for (int i = 0; i < repeat; i ++) {
//                qA[i] = generateQueryPointD(N, DIM);
//            }
//            return qA;
//        }
//
//        private double[] generateQueryPointD(final int N, final int DIM) {
//            double[] xyz = new double[DIM];
//            int pos = R.nextInt(N);
//            for (int d = 0; d < DIM; d ++) {
//                xyz[d] = R.nextDouble() * LEN;
//            }
//            return xyz;
//        }
//
//        public TestStats getTestStats() {
//            return S;
//        }
//    }
//
//    public void run(long SEED) {
//        Random R = new Random(SEED);
//        load(stats.TEST, stats.INDEX, stats.statNEntries, stats.statNDims, stats.statNBits);
//
//        NeigbourR resuNeigbourR = repeatNearestNeighbour(N_RANGE_QUERY);
//
//        tree = null;
//        long memTree = Tools.getMemUsed();
//        Tools.cleanMem(memTree);
//
//        stats.INDEX = TSTIDX.KD_LEVY;
//        this.INDEX = TSTIDX.KD_LEVY;
//        this.R = new Random(SEED);
//
//        load(stats.TEST, stats.INDEX, stats.statNEntries, stats.statNDims, stats.statNBits);
//
//        NeigbourR resuNeigbourLevy = repeatNearestNeighbour(N_RANGE_QUERY);
//
//        int i = 0, countDiff = 0;
//        for (double[] e : resuNeigbourR.neighArrayList) {
//
//            if ( ! Arrays.equals(e, resuNeigbourLevy.neighArrayList.get(i))) {
//                System.out.println("\u001B[31m" + Arrays.toString(resuNeigbourR.queryKeys[i]) + "\t=> "
//                                   + Arrays.toString(e) + "\t=>\t"
//                                   + Double.toString(Tools.squaredDistance(e, resuNeigbourR.queryKeys[i])));
//
//                System.out.println("\u001B[32m" + Arrays.toString(resuNeigbourLevy.queryKeys[i]) + "\t=> "
//                                   + Arrays.toString(resuNeigbourLevy.neighArrayList.get(i)) + "\t=> "
//                                   + Double.toString(Tools.squaredDistance(resuNeigbourLevy.neighArrayList.get(i), resuNeigbourLevy.queryKeys[i])));
//
//                countDiff ++;
//            }
//
//            //System.out.println("\u001B[31m" + Arrays.toString(resuNeigbourLevy.neighArrayList.get(i)));
//            //System.out.println("\u001B[36m" +Arrays.toString(e));
//            i ++;
//        }
//        System.out.println("\u001B[36mDifferent Results " + countDiff + "/" + N_RANGE_QUERY);
//        return null;
//    }
//
//    static void log(String string) {
//        System.out.println(string);
//    }
//
//    public static void testManage(int treesize, int numQuery, KDTreeADT kdt, int dimension, boolean linearizable) {
//        test(kdt, 1, TSTDATASET.CUBE, TSTIDX.KDLFTREE, dimension, 64, treesize, 1.0, numQuery);
//    }
//}
