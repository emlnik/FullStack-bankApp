# Coding Test

## Intro:
We have an API towards application developers, which returns information about all the banks which are available for the application. And we also have a basic design of a UI in Figma. https://www.figma.com/file/zGQ7p4lowje1r5c2TuDEt9/FullStack_challenge?node-id=5%3A8

The response from the API looks like this:
```json
[
  {
    "bic":"DOLORENOR9XXX",
    "name":"Bank Dolores",
    "countryCode":"NO",
    "auth":"ssl-certificate",
    "products":[
      "accounts",
      "payments"
    ]   
  },
  {
    ...
  }
]
```
There are two version of the API:

- `/v1/banks/all` - implementation is based on the static file, which is locally available
- `/v2/banks/all` - new version of the API, which will need to read the data from the remote servers

Both of the version need to return the same data structure.

## Challenge:

1. Complete the implementation of the `/v2/banks/all` endpoint, by implementing `BanksRemoteCalls.handle(Request request, Response response)` method.
The respective configuration file is `banks-v2.json`. Implementation needs to use the data from the configuration file,
and for each bank retrieve the data from the remote URL specified. You will need to add HTTP client of your choice to the project. 
You can find the mock implementation for the remote URLs in the MockRemotes class. 

2. Build a UI which displays the v1 and v2 banks list according to the provided design. The design should reflect the UI in Figma, and should follow the styles provided (font, colours etc).
- Functional requirements
  - Create pagination on the client side for V1 table, and for V2 table this is optional.
- Non-Functional requirements
  - You have the option to use Angular or React
  - You can use style template libraries.(Bootstrap, Material, etc.)
  - You can use style compilers like Sass
  - Name, bic, countrycode, products and authentication scheme should be displayed in a table for v1
  - Name, bic, countrycode and authentication scheme should be displayed in a table for v2
Feel free to add comments to the code to clarify the changes you are making.
  - Optionally you can either create a single page application displaying the two tables or display them in different pages.  
 
  The UI can be found here: https://www.figma.com/file/zGQ7p4lowje1r5c2TuDEt9/FullStack_challenge?node-id=5%3A8

3. Add unit tests for the code base.
  - Optinal: Add integration tests for both API versions, using a framework of your choice.

4. Refactor the existing code base, to be production ready (clean code example, design pattern). Feel free to add comments to the code to clarify the changes you are making.

## Sending in the assignment
- You may send in the assignment on a git repository or as a zip.
