# Display-PredictIt-Data-Webpage
Webapplication project which displays the data collected by my other project PredictitDataProcessing. The page is hosted with google cloud and available at:

[Link to Page](predictitplots.org/plot)

## Data displayed

### Data by politcal label
I took 5 different bets from predictit and labeled their contract outcomes by a liberal or conservative political direction. For example the contract
Victor Orban reelected for president in hungaria is labeled as conservative. 

There are two plots, in both plots the data is grouped by their sum of tradeprice and their timestamps.

In the dayplot the data is grouped in 10 minutes intervals for the current date. 

In the week date the data is grouped for each day and the last 7 days are shown.

Data is not representative for any real political fluctuations, it was more like the first idea to see if the setup is working. More plots to follow...

### Data by candidate
The second option is displaying the data by each candidate (or contract) for each collection.
As above the data is displayed by day and week.
The next step is to get data for a hashtag count corresponding to some of the candidates.

## Architecture
Is relative simple in the moment. Spring boot web is used for creating the webcontainer and jfreechart is used for creating the charts. 
The html pages are rendered by thymeleaf. Data is stored in a postgresl database. 


