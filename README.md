# Invoice API

I used dropwizard for my API service base. It's built on top of tomcat. I'm using maven to manage my dependencies and builds.
Locally when I developed this I used mysql (it's what I had on my machine). I created a mysql table using the following syntax: 

```
CREATE TABLE `invoices` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(64) NOT NULL DEFAULT '',
  `po_number` varchar(64) NOT NULL DEFAULT '',
  `due_date` date NOT NULL,
  `amount_cents` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `invoice_number` (`invoice_number`),
  KEY `po_number` (`po_number`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
```

# Building the client
To build the client, (at the project root), run:
`mvn clean package`

Assuming maven is installed.

# Running the server
To run the server, (at the project root), run:
`java -jar ./target/zola-api-1.0-SNAPSHOT.jar server ./configuration/{ENV}/configuration.yml`

Where `{ENV}` is `zola` for postgres and `local` for mysql. I didn't have a postgres server to test this on,
so there might be differences. The server by default pops up at (port :8080)[http://localhost:8080]

# Client code
I have an example client at com.hanzolo.zolapi.client along with a main function if you wanna test it out. I'm using
unirest library to make http calls.

You can also test it out by running:
```
# Test Post
curl -X POST -H "Content-Type: application/json" -d '{"invoice_number": "ABC12345","po_number": "X1B23C4D5E","due_date": "2017-03-05","amount_cents": 10000}' http://localhost:8080/v1/invoices

# Test Get
curl http://localhost:8080/v1/invoices?invoice_number=ABC12345
```