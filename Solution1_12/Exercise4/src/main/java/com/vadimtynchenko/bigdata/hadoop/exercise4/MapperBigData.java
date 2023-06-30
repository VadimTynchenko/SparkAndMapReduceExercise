package com.vadimtynchenko.bigdata.hadoop.exercise4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<Text, Text, Text, Text> {

    private static Double PM10Threshold = new Double(50);
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] fields = key.toString().split(",");
        String zone_id = fields[0];
        String date = fields[1];

        Double PM10Level = new Double(String.valueOf(value));
        if (PM10Level.compareTo(PM10Threshold) > 0) {
            context.write(new Text(zone_id), new Text(date));
        }
    }
}
