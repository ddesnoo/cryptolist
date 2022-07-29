package ddesnoo.nl.CryptoList;


import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * In this file we create a RestController with listens for the RequestMapping base path /API
 */

@RestController
@RequestMapping(path = "/api")
public class CurrenciesController {

    /**
     * Here we create a GetMapping for a specific Currency that we retriev based on the ID of the Currency
     */
    //GET Connectors
    @GetMapping("/currencies/{ID}")
    public CurrencyModel id_currency(@PathVariable(value = "ID") String ID) {
        Logging.Log("Get request received on /currencies/{ID}, Record requested for ID: " + ID);
        return H2DBFunctions_Currencies.ReadRecord_ID(ID);
    }

    /**
     * Here we create a GetMapping for all the other types of GET Request not based on a specific ID. This includes the functions for Sorting and Paging
     */
    @GetMapping("/currencies")
    public List<CurrencyModel> all_currencies(@RequestParam(value = "page", defaultValue = "null") String page, @RequestParam(value = "pagesize", defaultValue = "null") String pagesize, @RequestParam(value = "sort", defaultValue = "null") String sort) {

        if(page.equals("null") && pagesize.equals("null") && sort.equals("null")){
            Logging.Log("Get request received on /currencies, No RequestParams were given, Returning all records from the database");
            return H2DBFunctions_Currencies.ReadAllRecords();
        } else if (!page.equals("null") && !pagesize.equals("null") && !sort.equals("null")) {
            Logging.Log("Get request received on /currencies, Sorting and paging requestparams given, Returning records from the database based on the following parameters: Sort: " + sort + ", Page: " + page + " Pagesize: " + pagesize);
            return H2DBFunctions_Currencies.ReadAllRecords_Paging_And_Sorting(page, pagesize, sort);
        } else if (!page.equals("null") && !pagesize.equals("null") && sort.equals("null")) {
            Logging.Log("Get request received on /currencies, Paging requestparam given, No Sorting requestparam given, Returning records from the database based on page: " + page + " and pagesize: " + pagesize);
            return H2DBFunctions_Currencies.ReadAllRecords_Paging(page, pagesize);
        } else if (page.equals("null") && pagesize.equals("null") && !sort.equals("null")) {
            Logging.Log("Get request received on /currencies, Sorting requestparam given, No Paging requestparams given, Returning all records from the database sorted by " + sort);
            return H2DBFunctions_Currencies.ReadAllRecords_Sorting(sort);
        } else {
            Logging.Log("non-standerd Get request received on /currencies, returning all records from the database");
            return H2DBFunctions_Currencies.ReadAllRecords();
        }

    }

    /**
     * Here we create a PostMapping for Inserting a new Currency into the database
     */
    //POST Connector
    @PostMapping("/currencies")
    public ResponseCodeModel Insert_Currency(@RequestBody String content) throws SQLException {
        Logging.Log("post request received on /currencies, body-content received: " + content);
        return H2DBFunctions_Currencies.insertRecord(content);
    }

    /**
     * Here we create a PutMapping for updating a Currency in the database based on the ID we put behand the last /
     */
    //PUT Connector
    @PutMapping("/currencies/{ID}")
    public ResponseCodeModel Update_Currency(@RequestBody String content, @PathVariable(value = "ID") String ID){
        Logging.Log("put request received on /currencies, body-content received: " + content);
        ResponseCodeModel response = H2DBFunctions_Currencies.UpdateRecord(ID, content);
        return response;
    }

    /**
     * Here we create a DeleteMapping for Deleting a Currency in the database based on the ID we put behand the last /
     */
    //DELETE Connector
    @DeleteMapping("/currencies/{ID}")
    public ResponseCodeModel Delete_Currency(@PathVariable(value = "ID") String ID){
        Logging.Log("delete request received on /currencies, Requested record for deletion: " + ID);
        ResponseCodeModel response = H2DBFunctions_Currencies.DeleteRecord(ID);
        return response;
    }

}
