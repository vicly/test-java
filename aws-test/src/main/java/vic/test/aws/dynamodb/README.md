
see http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html


1. download http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_latest.zip
2. unzip and run `java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -inMemory`

endpoint: http://localhost:8000

UI: http://localhost:8000/shell