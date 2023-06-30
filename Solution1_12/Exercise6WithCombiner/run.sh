# Remove folders of the previous run
hdfs dfs -rm -r /ex6_withCombiner
rm -r ./ex6_out_withCombiner/

# Create exercise folder
hdfs dfs -mkdir /ex6_withCombiner

# Put input data collection into hdfs
hdfs dfs -put ex6_data_withCombiner /ex6_withCombiner/

# Run app
hadoop jar target/Exercise6WithCombiner-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise6withcombiner.DriverBigData 1 /ex6_withCombiner/ex6_data_withCombiner /ex6_withCombiner/ex6_out_withCombiner

hdfs dfs -get /ex6_withCombiner/ex6_out_withCombiner ./
