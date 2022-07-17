# CreditSuisseAssignment
# Log Analyzer

Read, Parse and Flag Events from a Log File

## To run the app

1. Clone the git repo


2. Open the project in an IDE of your choice.

3. Run the main application - `com.creditsuisseassignment.CreditsuisseassignmentApplication`.
    1. Create/Update the Run Configuration for the class to provide the program argument.<br />
       <br />
       A sample log file is available in `resources/Events.txt`
    

## Summary of task
Our custom-build server logs different events to a file named logfile.txt. Every event has 2 entries in
the file - one entry when the event was started and another when the event was finished. The entries
in the file have no specific order (a finish event could occur before a start event for a given id).

Every line in the file is a JSON object containing the following event data:

* id - the unique event identifier
* state - whether the event was started or finished (can have values `STARTED` or `FINISHED`)
* timestamp - the timestamp of the event in milliseconds

Application Server logs also have the following additional attributes:
* type - type of log
* host - hostname

Example contents can be found in Events.txt

The program should:
* Take the path to Events.txt as an input argument
* Parse the contents of Events.txt
* Flag any long events that take longer than 4ms
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
  * The application should create a new table if necessary and store the following values:
    * Event id
    * Event duration
    * Type and Host if applicable
    * Alert (true if the event took longer than 4ms, otherwise false)
CreditSuisseAssignment
