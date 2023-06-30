## Exercise 5 with Combiner
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
If we want to find average of some value, mapreduce might create several maps. For exampe: <br />
We have some values (0, 20, 10, 25, 15) and want to find avg. AVG(0, 20, 10, 25, 15) = 14 <br />
But if we have big size of map output, the result will be: AVG(AVG(0, 20, 10), AVG(25, 15)) = AVG(15, 20) = 17.5<br />
So we need to create Combiner. In fact, combiner = reducer, but it will run between map and reduce phase. <br />
If we directly feed this huge output to the Reducer, then that will result in increasing the Network Congestion. <br />
![image](https://github.com/VadimTynchenko/SparkAndMapReduceExercise/assets/43113777/6865006d-2d8e-471d-acc2-fb471e338b96)

Also, if we have as many mappers as numbers of input splits. If we have more than one mapper, than avg function should include combiner.


Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create StatisticWritable
(implements Writable)<br />
We have two parameters: sum and count.<br />
Create getters and setters. And implement funtions write() and readFields().<br />
Override function toString() as "" + sum / count

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: LongWritable and Text.<br />
At first, split each value as sensor_Id and PM10Level.<br />
Create StatisticWritable and set sum(PM10Level) and count(1).<br />
Output: Text and StatisticWritable. (for example output for mapper will be (s1, (20.5, 1)), (s1, (60.2, 1)), (s2, (30.1, 1)) )

###### Create Combiner
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, StatisticWritable)<br />
For each value, add to temp integer localSum and localCount. <br />
Create StatisticWritable and set sum(localSum) and count(localCount).<br />
Then use context.write(key, localSumAndCount). <br />
Output: Text and IntWritable. (for example (s1, 40.35, 2), (s2, 30.1, 1))

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, StatisticWritable)<br />
For each value, add to temp integer localSum and localCount. <br />
Create StatisticWritable and set sum(localSum) and count(localCount).<br />
Then use context.write(key, localSumAndCount). <br />
Output: Text and IntWritable. (for example (s1, 40.35, 2), (s2, 30.1, 1))

###### Create Driver.
(extend from Configured and implement Tool)<br />
The same, as previous versions.<br />
The difference between the previous version is the presence of job.setCombinerClass

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
