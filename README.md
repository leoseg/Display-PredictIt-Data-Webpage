# Display-PredictIt-Data-Webpage
Webapplication project which displays the data collected by my other project PredictitDataProcessing. The page is hosted with google cloud and available at:

[Link to Page](http://predictitplots.org/plot)

## Data displayed

### Data by politcal label
I took 5 different bets from predictit and labeled their contract outcomes by a liberal or conservative political direction. For example the contract
Victor Orban reelected for president in hungaria is labeled as conservative. 

There are two plots, in both plots the data is grouped by the average of their tradeprice and their timestamps.

In the dayplot the data is grouped in 10 minutes intervals for the current date. 

In the week date the data is grouped for each day and the last 7 days are shown.

Data is not representative for any real political fluctuations, it was more like the first idea to see if the setup is working. More plots to follow...

### Data by candidate
The second option is displaying the data by some candidates (or contract) from presidential election markets together with 
a corresponding twitterhashtag and the number of times it was tweeted over the time (twitterhashtagcount).
As above the data is displayed by day and week. 

## Architecture
Is relative simple in the moment. Spring boot web is used for creating the webcontainer and jfreechart is used for creating the charts. 
The html pages are rendered by thymeleaf. Data is stored in a postgresl database. 

## Bugs and Infos
Because the projekt is still in its beginnings there are still some bugs:
- data collection runs local (cause of cost reasons) so sometimes data leaks occure
- there are still occurring errors see issues in [other repository](https://github.com/leoseg/PredictitDataProcessing) and here 

