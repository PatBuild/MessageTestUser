# Test Plan

Automated test for Perry's Summer Vacation Goods and Services

Test Setup ( will need to be automated)
Setup 5 users (testUser name(N) )
    check current users 
    delete/clear users
    create the 5 users
    attach user IDs to user class


### Function

Post send message

Get   messages between 2 users 

Get   message from ID

~~Put Update a message by ID~~ //Put body not found

Delete message by ID.

_Issues_ 

No limits on input UUID, length of name/chars/
Date appropriate?
Message limits, char type/length
where do the messages go when user deleted


### Performance

how many requests per second will it take to stop it.
what is the needed turnaround time?

### Security
sql injection?
no authentication?

### Design issues
Put method for updating messages has not been finished in JS
Messages with random usernames, where are these stored? Loose on DB 
Are messages impacted when user deleted



### Future work
Add test suit to jenkins and Allure reporting.
https://blog.knoldus.com/integrating-rest-assured-with-jenkins-and-allure-reports/

Performance
Investigate Locust
https://www.blazemeter.com/blog/locust-performance-testing-using-java-and-kotlin