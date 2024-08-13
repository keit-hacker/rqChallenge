# Coding Challenge


### User ReadME

Was able to get all endpoints working! A bit of backstory on projection. Initially had service make actual API calls to the base URL. After running into some bugs early on, I discovered the rate limiting issue. Attempted to work around it by using a retry to back off of the API after calls. That failed to work so implemented some caching and basic mocks. If the endpoint wasn't responsive, I would rely on mocks to hold local data in cache. After testing that out, I realized that there were many issues and the additional code to fix this would require some big overhead changes. I ended up scrapping that logic and relying only on mocks going forward. After the basics were finished, I added pagination, logging, utilizing UUID to create new employee IDs, exception handling, and more. There are still many things I could improve such as: fine-tune error handling and better tests. But as it stands I think it is impressive!

Some ideas included:

- Dockerization
- Kafka streams (a basic service to watch for events happening and log them to some different service)
- Enhanced error handling/return data
- Hosting on AWS free tier
- Dummy Authorization


### Pre requisites 
- [JDK 11 or later](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle 6.8 or later (included in the project)](https://gradle.org/install/)



### Running Locally

1. clone the project with: **git clone https://github.com/keit-hacker/rqChallenge.git**



2. cd into the **java-employee-challenge** and run the below commands


3. To build the project run:  **./gradlew build**


4. To run the project run: **./gradlew bootRun**


5. To run the tests run: **./gradlew test** or **./gradlew test --info** for more info 




### Curl Commands 

After it is running you can hit the endpoints with the following curl commands:


**curl --location 'http://localhost:8080/employees'**


    output - list of employees
    description - this should return all employees

**curl --location 'http://localhost:8080/search/{PartialOrFullString}'**

    output - list of employees
    description - this should return all employees whose name contains or matches the string input provided

**curl --location 'http://localhost:8080/employee/{id}'**

    output - employee
    description - this should return a single employee

**curl --location 'http://localhost:8080/highestSalary'**

    output - integer of the highest salary
    description -  this should return a single integer indicating the highest salary of all employees

**curl --location 'http://localhost:8080/topTenHighestEarningEmployeeNames'**

    output - list of employees
    description -  this should return a list of the top 10 employees based off of their salaries

**curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe","salary":100000, "age":54}' http://localhost:8080**

    output - string of the status (i.e. success)
    description -  this should return a status of success or failed based on if an employee was created

**curl --location --request DELETE 'http://localhost:8080/{id}'**

    output - the name of the employee that was deleted
    description - this should delete the employee with specified id given

### External endpoints from base url
#### This section will outline all available endpoints and their request and response models from https://dummy.restapiexample.com
/employees

    request:
        method: GET
        parameters: n/a
        full route: https://dummy.restapiexample.com/api/v1/employees
    response:
        {
            "status": "success",
            "data": [
                {
                "id": "1",
                "employee_name": "Tiger Nixon",
                "employee_salary": "320800",
                "employee_age": "61",
                "profile_image": ""
                },
                ....
            ]
        }

/employee/{id}

    request:
        method: GET
        parameters: 
            id (String)
        full route: https://dummy.restapiexample.com/api/v1/employee/{id}
    response: 
        {
            "status": "success",
            "data": {
                "id": "1",
                "employee_name": "Foo Bar",
                "employee_salary": "320800",
                "employee_age": "61",
                "profile_image": ""
            }
        }

/create

    request:
        method: POST
        parameters: 
            name (String),
            salary (String),
            age (String)
        full route: https://dummy.restapiexample.com/api/v1/create
    response:
        {
            "status": "success",
            "data": {
                "name": "test",
                "salary": "123",
                "age": "23",
                "id": 25
            }
        }

/delete/{id}

    request:
        method: DELETE
        parameters:
            id (String)
        full route: https://dummy.restapiexample.com/api/v1/delete/{id}
    response:
        {
            "status": "success",
            "message": "successfully! deleted Record"
        }