/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "post password-1"], "isController": false}, {"data": [1.0, 500, 1500, "get repport page"], "isController": false}, {"data": [1.0, 500, 1500, "post login"], "isController": false}, {"data": [1.0, 500, 1500, "post password-0"], "isController": false}, {"data": [1.0, 500, 1500, "post login-2"], "isController": false}, {"data": [1.0, 500, 1500, "post bug repport"], "isController": false}, {"data": [1.0, 500, 1500, "post login-1"], "isController": false}, {"data": [1.0, 500, 1500, "post login-0"], "isController": false}, {"data": [1.0, 500, 1500, "get bug repport page"], "isController": false}, {"data": [1.0, 500, 1500, "get bug repport page-2"], "isController": false}, {"data": [1.0, 500, 1500, "post new bug repport"], "isController": false}, {"data": [1.0, 500, 1500, "get bug repport page-3"], "isController": false}, {"data": [1.0, 500, 1500, "get login page"], "isController": false}, {"data": [1.0, 500, 1500, "get bug repport page-0"], "isController": false}, {"data": [1.0, 500, 1500, "post password"], "isController": false}, {"data": [1.0, 500, 1500, "get bug repport page-1"], "isController": false}, {"data": [1.0, 500, 1500, "post repport bug"], "isController": false}, {"data": [1.0, 500, 1500, "get login page-0"], "isController": false}, {"data": [1.0, 500, 1500, "get login page-2"], "isController": false}, {"data": [1.0, 500, 1500, "get login page-1"], "isController": false}, {"data": [1.0, 500, 1500, "post password-3"], "isController": false}, {"data": [1.0, 500, 1500, "post password-2"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 80, 0, 0.0, 77.37499999999999, 33, 308, 44.5, 175.90000000000006, 202.0, 308.0, 36.166365280289334, 517.3472288935352, 16.362278198462928], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["post password-1", 7, 0, 0.0, 37.57142857142857, 34, 40, 38.0, 40.0, 40.0, 40.0, 3.6572622779519333, 2.217929565047022, 1.1428944618599792], "isController": false}, {"data": ["get repport page", 1, 0, 0.0, 52.0, 52, 52, 52.0, 52.0, 52.0, 52.0, 19.230769230769234, 484.95718149038464, 5.502554086538462], "isController": false}, {"data": ["post login", 7, 0, 0.0, 103.28571428571429, 41, 164, 141.0, 164.0, 164.0, 164.0, 3.615702479338843, 87.70499741735537, 2.500928137913223], "isController": false}, {"data": ["post password-0", 7, 0, 0.0, 41.857142857142854, 38, 48, 42.0, 48.0, 48.0, 48.0, 3.6288232244686367, 2.9129811430793158, 1.1016070502851218], "isController": false}, {"data": ["post login-2", 4, 0, 0.0, 72.25, 66, 82, 70.5, 82.0, 82.0, 82.0, 2.805049088359046, 90.49501994214587, 0.8108345021037868], "isController": false}, {"data": ["post bug repport", 1, 0, 0.0, 308.0, 308, 308, 308.0, 308.0, 308.0, 308.0, 3.246753246753247, 32.023640422077925, 2.362139813311688], "isController": false}, {"data": ["post login-1", 4, 0, 0.0, 36.5, 34, 41, 35.5, 41.0, 41.0, 41.0, 2.871500358937545, 1.7554289303661164, 0.8104136755204594], "isController": false}, {"data": ["post login-0", 4, 0, 0.0, 39.25, 38, 41, 39.0, 41.0, 41.0, 41.0, 2.8591851322373123, 15.65361977305218, 1.1803911275911365], "isController": false}, {"data": ["get bug repport page", 1, 0, 0.0, 162.0, 162, 162, 162.0, 162.0, 162.0, 162.0, 6.172839506172839, 122.95042438271605, 7.746190200617284], "isController": false}, {"data": ["get bug repport page-2", 1, 0, 0.0, 39.0, 39, 39, 39.0, 39.0, 39.0, 39.0, 25.64102564102564, 18.5546875, 8.313301282051283], "isController": false}, {"data": ["post new bug repport", 1, 0, 0.0, 45.0, 45, 45, 45.0, 45.0, 45.0, 45.0, 22.22222222222222, 192.94704861111111, 15.668402777777779], "isController": false}, {"data": ["get bug repport page-3", 1, 0, 0.0, 44.0, 44, 44, 44.0, 44.0, 44.0, 44.0, 22.727272727272727, 406.8936434659091, 7.191051136363637], "isController": false}, {"data": ["get login page", 7, 0, 0.0, 155.0, 91, 202, 142.0, 202.0, 202.0, 202.0, 3.721424774056353, 88.46534838516746, 2.027885765550239], "isController": false}, {"data": ["get bug repport page-0", 1, 0, 0.0, 41.0, 41, 41, 41.0, 41.0, 41.0, 41.0, 24.390243902439025, 15.720274390243901, 7.121760670731707], "isController": false}, {"data": ["post password", 7, 0, 0.0, 174.0, 110, 216, 183.0, 216.0, 216.0, 216.0, 3.3751205400192865, 97.57479131509162, 3.7353280195274836], "isController": false}, {"data": ["get bug repport page-1", 1, 0, 0.0, 38.0, 38, 38, 38.0, 38.0, 38.0, 38.0, 26.31578947368421, 17.012746710526315, 8.480674342105264], "isController": false}, {"data": ["post repport bug", 1, 0, 0.0, 47.0, 47, 47, 47.0, 47.0, 47.0, 47.0, 21.27659574468085, 330.55601728723406, 9.453956117021276], "isController": false}, {"data": ["get login page-0", 4, 0, 0.0, 38.5, 36, 42, 38.0, 42.0, 42.0, 42.0, 2.8149190710767065, 14.338494018296974, 0.8081896551724138], "isController": false}, {"data": ["get login page-2", 4, 0, 0.0, 72.0, 66, 84, 69.0, 84.0, 84.0, 84.0, 2.7681660899653977, 88.64011137543253, 0.8001730103806228], "isController": false}, {"data": ["get login page-1", 4, 0, 0.0, 36.0, 33, 38, 36.5, 38.0, 38.0, 38.0, 2.8328611898017, 1.7318077195467423, 0.7995086756373938], "isController": false}, {"data": ["post password-3", 5, 0, 0.0, 76.2, 65, 92, 72.0, 92.0, 92.0, 92.0, 2.5706940874035986, 84.76813544344473, 0.7430912596401028], "isController": false}, {"data": ["post password-2", 7, 0, 0.0, 39.00000000000001, 35, 48, 38.0, 48.0, 48.0, 48.0, 3.6610878661087867, 14.451797038441423, 1.0404068057008369], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 80, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
