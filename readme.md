1. Install jdk17.
2. Set JAVA_HOME for system variables 
3. Check that java successfully installed. Open terminal and run 'java -version'.
4. Install gradle.
5. Set GRADLE_HOME for system variables
6. Check that gradle is successfully installed. Open terminal and run 'gradle -v'.
7. Install postgresql 16.
8. Clone project from https://github.com/gololeg/survey 
9. Create database and scheme by executing script in survey/database/survey.ddl
10.  Execute script survey/database/survey.dml
11. Open terminal and run 'gradle build' in survey repository.
12. Go to /survey/build/libs in terminale and start server by running 'java -jar survey-{version}.jar'
13. Open link

