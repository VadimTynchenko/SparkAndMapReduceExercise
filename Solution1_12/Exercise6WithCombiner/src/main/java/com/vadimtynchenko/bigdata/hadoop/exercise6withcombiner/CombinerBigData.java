package com.vadimtynchenko.bigdata.hadoop.exercise6withcombiner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CombinerBigData extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Text value :
                values) {
            String[] fields = value.toString().split("_");
            if (Double.parseDouble(fields[0]) > max) {
                max = Double.parseDouble(fields[0]);
            }
            if (Double.parseDouble(fields[1]) < min) {
                min = Double.parseDouble(fields[1]);
            }
        }

        context.write(new Text(key), new Text(min + "_" + max));
    }
}
