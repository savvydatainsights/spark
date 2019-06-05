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
/opt/spark/bin/spark-submit --master spark://192.168.33.10:7077 \
    --conf spark.driver.host=192.168.33.10 \
    --class uk.co.savvydatainsights.WordCount \
    /vagrant/target/spark-examples-1.0-SNAPSHOT.jar \
    /vagrant/input/lorem-ipsum.txt
```

## Submitting your own Java application

You can also submit to the Spark cluster your own Java application, by setting the parameters *repo* and *class*, like in the example:

```bash
ansible-playbook submit-spark-application.yml \
    -e "repo=https://github.com/project/repo.git" \
    -e "class=com.domain.spark.JavaApplication"
```

You will be prompted to inform your repository credentials. Then, the application will be cloned, built and submitted to the Spark master instance.

Requirements:

- The project must be a [Maven](https://maven.apache.org) project;
- It is expected one input file under the *src/main/resources* folder.
