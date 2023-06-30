package com.vadimtynchenko.bigdata.hadoop.exercise5withcombiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DriverBigData extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Path inputPath;
        Path outputPath;
        int numberOfReducer;
        int exitCode;

        numberOfReducer = Integer.parseInt(args[0]);
        inputPath = new Path(args[1]);
        outputPath = new Path(args[2]);

        Configuration conf = this.getConf();

        Job job = Job.getInstance();

        job.setJobName("Average job");

        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setJarByClass(DriverBigData.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapperClass(MapperBigData.class);

        job.setMapOutputKeyClass(Text.class);
        job.setOutputValueClass(StatisticWritable.class);

        job.setCombinerClass(CombinerBigData.class);

        job.setReducerClass(ReducerBigData.class);

        job.setOutputKeyClass(TextOutputFormat.class);
        job.setOutputValueClass(StatisticWritable.class);

        job.setNumReduceTasks(numberOfReducer);

        if (job.waitForCompletion(true)) {
            exitCode = 0;
        } else {
            exitCode = 1;
        }
        return exitCode;
    }

    public static void main(String[] args) throws Exception{
        int res = ToolRunner.run(new Configuration(), new DriverBigData(), args);
        System.exit(res);
    }
}
