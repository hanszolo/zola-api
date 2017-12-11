package com.hanzolo.zolapi.invoice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Invoice {
    @JsonProperty
    private Integer id;

    @JsonProperty(value = "invoice_number", required = true)
    private String invoiceNumber;

    @JsonProperty(value = "po_number", required = true)
    private String poNumber;

    @JsonProperty(value = "due_date", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

    @JsonProperty(value = "amount_cents", required = true)
    private long amountCents;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date createdAt;

    public Invoice() {

    }

    public Invoice(Integer id, String invoiceNumber, String poNumber, Date dueDate, long amountCents, Date createdAt) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.poNumber = poNumber;
        this.dueDate = dueDate;
        this.amountCents = amountCents;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(long amountCents) {
        this.amountCents = amountCents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
