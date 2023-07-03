package com.vadimtynchenko.bigdata.hadoop.exercise6withcombinerdatatype;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CombinerBigData extends Reducer<Text, MinMaxWritable, Text, MinMaxWritable> {
    @Override
    protected void reduce(Text key, Iterable<MinMaxWritable> values, Reducer<Text, MinMaxWritable, Text, MinMaxWritable>.Context context) throws IOException, InterruptedException {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (MinMaxWritable value : values) {
            if (value.getMax() > max) {
                max = value.getMax();
            }
            if (value.getMin() < min) {
                min = value.getMin();
            }
        }

        MinMaxWritable minMax = new MinMaxWritable();
        minMax.setMax(max);
        minMax.setMin(min);

        context.write(key, minMax);
    }
}
