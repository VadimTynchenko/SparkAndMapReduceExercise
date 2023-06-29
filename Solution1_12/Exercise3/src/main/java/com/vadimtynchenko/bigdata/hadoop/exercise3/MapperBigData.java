package com.vadimtynchenko.bigdata.hadoop.exercise3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<Text, Text, Text, IntWritable> {

    private static Double PM10Threshold = new Double(50);
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // Extract sensor_id and date
        String[] fields = key.toString().split(",");
        String sensor_id = fields[0];
        Double PM10Level = new Double(value.toString());

        // Compare the value of PM10 with the threshold value
        if (PM10Level.compareTo(PM10Threshold)> 0) {
            //emit the pair (sensor_id, 1)
            context.write(new Text(sensor_id), new IntWritable(1));
        }
    }
}
