# Phoenix Games Publishing Platform Backend Developer Trial Task

## ToDo

- Tests
    - Add JSON helpers (research for something that will play with AssertJ nicely) and add JSON serialization tests
    - Add Mockito if needed and mention in below
    - Add missing integration test for the resource
- Various
    - Check if we use record classes correctly and where it can also be used
    - Spell-check the `README.md`
- GitHub
    - Create "Phoenix Games" organization
    - Create private repository `platform-team-backend-developer-trial-task`
    - Add GitHub repository as additional remote, GitLab should stay the `origin`
    - Setup CI to run tests on each commit
    - Prohibit forks and pushes (so candidates will not be able to see each-other work)

## Context

Our top individual contributor Gabriel Schweinsteinburgberger recently started to develop a new service called User Profile API.
Unfortunately for us, Gabriel got spontaneously married during his spiritual journey to Tibet. He decided to gave up developing software in
favour of running a mountain goats farm with his newly wedded wife. A lot of changes for the good for Gabriel, but we still have to deliver
the project without his valuable contributions. We also immediately thought that this situation makes a great trial task for our new
colleague.

## Project domain

The idea of the User Profile API is to accept commands from various sources and update user profile according to them. User profile is a
collection of properties associated with the `userId`. The service was born to life to decouple operations on the user profile and its
storage from the logic responsible for extracting the data from the outside world.

We foresee the following command types:

* `replace` to replace the value of certain property of the user profile.
  ```json
  {
    "userId": "de4310e5-b139-441a-99db-77c9c4a5fada",
    "type": "replace",
    "properties": {
      "currentGold": 500,
      "currentGems": 800
    }
  }
  ```

* `increment` increments the current value in the profile. Increment can also take negative numbers to decrement the value.
  ```json
  {
    "userId": "de4310e5-b139-441a-99db-77c9c4a5fada",
    "type": "increment",
    "properties": {
      "battleFought": 10
    }
  }
  ```

* `collect` adds values to a list of the values in the user profile property:
  ```json
  {
    "userId": "de4310e5-b139-441a-99db-77c9c4a5fada",
    "type": "collect",
    "properties": {
      "inventory": ["sword1", "sword2", "shield1"]
    }
  }
  ```

Multiple applications generate command to update the user profile and send them to User Profile API over HTTP. One example of such
applications is a streaming application that listen to the stream of user activity events and sent the command when certain criteria is met.
For example, for the user property called `numberOfLogins`, the streaming application can listen to `login` events and send an `increment`
command to User Profile API when such event occurs.

Another example is an ETL application which is run periodically and extracts information from the database to
populate `averagePlayTimePerWeek` property by sending `replace` commands to User Profile API.

## What's is already done

There is a project which uses the following:

* Java 17 (project has support for [jenv](https://www.jenv.be), if it's your thing)
* [Gradle](https://gradle.org/)
* [Dropwizard](https://www.dropwizard.io/en/latest/)
* [dropwizard-guicey](https://github.com/xvik/dropwizard-guicey) which brings [Guice](https://github.com/google/guice) power to Dropwizard
* [JUnit 5](https://junit.org/junit5/), [AssertJ](https://assertj.github.io/doc/)

These parts of the application are already implemented:

* General project setup is done
* `UserResource` is able to return profile of the user.
* `UserProfileDaoInMemory` implements storage of the user profile in memory.

## Your goal

* Finish the implementation started by Gabriel maintaining the same style and test coverage level.
* Add the following components:
    * Endpoint to accept commands.
    * Logic to process different command types and update the user profile accordingly.
    * We were not sure, but maybe we have to accept commands in batches and not one by one?

You are expected to work in your own Git repository and send us a link to it when you are done. Feel free to commit as often and as
granularly as you want, we like to see how the progress was.

## What's next?

In the end we will print the diff and send to Gabriel via paper mail, so he will be able to review it as well.

## A bit more serious

* This is a trial task for a developer position, the story above is a joke.
* We provide feedback on the trial task regardless of the result, since we think it's the least we can do for the candidates that heavily
  invested time into the trial task.
* Trial task will not be compensated or paid.

## How to?

### Run tests

```shell
./gradlew test
```

### Run application

```shell
./gradlew run --args='server'
```