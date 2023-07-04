package com.vadimtynchenko.bigdata.hadoop.exercise7;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String invIndex = "";

        for (Text word : values) {
            invIndex = invIndex.concat(word.toString() + ",");
        }
        context.write(new Text(key), new Text(invIndex));
    }
}
