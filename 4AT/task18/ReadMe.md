/Users/adamogorodnik/Downloads/apache-jmeter-5.6.3/bin/jmeter -n -t login_userflow.jmx

/Users/adamogorodnik/Downloads/apache-jmeter-5.6.3/bin/jmeter -n -t login_userflow.jmx -Jduration=10

/Users/adamogorodnik/Downloads/apache-jmeter-5.6.3/bin/jmeter -g summary.csv -o report

jmeter -n –t login_userflow.jmx -Jload_profile="const(10,10s) line(10,100,1m) step(5,25,5,1h)" -Jduration=100

jmeter -n -t load.jmx -l summary.csv -e -o path_to_web_report_folder
