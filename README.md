# Phoenix Games Publishing Platform Backend Developer Trial Task

## ToDo

- Add meaningful healthcheck

## Context

Our top individual contributor Gabriel Schweinburgberger recently started to develop a new service called User Profile API. Unfortunately
for us, Gabriel got spontaneously married during his spiritual journey to Tibet, so he decided to gave up developing software in favour of
running a mountain goats farm with his newly wedded wife. A lot of changes for the good for Gabriel, but we still have to ship the project.

## Project domain

The idea of the User Profile API is to accept commands from various sources and update user profile according to them. User profile is a
collection of properties associated with the `userId`. The service was born to life to decouple operations on the user profile and its
storage from the logic responsible for extracting the data from the outside world.

Example of the command types are:

* `replace` to replace the value of certain property of the user profile
* `increment` to increment the value

Multiple applications generate command to update the user profile and send them to User Profile API over HTTP. One example of such
applications is a streaming application that listen to the stream of user activity events and sent the command when certain criteria is met.
For example, for the user property called `numberOfLogins`, the streaming application can listen to `login` events and send an `increment`
command to User Profile API when such event occurs.

Another example is an ETL application which is run periodically and extracts information from the database to
populate `averagePlayTimePerWeek` property by sending `replace` commands to User Profile API.

## What's is already done

There is a project which uses the following components:

* Java 17 (project has support for [jenv](https://www.jenv.be))
* Gradle 8
* [Dropwizard](https://www.dropwizard.io/en/latest/)
* [dropwizard-guicey](https://github.com/xvik/dropwizard-guicey) which brings [Guice](https://github.com/google/guice) to Dropwizard

## Your goal

Your goal is to finish the implementation started by Gabriel.

## How to?

### Run tests

```shell
./gradlew test
```

### Run application

```shell
./gradlew run --args='server'
```