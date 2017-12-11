package com.hanzolo.zolapi.invoice.models;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

// our binding annotation
@BindingAnnotation(InvoiceBinder.InvoiceBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface InvoiceBinder
{

    class InvoiceBinderFactory implements BinderFactory
    {
        public Binder build(Annotation annotation)
        {
            return new Binder<InvoiceBinder, Invoice>()
            {
                public void bind(SQLStatement q, InvoiceBinder bind, Invoice arg)
                {
                    q.bind("id", arg.getId());
                    q.bind("invoice_number", arg.getInvoiceNumber());
                    q.bind("po_number", arg.getPoNumber());
                    q.bind("due_date", arg.getDueDate());
                    q.bind("amount_cents", arg.getAmountCents());
                    q.bind("created_at", arg.getCreatedAt());
                }
            };
        }
    }
}