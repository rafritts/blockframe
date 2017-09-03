Build Status: ![alt tag](https://travis-ci.org/rafritts/block-simple.svg?branch=master "Master Branch")

In its current form, this repo is nothing more than a educational tool for 
myself to learn how blockchain works.  If you would like to use it as well, the 
entry point for the app is BlockframeApp.java. 

To run this app, you will need Java 8 and Maven 3 installed.  

Simply run the command `mvn package` to build the jar, and the run the command
`java -jar target/blockframe-1.0-SNAPSHOT-jar-with-dependencies.jar` from the parent
directory.

If you want to see the blockchain in action, you need to submit an HTTP POST request to the url:
```http://localhost:4567/transaction/submitTransaction```

Here is an example request

```
{
    "details": "Whatever you like here"
}
```