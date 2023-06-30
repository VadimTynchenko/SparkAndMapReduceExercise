# Remove folders of the previous run
hdfs dfs -rm -r /ex5
rm -r ./ex5_out/

# Create exercise folder
hdfs dfs -mkdir /ex5

# Put input data collection into hdfs
hdfs dfs -put ex5_data /ex5/

# Run app
hadoop jar target/Exercise5-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise5.DriverBigData 1 /ex5/ex5_data /ex5/ex5_out

hdfs dfs -get /ex5/ex5_out ./
