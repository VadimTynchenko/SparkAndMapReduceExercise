package com.vadimtynchenko.bigdata.hadoop.exercise8;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerBigData extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        double totalIncome = 0;

        for (DoubleWritable value :
                values) {
            totalIncome += value.get();
        }
        context.write(key, new DoubleWritable(totalIncome));
    }
}
