package com.seigneurin.spark;

import org.apache.spark.api.java.JavaSparkContext;

public class Chess {

    public static void main(String[] args) {

        JavaSparkContext sc = new JavaSparkContext("local[16]", "chess");

        long start = System.currentTimeMillis();

        sc.textFile("/Users/aseigneurin/Downloads/ChessData-master/*/*.pgn")
                .filter(line -> line.startsWith("[Result ") && line.contains("-"))
                .map(res -> res.substring(res.indexOf("\"") + 1, res.indexOf("-")))
                .filter(res -> res.equals("0") || res.equals("1") || res.equals("1/2"))
                .countByValue()
                .entrySet()
                .stream()
                .forEach(s -> System.out.println(s.getKey() + " -> " + s.getValue()));

        long duration = System.currentTimeMillis() - start;
        System.out.println("Duration: " + duration + " ms");

        sc.close();
    }
}
