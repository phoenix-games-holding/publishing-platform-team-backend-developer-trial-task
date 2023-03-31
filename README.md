# Phoenix Games Publishing Platform Backend Developer Trial Task

## ToDo

- What's missing
    - Command classes
- Tests
    - Add JSON helpers (external lib) and add JSON serialization tests
    - Add missing integration test for the resource
    - Add integration test for the injection
- Various
    - Check if we use record classes correctly and where it can also be used
    - Add meaningful healthcheck
- GitHub
    - Create "Phoenix Games" organization
    - Create repository `platform-team-backend-developer-trial-task`
    - Add GitHub repository ass additional remote, GitLab should stay the `origin`
    - Setup CI to run tests on each commit

## Context

Our top individual contributor Gabriel Schweinsteinburgberger recently started to develop a new service called User Profile API.
Unfortunately for us, Gabriel got spontaneously married during his spiritual journey to Tibet. He decided to gave up developing software in
favour of running a mountain goats farm with his newly wedded wife. A lot of changes for the good for Gabriel, but we still have to ship the
project without him and his valuable contributions.

## Project domain

The idea of the User Profile API is to accept commands from various sources and update user profile according to them. User profile is a
collection of properties associated with the `userId`. The service was born to life to decouple operations on the user profile and its
storage from the logic responsible for extracting the data from the outside world.

Example of the command types are:

* `replace` to replace the value of certain property of the user profile;
* `increment` to increment the value.

Multiple applications generate command to update the user profile and send them to User Profile API over HTTP. One example of such
applications is a streaming application that listen to the stream of user activity events and sent the command when certain criteria is met.
For example, for the user property called `numberOfLogins`, the streaming application can listen to `login` events and send an `increment`
command to User Profile API when such event occurs.

Another example is an ETL application which is run periodically and extracts information from the database to
populate `averagePlayTimePerWeek` property by sending `replace` commands to User Profile API.

## What's is already done

There is a project which uses the following:

* Java 17 (project has support for [jenv](https://www.jenv.be), if you are up for it)
* Gradle 8
* [Dropwizard](https://www.dropwizard.io/en/latest/)
* [dropwizard-guicey](https://github.com/xvik/dropwizard-guicey) which brings [Guice](https://github.com/google/guice) magic to Dropwizard
* [JUnit 5](https://junit.org/junit5/)
* [AssertJ](https://assertj.github.io/doc/)

These parts of the application are already implemented:

* `UserResource` is able to return profile of the user
* `UserProfileDao` and `UserProfileDaoInMemory` implements storage of the user profile
* POJOs representing commands

## Your goal

Your goal is to finish the implementation started by Gabriel the way he will like it and may be get back to development.

What's still missing:

* Endpoint to accept commands.
* Logic to process commands and update the user profile

## How to?

### Run tests

```shell
./gradlew test
```

### Run application

```shell
./gradlew run --args='server'
```