#Shell script for running the sortable challenge
javac -cp ".:gson-2.2.2.jar" beans/*.java *.java
java -cp ".:gson-2.2.2.jar" challenge