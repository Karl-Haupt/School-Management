# School-Management

## Improvement(s) - Question 10
Creating a clean system architecture is an important part of any software. Loosely 
connected components, scalability, and maintainability are just a few of the advantages of a clean architecture.

#### Tightly Coupled
A few enhancements to the system's architecture could be performed during the 
development phase of the project life cycle. The first modification 
that might be done is to think about the diagram's strongly connected entities. 
Tightly connected entities are existence dependent, which implies they require the 
existence of other entities in order to be generated. For example, the studentAddress 
entity requires a reference to a city; however, if a new city has recently been 
established and has not yet been added to the database, a studentAddress row cannot 
be added. This leading to the student without an address in the database. 

Another improvement to the UML and code structure is to use the data type of the primary key of
the entity that is connected and not a reference to an Object. For example the attribute
'city' in Address is using an object to reference the City table. This is where changing the City 
object to the data type of the primary key in City - String in this example - 
could be beneficial. This reduces the amount of code required to create an Address. 
The reduction of code overhead assures that the application has fewer bugs and errors.

Consider bridging entities between entities and normalizing the table to solve the tightly coupled entities.

#### Normalization
One of the enhancements we recommend is normalizing the table before connecting 
each entity. This will improve the efficiency and accuracy of the data entered as 
well as the data returned to the client. We see this issue throughout the domain 
model, where all entities are tightly connected, and by normalizing the tables, 
we reduce the chances of redundant data being added to our database. 

Normalization assists users by preventing data anomalies and ensuring data consistency.
This problem can be solved by normalizing the table to at least 3rd Normal Form.

#### Bridging Entities Structure
The problem with the two bridging entities (EmployeeAddress and StudentAddress) 
is that they each have only one PK/FK (staffId and studentId respectively). 
This is not generally how a bridging entity is supposed to be implemented, 
and therefore could be improved. The solution to this issue would be to place 
two PKs/FKs (composite key) in these two bridging entities. It would be ideal to 
add the City id to the two bridging entities as a PK/FK, thus linking the Employee 
and Student entities directly to the City entity.


## Problem Domain
In the quest for seamless and effective organisational processes in a college, the technology 
department of the college has been given the task to digitize and automate its academic-facing 
processes.

The technology department has decided to use Domain Driven Design to actualize this. A section of 
the technology team was given the responsibility to solicit requirements based on the as-is processes. 
Thus, after many analysis meetings, they have come up with a domain model.

## UML Diagram
![2022-06-17 (2)](https://user-images.githubusercontent.com/69191757/174347276-28d19e09-6887-4f33-9272-6d7a58f1bd35.png)
![2022-06-17 (4)](https://user-images.githubusercontent.com/69191757/174347300-363285db-5fd6-498c-83b1-93e54f3b191f.png)

## Task Allocation
KARL WILLIAM HAUPT - Employee entity, Improvements, Assisting group <br />
JOSHUA DANIEL JONKERS - Country entity, Improvements <br />
TREVOR STHEMBISO NGCOBO - Student, Name entities, Improvements, Assisting group <br />
CHARLES MOSES LEMMERT - City entity, Improvements <br />
MPONENGE ZIKHONA RATEGO - StudentAddress entity, Improvements <br />
MIKE SOMELEZO TYOLANI - EmployeeAddress & Address entities, Improvements

## Installation (Coming soon)
<!-- 1. Download/Clone the repository onto your computer and run it using an Java compiler
2. Download/Clone the JAR file to run the application -->

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss changes/modification.

### Contributors
* [KARL WILLIAM HAUPT](https://github.com/Karl-Haupt)
* [JOSHUA DANIEL JONKERS](https://github.com/JoshJonk)
* [TREVOR STHEMBISO NGCOBO](https://github.com/TrevorNgcobo)
* [CHARLES MOSES LEMMERT](https://github.com/Astro-Alien)
* [MPONENGE ZIKHONA RATEGO](https://github.com/MANtor123)
* [MIKE SOMELEZO TYOLANI](https://github.com/miketyo)
