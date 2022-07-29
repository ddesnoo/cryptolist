package ddesnoo.nl.CryptoList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this file all the functions used for the Endpoint /Currencies
 *
 */

public class H2DBFunctions_Currencies {

    /**
     * In this part we declare all the Tables and columns for the table "CURRENCIES"
     *
     * Table:
     * T_CC = "CURRENCIES"
     *
     * Columns:
     * C_TIC = "TICKER"
     * C_NAM = "NAME"
     * C_NOC = "NUMBER_OF_COINS"
     * C_MAC = "MARKET_CAP"
     *
     */
    //Database Tables met Columns voor gedefineerd
    //Table
    public static String T_CC = "CURRENCIES";

    //Columns
    public static String C_TIC = "TICKER", C_NAM = "NAME", C_NOC = "NUMBER_OF_COINS", C_MAC = "MARKET_CAP";

    /**
     * In this part we declare all the Prepared statements"
     *
     * INSERT
     * INSERT_CURRENCY_QUERY = "INSERT INTO " + T_CC + " (" + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + ") VALUES (?, ?, ?, ?);
     *
     * READ
     * -List all Currencies
     * READ_ALL_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC;
     *
     * -List currencies based on Paging parameters
     * READ_PAGING_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
     *
     * -List currencies based on Paging parameters and Sorting
     * READ_PAGING_AND_SORTING_CURRENCY_QUERY_TICKER = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY TICKER OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
     * READ_PAGING_AND_SORTING_CURRENCY_QUERY_NAME = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NAME OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
     * READ_PAGING_AND_SORTING_CURRENCY_QUERY_NOC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NUMBER_OF_COINS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
     * READ_PAGING_AND_SORTING_CURRENCY_QUERY_MaC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY MARKET_CAP OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
     *
     * -Show currency based on the currency's IDENTIFIER
     * READ_ID_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " WHERE ID =?;
     *
     * UPDATE
     * UPDATE_CURRENCY_QUERY = "UPDATE " + T_CC + " SET " + C_TIC + " = ?, " + C_NAM + " = ?, " + C_NOC + " = ?, " + C_MAC + " = ? WHERE ID = ?;
     *
     * DELETE
     * DELETE_CURRENCY_QUERY = "DELETE FROM " + T_CC + " WHERE ID = ?;
     *
     */
    //Prepared statements
        //Insert
    private static final String INSERT_CURRENCY_QUERY = "INSERT INTO " + T_CC + " (" + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + ") VALUES (?, ?, ?, ?);";
        //Read
    private static final String READ_ALL_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC;

    private static final String READ_PAGING_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

    private static final String READ_SORTING_CURRENCY_QUERY_TICKER = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY TICKER;";
    private static final String READ_SORTING_CURRENCY_QUERY_NAME = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NAME;";
    private static final String READ_SORTING_CURRENCY_QUERY_NOC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NUMBER_OF_COINS;";
    private static final String READ_SORTING_CURRENCY_QUERY_MaC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY MARKET_CAP;";

    private static final String READ_PAGING_AND_SORTING_CURRENCY_QUERY_TICKER = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY TICKER OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String READ_PAGING_AND_SORTING_CURRENCY_QUERY_NAME = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NAME OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String READ_PAGING_AND_SORTING_CURRENCY_QUERY_NOC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY NUMBER_OF_COINS OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
    private static final String READ_PAGING_AND_SORTING_CURRENCY_QUERY_MaC = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " ORDER BY MARKET_CAP OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

    private static final String READ_ID_CURRENCY_QUERY = "SELECT ID, " + C_TIC + ", " + C_NAM + ", " + C_NOC + ", " + C_MAC + " FROM " + T_CC + " WHERE ID =?;";
        //Update
    private static final String UPDATE_CURRENCY_QUERY = "UPDATE " + T_CC + " SET " + C_TIC + " = ?, " + C_NAM + " = ?, " + C_NOC + " = ?, " + C_MAC + " = ? WHERE ID = ?;";
        //Delete
    private static final String DELETE_CURRENCY_QUERY = "DELETE FROM " + T_CC + " WHERE ID = ?;";

    /**
     * Function for Inserting Records into the database
     *
     */

    public static ResponseCodeModel insertRecord(String content) throws SQLException {
        System.out.println(INSERT_CURRENCY_QUERY);
        ResponseCodeModel response = new ResponseCodeModel();
        JSONParser jsonParser = new JSONParser();

        String Tic, Nam, NOC, MaC;
        Boolean bodyread_succes;

        try {

            /**
             * Parse the body content into a Object
             * Make a JSONObject from the Object
             *
             * after that we retriev the Ticker, Name, Number_of_coins and the market_cap from the JSON
             *
             * and set the boolean bodyread_succes to true
             *
             * if the JSONparser fails it will set all the value for the variables to null and set a false as the value for the boolean bodyread_succes
             */

            Object obj = jsonParser.parse(content);
            JSONObject jsonObject = (JSONObject) obj;

            Tic = (String) jsonObject.get("ticker");
            Nam = (String) jsonObject.get("name");
            NOC = (String) jsonObject.get("numberofcoins");
            MaC = (String) jsonObject.get("marketcap");

            bodyread_succes = true;
            Logging.Log("JSON translation succeeded, Inserting Record");

        } catch (Exception e) {

            e.printStackTrace();

            Tic = null;
            Nam = null;
            NOC = null;
            MaC = null;

            bodyread_succes = false;

        }

        if(bodyread_succes) {
            try (
                    //Maak een Connectie met de database
                    Connection connection = H2DBCUtils.getConnection();
                    //Maak een statement door gebruik te maken van het connection object
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CURRENCY_QUERY)) {

                //Zet de waardes in de Prepared statement
                preparedStatement.setString(1, Tic); //Ticker
                preparedStatement.setString(2, Nam); //Name
                preparedStatement.setString(3, NOC); //Number of Coins
                preparedStatement.setString(4, MaC); //Market Cap

                //Print de statement uit voordat je hem uitvoer
                System.out.println(preparedStatement);

                //Voer de prepared statement uit
                preparedStatement.executeUpdate();

                response.setStatusCode(201);
                response.setStatus("Currency successfully created");

                Logging.Log("Record inserted, Returning succes response");

                return response;
            } catch (SQLException e) {

                //Print SQL Exception informatie
                H2DBCUtils.printSQLException(e);

                response.setStatusCode(400);
                response.setStatus("Currency not successfully created");

                Logging.Log("Record not inserted, Returning failure response, Error: " + e);

                return response;
            }
        }else{
            response.setStatusCode(400);
            response.setStatus("Invalid JSON, Please send in a valid JSON");

            Logging.Log("JSON translation not succeeded, Record not inserted, returning error to user");

            return response;
        }

    }

    /**
     * This function retrieves all the currencies records in the database and return these to the client/user
     */

    //Functie om all records uit de table "CURRENCIES" te lezen
    public static List<CurrencyModel> ReadAllRecords() {

        List<CurrencyModel> currencyModels = new ArrayList<>();

        try (
            //Maak een connectie met de database
            Connection connection = H2DBCUtils.getConnection();
            //Maak een statement door gebruik te maken van het connection object
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_CURRENCY_QUERY)
            ) {

            //Voer de Query uit
            ResultSet rs = preparedStatement.executeQuery();

            //Loop door de resultaten heen totdat er geen resultaten meer zijn
            while (rs.next()) {

                //We halen de waardes op uit de ResultSet
                int id = rs.getInt("ID");
                String ticker = rs.getString(C_TIC);
                String name = rs.getString(C_NAM);
                String number_of_coins = rs.getString(C_NOC);
                String market_cap = rs.getString(C_MAC);

                CurrencyModel currencyModel = new CurrencyModel();
                currencyModel.setID(id);
                currencyModel.setTicker(ticker);
                currencyModel.setName(name);
                currencyModel.setNumberOfCoins(number_of_coins);
                currencyModel.setMarketCap(market_cap);

                currencyModels.add(currencyModel);
            }

            Logging.Log("Collect all records from the database, Returning JSON to user");

        } catch (SQLException e) {

            //Print SQL Exception
            H2DBCUtils.printSQLException(e);
            currencyModels = null;

            Logging.Log("Their was an error collect all records from the database, Returning error to the user, SQL error: " + e);
        }

        return currencyModels;
    }

    /**
     * This function retrieves records based on the Paging parameters that were send with the API Call: /api/currencies?page={Selected page}&pagesize={Requisted Pagesize}
     *
     * First we try to Integer parse the Page and Pagesize parameters. After that we set the start en end for the paging parameters
     *
     * We than put the Paging parameters into the Prepared statement connection and run the SQL statement which will return the selected rows to the user
     */

    public static List<CurrencyModel> ReadAllRecords_Paging(String page, String pagesize) {

        int int_page;
        int int_pagesize;
        int rownum_start;
        int rownum_end;

        boolean canConvert;
        List<CurrencyModel> currencyModels = new ArrayList<>();

        try {

            int_page = Integer.parseInt(page);
            int_pagesize = Integer.parseInt(pagesize);

            if (int_page == 1) {
                rownum_start = 0;
                rownum_end = int_pagesize;
            } else if (int_pagesize == 1) {
                rownum_start = int_page - 1;
                rownum_end = 1;
            } else if (int_page == 1 && int_pagesize == 1) {
                rownum_start = 0;
                rownum_end = 0;
            } else {
                rownum_start = int_page;
                rownum_end = int_pagesize;
            }

            canConvert = true;

        } catch (Exception e) {

            e.printStackTrace();

            rownum_start = 0;
            rownum_end = 0;

            canConvert = false;

        }

        System.out.println("canConvert: " + canConvert);
        System.out.println("rownum_start " + rownum_start);
        System.out.println("rownum_end " + rownum_end);

        if (canConvert) {

            try (
                    //Maak een connectie met de database
                    Connection connection = H2DBCUtils.getConnection();
                    //Maak een statement door gebruik te maken van het connection object
                    PreparedStatement preparedStatement = connection.prepareStatement(READ_PAGING_CURRENCY_QUERY)
            ) {

                System.out.println("preparedStatement: " + preparedStatement);

                preparedStatement.setInt(1, rownum_start);
                preparedStatement.setInt(2, rownum_end);

                System.out.println("preparedStatement: " + preparedStatement);

                //Voer de Query uit
                ResultSet rs = preparedStatement.executeQuery();

                //Loop door de resultaten heen totdat er geen resultaten meer zijn
                while (rs.next()) {

                    //We halen de waardes op uit de ResultSet
                    int id = rs.getInt("ID");
                    String ticker = rs.getString(C_TIC);
                    String name = rs.getString(C_NAM);
                    String number_of_coins = rs.getString(C_NOC);
                    String market_cap = rs.getString(C_MAC);

                    CurrencyModel currencyModel = new CurrencyModel();
                    currencyModel.setID(id);
                    currencyModel.setTicker(ticker);
                    currencyModel.setName(name);
                    currencyModel.setNumberOfCoins(number_of_coins);
                    currencyModel.setMarketCap(market_cap);

                    currencyModels.add(currencyModel);
                }

            } catch (SQLException e) {

                //Print SQL Exception
                H2DBCUtils.printSQLException(e);

                currencyModels = null;
            }

        } else {
            currencyModels = null;
        }

        return currencyModels;
    }

    /**
     * This function retrieves records based on the Paging and Sorting parameters that were send with the API Call: /api/currencies?&Sort={Requisted Sort}
     *
     * First we check if the requisted sorting column matches with the availible columns
     *
     * We than put the Prepared statement into the Prepared statement connection and run the SQL statement which will return the rows in the sort requisted to the user
     */

    public static List<CurrencyModel> ReadAllRecords_Sorting(String sort) {

        List<CurrencyModel> currencyModels = new ArrayList<>();
        boolean doesexist;
        String PreparedQuery;

        sort = sort.toUpperCase();

        switch (sort) {
            case "TICKER":
                PreparedQuery = READ_SORTING_CURRENCY_QUERY_TICKER;
                doesexist = true;
                break;
            case "NAME":
                PreparedQuery = READ_SORTING_CURRENCY_QUERY_NAME;
                doesexist = true;
                break;
            case "NUMBER_OF_COINS":
                PreparedQuery = READ_SORTING_CURRENCY_QUERY_NOC;
                doesexist = true;
                break;
            case "MARKET_CAP":
                PreparedQuery = READ_SORTING_CURRENCY_QUERY_MaC;
                doesexist = true;
                break;
            default:
                PreparedQuery = null;
                doesexist = false;
                break;
        }

        System.out.println("Kom ik hier 260 " + doesexist);

        if (doesexist) {
            try (
                    //Maak een connectie met de database
                    Connection connection = H2DBCUtils.getConnection();
                    //Maak een statement door gebruik te maken van het connection object
                    PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery)
            ) {
                //Voer de Query uit
                ResultSet rs = preparedStatement.executeQuery();

                //Loop door de resultaten heen totdat er geen resultaten meer zijn
                while (rs.next()) {

                    //We halen de waardes op uit de ResultSet
                    int id = rs.getInt("ID");
                    String ticker = rs.getString(C_TIC);
                    String name = rs.getString(C_NAM);
                    String number_of_coins = rs.getString(C_NOC);
                    String market_cap = rs.getString(C_MAC);

                    CurrencyModel currencyModel = new CurrencyModel();
                    currencyModel.setID(id);
                    currencyModel.setTicker(ticker);
                    currencyModel.setName(name);
                    currencyModel.setNumberOfCoins(number_of_coins);
                    currencyModel.setMarketCap(market_cap);

                    currencyModels.add(currencyModel);
                }

            } catch (SQLException e) {

                //Print SQL Exception
                H2DBCUtils.printSQLException(e);
            }
        } else {
            currencyModels = null;
        }

        return currencyModels;
    }

    /**
     * This function retrieves records based on the Paging and Sorting parameters that were send with the API Call: /api/currencies?page={Selected page}&pagesize={Requisted Pagesize}&Sort={Requisted Sort}
     *
     * First we check if the requisted sorting column matches with the availible columns
     * if it does the procedure continues and try's to Integer parse the Page and Pagesize parameters. After that we set the start en end for the paging parameters
     *
     * We than put the Prepared statement and Sort + Paging parameters into the Prepared statement connection and run the SQL statement which will return the selected rows to the user
     */

    public static List<CurrencyModel> ReadAllRecords_Paging_And_Sorting(String page, String pagesize, String sort) {
        boolean doesexist;
        String PreparedQuery;
        List<CurrencyModel> currencyModels = new ArrayList<>();

        sort = sort.toUpperCase();

        switch (sort) {
            case "TICKER":
                doesexist = true;
                PreparedQuery = READ_PAGING_AND_SORTING_CURRENCY_QUERY_TICKER;
                break;
            case "NAME":
                doesexist = true;
                PreparedQuery = READ_PAGING_AND_SORTING_CURRENCY_QUERY_NAME;
                break;
            case "NUMBER_OF_COINS":
                doesexist = true;
                PreparedQuery = READ_PAGING_AND_SORTING_CURRENCY_QUERY_NOC;
                break;
            case "MARKET_CAP":
                doesexist = true;
                PreparedQuery = READ_PAGING_AND_SORTING_CURRENCY_QUERY_MaC;
                break;
            default:
                doesexist = false;
                PreparedQuery = null;
                break;
        }

        int int_page;
        int int_pagesize;
        int rownum_start;
        int rownum_end;

        boolean canConvert;

        try {

            int_page = Integer.parseInt(page);
            int_pagesize = Integer.parseInt(pagesize);

            if (int_page == 1) {
                rownum_start = 0;
                rownum_end = int_pagesize;
            } else if (int_pagesize == 1) {
                rownum_start = int_page - 1;
                rownum_end = 1;
            } else if (int_page == 1 && int_pagesize == 1) {
                rownum_start = 0;
                rownum_end = 0;
            } else {
                rownum_start = int_page;
                rownum_end = int_pagesize;
            }

            canConvert = true;

        } catch (Exception e) {

            e.printStackTrace();

            rownum_start = 0;
            rownum_end = 0;

            canConvert = false;

        }

        if (doesexist || canConvert) {
            try (
                    //Maak een connectie met de database
                    Connection connection = H2DBCUtils.getConnection();
                    //Maak een statement door gebruik te maken van het connection object
                    PreparedStatement preparedStatement = connection.prepareStatement(PreparedQuery)
            ) {
                preparedStatement.setInt(1, rownum_start);
                preparedStatement.setInt(2, rownum_end);

                //Voer de Query uit
                ResultSet rs = preparedStatement.executeQuery();

                //Loop door de resultaten heen totdat er geen resultaten meer zijn
                while (rs.next()) {

                    //We halen de waardes op uit de ResultSet
                    int id = rs.getInt("ID");
                    String ticker = rs.getString(C_TIC);
                    String name = rs.getString(C_NAM);
                    String number_of_coins = rs.getString(C_NOC);
                    String market_cap = rs.getString(C_MAC);

                    CurrencyModel currencyModel = new CurrencyModel();
                    currencyModel.setID(id);
                    currencyModel.setTicker(ticker);
                    currencyModel.setName(name);
                    currencyModel.setNumberOfCoins(number_of_coins);
                    currencyModel.setMarketCap(market_cap);

                    currencyModels.add(currencyModel);
                }

            } catch (SQLException e) {

                //Print SQL Exception
                H2DBCUtils.printSQLException(e);
            }
        } else {
            currencyModels = null;
        }

        return currencyModels;
    }

    /**
     * This function retrieves a Record based on the ID that was given in the get call to the endpoint: /api/currencies/{ID}
     */

    //Functie om records uit de table "CURRENCIES" te lezen op basis van het ID (Identifiers)
    public static CurrencyModel ReadRecord_ID(String Identifier) {

        CurrencyModel currencyModel = new CurrencyModel();

        try (
                //Maak een connectie met de database
                Connection connection = H2DBCUtils.getConnection();
                //Maak een statement door gebruik te maken van het connection object
                PreparedStatement preparedStatement = connection.prepareStatement(READ_ID_CURRENCY_QUERY)
        ) {

            preparedStatement.setString(1, Identifier);

            //Voer de Query uit
            ResultSet rs = preparedStatement.executeQuery();

            //Loop door de resultaten heen totdat er geen resultaten meer zijn
            while (rs.next()) {

                //We halen de waardes op uit de ResultSet
                int id = rs.getInt("ID");
                String ticker = rs.getString(C_TIC);
                String name = rs.getString(C_NAM);
                String number_of_coins = rs.getString(C_NOC);
                String market_cap = rs.getString(C_MAC);

                currencyModel.setID(id);
                currencyModel.setTicker(ticker);
                currencyModel.setName(name);
                currencyModel.setNumberOfCoins(number_of_coins);
                currencyModel.setMarketCap(market_cap);
            }

        } catch (SQLException e) {

            //Print SQL Exception
            H2DBCUtils.printSQLException(e);

        }

        return currencyModel;

    }

    /**
     * This function updates a record based on the ID received from a PUT Call and the Body that was sent with the Call to the Endpoint
     *
     * First it will try to parse the content of the body. if this is a succes than it will continue and try to update the record.
     * If the parse does not succeed the user will receive a 400 error code.
     *
     * When a Currency has been updated the user will receive a succes response with code 200.
     */

    public static ResponseCodeModel UpdateRecord(String Identifier, String Content) {

        JSONParser jsonParser = new JSONParser();
        ResponseCodeModel response = new ResponseCodeModel();
        boolean bodyread_succes;
        String Ticker;
        String Name;
        String NumberOfCoins;
        String MarketCap;

        try {

            Object obj = jsonParser.parse(Content);
            JSONObject jsonObject = (JSONObject) obj;

            Ticker = (String) jsonObject.get("ticker");
            Name = (String) jsonObject.get("name");
            NumberOfCoins = (String) jsonObject.get("numberofcoins");
            MarketCap = (String) jsonObject.get("marketcap");

            Logging.Log("JSON translation succeeded, Now updating record");

            bodyread_succes = true;

        } catch (Exception e) {

            e.printStackTrace();

            Ticker = null;
            Name = null;
            NumberOfCoins = null;
            MarketCap = null;

            bodyread_succes = false;

            Logging.Log("Their was an error while translating the Body-Content, Returning Error, Body-Content: " + Content + ", Error:" + e);

        }

        if (bodyread_succes) {
            try (Connection connection = H2DBCUtils.getConnection();
                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CURRENCY_QUERY)) {
                preparedStatement.setString(1, Ticker);
                preparedStatement.setString(2, Name);
                preparedStatement.setString(3, NumberOfCoins);
                preparedStatement.setString(4, MarketCap);
                preparedStatement.setString(5, Identifier);

                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();

                response.setStatusCode(200);
                response.setStatus("Currency updated");
                Logging.Log("Currency not updated, Returning Error to JSON");

                return response;

            } catch (SQLException e) {

                // print SQL exception information
                H2DBCUtils.printSQLException(e);

                response.setStatusCode(400);
                response.setStatus("Currency not updated");
                Logging.Log("Currency not updated, Returning Error to JSON, SQL error: " + e);

                return response;
            }
        } else {
            response.setStatusCode(400);
            response.setStatus("JSON not valid, Please send a valid JSON.");
            Logging.Log("JSON invalid, Returning error to user");

            return response;
        }

    }

    /**
     * This function deletes a record based on the ID received from a DELETE Call to the Endpoint with a specific ID
     *
     * When a Currency has been deleted the user will receive a succes response with code 200.
     */

    public static ResponseCodeModel DeleteRecord(String Identifier) {

        ResponseCodeModel response = new ResponseCodeModel();

        try (
                //Maak een connectie met de database
                Connection connection = H2DBCUtils.getConnection();
                //Maak een statement door gebruik te maken van het connection object
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CURRENCY_QUERY)
        ) {
            preparedStatement.setString(1, Identifier);

            //Voer de Query uit
            preparedStatement.execute();

            response.setStatusCode(204);
            response.setStatus("Currency deleted successfully");
            Logging.Log("Currency deleted, Returning succes message to user");

            return response;

        } catch (SQLException e) {

            //Print SQL Exception
            H2DBCUtils.printSQLException(e);

            response.setStatusCode(400);
            response.setStatus("Currency not deleted");
            Logging.Log("Currency not deleted, Returning Error message to user, SQL error: " + e);

            return response;
        }

    }

}
