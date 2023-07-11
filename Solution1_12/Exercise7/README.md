## Exercise 7
#### Inverted index
* Input: a textual file containing a set of sentences
* Output: :  report for each word w the list of
sentenceIds of the sentences containing w

#### Example:
* Input:  Sentence#1 /t Hadoop or Spark<br />
Sentence#2 /t Hadoop or Spark and Java<br />
Sentence#3 /t Hadoop and Big Data<br />
* Output:   (hadoop, [Sentence#1, Sentence#2, Sentence#3])<br />
(spark, [Sentence#1, Sentence#2])<br />
(java, [Sentence#2])<br />
(big, [Sentence#3])<br />
(data, [Sentence#3])<br />


##### Explanation
Create standart mapper and reduser.

In mapper we split each row to array of words, cleaned by use method .toLowerCase() and compare each cleanedWord with except list(and, or, not).<br />
Write each cleanedWord and sentence, But, we should inverted it. If input is <Sentence, words>, in output we invert it such as <word, sentence>.<br/>
In reducer all are simple. Just get key and concat each value to this key.<br />

Use Java hadoop API. In maven use dependency:<br />
\<groupId>org.apache.hadoop\</groupId>\<artifactId>hadoop-client\</artifactId> <br />

###### Create mapper. 
(extend from mapper)<br />
Input for mapper: Text and Text.<br />
At first, split each value by regex"\\s+".<br />
Output: Text and Text. (for example output for mapper will be (hadoop, Sentence#1), (hadoop, Sentence#2), (spark, Sentence#1), (spark, Sentence#2) )

###### Create reducer. 
(extend from reducer)<br />
Input for reducer: as same as in mapper's output(Text, Text)<br />
For each value, concat with previous value. <br />
Then use context.write(key, value). <br />
Output: Text and Text. (for example (hadoop, [Sentence#1, Sentence#2, Sentence#3]), (spark, [Sentence#1, Sentence#2]))

###### Create Driver.
(extend from Configured and implement Tool)<br />
The same, as previous versions.<br />
Standart Driver.

###### Run app
* For start app, we package project to jar, and then use ####### hadoop jar \<path/to/jar> \<execute.class> args(if in input use file, app count just words in it, but if use folder, app count words in all directory)
