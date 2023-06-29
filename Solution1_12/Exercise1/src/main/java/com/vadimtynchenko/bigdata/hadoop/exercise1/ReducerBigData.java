package com.vadimtynchenko.bigdata.hadoop.exercise1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class ReducerBigData extends Reducer<
        Text,           // Input key type
        IntWritable,    // Input value type
        Text,           // Output key type
        IntWritable     // Output value type
        > {
    @Override
    protected void reduce(
            Text key,                       // Input key type
            Iterable<IntWritable> values,   // Input value type
            Reducer<Text, IntWritable, Text, IntWritable>.Context context
    ) throws IOException, InterruptedException {
        int occurrences = 0;

        // Iterate over the set of values and sum them
        for (IntWritable value : values) {
            occurrences = occurrences + value.get();
        }
        context.write(key, new IntWritable(occurrences));
    }
}
