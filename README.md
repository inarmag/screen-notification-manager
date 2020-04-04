## Requirements:

We want to create a Python/Flask application that listen for webhook notifications from Sqreen.
On receiving a notification the application should:

- Check that the signature is correct
- Redispatch the notification to multiple targets (e.g. log, email, HTTP, SMS, Slack).

####  Requested:
- Have a generic interface for target backends
- Two target backends
- Have relevant tests


## Start project localy

1- Update application.properties file with the correct config value:

    sqreen.webhook.secret.key=<to be filled>
    
    #Email config
    sqreen.notification.mail.receiver=<palceholder@mail.com>
    
    #Slack config
    slack.incoming.webhook.link=<to be filled>
    slack.channel=<to be filled>

2- Run spring boot Application:

    $ mvn package
    
    $ java -javaagent:notification-manager/lib/sqreen.jar -Dsqreen.token=<Sqreen TOKEN> -Dsqreen.app_name=notification-manager -jar target/notification-manager-0.0.1-SNAPSHOT.jar

3- Run ngrok:
    
    $ ./ngrok http 8080

4- Configure webhook url in Sqreen settings

5- Start testing!



