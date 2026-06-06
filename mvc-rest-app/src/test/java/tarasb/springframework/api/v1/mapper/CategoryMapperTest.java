package tarasb.springframework.api.v1.mapper;

import org.junit.jupiter.api.Test;
import tarasb.springframework.api.v1.model.CategoryDTO;
import tarasb.springframework.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static String NAME = "Joe";
    public static Long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getName(), categoryDTO.getName());
    }
}