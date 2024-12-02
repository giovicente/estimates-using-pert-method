# Estimates Using PERT Method

## Context

This project is a command-line application written in Java that allows developers to perform PERT (Program Evaluation and Review Technique) estimates.

PERT is a project management technique that calculates project durations by considering three scenarios: optimistic, nominal, and pessimistic. This specific application works with estimates in days and can assist in cases where someone needs to estimate just one task or when they are working on parallel projects. In the latter case, it provides a final estimate indicating when all tasks will be completed from both nominal and worst-case perspectives.

Personally, I find two things particularly challenging in the software development industry: naming things correctly to express their purpose and estimating how many days a task will take to complete. I’ve found PERT to be a way to be more precise in the second aspect. This is particularly useful because, despite the difficulty of providing estimates, it is an unavoidable activity that every software professional must undertake.

I came across this technique through the book *The Clean Coder* by Robert C. Martin, also known as "Uncle Bob", and I used the concepts shared in the book as a "business rule compendium" to code this application.

## Technologies used

- Java 11
- JUnit 5
- Maven
- I also used the SonarLint plugin in the IntelliJ IDE for local code validations.