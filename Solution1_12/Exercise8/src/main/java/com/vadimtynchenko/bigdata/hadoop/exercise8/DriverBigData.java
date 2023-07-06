package com.vadimtynchenko.bigdata.hadoop.exercise8;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DriverBigData extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        int exitCode;
        int numberOfReducers = Integer.parseInt(args[0]);
        Path inputPath = new Path(args[1]);
        Path outputPath = new Path(args[2]);
        Path outputPathStep2 = new Path(args[3]);

        Configuration conf = this.getConf();

        // First job
        Job job1 = Job.getInstance();

        job1.setJobName("Exercise#8");
        job1.setJarByClass(DriverBigData.class);
        FileInputFormat.addInputPath(job1, inputPath);
        FileOutputFormat.setOutputPath(job1, outputPath);

        job1.setMapperClass(MapperBigData.class);
        job1.setReducerClass(ReducerBigData.class);


        //Check this
        job1.setInputFormatClass(KeyValueTextInputFormat.class);

        job1.setOutputFormatClass(TextOutputFormat.class);

        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(DoubleWritable.class);

        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);

        job1.setNumReduceTasks(numberOfReducers);
        if (job1.waitForCompletion(true)) {
            exitCode = 0;
        } else {
            exitCode = 1;
        }
        if (exitCode == 0) {
            // Second job
            Job job2 = Job.getInstance(conf);

            job2.setJobName("Exercise#8 Step 2");
            FileInputFormat.addInputPath(job2, outputPath);
            FileOutputFormat.setOutputPath(job2, outputPathStep2);

            job2.setJarByClass(DriverBigData.class);
            job2.setInputFormatClass(KeyValueTextInputFormat.class);

            // Set job output format
            job2.setOutputFormatClass(TextOutputFormat.class);

            // Set map class
            job2.setMapperClass(MapperBigDataStep2.class);

            // Set map output key and value classes
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(DoubleWritable.class);

            // Set reduce class
            job2.setReducerClass(ReducerBigDataStep2.class);

            // Set reduce output key and value classes
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(DoubleWritable.class);

            job2.setNumReduceTasks(numberOfReducers);

            // Execute the job and wait for completion
            if (job2.waitForCompletion(true))
                exitCode = 0;
            else
                exitCode = 1;
        }
        return exitCode;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new DriverBigData(), args);
        System.exit(res);
    }
}
