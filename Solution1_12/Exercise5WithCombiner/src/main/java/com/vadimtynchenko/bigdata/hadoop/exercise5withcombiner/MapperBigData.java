package com.vadimtynchenko.bigdata.hadoop.exercise5withcombiner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<LongWritable, Text, Text, StatisticWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, StatisticWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");

        String sensor_id = fields[0];
        float PM10Level = Float.parseFloat(fields[2]);
        StatisticWritable localSumAndCount = new StatisticWritable();
        localSumAndCount.setSum(PM10Level);
        localSumAndCount.setCount(1);

        // emit the pair(sensor_id, value - 1)
        context.write(new Text(sensor_id), localSumAndCount);
    }
}
