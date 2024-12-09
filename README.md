# Estimates Using PERT Method

## Context

This project is a command-line application written in Java that allows developers to perform PERT (Program Evaluation and Review Technique) estimates.

PERT is a project management technique that calculates project durations by considering three scenarios: optimistic, nominal, and pessimistic. This specific application works with estimates in days and can assist in cases where someone needs to estimate just one task or when they are working on parallel projects. In the latter case, it provides a final estimate indicating when all tasks will be completed from both nominal and worst-case perspectives.

Personally, I find two things particularly challenging in the software development industry: naming things correctly to express their purpose and estimating how many days a task will take to complete. I’ve found PERT to be a way to be more precise in the second aspect. This is particularly useful because, despite the difficulty of providing estimates, it is an unavoidable activity that every software professional must undertake.

I came across this technique through the book *The Clean Coder* by Robert C. Martin, also known as "Uncle Bob", and I used the concepts shared in the book as a "business rule compendium" to code this application.

## Technologies used

- Java 17
- JUnit 5
- Mockito
- Maven
- I also used the SonarQube plugin in the IntelliJ IDE for local code validations and JaCoCo for tests coverage.

## Important Note on Using SonarQube

One of the main goals of this project is to improve my skills in clean code and architecture while also developing in the most professional way possible (within the reasonable constraints of a project that is orders of magnitude less complex than a real-world industry-level project). A strategic decision to align with this goal was to use SonarQube as a code linter. I made an effort to address all its findings as rigorously as possible. However, I opted to bypass the `java:S106` rule (regarding not using `System.out.print*` instructions but `Logger` instead), since, by design, this program runs in a command-line environment, making the use of such instructions necessary.

## On the Use of `BigDecimal`

Due to its extensive use in banking systems and its precision in rounding operations, one of the goals of this project was also an "excuse" to deepen my understanding of `BigDecimal`. However, this choice made the program inefficient in terms of memory consumption (as it allocates significant space to instantiate a `BigDecimal`, which, in practice, only holds a single decimal place after the integer).

That said, this choice wasn’t entirely negative when it came to programming logic and calculation challenges. At one point, I needed to compute the square root of a value, and since `BigDecimal` doesn’t provide this functionality natively, I ended up implementing square root calculation manually using the Raphson-Newton method. This turned out to be a fun challenge.

The goal of this program is to be cloned and executed locally in a command-line environment, so scalability is not a concern here. However, I am considering creating a specific branch to handle estimates as doubles, allowing the algorithms to be more space-efficient.

## Features

The program will calculate an estimated time in days based on two scenarios: individual estimation and batch estimation.

The individual estimation will calculate the expected duration based on three values: an optimistic estimate, a nominal estimate (most likely to be accurate), and a pessimistic estimate.

The batch estimation will consider the same variables but assumes that the professional is working on multiple projects. It will then return the time required to complete all of these projects.

**Important note:** For decimal values, always use a comma as the decimal separator.