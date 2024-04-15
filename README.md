Update the db url, password and usernmae in the application.properties to your creds and run the application.
Use pgAdmin or any other tool to create schema in DB.
```
create table TEMPLATE(
	TEMPLATE_ID UUID PRIMARY KEY,
	NAME VARCHAR(200) NOT NULL,
	TITLE VARCHAR(200) NOT NULL,
	SUB_TITLE VARCHAR(500) NOT NULL
);

create table TEMPLATE_ROW(
	ROW_ID UUID PRIMARY KEY,
	TEMPLATE_ID UUID NOT NULL CONSTRAINT ROW_FK references TEMPLATE ON DELETE CASCADE
);

create table TEMPLATE_COLUMN(
	COLUMN_ID UUID PRIMARY KEY,
	COLUMN_TYPE VARCHAR(50),
	TYPE_VALUE VARCHAR(1000),
	ROW_ID UUID NOT NULL CONSTRAINT COLUMN_FK references TEMPLATE_ROW ON DELETE CASCADE
);
```


Run the application and invoke below endpoints:

API to get all templates: http://localhost:8080/template, HTTP method = GET
<img width="513" alt="image" src="https://github.com/PalakRajput/template/assets/65886272/28d4807a-8eb4-47c2-a24c-08d88a3619eb">

API to save template: http://localhost:8080/template, HTTP method = POST
<img width="507" alt="image" src="https://github.com/PalakRajput/template/assets/65886272/f2cbbbca-00e0-4b79-8628-26b31f742163">
