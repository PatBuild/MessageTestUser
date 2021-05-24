# Test Plan

## Automated test for Perry's Summer Vacation Goods and Services

###### To be run using "mvn clean install" in the pom containing folder

##Scope of the testing completed
Functional,ErrorHandling,Performance,Integration,Bounds


### Function

Post send message

Get   messages between 2 users 

Get   message from ID

~~Put Update a message by ID~~ //Put body not found

Delete message by ID.

### Error Handling,
Codes non-standard 500/400 (bad JSON) 200/201 (created)

### Performance
how many requests per second will it take to stop it.
what is the needed turnaround time?
Average time 100 operations establish baseline

### Integration
Integration testing started with user API

100 POST - Create Message 
(realUsers:98724ms   /
    fakeUsers:150944ms)

100 GET -  get Message
 (realUsers:60708 / 
 fakeUsers:81706)

### Bounds
Bounds testing/Fuzz testing
No input validation. 



## Design issues
Put method for updating messages has not been finished in JS

Return codes not correct 

No input validation


###Questions on operation
Messages with random usernames, where are these stored? Loose on DB
Are messages impacted when user deleted

##### Security
sql injection?
no authentication?




## Future work
Randomise passed in variables, environment etc.

Add test suit to jenkins and Allure reporting.
https://blog.knoldus.com/integrating-rest-assured-with-jenkins-and-allure-reports/

####Performance
Investigate Locust
https://www.blazemeter.com/blog/locust-performance-testing-using-java-and-kotlin
https://aws.amazon.com/blogs/devops/using-locust-on-aws-elastic-beanstalk-for-distributed-load-generation-and-testing/
