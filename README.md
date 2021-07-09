# README

The SLUBER service is a RESTful service which performs operations on passengers and drivers.

## Installation
This project requires JDK 14

* Clone repo: https://bitbucket.org/tschwebach/sluber-service
* Build:  mvn clean install
* Run: mvn spring-boot:run
* Build Docker image:  docker build -t sluber-service -f ./Dockerfile .
* Run Docker image:  docker run -p 8080:8080 --name sluber-service sluber-service

## Local DynamoDB Setup
Before the AWS DynamoDB gets setup online, a local downloadable version can be installed for testing.

* Local DynamoDB download: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html
* Install AWS CLI: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Tools.CLI.html#Tools.CLI.DownloadingAndRunning
* Configure AWS CLI: https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html
    * Since we're doing the db locally, the access key and secret access key can be anything.
    * Default output format should be: json
* Create a test table within the database
    * Launch the local database: `java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb`
        * Make sure to cd into the dir you saved dynamoDB in
    * Create the table: `aws dynamodb create-table --table-name People --attribute-definitions AttributeName=firstName,AttributeType=S --key-schema AttributeName=firstName,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url
  http://localhost:8000`
        * AWS instructions: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Tools.CLI.html#Tools.CLI.DownloadingAndRunning
        * Verify table created succesfully: `aws dynamodb list-tables --endpoint-url http://localhost:8000`
    *  Add an item to the table:  `aws dynamodb put-item --table-name People --item "{""firstName"": {""S"": ""Tim""}}" --return-consumed-capacity TOTAL --endpoint-url http://localhost:8000`
        * If using a windows terminal - the double sets of " are necessary for the CLI parser to work. Otherwise, use the following: `'{"firstName": {"S": "Tim"}}'`
        * AWS instructions: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/getting-started-step-2.html
    * Query the table: `aws dynamodb get-item --consistent-read --table-name People --key "{""firstName"": {""S"": ""Tim
      ""}}" --endpoint-url http://localhost:8000`
      
The local version DynamoDB saves its data in a file called "shared-local-instance" in the folder with the rest of the DynamoDB files, so tables are saved even after quitting the run database command. 