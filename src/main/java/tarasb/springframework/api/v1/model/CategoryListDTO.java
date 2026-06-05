package tarasb.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import tarasb.springframework.domain.Category;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
