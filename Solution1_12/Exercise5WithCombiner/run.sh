# Remove folders of the previous run
hdfs dfs -rm -r /ex5_withcombiner
rm -r ./ex5_out_withcombiner/

# Create exercise folder
hdfs dfs -mkdir /ex5_withcombiner

# Put input data collection into hdfs
hdfs dfs -put ex5_data_withcombiner /ex5_withcombiner/

# Run app
hadoop jar target/Exercise5WithCombiner-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise5withcombiner.DriverBigData 1 /ex5_withcombiner/ex5_data_withcombiner /ex5_withcombiner/ex5_out_withcombiner

hdfs dfs -get /ex5_withcombiner/ex5_out_withcombiner ./
