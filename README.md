# Blockframe

Blockframe is currently an educational tool for myself to practice blockchain technology.  It is still very basic.  I strongly advise against using this application for any real blockchain programming. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Required. 

* [Maven](https://maven.apache.org/download.cgi) - Required.

* [Redis](https://redis.io/download) - Required.

* [A Java IDE.  I recommend IntelliJ Community Edition.](https://www.jetbrains.com/idea/download/) - This isnt required, but it will help you understand the app. Maven comes preinstalled with IntelliJ.

* [Postman](https://www.getpostman.com/) - This isnt required, but it helps enourmously with using the web services.

### Clone the Repository

```
git clone git@github.com:rafritts/blockframe.git
cd blockframe
```

### Redis

You need to start a local Redis server before you can use the blockframe app. If you do not start Redis before you send a transaction to blockframe, the jar will crash and you will need to exit out of it (`ctrl + c`), and restart the jar. Fortunately, this is very easy.  You will need to replace the version below with the version you have installed.

```
redis-4.0.1/src/redis-server &
```
If you wish to stop your Redis server gracefully, you can excute this command:
```
redis-4.0.1/src/redis-cli SHUTDOWN
```

More info about how to use Redis can be found [here](https://redis.io/topics/quickstart).

### Running the app via command line

Before you do anything else, you will need to make the blockframe jar. 


```
mvn package
```

Then you can launch the jar via command line. 

```
java -jar target/blockframe-1.0-SNAPSHOT-jar-with-dependencies.jar
```

If you see the following output, the jar is running.

```
Starting echo web service...
Starting transaction web service...
Starting blockchain web service...
Starting difficultyTarget web service...
Ready to mine.

Waiting for transactions to mine...
```

## How to use

Blockframe comes with a few web services that allow you the interact with the blockchain while its executing.  

### Transaction Service

This is probably the most important service in the blockframe app.  After all, what good is a blockchain if it has no transactions to mine.  In its current form, transactions have no structure.  They are just strings.  The only validation blockframe does is that it will not let an empty transaction be mined.  

With blockframe running, open a new terminal and execute the following `curl` command to send a transaction to blockframe:

```
curl -X POST \
  http://localhost:4567/transaction/submitTransaction \
  -H 'content-type: application/json' \
  -d '{
	"details": "You can put whatever you like here."
}'
```

### Blockchain Service

Once the transaction you have sent has finished mining, you can view the blockchain in JSON format by executing the following `curl` command.  NOTE: This will give you the entire blockchain in JSON format.  So if you are several blocks in, you will get a hefty response.  

```
curl -X GET http://localhost:4567/blockchain
```
If you just want to get a single block, you can use the following `curl` command:

```
curl -X GET http://localhost:4567/blockchain/block/$BLOCK-NUMBER$
```

There are other services.  You can find them in the following package: `blockframe/src/main/java/com/blockframe/restfulservices/services/`.  If you have Postman installed, I have included two Collections.  The first is a complete listing of all the services currently available, and the second is a single call that you can use in Postman Runner to continuously send transactions to blockframe. These collections can be found in the following directory: `blockframe/src/main/resources/postman/`.

## Important Notes

*  
* Currently Redis persistence is being developed.  As such, the blockchain is NOT persistent yet.
* Redis likes to throw an warning when it connects for the first time.  Just ignore it. 
* Do not expect huge hashrates.  My average hashrate on my i5 is around 500k hashes per second.  This is single threaded. 
* Block difficulty is not measured like bitcoin difficulty.  In blockframe, it is simply the number of leading zeros required for proof of work.  By default it is set to five.  I have found that five leading zeros allows for decently fast block mining times. However, if you want to go beyond seven leading zeros, you will need more hashing power, as the mining times can become really quite long (several hours). 

## Built With:

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spark](http://sparkjava.com/) - Web Service Framework
* [Lettuce](https://lettuce.io/) - Redis Client
* [Gson](https://github.com/google/gson) - JSON serialization/deserialization library
* [Apache Commons - DigestUtils](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/DigestUtils.html) - Hashing Library