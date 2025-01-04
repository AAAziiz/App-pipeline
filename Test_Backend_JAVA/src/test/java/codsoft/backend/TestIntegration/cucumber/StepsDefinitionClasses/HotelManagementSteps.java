package codsoft.backend.TestIntegration.cucumber.StepsDefinitionClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import codsoft.backend.dtos.HotelDTO;
import codsoft.backend.models.Hotel;
import codsoft.backend.repositories.HotelRepository;
import codsoft.backend.services.HotelService;
import io.cucumber.java.en.*;

@ContextConfiguration(classes = { HotelService.class })
public class HotelManagementSteps {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;
    private List<Hotel> hotels;
    private Long hotelId;
    @ParameterType(".*")
    public String dest(String value) {
        return value;
    }

    @Given("a hotel with the following details:")
    public void aHotelWithDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(Long.parseLong(data.get("id")));
        hotelDTO.setHotel_Type(data.get("hotel_Type"));
        hotelDTO.setDestination(data.get("destination"));
        hotelDTO.setRooms(Integer.parseInt(data.get("rooms")));
        hotelDTO.setStar_rating(Integer.parseInt(data.get("star_rating")));
        hotelDTO.setArrival_date(data.get("arrival_date"));
        hotelDTO.setLeave_date(data.get("leave_date"));
        hotelDTO.setAdults(Integer.parseInt(data.get("adults")));
        hotelDTO.setChildren(Integer.parseInt(data.get("children")));
        this.hotel = hotelService.createHotel(hotelDTO);
    }

    @When("the hotel is created")
    public void theHotelIsCreated() {
        hotel = hotelRepository.save(hotel);
        hotelId = hotel.getId();
        Assertions.assertNotNull(hotelId, "L'hôtel n'a pas été sauvegardé correctement : ID est nul.");
        System.out.println("L'hôtel a été créé avec l'ID : " + hotelId);
    }

    @Then("the hotel should exist in the system")
    public void theHotelShouldExistInTheSystemWithId() {
        Long id= hotel.getId();
        Optional<Hotel> retrievedHotel = hotelRepository.findById(id);
        Assertions.assertTrue(retrievedHotel.isPresent(), "L'hôtel avec l'ID " + id + " existe pas dans le système.");
    }

    @Given("an existing hotel with the destination {dest}")
    public void anExistingHotelWithID(Long id) {
        hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }

    @When("the hotel details are updated with:")
    public void theHotelDetailsAreUpdated(io.cucumber.datatable.DataTable dataTable) {
        HotelDTO updatedHotelDTO = new HotelDTO();
        dataTable.asMaps().forEach(row -> {
            updatedHotelDTO.setHotel_Type(row.get("hotel_Type"));
            updatedHotelDTO.setDestination(row.get("destination"));
            updatedHotelDTO.setRooms(Integer.parseInt(row.get("rooms")));
            updatedHotelDTO.setStar_rating(Integer.parseInt(row.get("star_rating")));
            updatedHotelDTO.setArrival_date(row.get("arrival_date"));
            updatedHotelDTO.setLeave_date(row.get("leave_date"));
            updatedHotelDTO.setAdults(Integer.parseInt(row.get("adults")));
            updatedHotelDTO.setChildren(Integer.parseInt(row.get("children")));
        });
        hotel = hotelService.updateHotel(hotel.getId(), updatedHotelDTO);
    }

    @Then("the hotel should have the updated details")
    public void theHotelWithIdShouldHaveTheUpdatedDetails() {
        Hotel updatedHotel = hotelRepository.findById(hotel.getId()).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertEquals(hotel.getHotel_Type(), updatedHotel.getHotel_Type());
        assertEquals(hotel.getDestination(), updatedHotel.getDestination());
        assertEquals(hotel.getRooms(), updatedHotel.getRooms());
        assertEquals(hotel.getStar_rating(), updatedHotel.getStar_rating());
        assertEquals(hotel.getArrival_date(), updatedHotel.getArrival_date());
        assertEquals(hotel.getLeave_date(), updatedHotel.getLeave_date());
        assertEquals(hotel.getAdults(), updatedHotel.getAdults());
        assertEquals(hotel.getChildren(), updatedHotel.getChildren());
    }


    @Given(value = "an existing hotel with destination {dest}")
    public void anExistingHotelWithDest(String des) {
        hotel = hotelRepository.findOneByDestination(des);
    }
    @When("the hotel is deleted")
    public void theHotelIsDeleted() {
        System.out.println(hotel);
        hotelService.deleteHotel(hotel.getId());
    }
    @Then("the hotel should no longer exist in the system")
    public void theHotelShouldNoLongerExistInTheSystem() {
        assertNull(hotel);
    }

    @Given("hotels exist in the system with destinations:")
    public void hotelsExistInTheSystemWithDestinations(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            Hotel hotel = new Hotel();
            hotel.setDestination(row.get("destination"));
            hotelRepository.save(hotel);
        });
    }

    @When("searching for hotels with destination {string}")
    public void searchingForHotelsWithDestination(String destination) {
        hotels = hotelRepository.findByDestination(destination);
    }

    @Then("the system should return hotels with destination {string}")
    public void theSystemShouldReturnHotelsWithDestination(String destination) {
        assertFalse(hotels.isEmpty());
        for (Hotel hotel : hotels) {
            assertEquals(destination, hotel.getDestination());
        }
    }
}
