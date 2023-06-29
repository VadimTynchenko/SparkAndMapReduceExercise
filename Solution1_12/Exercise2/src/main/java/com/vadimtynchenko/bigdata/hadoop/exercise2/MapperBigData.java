package com.vadimtynchenko.bigdata.hadoop.exercise2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\\s+");

        for (String word : words) {
            String cleanedWord = word.toLowerCase();
            context.write(new Text(cleanedWord), new IntWritable(1));
        }
    }
}
