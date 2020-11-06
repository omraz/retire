#!/bin/bash

javac retire.java
if [ $? == 0 ]
then
  echo Compilation SUCCEED.
  echo
  java retire $1 $2 $3
else
  echo Compilation FAILED!
fi
