
var sql = require("mssql");
var _ = require('lodash');
const config = require("./cypress/config/config")

//1
function executeStoredProc() {
    //2. 
    var dbConn = new sql.ConnectionPool(config.dbConfig);
    dbConn.connect().then(function () {
         
        //3.
        var request = new sql.Request(dbConn);

        request.execute("<your stored procedure name>")
        .then(function (recordSet) {
            //4.
            console.log(recordSet);
            dbConn.close();
        }).catch(function (err) {
            //5.
            console.log(err);
            dbConn.close();
        });
    }).catch(function (err) {
        //6.
        console.log(err);
    });
}

//7.
executeStoredProc();

