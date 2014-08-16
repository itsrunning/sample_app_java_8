#Its Running Sample App


##Required 
    Postgres 9.3
    Java 8

##Steps

1. Set environment variable DATABASE_URL as 

        [postgres://[username]:[password]@[host]:[port]/[database name]
        
        For Linux or MAC users
        
        export DATABASE_URL=postgres://joe:joe@localhost:5432/microblog
        
        
        For windows users use environment variables dialog box.
    
2. Create a database called   "microblog"
3. Download the following jar


4. Run the following command in the path where the jar's are downloaded
``` 
    java -jar target/microblog-0.0.1-SNAPSHOT.jar db migrate microblog.yml
    java -jar target/microblog-0.0.1-SNAPSHOT.jar server microblog.yml
```
