     

        getEventBus().addHandler(FinanceCashReceiptEvent.TYPE, new FinanceCashReceiptEventHandler() {
            public void onEvent(FinanceCashReceiptEvent event, FinanceCashReceiptEventType eventType) {
                if (eventType.equals(FinanceCashReceiptEventType.CASH_RECEIPT_VERSION_CHANGE)) {

                    CashReceiptsApplicationData cashReceiptsApplicationData = getApplicationData().getCashReceiptsApplicationData();
                    ClientCashReceipt entity = cashReceiptsApplicationData.getClientCashReceiptEntityItem().getEntity();

                    FmJPAContainer<ClientCashReceiptAudit> clientCashReceiptAuditFmJPAContainer = cashReceiptsApplicationData.getClientCashReceiptAuditFmJPAContainer();
                    clientCashReceiptAuditFmJPAContainer.removeAllFilters();
                    clientCashReceiptAuditFmJPAContainer.addFilter(Filters.eq(CLIENT_CASH_RECEIPT, entity));
                    Version version = cashReceiptsApplicationData.getVersion();
                    clientCashReceiptAuditFmJPAContainer.addFilter(Filters.eq(VERSION, version.getCurrentVersion()));
                    clientCashReceiptAuditFmJPAContainer.applyFilters();

                    Object firstItem = clientCashReceiptAuditFmJPAContainer.firstItemId();
                    EntityItem<ClientCashReceiptAudit> clientCashReceiptAuditEntityItem =
                            clientCashReceiptAuditFmJPAContainer.getItem(firstItem);

                    cashReceiptVersionsView.setModel(clientCashReceiptAuditEntityItem, version);
                }
            }
        });
