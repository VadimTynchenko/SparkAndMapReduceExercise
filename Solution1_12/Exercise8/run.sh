# Remove folders of the previous run
hdfs dfs -rm -r /ex8
rm -r ./ex8_out/
rm -r ./ex8_out_final/

# Create exercise folder
hdfs dfs -mkdir /ex8

# Put input data collection into hdfs
hdfs dfs -put ex8_data /ex8/

# Run app
hadoop jar target/Exercise8-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise8.DriverBigData 2 /ex8/ex8_data /ex8/ex8_out /ex8/ex8_out_final

hdfs dfs -get /ex8/ex8_out ./
hdfs dfs -get /ex8/ex8_out_final ./
