# Display-PredictIt-Data-Webpage
Webapplication project which displays the data collected by my other project predictit-stream. The page is hosted with google cloud and available at:

https.webpage.de

## Data displayed

I took 5 different bets from predictit and labeled their contract outcomes by a liberal or conservative political direction. For example Victor Orban reelected is labeled as conservative.

There are two plots, in both plots the data is grouped by their average tradeprice and their timestamps.

In the dayplot the data is grouped in 10 minutes intervals for the current date. 

In the week date the data is grouped for each day and the last 7 days are shown.



## Architecture
Is relative simple in the moment. Spring boot web is used for creating the webcontainer and jfreechart is used for creating the charts. 
The html pages are rendered by thymeleaf. Data is stored in a postgresl database. 

## Further Ideas
* Calendar for selecting different dates
* Email for registration confirmation
* More different plots
* React for a more beautiful frontend
* Another datasource like twitter
