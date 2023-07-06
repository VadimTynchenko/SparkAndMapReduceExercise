# Remove folders of the previous run
hdfs dfs -rm -r /ex8_single_job
rm -r ./ex8_out_single_job/

# Create exercise folder
hdfs dfs -mkdir /ex8_single_job

# Put input data collection into hdfs
hdfs dfs -put ex8_data_single_job /ex8_single_job/

# Run app
hadoop jar target/Exercise8SingleJob-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise8singlejob.DriverBigData 2 /ex8_single_job/ex8_data_single_job /ex8_single_job/ex8_out_single_job

hdfs dfs -get /ex8_single_job/ex8_out_single_job ./
