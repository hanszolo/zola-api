package com.hanzolo.zolapi.invoice.models;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InvoiceMapper implements ResultSetMapper<Invoice> {
    public Invoice map(int index, ResultSet r, StatementContext statementContext) throws SQLException {
        return new Invoice(
                r.getInt("id"),
                r.getString("invoice_number"),
                r.getString("po_number"),
                r.getDate("due_date"),
                r.getLong("amount_cents"),
                new Date(r.getTimestamp("created_at").getTime())
        );
    }
}
