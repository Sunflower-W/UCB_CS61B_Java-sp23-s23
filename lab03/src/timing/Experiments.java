package timing;

import edu.princeton.cs.algs4.Stopwatch;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class Experiments {

    private static void printTimingTable(TimingData data) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < data.getNs().size(); i += 1) {
            int N = data.getNs().get(i);
            double time = data.getTimes().get(i);
            int opCount = data.getOpCounts().get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    /** Computes the nth Fibonacci number using a slow naive recursive strategy.*/
    private static int fib(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static TimingData exampleFibonacciExperiment() {
        List<Integer> Ns = new ArrayList<>(); // The size of the data structure, or how many elements it contains.
        List<Double> times = new ArrayList<>(); // The total time required for all operations, in seconds.
        List<Integer> opCounts = new ArrayList<>(); // The number of operations made during the experiment.

        // We're computing each fibonacci number 100 times to get a more stable number
        int ops = 100;

        for (int N = 10; N < 31; N++) {
            Ns.add(N);
            opCounts.add(ops);
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < ops; j++) { //计算10-30间每个fib数100此所需要的时间
                int fib = fib(N);
            }
            times.add(sw.elapsedTime());
        }

        return new TimingData(Ns, times, opCounts);
    }

    public static TimingData timeAListConstruction() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        // TODO: YOUR CODE HERE
        for (int N = 1000; N <= 128000; N = N * 2) {
//        for (int N = 1000; N <= 10000000; N = N * 2) {
            Ns.add(N);
            opCounts.add(N);
            Stopwatch sw = new Stopwatch();
            AList lst = new AList();
//            Item[] lst = (Item[]) new Object();
            for (int i = 0; i < N; i++) {
                lst.addLast(0);
//                lst.size() += 1;
            }
            times.add(sw.elapsedTime());
        }

        return new TimingData(Ns, times, opCounts);
    }


    public static TimingData timeSLListGetLast() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();

        // TODO: YOUR CODE HERE
        int M = 10000;
        for (int N = 1000; N <= 128000; N = N * 2) {
            SLList lst = new SLList();
            for (int i=0; i < N; i ++) {
                lst.addLast(0);
            }
            Ns.add(N);
            opCounts.add(M);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < M; i++) {
                lst.getLast();
            }
            times.add(sw.elapsedTime());
        }

        return new TimingData(Ns, times, opCounts);
    }

    public static void main(String[] args) {
        // TODO: Modify the following line to change the experiment you're running
//        TimingData td = exampleFibonacciExperiment();
//        TimingData td = timeAListConstruction();
        TimingData td = timeSLListGetLast();
        // Modify this line to make the chart title make sense
//        String title = "Naive Recursive Fibonacci";
//        String title = "Naive Recursive AList";
        String title = "Naive Recursive SLList";

        // Convert "times" (in seconds) and "opCounts" to nanoseconds / op
        List<Double> timesUsPerOp = new ArrayList<>();
        for (int i = 0; i < td.getTimes().size(); i++) {
            timesUsPerOp.add(td.getTimes().get(i) / td.getOpCounts().get(i) * 1e6);
        }

        printTimingTable(td);

        XYChart chart = QuickChart.getChart(title, "N", "time (us per op)", "Time", td.getNs(), timesUsPerOp);
        new SwingWrapper(chart).displayChart();
    }
}
