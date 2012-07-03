public abstract class CashReceiptsSearchResultsPresenter implements MvpPresenter<CashReceiptsSearchResultsView> {

    protected CashReceiptsSearchResultsView cashReceiptsSearchResultsView;
    private CashReceiptsSearchCriteriaBean cashReceiptsSearchCriteriaBean;

    protected CashReceiptsSearchResultsPresenter() {
        registerHandlers();
    }

    private void registerHandlers() {
        getEventBus().addHandler(FinanceCashReceiptEvent.TYPE, new FinanceCashReceiptEventHandler() {
            public void onEvent(FinanceCashReceiptEvent event, FinanceCashReceiptEventType eventType) {
                if (eventType.equals(FinanceCashReceiptEventType.CASH_RECEIPTS_SEARCH)) {

                    getApplicationData().getCashReceiptsApplicationData().getClientCashReceiptFmJPAContainer().removeAllFilters();
                    Option<Filter> filterOption = buildFilter(cashReceiptsSearchCriteriaBean);
                    if (filterOption.hasValue()) {
                        getApplicationData().getCashReceiptsApplicationData().getClientCashReceiptFmJPAContainer().addFilter(filterOption.get());
                    }
                    cashReceiptsSearchResultsView.showResults(getApplicationData().getCashReceiptsApplicationData().getClientCashReceiptFmJPAContainer());
                    
                }
            }
        });
    }
}