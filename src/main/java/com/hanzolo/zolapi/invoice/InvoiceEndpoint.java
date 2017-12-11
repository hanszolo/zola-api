package com.hanzolo.zolapi.invoice;

import com.hanzolo.zolapi.invoice.models.Invoice;
import com.hanzolo.zolapi.invoice.models.InvoiceDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/invoices")
public class InvoiceEndpoint {
    private final InvoiceDAO invoiceDAO;

    public InvoiceEndpoint(InvoiceDAO dao) {
        this.invoiceDAO = dao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice createInvoice(Invoice invoice) {
        int id = invoiceDAO.insert(invoice);
        return invoiceDAO.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> getInvoices(
            @QueryParam("invoice_number") String invoiceNumber,
            @QueryParam("po_number") String poNumber
    ) {
        List<Invoice> results = invoiceDAO.search(invoiceNumber, poNumber);
        return results;
    }
}
