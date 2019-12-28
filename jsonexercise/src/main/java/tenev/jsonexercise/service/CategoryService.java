package tenev.jsonexercise.service;

import tenev.jsonexercise.domain.dto.CategorySeedDto;

public interface CategoryService {
    void seedCategory(CategorySeedDto[] categorySeedDto);
}
