package com.kazontech.kazon;

public class ListDataModel {

    String buy_sell_flag;
    String symbol;
    String call_price;
    String call_time;
    String sell_price;
    String sell_time;
    String ltp;
    String gain_loss;

    public ListDataModel(String buy_sell_flag, String symbol, String call_price, String call_time,
                         String ltp, String gain_loss, String sell_price, String sell_time) {
        this.buy_sell_flag=buy_sell_flag;
        this.symbol=symbol;
        this.call_price=call_price;
        this.call_time=call_time;
        this.ltp=ltp;
        this.gain_loss=gain_loss;
        this.sell_price=sell_price;
        this.sell_time=sell_time;
    }

    public String getBuySellFlag() {
        return buy_sell_flag;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getCallPrice() {
        return call_price;
    }

    public String getCallTime() {
        return call_time;
    }
    public String getSellPrice() {
        return sell_price;
    }

    public String getSellTime() {
        return sell_time;
    }
    public String getLtp() {
        return ltp;
    }

    public String getGainLoss() {
        return gain_loss;
    }
}