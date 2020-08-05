# Fuzz-Movie-API 

## Prerequisites

* Java 8 or later
* maven (optional)

## How to build

In the folder root

````
mvn clean package
````
or

````
mvnw clean package
````

## How to run

````
java -jar target/movies-0.0.1-SNAPSHOT.jar
````

## How to use

* First, you have to generate an apiKey to use the endpoints. Keep in mind the apiKeys are deleted each hour 

````
POST /apiKey
````

* You can use the API, with some preloaded data in H2 DB

Get details from a movie
````
GET /movie/:id?apiKey={API_KEY}
````

Get favorite a movies
````
GET /favorites?apiKey={API_KEY}
````

Get favorite a movies
````
GET /favorites?apiKey={API_KEY}
````

Add movie as favorite
````
POST /favorite/:id?apiKey={API_KEY}
````

Search movies: You can put a word, year or text in order to retrieve movies who match
with this parameter by director, year, actors or title, the implementation works as a regular expression
````
GET /movies?serach={KEYWORDS}&apiKey={API_KEY}
````

Save a movie
````
POST /save?apiKey={API_KEY}

{
    "title": "Harry Potter",
    "year": "2020",
    "language": "es",
    "director": "David Yates",
    "plot": "final movie",
    "runtime": "130 min",
    "favorite": false,
    "actors": [
        "Daniel Raddfile"
    ]
}
````

## Enhancements

* Filter and Handling error
* Scheduling
* Preload Data to H2
* Swagger-ui
* Readme