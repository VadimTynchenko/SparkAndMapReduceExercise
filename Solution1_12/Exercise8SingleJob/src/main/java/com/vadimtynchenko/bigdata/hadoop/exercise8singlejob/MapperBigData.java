package com.vadimtynchenko.bigdata.hadoop.exercise8singlejob;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperBigData extends Mapper<Text, Text, Text, MonthIncome> {
    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, MonthIncome>.Context context) throws IOException, InterruptedException {
        String[] date = key.toString().split("-");
        String year = date[0];
        String month = date[1];

        Double income = Double.parseDouble(value.toString());
        MonthIncome monthIncome = new MonthIncome();
        monthIncome.setMonthId(month);
        monthIncome.setIncome(income);

        context.write(new Text(year), monthIncome);
    }
}
