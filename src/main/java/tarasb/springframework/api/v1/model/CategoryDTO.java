package tarasb.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @JsonIgnore
    private Long id;
    private String name;

    @JsonProperty("category_url")
    private String categoryUrl;
}
