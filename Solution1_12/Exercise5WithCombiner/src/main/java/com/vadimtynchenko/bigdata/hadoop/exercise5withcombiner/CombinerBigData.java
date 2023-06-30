package com.vadimtynchenko.bigdata.hadoop.exercise5withcombiner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CombinerBigData extends Reducer<Text, StatisticWritable, Text, StatisticWritable> {
    @Override
    protected void reduce(Text key, Iterable<StatisticWritable> values, Reducer<Text, StatisticWritable, Text, StatisticWritable>.Context context) throws IOException, InterruptedException {
        int localCount = 0;
        float localSum = 0;

        for (StatisticWritable value :
                values) {
            localSum += value.getSum();
            localCount += value.getCount();
        }

        StatisticWritable localSumAndCount = new StatisticWritable();
        localSumAndCount.setSum(localSum);
        localSumAndCount.setCount(localCount);

        context.write(key, localSumAndCount);
    }
}
