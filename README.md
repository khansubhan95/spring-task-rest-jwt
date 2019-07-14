# Tasks REST API

A REST API made using Spring that allows users to create, read, update and delete their tasks/todos. Only authenticated users are allowed access to these operations. The authentication scheme uses JWT to authenticate the users. To get the token users must register and login. JWT tokens are valid for only 15 minutes. Tasks and users are stored in the form of collections in a MongoDB database.

- Spring REST
- Spring Security
- MongoDB

## API endpoints

- POST /signup - register for a new account. User must post with a unique username and password to get a new account.
- POST /login - login an existing user. Post the username and password to this endpoint to get JWT token in the headers of the respone
- GET /api/tasks - view all tasks of logged in user
- POST /api/tasks - create a new task. Requires title and content in the body.
- GET /api/tasks/{taskId} - view task by id
- PUT /api/tasks/{taskId} - update task with given task id. Requires title, content and done in the body.
- PATCH /api/tasks/{taskId} - update done field of a given task.
- DELTE /api/tasks/{taskId} - delete task with given task id.