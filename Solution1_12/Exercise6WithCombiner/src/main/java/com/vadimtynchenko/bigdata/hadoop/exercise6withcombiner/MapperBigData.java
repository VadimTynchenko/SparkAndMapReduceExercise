package com.vadimtynchenko.bigdata.hadoop.exercise6withcombiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        context.write(new Text(fields[0]), new Text(fields[2] + "_" + fields[2]));
    }
}
