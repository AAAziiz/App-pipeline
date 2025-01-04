Feature: User Account Management

  Scenario Outline: Add a new user with valid information
    When a sign-up request is sent for a new user with name <name>, email <email>, and password <password>
    Then the new user with email <email> is successfully registered and appears in the user list
    Examples:
      | name    | email                  | password |
      |   Arwa  |   Arwa@gmail.com          | god     |
      | Yasmine | Yasmineee@yahoo.fr       | iilmk    |
      | Hachem  | Hachemmmm.cd@gmail.com    | klkmgod  |

  Scenario Outline: Add a new user with invalid information
    When a sign-up request is sent for a new user with username <userName>, email <userEmail>, and Password<userPassword>
    Then the sign-up should be rejected due to the uniqueness constraint of the name and email combination
    Examples:
      | userName  | userEmail            | userPassword       |
      | Arwa      | Arwa@gmail.com           | jdkdl          |
      |Yasmine    | Yasmineee@yahoo.fr       | godness        |

  Scenario Outline: Verify the uniqueness of the email field
    Given  user with email <email> already exists
    When a new user attempts to sign up with the same email <email>
    Then the sign-up should be rejected due to the uniqueness constraint of the email attribute
    Examples:
      | email           |
      | Arwa@gmail.com  |



  Scenario Outline: Update a User's email
    Given A user with email <OldEmail> already exists
    When the user updates their email to a new one <NewEmail>
    Then the update with the new email <NewEmail> should be successfully completed
    Examples:
      | OldEmail             | NewEmail                  |
      | Hachemmmm.cd@gmail.com  | Hachem.cd@enicar.ucar.tn |

  Scenario Outline: Update a User's password
    Given A user with this email <Email> already exists
    When the user with the email <Email> updates their password to a new one <NewPassword>
    Then the update with the new password <NewPassword> should be successfully completed
    Examples:
      |   NewPassword              |  Email               |
      |iilmk                       | Yasmineee@yahoo.fr   |

  Scenario Outline: Add a card to an existant user
   Given  A user with the email <Email> already exists
    When the user want to add a card with cardNumber <cardNumber> monthExpir <monthExpir> yearExpir <yearExpir> and cardCvc <cardCvc>
   Then the Add should be done successfully
   Examples:
       | Email                  | cardNumber |  monthExpir |yearExpir|cardCvc|
       |   Arwa@gmail.com       |  212221   |   JUNE        |   2026  |K     |
       |  Yasmineee@yahoo.fr    |298827   | JUNE |       2029         |N     |
       | Hachem.cd@enicar.ucar.tn| 635288  | JULY        | 2099         |C     |