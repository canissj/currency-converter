# currency-converter
This is a live currency converter app.

![](https://media.giphy.com/media/QxRp0pDljoYuT734eU/giphy.gif)

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
  
