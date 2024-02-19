# Intuit Craft Round

This project is build on gradle 6.8 and java 17.

Problem Statement: [Stackoverflow](SE2BackendCraft.pdf)

Heroku URL: [intuit-stackoverflow](https://stackoverflow-aditya-dhiman-7ab9318c14f4.herokuapp.com/) (Currently not working because only backend APIs are there)

Postman collection: [Postman Collection](Personal.postman_collection.json)

DB schema: [Schema](https://whimsical.com/intuit-stackoverflow-db-schema-647jqm4W4ozavXLzHKR2Y2)

To run the collection:
* Open Postman
* Click on import and paste/drag-drop the `.json` file there.

### Feature which are currently Implemented:
* You can create a user.
  * You can delete a user.
  * You can update a user profile name.
* You can add a question.
  * You can add question with tags.
  * You can delete a question.
  * You can update a question.
  * You can get top questions by upvote count. 
  * You can tag an already created question.
* You can add answer to a question.
  * You can added answer to an answer.
* You can upvote an answer.
  * You can upvote a question.
* You can search for a question.
  * You can search for a tag.
  * You can search for a answer.
* You can get top questions by upvote count.

### Feature which are not yet implemented:
* Rich media content (photos/videos) can be added as an answer.
* Test cases not written.


### Regardless of the features implemented, many things can be improved like:
* Some APIs don't need a request body and can be done from request params.
* Multiple tasks can be done with needing extra attributes in a class.
* All the unique ids of classes can be made auto-increment.
* Instead of passing the whole object stored in DB as response, we can send just the id of created data to front-end.