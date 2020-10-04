# currency-converter
This is a live currency converter app. 
When you click on a cell it comes as first responder. When you change the amount of the first currency, other currencies update their values. Every 1 second an api call is made to update rates. This triggers a refresh on the amounts of the cells. 

![gif displaying a demo](https://media.giphy.com/media/QxRp0pDljoYuT734eU/giphy.gif)

# Structure
This project follows the MVVM and Repository pattern

### Data Folder : layer to communicate with apis
Dto: it gathers the DTO to communicate with the APIS

Repository: repository

Source: holds interfaces, remote and local implementations to communicate with APIS

### Model Folder 
classes to communicate view model with view

------

### Networking Folder: 
core network classes

------

### ServiceLocator Folder: 
provides Injection

------

### Util Folder
holds extensions and other utility classes

------

### View Folder
Holds all the UI related classes (activities, fragments, holders, etc)

------

### ViewModel Folder
View Model Layer. Hold business logic related classes. Communicate between APIS and Views
  
