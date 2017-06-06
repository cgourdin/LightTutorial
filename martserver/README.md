# MartServer for tutorial light

To build and test with light, you must use maven to build and execute the project :
<pre>
<code>mvn initialize
mvn clean install -DskipTests
cd ./org.occiware.mart.jetty/
mvn exec:java
</code>
</pre>

## Check if lights are in realm : 
Execute this curl command :
<pre>
<code>curl -v -X GET http://localhost:8080/-/?category=lampe
</code>
</pre>

It must return the schema for the kind <b>"lampe"</b> :

<pre>
<code>
{
    "term" : "lampe",
    "scheme" : "http://occiware.org/light#",
    "title" : "A light resource",
    "attributes" : {
        "occi.light.state" : {
            "mutable" : true,
            "required" : false,
            "type" : "string",
            "description" : "State",
            "pattern" : {
                "$schema" : "http://json-schema.org/draft-04/schema#",
                "pattern" : "\\S+",
                "type" : "string"
            },
            "default" : "off"
        }
    },
    "parent" : "http://schemas.ogf.org/occi/core#resource",
    "location" : "/lampe/",
    "actions" : [ "http://occiware.org/light/lampe/action#switchOn", "http://occiware.org/light/lampe/action#switchOff" ]
}
</code>
</pre>
