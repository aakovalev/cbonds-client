
This is a thin wrapper around [CBonds.ru](https://cbonds.ru/api/catalog/folders/) web services.
The client is still in early development and can't be yet recommended for production usage.
 
### Usage
Add dependency of the client library to your project (e.g. for Gradle):

    dependencies {
        implementation "io.github.aakovalev:cbonds-client:0.1.0"
        ...
    }

Then you could use client in your project:

        ...
        Client client = new Client(USER, PASSWORD);
        Response response = client.execute(new Request(GET_STOCKS));
        System.out.println("Got the following info " + response.getItems());
        
