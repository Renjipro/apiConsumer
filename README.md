# apiConsumer
This project is a simple API consumer that interacts with the GitHub API to retrieve repository information for a given user.

## Features

- Retrieve a list of GitHub repositories for a user that are not forks.
- For each repository display the branch names and last commit SHA.

## Install

The IDE should automatically download the necessary dependencies based on the project's `pom.xml` file. If not, you can manually trigger the dependency download in your IDE.

## Run the project

Click on the green triangle next to main function. That function is in Application.java file.

## Usage

The API provides one endpoint:
- /repositories/{username}

The last path parameter {username} should be replaced by the github user's name to retrieve a list of his repositories
