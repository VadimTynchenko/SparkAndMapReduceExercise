## Exercise 4
#### PM10 pollution analysis per city zone
* Input:  a (structured) textual file containing
the daily value of PM10 for a set of city zones
* Output: report for each zone the list of dates
associated with a PM10 value above a specific
threshold

#### Example:
* Input:  s1,2016-01-01 20.5<br />
        s2,2016-01-01 30.1<br />
* Output:   (zone1, [2016-01-03, 2016-01-02]) <br />
(zone2, [2016-01-01])<br />

##### Explanation
Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: Text and Text.<br />
At first, split each key as sensor_Id and date.
Then split value for each sensor as PM10Level and compare with PM10Threshold.<br />
If value more than threshold, use context.write to output sensor_ID and date.<br />
Output: Text and Text. (for example output for mapper will be (s1, 2016-01-01), (s1, 2016-01-02), (s2. 2016-01-01) )

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, Text). Create temp String as "". 
Iterate each value and sum String value.get().
Then use context.write(key, value). <br />
Output: Text and IntWritable. (for example (s1, [2016-01-01,2016-01-01]), (s2, [2016-01-01]) )

###### Create Driver.
(extend from Configured and implement Tool)<br />
Create Job as: Job name = Job.getIntance()<br />
job.setJobName("name") - Name for job <br />

* Set path for input and output (FileInputFormat.addInputPath(job, inputPath) and FileOutputFormat.setOutputPath(job, outputPath)) <br />
* Specify the class of Driver for this job (job.setJarByClass(DriverBigData.class))<br />
* Set format for input and output (job.setInputFormatClass(KeyValueTextInputFormat.class) and job.setOutputFormatClass(TextOutputFormat.class))<br />
* Set map and reduce class (job.setMapperClass(Mapper.class) and job.setReducerClass(Reducer.class))<br />
* Set type for key and value for map and reducer (job.setMapOutputKeyClass(Text.class);job.setMapOutputValueClass(IntWritable.class); and job.setOutputKeyClass(Text.class);job.setOutputValueClass(IntWritable.class);)<br />
* Set number of reducer (job.setNumReduceTasks(numberOfReduces)). 

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
