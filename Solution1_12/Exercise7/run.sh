# Remove folders of the previous run
hdfs dfs -rm -r /ex7
rm -r ./ex7_out/

# Create exercise folder
hdfs dfs -mkdir /ex7

# Put input data collection into hdfs
hdfs dfs -put ex7_data /ex7/

# Run app
hadoop jar target/Exercise7-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise7.DriverBigData 1 /ex7/ex7_data /ex7/ex7_out

hdfs dfs -get /ex7/ex7_out ./
