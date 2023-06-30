package com.vadimtynchenko.bigdata.hadoop.exercise6;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, FloatWritable, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Reducer<Text, FloatWritable, Text, Text>.Context context) throws IOException, InterruptedException {
        float max = Float.MIN_VALUE;
        float min = Float.MAX_VALUE;

        for (FloatWritable value : values) {
            if (value.get() > max) {
                max = value.get();
            }
            if (value.get() < min) {
                min = value.get();
            }
        }
        context.write(key, new Text("max=" + max + "_min=" + min));
    }
}
