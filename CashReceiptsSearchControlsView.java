public abstract class CashReceiptsSearchControlsView extends ButtonBarLayout implements MvpView {
    protected boolean needsBuilding = true;

    public void buildView() {
        if (needsBuilding) {
            needsBuilding = false;

            FmButton searchBtn = new FmButton("Search",
                    new Button.ClickListener() {
                        public void buttonClick(Button.ClickEvent event) {
                            getEventBus().fireEvent(new FinanceCashReceiptEvent(FinanceCashReceiptEventType.CASH_RECEIPTS_SEARCH));
                        }
                    });

            searchBtn.setStyleName("primary");
            searchBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

            FmButton clearBtn = new FmButton("Clear",
                    new Button.ClickListener() {
                        public void buttonClick(Button.ClickEvent event) {
                            getEventBus().fireEvent(new FinanceCashReceiptEvent(FinanceCashReceiptEventType.CASH_RECEIPTS_SEARCH_CLEAR));
                        }
                    });

            addComponent(searchBtn);
            addComponent(clearBtn);
        }
    }
}
