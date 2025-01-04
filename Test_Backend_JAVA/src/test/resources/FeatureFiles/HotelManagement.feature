Feature: Hotel Management

  Scenario Outline: Create a new hotel
    Given a hotel with the following details:
      | id  | hotel_Type | destination | rooms | star_rating | arrival_date | leave_date | adults | children |
      | <id> | <hotel_Type> | <destination> | <rooms> | <star_rating> | <arrival_date> | <leave_date> | <adults> | <children> |
    When the hotel is created
    Then the hotel should exist in the system

    Examples:
      | id | hotel_Type | destination | rooms | star_rating | arrival_date | leave_date | adults | children |
      | 1  | Resort     | Paris       | 20    | 5          | 2024-11-10   | 2024-11-20 | 2      | 1        |
      | 2  | Villa      | Nice        | 10    | 4          | 2024-12-05   | 2024-12-15 | 4      | 3        |
      | 3  | Apartment  | Marseille   | 5     | 3          | 2024-10-15   | 2024-10-20 | 1      | 0        |

  Scenario Outline: Find hotels by destination
    Given hotels exist in the system with destinations:
      | destination |
      | <destination> |
      | <other_destination> |
    When searching for hotels with destination "<destination>"
    Then the system should return hotels with destination "<destination>"

    Examples:
      | destination | other_destination |
      | Paris       | Nice              |
      | Marseille   | Lyon              |
      | Nice        | Paris             |
