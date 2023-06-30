package com.vadimtynchenko.bigdata.hadoop.exercise4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DriverBigData extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        int numberOfReducer;
        Path importPath;
        Path outputPath;
        int exitCode;

        numberOfReducer = Integer.parseInt(args[0]);
        importPath = new Path(args[1]);
        outputPath = new Path(args[2]);

        Configuration conf = this.getConf();

        Job job = Job.getInstance();

        job.setJobName("Exercise 4 - PM10 pollution analysis per city zone");

        job.setJarByClass(DriverBigData.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, importPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setMapperClass(MapperBigData.class);
        job.setReducerClass(ReducerBigData.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputKeyClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(numberOfReducer);

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
