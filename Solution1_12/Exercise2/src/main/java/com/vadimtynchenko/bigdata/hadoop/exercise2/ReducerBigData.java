package com.vadimtynchenko.bigdata.hadoop.exercise2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int occurrences = 0;

        for (IntWritable value : values) {
            occurrences = occurrences + value.get();
        }

        context.write(key, new IntWritable(occurrences));
    }
}
