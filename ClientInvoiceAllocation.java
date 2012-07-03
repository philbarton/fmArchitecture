package com.fremantlemedia.rms.sales.model.entity;


import com.fremantlemedia.rms.coredata.model.entity.enums.YesNoEnum;
import com.fremantlemedia.rms.model.entity.audit.Created;
import com.fremantlemedia.rms.model.entity.audit.LastUpdated;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.security.Identity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "ClientInvoiceAllocation.allocatedInvoiceAmount",
                query = "select nvl(sum(cia.totalAmount),0) from ClientInvoiceAllocation cia " +
                        "where cia.clientInvoice = :clientInvoice " +
                        "and cia != :cia")
})

@Name("ClientInvoiceAllocationEntity")
@Table(name = "CLT_INV_ALLOC")
@Install(false)
@Restrict
public class ClientInvoiceAllocation extends ClientInvoiceAllocationBase {
    private Long clientInvoiceAllocationId;
    private List<ClientInvoiceAllocationAudit> clientInvoiceAllocationAudits;

    @Transient
    public Long getId() {
        return clientInvoiceAllocationId;
    }

    @Id
    @Column(name = "CLT_INV_ALLOC_ID", nullable = false)
    @GeneratedValue(generator = "seqCltInvoiceAlloc")
    @SequenceGenerator(name = "seqCltInvoiceAlloc", sequenceName = "SEQ_CLT_INV_ALLOC", allocationSize = 1)
    public Long getClientInvoiceAllocationId() {
        return clientInvoiceAllocationId;
    }

    public void setClientInvoiceAllocationId(Long clientInvoiceAllocationId) {
        this.clientInvoiceAllocationId = clientInvoiceAllocationId;
    }

    @OneToMany(mappedBy = "clientInvoiceAllocation", cascade = CascadeType.REFRESH)
    public List<ClientInvoiceAllocationAudit> getClientInvoiceAllocationAudits() {
        return clientInvoiceAllocationAudits;
    }

    public void setClientInvoiceAllocationAudits(List<ClientInvoiceAllocationAudit> clientInvoiceAllocationAudits) {
        this.clientInvoiceAllocationAudits = clientInvoiceAllocationAudits;
    }

    @PrePersist
    public void prePersist() {
        setCreated(Created.newInstance(Identity.instance().getCredentials().getUsername()));
    }

    @PreUpdate
    public void preUpdate() {
        setLastUpdated(LastUpdated.newInstance(Identity.instance().getCredentials().getUsername()));
    }

    public void setDefaults() {
        setTotalAmount(BigDecimal.ZERO);
        setVatAmount(BigDecimal.ZERO);
        setExchangeRateDifferenceAmount(BigDecimal.ZERO);
        setClientInvoiceAllocationDate(new Date());
        setPosted(YesNoEnum.N);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof ClientInvoiceAllocation))
            return false;

        final ClientInvoiceAllocation other = (ClientInvoiceAllocation) o;

        if (getClientInvoiceAllocationId() == null && other.getClientInvoiceAllocationId() != null) {
            return false;
        } else if (!getClientInvoiceAllocationId().equals(other.getClientInvoiceAllocationId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 + (getClientInvoiceAllocationId() != null ? getClientInvoiceAllocationId().hashCode() : 0);
    }
}
