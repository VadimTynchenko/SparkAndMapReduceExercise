# Remove folders of the previous run
hdfs dfs -rm -r /ex9
rm -r ./ex9_out/

# Create exercise folder
hdfs dfs -mkdir /ex9

# Put input data collection into hdfs
hdfs dfs -put ex9_data /ex9/

# Run app
hadoop jar target/Exercise9-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise9.DriverBigData 1 /ex9/ex9_data /ex9/ex9_out

hdfs dfs -get /ex9/ex9_out ./
