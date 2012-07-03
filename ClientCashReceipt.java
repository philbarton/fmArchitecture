package com.fremantlemedia.rms.sales.model.entity;

import com.fremantlemedia.rms.coredata.model.entity.enums.YesNoEnum;
import com.fremantlemedia.rms.model.entity.audit.Created;
import com.fremantlemedia.rms.model.entity.audit.LastUpdated;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Identity;

import javax.persistence.*;
import java.util.List;

@Entity
@Name("ClientCashReceiptEntity")
@Table(name = "CLT_CASH_RCPT")
@Install(false)
public class ClientCashReceipt extends ClientCashReceiptBase {
    private Long receiptId;
    private List<ClientCashReceiptAudit> clientCashReceiptAudits;
    private List<ClientInvoiceAllocation> clientInvoiceAllocations;
    private List<ClientSaleAllocation> clientSaleAllocations;
    private List<AdditionalFeeAllocation> additionalFeeAllocations;
    private YesNoEnum eurosReceived;
    private Integer version;

    @Transient
    public Long getId() {
        return receiptId;
    }

    @Id
    @Column(name = "RCPT_ID")
    @GeneratedValue(generator = "seqCltCashReceipt")
    @SequenceGenerator(name = "seqCltCashReceipt", sequenceName = "SEQ_CLT_CASH_RCPT", allocationSize = 1)
    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    @OneToMany(mappedBy = "clientCashReceipt", cascade = CascadeType.REFRESH)
    public List<ClientCashReceiptAudit> getClientCashReceiptAudits() {
        return clientCashReceiptAudits;
    }

    public void setClientCashReceiptAudits(List<ClientCashReceiptAudit> clientCashReceiptAudits) {
        this.clientCashReceiptAudits = clientCashReceiptAudits;
    }

    @OneToMany(mappedBy = "clientCashReceipt", cascade = CascadeType.REFRESH)
    public List<AdditionalFeeAllocation> getAdditionalFeeAllocations() {
        return additionalFeeAllocations;
    }

    public void setAdditionalFeeAllocations(List<AdditionalFeeAllocation> additionalFeeAllocations) {
        this.additionalFeeAllocations = additionalFeeAllocations;
    }

    @OneToMany(mappedBy = "clientCashReceipt", cascade = CascadeType.REFRESH)
    public List<ClientInvoiceAllocation> getClientInvoiceAllocations() {
        return clientInvoiceAllocations;
    }

    public void setClientInvoiceAllocations(List<ClientInvoiceAllocation> clientInvoiceAllocations) {
        this.clientInvoiceAllocations = clientInvoiceAllocations;
    }

    @OneToMany(mappedBy = "clientCashReceipt", cascade = CascadeType.REFRESH)
    public List<ClientSaleAllocation> getClientSaleAllocations() {
        return clientSaleAllocations;
    }

    public void setClientSaleAllocations(List<ClientSaleAllocation> clientSaleAllocations) {
        this.clientSaleAllocations = clientSaleAllocations;
    }

    @Column(name = "CCY_RECD_EURO", nullable = false)
    @Enumerated(EnumType.STRING)
    public YesNoEnum getEurosReceived() {
        return eurosReceived;
    }

    public void setEurosReceived(YesNoEnum eurosReceived) {
        this.eurosReceived = eurosReceived;
    }

    @Column(name = "VERSION", nullable = false)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void incrementVersion() {
        setVersion(getVersion() + 1);
    }

    @PrePersist
    public void prePersist() {
        this.setCreated(Created.newInstance(Identity.instance().getCredentials().getUsername()));
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastUpdated(LastUpdated.newInstance(Identity.instance().getCredentials().getUsername()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof ClientCashReceipt))
            return false;

        final ClientCashReceipt other = (ClientCashReceipt) o;

        if (getReceiptId() == null && other.getReceiptId() != null) {
            return false;
        } else if (!getReceiptId().equals(other.getReceiptId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 + (getReceiptId() != null ? getReceiptId().hashCode() : 0);
    }
}
