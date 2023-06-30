package com.vadimtynchenko.bigdata.hadoop.exercise5withcombiner;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StatisticWritable implements Writable {

    private float sum = 0;
    private int count = 0;

    public float getSum() {
        return sum;
    }

    public void setSum(float sumValue) {
        sum = sumValue;
    }

    public int getCount() {
        return count;
    }

    protected void setCount(int countValue) {
        count = countValue;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeFloat(sum);
        dataOutput.writeInt(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        sum = dataInput.readFloat();
        count = dataInput.readInt();
    }

    @Override
    public String toString() {
        return "" + sum / count;
    }
}
