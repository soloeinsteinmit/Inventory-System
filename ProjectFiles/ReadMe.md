# Inventory management system

# Project Structure
![](../src/main/resources/project_files.png)

![](../src/main/resources/project_files_01.png)

# The lib folder contains all the library dependancy you can import in to the project

> The main java class is the *InventoryManagementSystem.java*

> Logins for the app auths
The login for the frontend logins
- Admin Id: 2468
- Password: structures
> The non vendor
- user Id: 8642
- password: vendor

## Some Java files and their functions
- The DBConstantConnection.java is the connection class to the database
- Algorithms.java contains some algorithms
- DataAccess.java contains some algorithms and database access. Also for keeping balance between high and low price.

## Implementation of data Structures
- AddGoodsController.java and DataAccess.java contain codes for adding and removing of goods in their various structures
    - stacks 
    - queue
    - list
- ViewVendorsController.java uses HashMap to display information about vendors
- RegisterVendorController.java also uses HashMap to store information about vendor and store them into the database.
- IssuedGoodsController.java uses queue for the adding of cart
  - It's also use HashMaps for tracking of product sales 



## Some frontend files and their functions 
- Controllers is the controller for the javafx files
- And each page as a controller for the functionality.

