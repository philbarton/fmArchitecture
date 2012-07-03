package com.fremantlemedia.rms.sales.ria.model.containerfactories;

import com.fremantlemedia.rms.sales.model.entity.ClientCashReceiptAudit;
import com.fremantlemedia.rms.sales.ria.model.FmJPAContainer;

import static com.fremantlemedia.rms.sales.ria.model.containerfactories.ClientCashReceiptContainerFactory.*;

public class ClientCashReceiptAuditContainerFactory extends AbstractContainerFactory<ClientCashReceiptAudit> {

    public FmJPAContainer<ClientCashReceiptAudit> createClientCashReceiptAuditContainer() {
        FmJPAContainer<ClientCashReceiptAudit> entityContainer = super.createEditableEntityContainer(ClientCashReceiptAudit.class);
        entityContainer.sort(new Object[]{VERSION}, new boolean[]{true});
        entityContainer.addNestedContainerProperty(GBP_ACCOUNT_ACC_NAME);
        entityContainer.addNestedContainerProperty(GBP_ACCOUNT_ACC_ID);
        entityContainer.addNestedContainerProperty(COMPANY_CODE);
        entityContainer.setWriteThrough(false);
        return entityContainer;
    }
}
