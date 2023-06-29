package com.vadimtynchenko.bigdata.hadoop.exercise1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<
        LongWritable,   // Input key type
        Text,           // Input value type
        Text,           // Output key type
        IntWritable     // Output value type
        > {
    protected void map(
            LongWritable key,      // Input key type
            Text value,            // Inut value type
            Context context        // Mapper context
    ) throws IOException, InterruptedException {
        // Split each line in words. Use whitespace(s) as delimiter
        // (=a space, a tab, a line brake, or a form feed)
        // The split method returns an array of strings
        String[] words = value.toString().split("\\s+");

        for (String word : words) {
            // Transform word case
            String cleanedWord = word.toLowerCase();
            // emit the pair (word, 1)
            context.write(new Text(cleanedWord), new IntWritable(1));
        }
    }
}
