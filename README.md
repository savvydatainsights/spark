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

`ansible-playbook submit-spark-application.yml`

The output then can be viewed under the *output* folder.