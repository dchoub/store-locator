# store-locator
# Author: Dhwani Choubisa, Ankur Sharma

Setup Mongo DB

1.	Start by creating a test database : 

Use store_db

This will create a new database in MongoDB that we can use

2.	Create the collection with the following command:

db.createCollection(“store”);

3.	Once the collection is created, we need to add some data. This collection will hold the _id, storied, storename, address, postcode, latitude and longitude

Creating a Spring Boot Project 

1.	Using Spring Initializr to create a project:
We need to specify the Group, Artifact and the dependencies that needs to be added. The project uses Web and MongoDB dependency.

2.	We will want to add a store model, so that Spring will know what kind of data the database will return. We have created a new folder in src/main/java/[package name]/ called "models". Created a model class inside the package called – Store. The models class helps in identifying the data structure stored in the database to Spring Boot.

3.	To establish the connection between  the model and MongoDB, we create a Repository interface. We have created a new folder in src/main/java/[package name]/ called "repositories". Created a repository interface inside the package called – StoreRepository. 

4.	The MongoDB connection details are present in the application.properties under src/main/resources" folder.

5.	Creating REST Controller – inside the src/main/java/[package name]/controller, we create a new class called the StoreController. The controller is the first class called when the REST API is called. It is tagged with @RestController. We also add the REST Endpoints inside the controller, which in turns calls the service class to do the appropriate action.

6.	Creating Services - inside the src/main/java/[package name]/services, we create a new class called the StoreService. The functions inside the Service class are called by the controller to perform certain actions when API call is made.

7.	StoreLocatorApplication – The main class which is executed when the application must be run. The beans are specified here for the services used.


Launching the Application
Once the DB and project is imported successfully, execute the application by running it as ‘Spring Boot App’. Once the server is up, please call the APIs to check the functionality.

•	GET the list of all stores :
On the browser, open the localhost:8080/store/
This call should gives you the details of all the stores present in the mongo collection.

•	GET the store by _id:
On the browser, open the localhost:8080/store/{_id}
This call should give you the details of the store with the unique _id present from the mongo collection.

•	DELETE the store by _id:
On the postman, make the request type as Delete and hit the url as : localhost:8080/store/{_id}
This should delete the store details for the specific store corresponding to the _id from the mongo collection.

•	Update the store details using PUT request:
On the postman, make the request type as Put and hit the url as : localhost:8080/store/{_id}, also give the Request Body in the correct json format.
This should update the store details for the specific store corresponding to the _id in the mongo collection with the details given in the request body.

•	Create the store in Mongo collection, using the POST request:
On the postman, make the request type as Post and hit the url as : localhost:8080/store/, also give the Request Body in the correct json format. Please don’t provide the latitude and longitude, as this will be fetched by the Google Geocode API using the postcode provided in the request body.
This should create a store with the given details and latitude longitude being fetched from the Geocode API the mongo collection.

•	GET the list of all stores near you:
Yet to be implemented, work in progress.
