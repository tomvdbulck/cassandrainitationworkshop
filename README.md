# cassandrainitationworkshop
Cassandra first workshop

## Installation & Setup ##

1. Make sure the following items have been installed on your machine:
   - Java 7 or 8
   - Git _(if you like a pretty interface to deal with git, try [SourceTree](http://www.sourcetreeapp.com/))_
   - Maven
   
2. Install Oracle VirtualBox
	https://www.virtualbox.org/wiki/Downloads

3. Install Vagrant
	https://www.vagrantup.com/downloads.html
	_(on Mac and Windows the installer will make sure that vagrant command is known in the command prompt)_

4. Clone this repository into your workspace

5. Open a command prompt, go to the cassandrainitiationworkshop/vagrant folder and run
	```sh
	vagrant up
	```
	This will start up the vagrant box. The first time will take a while as it has to download the OS image and other dependencies.
   
	Shutting down the vagrant box can be done by typing
	```sh
	vagrant halt
	```
	You can restore the environment back to a clean, working state (in case things go south) by typing 
	```sh
	vagrant provision
	```
	You can SSH into the virtual environment with
	```sh
	vagrant ssh
	```
	And when you're done playing around, you can remove all traces of it with
	```sh
	vagrant destroy
	```

6. Import the maven projects into IDE of your choice

7. Run the tests of  to verify everything has been setup correctly



## Known issues ##
- IntelliJ users: In some rare cases you might need to set the working directory 
	in the running configurations to _$MODULE_DIR$_

	To avoid this issue, we'll be using a 32bit box for this workshop.
