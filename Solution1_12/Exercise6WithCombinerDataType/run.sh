# Remove folders of the previous run
hdfs dfs -rm -r /ex6_withCombiner_datatype
rm -r ./ex6_out_withCombiner_datatype/

# Create exercise folder
hdfs dfs -mkdir /ex6_withCombiner_datatype

# Put input data collection into hdfs
hdfs dfs -put ex6_data_withCombiner_datatype /ex6_withCombiner_datatype/

# Run app
hadoop jar target/Exercise6WithCombinerDataType-1.0.0.jar com.vadimtynchenko.bigdata.hadoop.exercise6withcombinerdatatype.DriverBigData 1 /ex6_withCombiner_datatype/ex6_data_withCombiner_datatype /ex6_withCombiner_datatype/ex6_out_withCombiner_datatype

hdfs dfs -get /ex6_withCombiner_datatype/ex6_out_withCombiner_datatype ./
