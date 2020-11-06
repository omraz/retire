#!/bin/bash

javac retire.java
if [ $? == 0 ]
then
  echo Compilation SUCCEED.
  echo
  java retire $*
else
  echo Compilation FAILED!
fi
