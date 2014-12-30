#!/bin/bash

# update apt
sudo apt-get update
 
# install java
sudo apt-get install openjdk-7-jre-headless -y

#install cassandra
wget http://apache.mirrors.pair.com/cassandra/2.1.2/apache-cassandra-2.1.2-bin.tar.gz
tar xvzf apache-cassandra-2.1.2-bin.tar.gz
cd apache-cassandra-2.1.2

#copy config file
sudo cp /vagrant/cassandra.yaml conf/cassandra.yaml

# next create the data and log directories for Cassandra
#sudo mkdir /var/lib/cassandra
#sudo mkdir /var/log/cassandra
#sudo chown -R $USER:$GROUP /var/lib/cassandra
#sudo chown -R $USER:$GROUP /var/log/cassandra
