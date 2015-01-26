#!/bin/bash

{{ansible_env.HOME}}/spark-1.2.0-bin-hadoop2.4/bin/spark-submit \
  --class com.seigneurin.spark.Chess \
  --master spark://{{master_hostname}}:7077 \
  --deploy-mode cluster \
  hdfs://{{master_hostname}}:9000/spark-chess-0.0.1-SNAPSHOT.jar
