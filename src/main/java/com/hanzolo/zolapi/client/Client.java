package com.hanzolo.zolapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hanzolo.zolapi.invoice.models.Invoice;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Client {
    private static final String URL = "http://localhost:8080/v1/invoices";

    public Client() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public List<Invoice> searchInvoices(String invoiceNumber, String poNumber) throws UnirestException {
        HttpResponse<Invoice[]> response = Unirest.get(URL)
                .header("Accept", "application/json")
                .queryString("invoice_number", invoiceNumber)
                .queryString("po_number", poNumber)
                .asObject(Invoice[].class);
        if (response.getStatus() != 200) {
            throw new UnirestException("Something went wrong with the request: " + response.getRawBody());
        }

        return Arrays.asList(response.getBody());
    }

    public Invoice postInvoice(String invoiceNumber, String poNumber, Date dueDate, long amountCents) throws UnirestException {
        return this.postInvoice(new Invoice(null, invoiceNumber, poNumber, dueDate, amountCents, null));
    }

    public Invoice postInvoice(Invoice invoice) throws UnirestException {
        HttpResponse<Invoice> response = Unirest.post(URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(invoice)
                .asObject(Invoice.class);
        if (response.getStatus() != 200) {
            throw new UnirestException("Something went wrong with the request: " + response.getRawBody());
        }

        return response.getBody();
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.postInvoice("ABC123", "PO654321", new Date(), 3000l);
        List<Invoice> invoices = client.searchInvoices("ABC123", "PO654321");
        for (Invoice i : invoices) {
            System.out.println(i.getInvoiceNumber() + " " + i.getPoNumber() + i.getDueDate());
        }
    }
}
