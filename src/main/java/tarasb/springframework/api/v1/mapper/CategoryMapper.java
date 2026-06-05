package tarasb.springframework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tarasb.springframework.api.v1.model.CategoryDTO;
import tarasb.springframework.domain.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(
            target = "categoryUrl",
            expression = "java(\"/api/v1/categories/\" + category.getId())"
    )
    CategoryDTO categoryToCategoryDTO(Category category);
}
