
<html>
  <head>
    <script type="text/javascript"
          src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>

          <script type="text/javascript">
                google.load("visualization", "1", {packages: ["corechart"]});
                google.setOnLoadCallback(drawChart);
                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Jaar', 'Aantal klachten per maand weergegeven'],
                        ['1', ${monthMap.get("jan")}],
                        ['2', ${monthMap.get("feb")}],
                        ['3', ${monthMap.get("maart")}],
                        ['4', ${monthMap.get("april")}],
                        ['5', ${monthMap.get("mei")}],
                        ['6', ${monthMap.get("juni")}],
                        ['7', ${monthMap.get("juli")}],
                        ['8', ${monthMap.get("augustus")}],
                        ['9', ${monthMap.get("september")}],
                        ['10', ${monthMap.get("oktober")}],
                        ['11', ${monthMap.get("november")}],
                        ['12', ${monthMap.get("december")}]

                    ]);

                    var options = {
                        title: 'Aantal klachten over tijd',
                        hAxis: {title: 'Maanden', titleTextStyle: {color: '#333'}},
                        vAxis: {minValue: 0}
                    };
                    var chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
                    chart.draw(data, options);
                }
            </script>
  </head>
  <body>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>
  </body>
</html>
