package api.client;

import api.AbstractClient;
import api.model.Pet;
import api.model.PetStatus;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class PetStoreClient extends AbstractClient {

    private static final String PET_FIND_BY_STATUS = "/pet/findByStatus";

    @Step
    public List<Pet> getPetByStatus(List<PetStatus> statuses) {
        RequestSpecification spec = baseSpec()
                .basePath(PET_FIND_BY_STATUS);
        statuses.forEach(status -> spec.queryParam("status", status));

        return get(spec).jsonPath().getList(".", Pet.class);
    }

}
