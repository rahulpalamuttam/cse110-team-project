# **Setting Up for the Project** #
## 1. Java/JDK ##

* If you've already installed idk and set JAVA_HOME environment variable, skip this step.
* [Download JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Set JAVA_HOME environment variable
    * $ export JAVA_HOME=$(/usr/libexec/java_home) >> ~/.bash_profile
    * $ source ~/.bash_profile

* *Note:*
    * *The java version for this project is configured as 1.7 so you might want to download JDK version 7.*
    * *JAVA_HOME setting might be different for different operating system. The example above is for OS X*
    * *You can use .bash_profile or .bashrc or .profile whichever file you've been configured your shell. This README file will use .bash_profile as an example*


## 2. Maven Installation. ##

* If you already have installed maven skip this step
* Go to [Maven](https://maven.apache.org/download.cgi) download page and install binary files.
* Export maven binary path. You can export path by following command line
    * **$ export PATH=/{path}/{to}/{your}/{maven dir}/bin:$PATH >> ~/.bash_profile**
    * **ex) export PATH=/Users/cse110/Downloads/apache-maven-x.x.x/bin:$PATH >> ~/.bash_profile**

    * **$ source ~/.bash_profile**

* Check if maven work
    * **$ mvn -v**
    * The command above should show the maven version, java version, java_home etc.

## 3. Git/Bitbucket Setup ##
* Go to your workspace directory or if you do not have one create one.
* In your workspace, clone project.
    * **git clone https://jjl112@bitbucket.org/googleit/cse110-team-project.git**

## 4. Run Project ##
* Under the project directory, run the web app by following command line
    * **$ mvn tomcat:run**
* The terminal will keep running and should not terminate.
* Open up the web browser and type 'localhost:8080/telecom/' on the URL
*  To terminate tomcat server, press ctrl+c