package uk.co.savvydatainsights;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.SPACE;

public class WordCount {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: WordCount <file>");
            System.exit(1);
        }
        SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);

        JavaRDD<String> textFile = ctx.textFile(args[0], 1);
        JavaPairRDD<String, Integer> counts = textFile
            .flatMap(s -> Arrays.asList(s.split(SPACE)).iterator())
            .mapToPair(word -> new Tuple2<>(word, 1))
            .reduceByKey((a, b) -> a + b);

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<String, Integer> tuple : output) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }

        if (args.length > 1) {
            counts.saveAsTextFile(args[1]);
        }
        ctx.stop();
    }

}
