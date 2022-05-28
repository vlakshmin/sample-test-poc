package api.dto.auth;

import utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Account account;
    private Payload payload;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
