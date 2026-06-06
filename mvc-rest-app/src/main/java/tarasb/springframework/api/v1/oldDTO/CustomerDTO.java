package tarasb.springframework.api.v1.oldDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Schema(description = "Customer ID", example = "1")
    @JsonIgnore
    private Long id;

    @Schema(description = "Customer first name", example = "Freddy")
    @JsonProperty("first_name")
    private String firstname;

    @Schema(description = "Customer last name", example = "Krueger")
    @JsonProperty("last_name")
    private String lastname;

    @Schema(description = "Customer API URL", example = "/api/v1/customers/1")
    @JsonProperty("customer_url")
    private String customerUrl;
}
