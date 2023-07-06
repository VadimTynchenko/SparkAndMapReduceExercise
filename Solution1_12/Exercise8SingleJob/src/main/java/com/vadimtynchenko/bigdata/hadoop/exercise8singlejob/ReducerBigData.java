package com.vadimtynchenko.bigdata.hadoop.exercise8singlejob;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReducerBigData extends Reducer<Text, MonthIncome, Text, DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<MonthIncome> values, Reducer<Text, MonthIncome, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {

        // Store in the hashmap
        // monthId -> monthly income
        // for each month of the current year (=current key).
        // At most 12 => we can store it in the main memory of each reducer
        HashMap<String, Double> totalMonthIncome = new HashMap<String, Double>();

        String year = key.toString();

        // Counters used to compute
        // - the total income for the current year (current key)
        // - the number of distinct months for this year (I consider only those months with an associated income)
        double totalYearIncome = 0;
        int countMonth = 0;

        // Iterate over the set of values and compute
        // - the total income for each month
        // - the overall total income for this year
        for (MonthIncome value : values) {
            // Retrieve the current income for the current month
            Double income = totalMonthIncome.get(value.getMonthId());

            if (income != null) {
                // This month is already in the hashmap (other local incomes for this month have been already analyzed).
                // Update the total income for this month
                totalMonthIncome.put(new String(value.getMonthId()), new Double(value.getIncome() + income));
            } else {
                // First occurrence of this monthId
                // Insert monthid - income in the hashmap
                totalMonthIncome.put(new String(value.getMonthId()), new Double(value.getIncome()));
                countMonth++;
            }
            // Update the total income of the current year
            totalYearIncome += value.getIncome();
        }
        // First part of the result
        // Emit the pairs (year-month, total monthly income)
        for (Map.Entry<String, Double> pair : totalMonthIncome.entrySet()) {
            context.write(new Text(year + "-" + pair.getKey()), new DoubleWritable(pair.getValue()));
        }

        // Second part of the result
        // Emit the average monthly income for each year
        context.write(new Text(year), new DoubleWritable(totalYearIncome / countMonth));
    }
}
