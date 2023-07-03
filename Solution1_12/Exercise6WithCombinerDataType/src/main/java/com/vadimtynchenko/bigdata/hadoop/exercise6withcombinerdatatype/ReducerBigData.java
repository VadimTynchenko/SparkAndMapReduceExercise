package com.vadimtynchenko.bigdata.hadoop.exercise6withcombinerdatatype;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, MinMaxWritable, Text, MinMaxWritable> {
    @Override
    protected void reduce(Text key, Iterable<MinMaxWritable> values, Reducer<Text, MinMaxWritable, Text, MinMaxWritable>.Context context) throws IOException, InterruptedException {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (MinMaxWritable value : values) {
            if (value.getMin() < min) {
                min = value.getMin();
            }
            if (value.getMax() > max) {
                max = value.getMax();
            }
        }

        MinMaxWritable minMax = new MinMaxWritable();
        minMax.setMin(min);
        minMax.setMax(max);

        context.write(key, minMax);
    }
}
