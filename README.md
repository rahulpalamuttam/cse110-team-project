# **Bug Fix for Database Schema** #

* Run mysql in terminal
* DROP DATABASE telecom;
* CREATE DATABASE telecom;

# **Setting Up for the Project** #
## 1. Java/JDK ##

* If you've already installed jdk and set JAVA_HOME environment variable, skip this step.
* [Download JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Set JAVA_HOME environment variable
    * **$ export JAVA_HOME=$(/usr/libexec/java_home) >> ~/.bash_profile**
    * **$ source ~/.bash_profile**
* Check
    * **$ java -version**
    * **$ echo $JAVA_HOME**

* *Note:*
    * *The java version for this project is configured as 1.7 so you might want to download JDK version 7.*
    * *JAVA_HOME setting might be different for different operating system. The example above is for OS X*
    * *You can use .bash_profile or .bashrc or .profile whichever file you've been configured your shell. This README file will use .bash_profile as an example*


## 2. Maven Installation ##

* If you already have installed maven skip this step
* Go to [Download Maven](https://maven.apache.org/download.cgi) download page and install binary files.
* Path Configuration
    * **$ export PATH=/{path}/{to}/{your}/{maven dir}/bin:$PATH >> ~/.bash_profile**
    * **ex) export PATH=/Users/cse110/Downloads/apache-maven-x.x.x/bin:$PATH >> ~/.bash_profile**

    * **$ source ~/.bash_profile**

* Check
    * **$ mvn -v**
    * The command above should show the maven version, java version, java_home etc.

## 3. MySQL Installation ##
### Mac OS X ###
* [Download MySQL](http://dev.mysql.com/downloads/mysql/)
* It will ask you to sign up, but you can just click "No thanks, just take me to the download"
* Install MySQL
* To Start MySQL Server
    * **$ sudo /usr/local/mysql/support-files/mysql.server start**
* To Stop MySQL Server
    * **$ sudo /usr/local/mysql/support-files/mysql.server stop**
* Path Configuration
    * **$ export PATH="/usr/local/mysql/bin:$PATH" >> ~/.bash_profile**
    * **$ source ~/.bash_profile**
* Check
    * **$ mysql -v**
* Setup root user
    * **$ /usr/local/mysql/bin/mysqladmin -u root password 'your password'**
    * *Note: single quote is included*
* Login as root
    * **$ mysql -u root -p**
* Fix the 2002 MySQL Socket Error
    * **$ sudo mkdir /var/mysql**
    * **$ sudo ln -s /tmp/mysql.sock /var/mysql/mysql.sock**

## 4. Git/Bitbucket Setup ##
* Go to your workspace directory or if you do not have one create one.
* In your workspace, clone project.
    * **git clone https://<bitbucket-account>@bitbucket.org/googleit/cse110-team-project.git**

## 5. Run Project ##
### Set up database ###
* **$ mysql -u root -p**
* **mysql> CREATE DATABASE telecom;**
* **mysql> CREATE USER 'git110'@'localhost';**
* **mysql> GRANT ALL ON telecom.* TO 'git110'@'localhost';**
 * **mysql> quit**

### Check ###
* **$ mysql -u git110**
* **mysql>SHOW DATABASES;**
* **mysql>USE telecom;**
* **mysql>SHOW TABLES;**
* There should be 'users' table.

### Run ###
* Under the project directory, run the web app by following command line
    * **$ mvn tomcat:run**
* The terminal will keep running and should not terminate.
* Open up the web browser and type 'localhost:8080/telecom/' on the URL
*  To terminate tomcat server, press ctrl+c

## Code Coverage ##
### Using Eclipse ###
* [Eclipse Code Coverage](http://www.eclemma.org/)
* Follow the installation guide line. It's straight forward. 
* Click package and run as code coverage.
* For more info: [How to lunch](http://www.eclemma.org/userdoc/launching.html)

### Using IntelliJ###
* Code Coverage is already installed in intelliJ
* Right click package and click run as code coverage.

## Selenium Integration testing ##
* [Download](http://www.seleniumhq.org/download/)
* Firefox is essential for this framework.
* Once you installed open up the selenium.
* The picture below is some example how you do the test

![Selenium.png](https://bitbucket.org/repo/9edK8p/images/13577703-Selenium.png)

* Set up the base URL
* Click the record button
* Web Serf through the project then it will automatically generate the test.
* To see the test, click the play button.

### *If any confusion or error please send an email or leave message on slack* ###