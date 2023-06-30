## Exercise 6
#### PM10 pollution analysis Max and Min
* Input:  collection of (structured) textual csv files
containing the daily value of PM10 for a set of
sensors
* Output: : report for each sensor the maximum and
the minimum value of PM10

#### Example:
* Input:  s1,2016-01-01,20.5<br />
        s2,2016-01-01,30.1<br />
        s1,2016-01-02,60.2<br />
s2,2016-01-02,20.4<br />
s1,2016-01-03,55.5<br />
s2,2016-01-03,52.5<br />
* Output:   (s1, max=60.2_min=20.5)<br />
(s2, max=52.5_min=20.4)<br />


##### Explanation
Split each row to sensor_id and PM10Level in map and in reduce for each key-value pair compare with max and min. <br /> 
If value < min, than min = value. If value > max, than max = value.<br />

Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: LongWritable and Text.<br />
At first, split each value as sensor_Id and PM10Level.<br />
Output: Text and FloatWritable. (for example output for mapper will be (s1, 20.5), (s1, 60.2), (s2, 30.1), (s2, 55.5) )

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, FloatWritable)<br />
For each value, compare with max and min. <br />
Then use context.write(key, "max="+max+"_min="+min). <br />
Output: Text and Text. (for example (s1, max=60.2_min=20.5), (s2, max=55.5_min=30.1))

###### Create Driver.
(extend from Configured and implement Tool)<br />
The same, as previous versions.<br />
Standart Driver.

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
