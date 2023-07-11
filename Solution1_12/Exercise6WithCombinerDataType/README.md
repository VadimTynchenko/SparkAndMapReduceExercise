## Exercise 6 With Combiner Data Type
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
Create new class MinMaxWritable and implement it Writable. It this class create two parameters: double min, double max. Create setters and getters for these two parameters.<br />
Implement two methods from Writable: write() and readFields().<br />
Method write() creates dataOutput value for this "new" type of writable and put new field into output.<br />
Method readFields() reads received values from input and assigns these value to created fields(min and max).<br />
Split each row to sensor_id and PM10Level in map(mapper's output should be <sensor_id, MinMaxWritable>), combine(as same as reducer) and in reduce for each MinMaxWritable pair compare with max and min by methods getMin() and getMax(). <br /> 
If value < min, than min = value.setMin(). If value > max, than max = value.setMax().<br />

In this exercise we create new data type as MinMaxWritable and implement it.<br />

Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: LongWritable and Text.<br />
At first, split each value as sensor_Id and PM10Level.<br />
Output: Text and MinMaxWritable. (for example output for mapper will be (s1, (20.5, 20.5)), (s1, (60.2,60.2)), (s2, (30.1,30.1)), (s2, (55.5,55.5)) )

###### Create Combiner
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, MinMaxWritable)<br />
For each value, compare with max and min. <br />
Then use context.write(key, minMax). <br />
Output: Text and MinMaxWritable. (for example (s1, (20.5,60.2)), (s2, (30.1,55.5)))

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, MinMaxWritable)<br />
For each value, compare with max and min. <br />
Then use context.write(key, minMax). <br />
Output: Text and MinMaxWritable. (for example (s1, (20.5,60.2)), (s2, (30.1,55.5)))

###### Create Driver.
(extend from Configured and implement Tool)<br />
The same, as previous versions.<br />
Standart Driver.

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
