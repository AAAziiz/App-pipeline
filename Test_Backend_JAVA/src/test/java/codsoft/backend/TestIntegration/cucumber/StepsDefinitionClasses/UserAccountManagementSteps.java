package codsoft.backend.TestIntegration.cucumber.StepsDefinitionClasses;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import codsoft.backend.dtos.SignupRequest;
import codsoft.backend.dtos.UserDTO;
import codsoft.backend.models.Card;
import codsoft.backend.models.User;
import codsoft.backend.repositories.CardRepository;
import codsoft.backend.repositories.UserRepository;
import codsoft.backend.services.AuthService;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testcontainers.junit.jupiter.Testcontainers;



@Testcontainers
@SpringBootTest
@Transactional

public class UserAccountManagementSteps {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthService authService;
    @Autowired
    private CardRepository cardRepo;

    private Card card;
    private SignupRequest newUser;
    private User  existingUser,updatedUser;
    private UserDTO existingUserDTO,NewUserDTO;
    private boolean test;

    @ParameterType(".*")
    public String name(String value) {
        return value;
    }
    @ParameterType(".*")
    public String email(String value) {
        return value;
    }
    @ParameterType(".*")
    public String password(String value) {
        return value;
    }
    @ParameterType(".*")
    public String username(String value) {
        return value;
    }
    @ParameterType(".*")
    public String userEmail(String value) {
        return value;
    }
    @ParameterType(".*")
    public String userPassword(String value) {
        return value;
    }

    @ParameterType(".*")
    public String cardNumber(String value) {
        return value;
    }
    @ParameterType(".*")
    public String monthExpir(String value) {
        return value;
    }
    @ParameterType(".*")
    public String yearExpir(String value) {
        return value;
    }
    @ParameterType(".*")
    public String cardCvc(String value) {
        return value;
    }



    // Scenario 1: Add a new user with valid information
    @When(value = "a sign-up request is sent for a new user with name {name}, email {email}, and password {password}")
    public void signUpRequest_SentForNewUser(String name, String email, String password) throws Throwable{
        newUser = new SignupRequest();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(password);
        System.out.println("newUser: " + newUser);
        NewUserDTO= authService.createUser(newUser);
        System.out.println("UserDTO: " + NewUserDTO);
    }

    @Then(value = "the new user with email {email} is successfully registered and appears in the user list")
    public void verifyUserIsRegistered(String email)throws Throwable {
        System.out.println("UserDTO: " + NewUserDTO);
        assertNotNull(NewUserDTO);
        System.out.println(userRepo.findAll());
        boolean userExists = userRepo.findAll().stream()
                .anyMatch(user -> user.getEmail().equals(email));
        assertTrue(userExists, "User should be registered.");
    }


    // Scenario 2: Add a new user with invalid information (name, email, password already exist)
    @When("a sign-up request is sent for a new user with username {username}, email {userEmail}, and Password{userPassword}")
    public void signUpRequest_SentFor_NewUser(String name, String email,String password) throws Throwable{
        SignupRequest newUser = new SignupRequest();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(password);
        System.out.println("newUser: " + newUser);
        existingUserDTO= authService.createUser(newUser);
        System.out.println("UserDTO: " + existingUserDTO);
    }

    @Then("the sign-up should be rejected due to the uniqueness constraint of the name and email combination")
    public void verifySignUpRejectedForDuplicateUser() throws Throwable{
        assertNull(existingUserDTO);
    }

    // Scenario 3: Verify the uniqueness of the email field
    @Given("user with email {email} already exists")
    public void aUserWith_Email_AlreadyExists(String email) throws Throwable {
        existingUser = userRepo.findByEmail(email);
        assertNotNull(existingUser);
    }

    @When("a new user attempts to sign up with the same email {email}")
    public void newUserAttemptsToSignUpWithSameEmail(String email) throws Throwable {

        User newUser = new User();
        try {
            newUser.setEmail(email);
        }
        catch (DataIntegrityViolationException e) {

            this.test=false;
         }
        }

    @Then("the sign-up should be rejected due to the uniqueness constraint of the email attribute")
    public void verifySignUpRejectedForDuplicateEmail() throws Throwable {
        assertFalse(test);
        System.out.println(test);
    }
    // Scénario 4 : Update a User's email
    @Given("A user with email {email} already exists")
    public void userWithEmail_AlreadyExists(String oldEmail) {
        existingUser = userRepo.findByEmail(oldEmail);
        System.out.println(existingUser);
        assertNotNull(existingUser, "User with email " + oldEmail + " should exist.");
    }

    @When("the user updates their email to a new one {email}")
    public void userUpdatesEmail(String newEmail) {
        System.out.println(existingUser);
        existingUser.setEmail(newEmail);
        userRepo.save(existingUser);
    }

    @Then("the update with the new email {email} should be successfully completed")
    public void updateShouldBeSuccessful(String newEmail) {
        assertTrue(userRepo.existsByEmail(newEmail), "The user's email should be updated.");
    }

    // Scénario 5 : Update a User's password
    @Given("A user with this email {email} already exists")
    public void userWithEmailAlreadyExists(String email) {

        existingUser = userRepo.findByEmail(email);
        System.out.println(existingUser);
        assertNotNull(existingUser, "User with email " + email + " should exist.");
    }

    @When("the user with the email {email} updates their password to a new one {password}")
    public void userUpdatesPassword(String email, String newPassword) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        existingUser.setPassword(encodedPassword);
        userRepo.save(existingUser);
        System.out.println("Updated hashed password: " + encodedPassword);
    }

    @Then("the update with the new password {password} should be successfully completed")
    public void theUpdateWithTheNewPasswordNewPasswordOfTheUserWithTheEmailEmailShouldBeSuccessfullyCompleted(String newPassword) {
        updatedUser = userRepo.findByEmail(existingUser.getEmail());
        assertNotNull(updatedUser, "Updated user should exist.");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertTrue(passwordEncoder.matches(newPassword, updatedUser.getPassword()),
                "The user's password should be updated and match the new password.");
    }

   //Scenario 6:Add a card to an existant user

    @Given("A user with the email {email} already exists")
    public void userAlreadyExists(String email) {

        existingUser = userRepo.findByEmail(email);
        System.out.println(existingUser);
        assertNotNull(existingUser, "User with email " + email + " should exist.");
    }

    @When("the user want to add a card with cardNumber {cardNumber} monthExpir {monthExpir} yearExpir {yearExpir} and cardCvc {cardCvc}")
    public void add_A_card(String cardNUm, String MonthExp,String YearExp,String Cvc ) {

        card = new Card();
        card.setUser(existingUser);
        card.setCardNumber(cardNUm);
        card.setMonthExpir(MonthExp);
        card.setYearExpir(YearExp);
        card.setCardCvc(Cvc);
        cardRepo.save(card);
        existingUser.setCard(card);
        userRepo.save(existingUser);
        System.out.println("User after update: " + existingUser);

    }

    @Then("the Add should be done successfully")
    public void theAdd_should_be_done_successfully() {
        assertNotNull(existingUser.getCard());
        System.out.println("User card after update: " + existingUser.getCard());
    }




}
