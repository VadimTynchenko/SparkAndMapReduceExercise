package com.vadimtynchenko.bigdata.hadoop.exercise3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int numDays = 0;

        for (IntWritable value : values) {
            numDays += value.get();
        }
        context.write(key, new IntWritable(numDays));
    }
}
