package com.vadimtynchenko.bigdata.hadoop.exercise8;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<Text, Text, Text, DoubleWritable> {
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        String[] date = key.toString().split("-");
        double income = Double.parseDouble(value.toString());

        String month = date[0] + "-" + date[1];

        context.write(new Text(month), new DoubleWritable(income));
    }
}
