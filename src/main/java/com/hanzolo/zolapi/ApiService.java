package com.hanzolo.zolapi;

import com.hanzolo.zolapi.invoice.InvoiceEndpoint;
import com.hanzolo.zolapi.invoice.models.InvoiceDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class ApiService extends Application<ApiConfiguration> {
    public static void main(String[] args) throws Exception {
        new ApiService().run(args);
    }

    @Override
    public String getName() {
        return "zola-api";
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
    }

    @Override
    public void run(ApiConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), configuration.getDatabaseType());
        final InvoiceDAO invoiceDAO = jdbi.onDemand(InvoiceDAO.class);
        environment.jersey().register(new InvoiceEndpoint(invoiceDAO));
    }
}
