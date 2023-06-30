# Remove folders of the previous run
hdfs dfs -rm -r /ex4
rm -r ./ex4_out/

# Create exercise folder
hdfs dfs -mkdir /ex4

# Put input data collection into hdfs
hdfs dfs -put ex4_data /ex4/

# Run app
hadoop jar target/Exercise4-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise4.DriverBigData 1 /ex4/ex4_data /ex4/ex4_out

hdfs dfs -get /ex4/ex4_out ./
