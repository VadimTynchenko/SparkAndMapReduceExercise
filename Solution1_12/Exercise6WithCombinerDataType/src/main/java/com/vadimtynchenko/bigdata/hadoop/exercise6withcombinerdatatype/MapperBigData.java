package com.vadimtynchenko.bigdata.hadoop.exercise6withcombinerdatatype;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, MinMaxWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, MinMaxWritable>.Context context) throws IOException, InterruptedException {
        String record = value.toString();
        String[] fields = record.split(",");
        MinMaxWritable minMax = new MinMaxWritable();

        minMax.setMin(Double.parseDouble(fields[2]));
        minMax.setMax(Double.parseDouble(fields[2]));
        context.write(new Text(fields[0]), minMax);
    }
}
