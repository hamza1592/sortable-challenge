#!/bin/bash
javac -cp ".:gson-2.2.2.jar:$HOME/sortable-challenge/*" Challenge.java
java -cp ".:gson-2.2.2.jar:$HOME/sortable-challenge/*" Challenge
