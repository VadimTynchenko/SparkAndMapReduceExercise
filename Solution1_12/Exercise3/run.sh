# Remove folders of the previous run
hdfs dfs -rm -r /ex3
rm -r ./ex3_out/

# Create exercise folder
hdfs dfs -mkdir /ex3

# Put input data collection into hdfs
hdfs dfs -put ex3_data /ex3/

# Run app
hadoop jar target/Exercise3-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise3.DriverBigData 1 /ex3/ex3_data /ex3/ex3_out

hdfs dfs -get /ex3/ex3_out ./
