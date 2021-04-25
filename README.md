# AllegroTask
app adress:
(https://springallegro.azurewebsites.net/)

## Author
Bartłomiej Łukasik

## Technologies:
- Java 8
- Spring 2.4.5

## Usage
### To get JSON with Repo list for username:
- https://springallegro.azurewebsites.net/repoList?user=<username>
  - response: JSON with 2 fields (name and stargazers_count)
### To get JSON with star sum for username:
- https://springallegro.azurewebsites.net/starSum?user=<username>
  - response: JSON with 1 field (stargazers_count)

## Warning
- If anything goes wrong response is JSON with 1 field (message)
- Github api has 5000 reguest per hour limit

## How to deploy solution?
### Standard way
While in project catalog type mvnw package. After that you should have test-0.0.1-SNAPSHOT.jar file in target folder. You can use it to host app on any web serwer (for example: Tomcat)
### Azure hosting
I personally use Azure service. All you have to do is get AppService with java 8 and go to section deploy where you can add link to this repo. Moreover every time master gets updated on github Azure creates new deploy and ecerything is up to date!
