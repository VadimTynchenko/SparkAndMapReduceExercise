# Remove folders of the previous run
hdfs dfs -rm -r /ex1/ex1_data
hdfs dfs -rm -r /ex1/ex1_out
rm -r ./ex1_out/*

# Put input data collection into hdfs
hdfs dfs -put ex1_data /ex1/

# Run application
hadoop jar target/Exercise1-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise1.DriverBigData 2 /ex1/ex1_data/  /ex1/ex1_out

hdfs dfs -get /ex1/ex1_out ./ex1_out/