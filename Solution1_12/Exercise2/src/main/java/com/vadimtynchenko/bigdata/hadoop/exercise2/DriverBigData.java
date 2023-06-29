package com.vadimtynchenko.bigdata.hadoop.exercise2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
        int numberOfReducers;
        int exitCode;

        // Parse parameters
        numberOfReducers = Integer.parseInt(args[0]);
        inputPath = new Path(args[1]);
        outputPath = new Path(args[2]);

        Configuration conf = this.getConf();

        // Define a new Job
        Job job = Job.getInstance();

        // Set job name
        job.setJobName("Exercise 2 - Word count from multiple files");

        // Set count of reducers, input and output
        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);
        job.setNumReduceTasks(numberOfReducers);

        // Specify Driver class
        job.setJarByClass(DriverBigData.class);

        // Specify Map and Reducer class
        job.setMapperClass(MapperBigData.class);
        job.setReducerClass(ReducerBigData.class);

        // Set input and output format for mapper and reducer
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // Set type of key and value for map and reduce
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Execute and wait for compile job
        if (job.waitForCompletion(true)) {
            exitCode = 0;
        } else {
            exitCode = 1;
        }
        return exitCode;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new DriverBigData(), args);
        System.exit(res);
    }
}
