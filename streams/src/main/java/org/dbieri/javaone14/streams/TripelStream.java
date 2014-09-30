package org.dbieri.javaone14.streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dbieri on 30.09.14.
 */
public class TripelStream {

    public static void main(String[] args) {
        TripelStream tripelStream = new TripelStream();
        tripelStream.computeTripel();

    }

    public void computeTripel() {
        long starttime = System.nanoTime();

        //we start with a stream for c
        List<Tripel> list = IntStream.range(5, 5000)
                .parallel()
                .mapToObj(c -> computeTripelB(c))
                .flatMap(l -> l.stream())
                        //.sorted()
                .collect(Collectors.toList());

        long duration = System.nanoTime() - starttime;

        //list.stream().forEach(t -> System.out.println(t));

        Collections.reverse(list);
        list.stream()
                .findFirst()
                .ifPresent(t -> System.out.println(t));

        System.out.println("Finished in " + duration / 1000000000.0 + "s");
    }

    private List<Tripel> computeTripelB(int c) {
        //and now we go over b
        return IntStream.range(1, c)
                .mapToObj(b -> computeTripelA(c, b))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
    }


    private List<Tripel> computeTripelA(int c, int b) {
        int cc = c * c;
        int bb = b * b;
        //and now we go over a
        return IntStream.range(1, b)
                .filter(a -> a * a + bb == cc)
                .mapToObj(a -> new Tripel(a, b, c))
                .collect(Collectors.toList());
    }

    private class Tripel implements Comparable<Tripel> {
        int a;
        int b;
        int c;

        public Tripel(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Tripel{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }

        public int compareTo(Tripel tOther) {
            int ret = 0;
            ret = a - tOther.a;
            if (ret == 0) {
                ret = b - tOther.b;
            }
            if (ret == 0) {
                ret = c - tOther.c;
            }
            return ret;
        }
    }
}
