package com.vadimtynchenko.bigdata.hadoop.exercise8;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigDataStep2 extends Mapper<Text, Text, Text, DoubleWritable> {
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        String[] date = key.toString().split("-");
        String year = date[0];

        context.write(new Text(year), new DoubleWritable(Double.parseDouble(value.toString())));
    }
}
