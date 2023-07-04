package com.vadimtynchenko.bigdata.hadoop.exercise7;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<Text, Text, Text, Text> {
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\\s+");

        for (String word : words) {
            String cleanedWord = word.toLowerCase();

            if (cleanedWord.compareTo("and") != 0
                    && cleanedWord.compareTo("or") != 0
                    && cleanedWord.compareTo("not") != 0) {
                context.write(new Text(cleanedWord), new Text(key));
            }
        }
    }
}
