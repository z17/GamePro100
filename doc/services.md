## Services: 
There are five services:

### 1. Gateway service
http://localhost:8080
Main service, common API.

All requests from FE should send to Gateway service.

Public API you can see at [api documentation](api.md)

### 2. Lesson service
http://localhost:8081

Service to store info about tasks and lessons.

### 3. User service
http://localhost:8082

Service to store info about users.

### 4. Task executor service
http://localhost:8083

Service to execute user's tasks.

### 5. Result service
http://localhost:8084

Service to store user's results, submit tasks and other. It can send requests to another services (2, 3 and 4)