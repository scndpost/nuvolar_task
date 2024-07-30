package api.model;

import java.util.List;

public record Pet(long id, Category category, String name, List<String> photoUrls, List<Tag> tags, PetStatus status) {}
