# Blockframe

Blockframe is currently an educational tool for myself to practice blockchain technology.  It is still very basic.  I strongly advise against using this application for any real blockchain programming. In its current form, it is single threaded, and does not have any distributed capabilities. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Required. 

* [Maven](https://maven.apache.org/download.cgi) - Required.

* [Docker](https://www.docker.com/get-docker) - Required.

### Clone the Repository

```
git clone git@github.com:rafritts/blockframe.git
cd blockframe
```
### Running the app

Before you do anything else, you will need to make the blockframe jar. 


```
mvn package
```

Then you can launch the app with docker-compose. 

```
docker-compose up
```

If you see the following output, the app is running.

```
...
...
...
redis_1       | 1:M 02 Jul 02:40:51.475 * Ready to accept connections
blockframe_1  | Starting echo web service...
blockframe_1  | Starting transaction web service...
blockframe_1  | Starting blockchain web service...
blockframe_1  | Starting difficultyTarget web service...
blockframe_1  | Starting blockValidator web service...
blockframe_1  | Ready to mine.
blockframe_1  |
```

## How to use

Blockframe comes with a few web services that allow you the interact with the blockchain while its executing.  

### Transaction Web Services

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

If you wish to see the current transaction list, of unmined/unverified transactions, execute the following `curl` command:

```
curl -X GET http://localhost:4567/transaction/transactionList 
```

### Blockchain Web Service

Once the transaction(s) you have sent finished mining, you can view the current blockchain in JSON format by executing the following `curl` command.  NOTE: This will give you the entire blockchain in JSON format.  So if you are several blocks in, you will get a hefty response.  

```
curl -X GET http://localhost:4567/blockchain
```
If you just want to get a single block, you can use the following `curl` command:

```
curl -X GET http://localhost:4567/blockchain/block/$BLOCK-NUMBER$
```
If you just want to see how many blocks are in the blockchain, you can use the following `curl` command:
```
curl -X GET http://localhost:4567/blockchain/length
```
Also, you can view the average nonces, with the following `curl` command.  This is interesting as it shows roughly how many times a block needs to be hashed on average before it is mined.  
```
curl -X GET http://localhost:4567/blockchain/averageNonce
```

### Block Validator Web Service

You can manually check a block to see if it is valid by performing the following steps:

First, get the block you want to validate:
```
curl -X GET http://localhost:4567/blockchain/block/$BLOCK-NUMBER$
```

Second, copy and paste the block's json into the body of a `curl` POST command modeled after the following.  I strongly suggest you use Postman at this point, as it makes the whole thing much easier. However, if you want to use `curl`, it will look something like this:
```
curl -X POST \
  http://localhost:4567/blockValidator \
  -H 'Content-Type: application/json' \
  -d '{
    "blockHeader": {
        "version": "1.0.0",
        "blockId": "1",
        "difficultyTarget": 5,
        "previousBlockHash": "0000000000000000000000000000000000000000000000000000000000000000",
        "minedHash": "00000c3c012f60c6691b433819290f5d4d6e4dc1a296996caf27bb91f4095ab9",
        "merkleRoot": "402582e0814c685a3dc31740db09aa78417ae712903ef82cf94ddf61be779c36",
        "timeStamp": 1530484111051,
        "nonce": 1249669,
        "miningTimeInSeconds": 0
    },
    "payloadAsJson": "[{\"verified\":true,\"mined\":false,\"details\":\"transactionOnline639\"}]",
    "listOfVerifiedTransactions": [
        {
            "verified": true,
            "mined": true,
            "details": "transactionOnline639"
        }
    ]
}'
```

### Difficulty Target Web Service

You can get the current difficulty with the following `curl` command:
```
curl -X GET http://localhost:4567/difficultyTarget
```

Likewise, if you wanted to change the target difficulty to 6, you can do so like this:
```
curl -X PUT \
  'http://{{hostname}}:4567/difficultyTarget' \
  -H 'Content-Type: application/json' \
  -d '{
	"difficultyTarget": 6
}'
```

## Postman
If you have Postman installed, I have included two Collections.  The first is a complete listing of all the services currently available, and the second is a single call that you can use in Postman Runner to continuously send transactions to blockframe. These collections can be found in the following directory: `blockframe/src/main/resources/postman/`.

## Important Notes

* Redis likes to throw an warning when it connects for the first time.  Just ignore it. 
* Do not expect huge hashrates.  My average hashrate on my i5 is around 500k hashes per second.  This is single threaded. 
* Block difficulty is not measured like bitcoin difficulty.  In blockframe, it is simply the number of leading zeros required for proof of work.  By default it is set to five.  I have found that five leading zeros allows for decently fast block mining times. However, if you want to go beyond seven leading zeros, you will need more hashing power, as the mining times can become really quite long (several hours). 

## Built With:

* [Maven](https://maven.apache.org/) - Dependency Management
* [SparkJava](http://sparkjava.com/) - Web Service Framework
* [Lettuce](https://lettuce.io/) - Redis Client
* [Gson](https://github.com/google/gson) - JSON serialization/deserialization library
* [Apache Commons - DigestUtils](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/DigestUtils.html) - Hashing Library