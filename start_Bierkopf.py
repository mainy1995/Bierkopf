#!/usr/bin/python
# -*- coding: utf-8 -*-

  
import subprocess 
import os 
  
  
def executeJava(): 
  
    # 1. compile all java files, save return value in checksum
    # 2. run app, if compiling worked, else exit
    checksum = subprocess.check_call("javac @sources.txt",cwd = "/home/alex/Documents/Software/Java/Bierkopf", shell = True)
    if(checksum != 0):
    	exit()
    else: 
    	subprocess.call("java Bierkopf/Start", shell = True) 
  
  
# Driver function 
if __name__=="__main__": 

    executeJava() 
