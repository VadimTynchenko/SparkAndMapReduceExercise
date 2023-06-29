## Exercise 2
#### Word Count Problem (multiple files)
* Input: (unstructed) text files in one folder
* Output: number of occurrences of each word
appearing in the input files

#### Example:
* Input:  Toy example<br />
        file for Hadoop.<br />
        Hadoop running<br />
        example.<br />
* Output:   (toy, 1) <br />
          (example, 2)<br />
          (file, 1) <br />
          (for, 1) <br />
          (hadoop, 2) <br />
          (running, 1)<br />

##### Explanation
Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: LongWritable(it's a key for each char) and Text(it's text, obviously).<br />
At first, split each word into list.
Then for each word in list of words cleanese it(set word to lower case) 
and use context.write(key, value) funtion to write words as key and 1 as value. <br />
Output: Text and IntWritable. (for example output for mapper will be (word, 1), (word, 1), (example. 1) )

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, IntWritable). Create temp int as 0. 
Iterate each value and sum temp int and value.get().
Then use context.write(key, value). <br />
Output: Text and IntWritable. (for example (word, 3), (example, 3) )

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
