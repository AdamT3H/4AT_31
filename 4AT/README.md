
## Write a maven command for executing one of your tasks with arguments (number of elements).

mvn clean install exec:java -Dexec.mainClass="org.example.task5.Stream" -Dexec.args="arg0 arg1"
