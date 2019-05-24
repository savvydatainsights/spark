# Spark

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[Apache Spark](https://spark.apache.org) cluster lab.

## Setup

`vagrant up`

## WebUIs

| Node | URL |
| ---- | --- |
| master | <http://192.168.33.10:8080> |
| slave1 | <http://192.168.33.20:8081> |
| slave2 | <http://192.168.33.30:8081> |

## Submitting the sample application

The sample application counts how many times each word appears in a [lorem ipsum](https://www.lipsum.com) text.

In order to submit it, execute:

`ansible-playbook submit-spark-application.yml`

The output then can be viewed under the *output* folder.

### Manually from the master

The application can also be submitted manually from the master host of the cluster.

First, SSH into the master: `vagrant ssh master`.

Once inside the master, become root: `sudo su -`.

After that, build the application: `mvn install -f /vagrant`.

Finally, submit the application:

```bash
/opt/spark/bin/spark-submit --class uk.co.savvydatainsights.WordCount \
    /vagrant/target/spark-examples-1.0-SNAPSHOT.jar \
    /vagrant/input/lorem-ipsum.txt
```