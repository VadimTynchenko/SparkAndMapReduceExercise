package com.vadimtynchenko.bigdata.hadoop.exercise5;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, FloatWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FloatWritable>.Context context) throws IOException, InterruptedException {
        // Split each record by using field separator
        // field[0] - sensor_id
        // field[1] - date
        // field[2] - PM10Level
        String[] fields = value.toString().split(",");
        String sensorId = fields[0];
        float PM10Level = Float.parseFloat(fields[2]);

        context.write(new Text(sensorId), new FloatWritable(PM10Level));
    }
}
