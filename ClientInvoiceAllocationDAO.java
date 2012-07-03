package com.fremantlemedia.rms.sales.ria.service.cashapplication;

import com.fremantlemedia.rms.sales.model.entity.ClientInvoiceAllocation;
import com.fremantlemedia.rms.sales.ria.service.common.CommonDAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;

public class ClientInvoiceAllocationDAO extends CommonDAO<ClientInvoiceAllocation> {
    public BigDecimal getAllocatedInvoiceAmount(ClientInvoiceAllocation clientInvoiceAllocation) {
        EntityManager e = entityManager;
        Query query = e.createNamedQuery("ClientInvoiceAllocation.allocatedInvoiceAmount");
        query.setParameter("clientInvoice", clientInvoiceAllocation.getClientInvoice());
        query.setParameter("cia", clientInvoiceAllocation);
        return (BigDecimal) query.getSingleResult();
    }

}
