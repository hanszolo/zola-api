package com.hanzolo.zolapi.invoice.models;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(InvoiceMapper.class)
public interface InvoiceDAO {
    @SqlUpdate("" +
            "INSERT INTO `invoices` (`invoice_number`, `po_number`, `due_date`, `amount_cents`)" +
            "VALUES (:invoice_number, :po_number, :due_date, :amount_cents);"
    )
    @GetGeneratedKeys
    int insert(@InvoiceBinder Invoice invoice);

    @SqlQuery("" +
            "SELECT `id`, `invoice_number`, `po_number`, `due_date`, `amount_cents`, `created_at`" +
            "FROM `invoices`" +
            "WHERE id = :id"
    )
    Invoice getById(@Bind("id") int id);

    @SqlQuery("" +
            "SELECT `id`, `invoice_number`, `po_number`, `due_date`, `amount_cents`, `created_at`" +
            "FROM `invoices`" +
            "WHERE (:invoice_number IS NULL OR `invoice_number` = :invoice_number) AND" +
            "      (:po_number IS NULL OR `po_number` = :po_number)"
    )
    List<Invoice> search(@Bind("invoice_number") String invoiceNumber, @Bind("po_number") String poNumber);
}
