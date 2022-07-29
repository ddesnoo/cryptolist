package ddesnoo.nl.CryptoList;

public class CurrencyModel {

    /**
     * In this file we create a custom model for a currency. This will make it easy to return a JSON with the desired fields
     *
     * fields:
     * ID
     * Ticker
     * Name
     * NumberOfCoins
     * MarketCap
     *
     * JSON:
     * {
     *  "name": "Ripple",
     *  "id": 3,
     *  "numberOfCoins": "38590000",
     *  "marketCap": "64750000",
     *  "ticker": "XRP"
     * }
     */

    private int ID;
    private String Ticker;
    private String Name;
    private String NumberOfCoins;
    private String MarketCap;

    public CurrencyModel(int ID, String ticker, String name, String numberOfCoins, String marketCap) {
        this.ID = ID;
        Ticker = ticker;
        Name = name;
        NumberOfCoins = numberOfCoins;
        MarketCap = marketCap;
    }

    public CurrencyModel() {
    }

    @Override
    public String toString() {
        return "CurrencyModel{" +
                "ID=" + ID +
                ", Ticker='" + Ticker + '\'' +
                ", Name='" + Name + '\'' +
                ", NumberOfCoins='" + NumberOfCoins + '\'' +
                ", MarketCap='" + MarketCap + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumberOfCoins() {
        return NumberOfCoins;
    }

    public void setNumberOfCoins(String numberOfCoins) {
        NumberOfCoins = numberOfCoins;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        MarketCap = marketCap;
    }
}
