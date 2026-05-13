- we need role for flexibility (we can add more roles without the need to re deploy)
- role enum is kept for now just to make sure that role label is written like in DB (could be deleted later without a problem)
- we changed application properties, changed DB name and ddl-auto
- different CRUD tests with postman (we need auth in most of them)
- various debug and minor changes to make sure that the app runs without crashing down

- 14/5 commit : - we started adding different authorities for user with admin role 
                - .env added
                - we implemented cloudinary logic and tested uploading a picture with postman successfully
                  the picture's url is saved in users table in DB as user_photo with success
---------------------------------------------------------------------------------------------------------------
----- next step is to complete giving different authorities between different roles with security config ------
--------------------------------- give clear api paths for all end points -------------------------------------