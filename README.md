# Rest APIs for a variation of Toy Robot puzzle

- The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units
- There are no other obstructions on the table surface
- The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.

Use the provided Rest APIs endpoints (http://localhost:8080/swagger-ui/index.html#/robot-controller) to create, change direction or move the robot. Start by creating the robot and then use its ID in other endpoints for further commands.

![image](https://user-images.githubusercontent.com/21066026/222265543-f29c46db-aaf4-4680-8724-1781c46b65d3.png)
