package com.vadimtynchenko.bigdata.hadoop.exercise6;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, FloatWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FloatWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        String sensor_id = fields[0];
        float PM10Level = Float.parseFloat(fields[2]);
        context.write(new Text(sensor_id), new FloatWritable(PM10Level));
    }
}
