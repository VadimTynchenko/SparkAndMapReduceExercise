package com.vadimtynchenko.bigdata.hadoop.exercise4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String aboveThresholdDates = "";

        for (Text date : values) {
            if (aboveThresholdDates.length() == 0)
                aboveThresholdDates = date.toString();
            else
                aboveThresholdDates = aboveThresholdDates.concat(","
                        + date.toString());
        }
        context.write(key, new Text(aboveThresholdDates));
    }
}
