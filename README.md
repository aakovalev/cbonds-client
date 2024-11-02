
This is a thin wrapper around [CBonds.ru](https://cbonds.ru/api/catalog/folders/) web services.
The client is still in early development and can't be yet recommended for production.
 
### Usage
Add dependency of the client library to your project (e.g. for Gradle):

    dependencies {
        implementation "io.github.aakovalev:cbonds-client:0.1.0"
    }

Then you could use client in your project:

        client = new Client("your-login", "your-password"); // use "test" for user and password if not registered at CBonds.ru
        Request request = new Request(GET_STOCKS);

        // specify the fields that are needed in the response
        request.addField("id");
        request.addField("isin");
        request.addField("emitent_inn");

        // specify filter that is needed
        request.addFilter(new Filter("kind_id", FilterOperator.EQUAL, "2" /* stocks only */));

        // specify the order of result items
        request.addSorting("id", Order.DESC);

        Response response = client.execute(request);
        System.out.println("Returned items: " + response.getItems());

        
