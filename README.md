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
3. Download the following files
      1. [Jar](https://github.com/itsrunning/sample_app_java_8/releases/download/0.0.1/microblog-0.0.1-SNAPSHOT.jar)
      2. [Yaml](https://github.com/itsrunning/sample_app_java_8/releases/download/0.0.1/microblog.yml)

4. Run the following command from the path where the files are downloaded
``` 
    java -jar microblog-0.0.1-SNAPSHOT.jar db migrate microblog.yml
    java -jar microblog-0.0.1-SNAPSHOT.jar server microblog.yml
```


## Forked from 
https://github.com/sahilm/dropwizard-snapci-sample
