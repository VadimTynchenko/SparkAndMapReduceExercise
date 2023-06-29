# Remove folders of the previous run
hdfs dfs -rm -r /ex2
rm -r ./ex2_out/

# Create exercise folder
hdfs dfs -mkdir /ex2

# Put input data collection into hdfs
hdfs dfs -put ex2_data /ex2/

# Run app
hadoop jar target/Exercise2-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise2.DriverBigData 1 /ex2/ex2_data /ex2/ex2_out

hdfs dfs -get /ex2/ex2_out ./
