@echo on

java -jar jars/forum_eureka.jar
&
java -jar jars/forum_web.jar
&
java -jar jars/forum_base.jar


pause
