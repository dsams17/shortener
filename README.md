# shortener

Rudimentary URL shortener built using Angular on the frontend, Clojure on the backend, and Mongodb as a database

## Prerequisites

You will need [Leiningen][] 2.0.0 or above, [Angular CLI][] version 8.2.2 or above, and [Mongo DB][] version 4.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen
[Angular CLI]: https://github.com/angular/angular-cli
[Mongo DB]: https://github.com/mongodb/mongo


## Frontend

Located within the "frontend" directory

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Backend

Located within "src/shortener"

### Running

Before kicking off the leiningen web server, you will need to start an instance of Mongo. Assuming it is added to your path, this can be kicked off by running `mongod` in a shell. After, start a web server for the application by running `lein run`


