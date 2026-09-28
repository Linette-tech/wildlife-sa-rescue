Student name: Tabudi Linette Malatji
Student ID: ST10457818
Module: Programming 1B
Module code: PROG6112w
Assessment: Practical Assignment 2
GitHub: https://github.com/Linette-tech/wildlife-sa-rescue

# Wildlife SA Rescue Operations System

Programming 1B practical assignment: a Java console application
for managing wildlife rescue cases.

## Features

- Create injured, orphaned and endangered species rescue cases.
- Search for cases using a unique case ID.
- Update rescue statuses.
- Start and complete rescue operations.
- Display case details and individual rescue summaries.
- Generate a report with case counts and total estimated costs.
- Validate user input and prevent duplicate case IDs.

## Requirements

- JDK 25
- Apache NetBeans with Maven support
- Internet access for the initial Maven dependency downloads

## Running the application

1. Download or clone this repository.
2. Open NetBeans and select File > Open Project.
3. Select the WildlifeSA folder containing pom.xml.
4. Right-click the project and select Run.
5. Enter menu choices in the Output window.
6. Choose option 9 to exit.

## Running the tests

Right-click the WildlifeSA project in NetBeans and select Test.

The project contains 22 JUnit tests covering:
- Rescue costs with and without additional fees.
- Rescue priorities and threshold boundaries.
- Rescue status changes.
- Searching for rescue cases.
- Preventing duplicate case IDs.
- Total costs across different rescue types.

All 22 tests passed during testing on 28 September 2026.

## Class structure

- Rescuable: interface defining the required rescue operations.
- RescueCase: abstract parent class containing shared information.
- InjuredAnimalRescue: injury details and veterinary costs.
- OrphanedAnimalRescue: age, feeding costs and foster care.
- EndangeredSpeciesRescue: conservation classification and security costs.
- RescueManager: ArrayList storage, searching, updates and reports.
- WildlifeSA: console menu and input validation.
- RescueSystemTest: JUnit tests.

## Object-oriented design

Encapsulation is demonstrated by private fields and public methods.

Inheritance is demonstrated by the three rescue classes extending
RescueCase.

Abstraction is demonstrated by RescueCase and its abstract methods.

The Rescuable interface defines startRescue, completeRescue and
generateSummary.

Method overriding allows each rescue type to supply its own cost,
priority and specific details.

Polymorphism allows RescueManager to store different rescue types
in one ArrayList and call their individual implementations.

## Cost calculations

Base care cost = rescue days multiplied by daily care cost.

- Injured: base care cost + veterinary cost + R5000 if surgery is needed.
- Orphaned: base care cost + feeding cost + R2500 if foster care is needed.
- Endangered: base care cost + security cost + R8000 if a specialist
  team is needed.

Veterinary, feeding and security costs are entered as total charges
for the rescue, not daily charges.

## Design assumptions

The assignment does not specify exact priority thresholds.
This implementation uses these rules:

- Injured: Critical if surgery is required; otherwise High when
  veterinary cost is at least R10000, and Medium below R10000.
- Orphaned: High at 3 months or younger, Medium from 4 to 12 months,
  and Low above 12 months.
- Endangered: Critical if a specialist team is required or the
  classification is Critically Endangered; otherwise High.

New cases begin with Reported status.
Starting a rescue changes Reported to In Progress.
Completing a rescue changes In Progress to Completed.
The manual status-update option allows any of these three statuses.

Case IDs are case-insensitive, and surrounding spaces are removed.

An unknown animal name is stored as Unnamed.
Age is entered in positive whole months.
Entered costs and rescue days must be greater than zero.

## Data storage

Cases are stored in an ArrayList in memory.
They are not saved after the application exits.
No database or external data file is used.

## AI assistance disclosure

ChatGPT assisted with planning, code examples, explanations,
test design and documentation.