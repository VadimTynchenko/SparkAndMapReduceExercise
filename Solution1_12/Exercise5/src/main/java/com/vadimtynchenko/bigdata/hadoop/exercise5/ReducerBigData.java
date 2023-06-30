package com.vadimtynchenko.bigdata.hadoop.exercise5;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Reducer<Text, FloatWritable, Text, FloatWritable>.Context context) throws IOException, InterruptedException {
        int count = 0;
        float sum = 0;

        for (FloatWritable value :
                values) {
            sum += value.get();
            count += 1;
        }
        context.write(key, new FloatWritable(sum/count));
    }
}
