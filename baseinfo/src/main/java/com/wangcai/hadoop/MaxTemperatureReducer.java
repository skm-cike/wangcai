package com.wangcai.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by 陆华 on 16-3-10 上午10:37
 */
public class MaxTemperatureReducer extends MapReduceBase implements Reducer<LongWritable, Text, Text, IntWritable> {

    public void reduce(LongWritable longWritable, Iterator<Text> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {

    }
}
