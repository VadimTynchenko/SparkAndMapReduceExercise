# Remove folders of the previous run
hdfs dfs -rm -r /ex6
rm -r ./ex5/

# Create exercise folder
hdfs dfs -mkdir /ex6

# Put input data collection into hdfs
hdfs dfs -put ex6_data /ex6/

# Run app
hadoop jar target/Exercise6-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise6.DriverBigData 1 /ex6/ex6_data /ex6/ex6_out

hdfs dfs -get /ex6/ex6_out ./
