
Feature: Flight Management

  Scenario Outline: Create a new flight
    Given a new flight request with the following details:
      | direct_flight         | flightType | fromcity | tocity | addNearbyAirportsFrom | addNearbyAirportsTo | travelClass | departureDate | returnDate | adults | children |
      | <direct_flight>       | <flightType> | <fromcity> | <tocity> | <addNearbyAirportsFrom> | <addNearbyAirportsTo> | <travelClass> | <departureDate> | <returnDate> | <adults> | <children> |
    When the flight creation request is sent
    Then the flight should be successfully created and available in the flights list
    Examples:
      | direct_flight | flightType | fromcity | tocity | addNearbyAirportsFrom | addNearbyAirportsTo | travelClass | departureDate | returnDate | adults | children |
      | true          | International | Tunis | Paris | true                  | false               | Economy     | 2024-11-15     | 2024-12-01 | 2      | 1       |
      | false         | Domestic      | Sfax  | Djerba | false                 | false               | Business    | 2025-03-20     | 2025-03-25 | 1      | 0       |


  Scenario Outline: Add additional children to flight
    Given an existing flight with ID <id> and <currentChildren> children
    When the number of children is increased by <newChildren>
    Then the total children should be <totalChildren> on the flight
    Examples:
      | id | currentChildren | newChildren | totalChildren |
      | 1  | 1               | 1           | 2             |
      | 2  | 0               | 2           | 2             |



  Scenario Outline: Add additional adults to flight
    Given an existing flight with ID <id> and <currentAdults> adults
    When the number of adults is increased by <newAdults>
    Then the total adults should be <totalAdults> on the flight
    Examples:
      | id | currentAdults | newAdults | totalAdults |
      | 1  | 2             | 1         | 3           |
      | 2  | 1             | 2         | 3           |
