package com.vadimtynchenko.bigdata.hadoop.exercise8singlejob;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MonthIncome implements Writable {

    private String monthId;
    private double income;

    public String getMonthId() {
        return monthId;
    }

    public void setMonthId(String monthId) {
        this.monthId = monthId;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(monthId);
        dataOutput.writeDouble(income);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        monthId = dataInput.readUTF();
        income = dataInput.readDouble();
    }
}
