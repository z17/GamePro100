## Public API
Gateway to API:
`http://localhost:8080/services/`

Path to API url = `Gateway Url` + `Service Path` + `Method Path`

### Lessons & tasks:
Path to service: `/lesson-service`

#### Lessons
* get one lesson: `/lesson/{id}` `GET`
* get all lessons: `/lesson/` `GET`
* add lesson: `/lesson/add` `POST`
* update lesson: `/lesson/update` `POST`

#### Tasks
* get one task: `/task/{id}` `GET`
* get all tasks: `/task/` `GET`
* get all tasks of lesson: `/task/getByLesson/{id}` `GET`
* add task: `/task/add/` `POST`

### Users:
Path to service: `/user-service`

* get one user: `/{id}` `GET`
* update user: `/update/` `POST`
* create user: `/add/` `POST`

### Results:
Path to service: `/result-service`

* submit: `/submit/` `POST`
* get task results: `/getByTask/{id}` `GET`

***
Information about services you can read [here](services.md)