package com.petstore.tests;

import api.client.PetStoreClient;
import api.model.Pet;
import api.model.PetStatus;
import com.petstore.BaseApiTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PetsTests extends BaseApiTest {

    @Test(description = "Getting pet by specified statuses", dataProvider = "statusesOptions")
    public void getPetByStatusesTest(List<PetStatus> statuses) {
        List<Pet> petByStatus = new PetStoreClient().getPetByStatus(statuses);

        List<PetStatus> actualPetStatuses = petByStatus.stream()
                .map(Pet::status)
                .toList();

        Assertions.assertThat(actualPetStatuses)
                .as("Verify pets statuses match queried")
                .allMatch(statuses::contains);
    }

    @DataProvider(parallel = true)
    private Object[][] statusesOptions() {
        PetStatus randomStatus = PetStatus.values()[new Random().nextInt(PetStatus.values().length)];
        return new Object[][]{
                {Collections.singletonList(randomStatus)},
                {Arrays.stream(PetStatus.values()).toList()}
        };
    }
}
