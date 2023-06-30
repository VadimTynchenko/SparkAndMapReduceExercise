## Exercise 5
#### PM10 pollution analysis per city zone
* Input:  collection of (structured) textual csv files
containing the daily value of PM10 for a set of
sensors
* Output: report for each sensor the average value
of PM10

#### Example:
* Input:  s1,2016-01-01,20.5<br />
        s2,2016-01-01,30.1<br />
        s1,2016-01-02,60.2<br />
s2,2016-01-02,20.4<br />
s1,2016-01-03,55.5<br />
s2,2016-01-03,52.5<br />
* Output:   (s1, 45.4) <br />
(s2, 34.3)<br />

##### Explanation
Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: LongWritable and Text.<br />
At first, split each value as sensor_Id and PM10Level.<br />
Output: Text and FloatWritable. (for example output for mapper will be (s1, 20.5), (s1, 60.2), (s2, 30.1) )

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, FloatWritable). Create temp int as count and sum. 
Iterate each value and sum all value value.get() and for each value count add +1.
Then use context.write(key, sum/count). <br />
Output: Text and IntWritable. (for example (s1, 40.35), (s2, 30.1) )

###### Create Driver.
(extend from Configured and implement Tool)<br />
Create Job as: Job name = Job.getIntance()<br />
job.setJobName("name") - Name for job <br />

* Set path for input and output (FileInputFormat.addInputPath(job, inputPath) and FileOutputFormat.setOutputPath(job, outputPath)) <br />
* Specify the class of Driver for this job (job.setJarByClass(DriverBigData.class))<br />
* Set format for input and output (job.setInputFormatClass(TextInputFormat.class) and job.setOutputFormatClass(TextOutputFormat.class))<br />
* Set map and reduce class (job.setMapperClass(Mapper.class) and job.setReducerClass(Reducer.class))<br />
* Set type for key and value for map and reducer (job.setMapOutputKeyClass(Text.class);job.setMapOutputValueClass(IntWritable.class); and job.setOutputKeyClass(Text.class);job.setOutputValueClass(IntWritable.class);)<br />
* Set number of reducer (job.setNumReduceTasks(numberOfReduces)). 

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
