package com.vadimtynchenko.bigdata.hadoop.exercise9;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapperBigData extends Mapper<LongWritable, Text, Text, IntWritable> {
    HashMap<String, Integer> wordsCounts;
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        wordsCounts = new HashMap<String, Integer>();
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        Integer currentFreq;
        // Split each sentence in words. Use whitespace(s) as delimiter
        // ( = a space, a tab, a line brake or a form feed)
        // The split method returns an array of strings
        String[] words = value.toString().split("\\s+");

        // Iterate over the set of words
        for (String word : words) {
            // Transform words to lower case
            String cleanedWord = word.toLowerCase();
            currentFreq = wordsCounts.get(cleanedWord);
            // If it's the first time in the mapper
            if (currentFreq == null) {
                wordsCounts.put(cleanedWord, 1);
            } else {    // Increase the number of occurrences of current word
                currentFreq++;
                wordsCounts.put(cleanedWord, currentFreq);
            }
        }
    }

    @Override
    protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // Emit the set of (key, value) pairs of this mapper
        for (Map.Entry<String, Integer> pair : wordsCounts.entrySet()) {
            context.write(new Text(pair.getKey()), new IntWritable(pair.getValue()));
        }
    }
}
