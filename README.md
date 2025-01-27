# HUAWEI ASSIGNMENT


## Using Tools & Technologies
* Spring boot
* Rest Api
* Docker
* Optimistic Lock (for future DB replication)
* Soft Delete
* Lombok
* Mapstruct

##### Postman
The **Postman export file** prepared for all endpoints in the project is located in the main directory of the project as **"Huawei Assignment.postman_collection.json"**. You can add the file to the Postman program using the "Import" button.

<span style="color:red">**NOTE:** **SAMPLE DATA** is added to the database through the DBInitializer class when the project starts.</span>
##### Run Docker:
```
$ cd huawei-case-study
$ docker-compose up
```
##### Run Maven:
```
$ mvn clean install
$ mvn spring-boot:run
```